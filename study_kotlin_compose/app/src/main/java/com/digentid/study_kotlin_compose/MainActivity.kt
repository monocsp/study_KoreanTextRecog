package com.digentid.study_kotlin_compose

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.digentid.study_kotlin_compose.ui.theme.Study_kotlin_composeTheme
import java.io.File
import java.util.concurrent.ExecutorService

class MainActivity : ComponentActivity() {
    private lateinit var outputDirectory: File
    private lateinit var cameraExcutor: ExecutorService

    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            if (shouldShowCamera.value) {
                CameraView(
                    outputDirectory = outputDirectory,
                    executor = cameraExcutor,
                    onImageCaptured = ::handleImageCapture,
                    onError = { Log.e("TAG", "VIEW ERROR ", it) })
            }

//            Study_kotlin_composeTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                }
//            }
        }
        requestCameraPermission()
    }

    private fun handleImageCapture(uri: Uri) {
        Log.i("TAG", "IMAGE CAPTURE : $uri")

        shouldShowCamera.value = false
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.i("TAG", "Permission Previously granted")
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            ) -> Log.i("TAG", "SHOW CAMERA PERMISSION DIALOG")
            else -> requestPermissionLauncher.launch(Manifest.permission.CAMERA)

        }
    }

    private val requestPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
            if (isGranted) {
                Log.i("TAG", "PERMISSION GRANT")


            } else {
                Log.i("TAG", "PERMISSION DENIED")

            }
        }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()
            ?.let { File(it, resources.getString(R.string.app_name)).apply { mkdirs() } }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExcutor.shutdown()
    }

}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Study_kotlin_composeTheme {
        Greeting("Android")
    }
}