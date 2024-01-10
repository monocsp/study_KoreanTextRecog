package com.digentid.study_kotlin_compose

import android.content.Context
import android.net.Uri
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.text.Text
import com.google.mlkit.vision.text.TextRecognition
import com.google.mlkit.vision.text.korean.KoreanTextRecognizerOptions


class OcrView {
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

//    private fun processTextRecognitionResult(texts: Text) {
//        val blocks = texts.textBlocks
//        if (blocks.size == 0) {
//
//            return
//        }
//        mGraphicOverlay?.clear()
//        for (i in blocks.indices) {
//            val lines = blocks[i].lines
//            for (j in lines.indices) {
//                val elements = lines[j].elements
//                for (k in elements.indices) {
//                    val textGraphic: GraphicOverlay.Graphic = TextGraphic(mGraphicOverlay, elements[k])
//                    mGraphicOverlay?.add(textGraphic)
//                }
//            }
//        }
//    }


}