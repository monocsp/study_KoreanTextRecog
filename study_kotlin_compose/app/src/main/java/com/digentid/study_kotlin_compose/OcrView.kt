package com.digentid.study_kotlin_compose

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.android.gms.tasks.Tasks
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.TextRecognizer
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


class OcrView  {


    private val mGraphicOverlay: GraphicOverlay? = null
    fun showCaptureImage(context: Context, img: Uri): MutableList<String> {
        val resultText = mutableListOf<String>()
        val image = InputImage.fromFilePath(context, img)
        //        TextRecognizer recognizer = TextRecognition.getClient();
        val recognizer = TextRecognition.getClient(KoreanTextRecognizerOptions.Builder().build())
        val task :Task<Text> =  recognizer.process(image)
            .addOnSuccessListener {
                Log.d("TAG", "ShowCaptureImage: " + it.text)
                resultText.add(it.text)
//                processTextRecognitionResult(it)
            }


            .addOnFailureListener {
                Log.d("TAG", "ShowCaptureImage: " + it.message)
            }



        return resultText
    }

    suspend fun getTextRecogitionResult(image : InputImage) = suspendCancellableCoroutine<Text?> {cancellableContinuation ->

        TextRecognition.getClient(KoreanTextRecognizerOptions.Builder().build()).process(image).addOnCompleteListener {
            if(it.isSuccessful){
                cancellableContinuation.resume(it.result)
            }else{
                cancellableContinuation.resume(null)
            }

        }


    }



}