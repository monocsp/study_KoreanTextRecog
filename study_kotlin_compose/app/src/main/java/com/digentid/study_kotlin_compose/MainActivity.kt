package com.digentid.study_kotlin_compose

import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomAppBar
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import coil.compose.rememberImagePainter

class MainActivity : ComponentActivity() {
    private lateinit var outputDirectory: File
    private lateinit var cameraExcutor: ExecutorService

    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)
    private lateinit var photoUri: Uri
    private var shouldShowPhoto: MutableState<Boolean> = mutableStateOf(false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            if (shouldShowCamera.value) {
                CameraView(
                    outputDirectory = outputDirectory,
                    executor = cameraExcutor,
                    onImageCaptured = ::handleImageCapture,
                    onError = { Log.e("TAG", "VIEW ERROR ", it) })
            } else {
                CameraView(
                    outputDirectory = outputDirectory,
                    executor = cameraExcutor,
                    onImageCaptured = ::handleImageCapture,
                    onError = { Log.e("TAG", "VIEW ERROR ", it) })

            }

            if (shouldShowPhoto.value) {
                Image(
                    painter = rememberImagePainter(photoUri),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }


        }


        requestCameraPermission()
        outputDirectory = getOutputDirectory()
        cameraExcutor = Executors.newSingleThreadExecutor()
    }

    private fun handleImageCapture(uri: Uri) {
        Log.i("TAG", "IMAGE CAPTURE : $uri")

        shouldShowCamera.value = false
        photoUri = uri
        shouldShowPhoto.value = true
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.i("TAG", "Permission Previously granted")
                shouldShowCamera.value = true
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
                shouldShowCamera.value = true

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




@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ScaffoldExample()

}


@Composable
fun ScaffoldExample() {
    Scaffold(
        topBar = {
            TopAppBar(

                title = {
                    Text("Top app bar")
                }
            )
        },


    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text =
                """
                    This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.

                    It also contains some basic inner content, such as this text.

                    
                """.trimIndent(),
            )
        }
    }
}