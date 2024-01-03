package com.digentid.cameratextreco

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.digentid.cameratextreco.ui.theme.CameraTextRecoTheme
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions
import java.io.IOException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CameraTextRecoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }




    private fun requestCameraPermission( ){
        when{
            ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)== PackageManager.PERMISSION_GRANTED->{
                Log.i("TAG" , "Permission Previously granted")
            }
            ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)->Log.i("TAG","SHOW CAMERA PERMISSION DIALOG")
            else ->requestPermissionLauncher.launch(Manifest.permission.CAMERA)

        }


    }

    private fun requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){isGranted:Boolean -> if(isGranted){



    } }


}


fun showCaptureImage( context:Context ,img: Uri) {

    val image = InputImage.fromFilePath(context, img)


    val recognizer = TextRecognition.getClient(KoreanTextRecognizerOptions.Builder().build())



    recognizer.process(image)
        .addOnSuccessListener {
            Log.d("TAG", "ShowCaptureImage: " + it.text)
        }


        .addOnFailureListener {
            Log.d("TAG", "ShowCaptureImage: " + it.message)
        }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello  $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CameraTextRecoTheme {
        Greeting("Android")
    }
}



