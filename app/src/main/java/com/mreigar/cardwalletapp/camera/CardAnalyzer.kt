package com.mreigar.cardwalletapp.camera

import android.annotation.SuppressLint
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageProxy
import com.google.firebase.ml.vision.FirebaseVision
import com.google.firebase.ml.vision.common.FirebaseVisionImage
import com.google.firebase.ml.vision.common.FirebaseVisionImageMetadata
import com.google.firebase.ml.vision.text.FirebaseVisionText
import com.mreigar.cardwalletapp.addcard.ScannedCardData
import com.mreigar.cardwalletapp.customview.CardUtils

class CardAnalyzer(
    private val rotation: Int,
    private val listener: CardAnalyzerListener
) : ImageAnalysis.Analyzer {

    companion object {
        private val expiryDateRegex = Regex("(?:0[1-9]|1[0-2])/[0-9]{2}")
    }

    @SuppressLint("UnsafeExperimentalUsageError")
    override fun analyze(imageProxy: ImageProxy) {
        val mediaImage = imageProxy.image ?: return
        val imageRotation = degreesToFirebaseRotation(rotation)

        val detector = FirebaseVision.getInstance().onDeviceTextRecognizer

        val image = FirebaseVisionImage.fromMediaImage(mediaImage, imageRotation)

        detector.processImage(image)
            .addOnSuccessListener { firebaseVisionText -> handleSuccess(imageProxy, firebaseVisionText) }
            .addOnFailureListener { e ->
                imageProxy.close()
                listener.onError(e)
            }
    }

    private fun handleSuccess(imageProxy: ImageProxy, firebaseVisionText: FirebaseVisionText) {
        var cardNumber = ""
        var expiryDate = ""

        for (block in firebaseVisionText.textBlocks) {
            for (line in block.lines) {
                for (element in line.elements) {
                    if (CardUtils.isValidCard(line.text)) cardNumber = line.text
                    if (expiryDateRegex.matches(element.text)) expiryDate = element.text
                }
            }
        }

        if (cardNumber.isNotEmpty() && expiryDate.isNotEmpty()) {
            listener.onCardScanned(ScannedCardData("", cardNumber, expiryDate))
        } else {
            imageProxy.close()
        }
    }

    private fun degreesToFirebaseRotation(degrees: Int): Int {
        return when (degrees) {
            0 -> FirebaseVisionImageMetadata.ROTATION_0
            90 -> FirebaseVisionImageMetadata.ROTATION_90
            180 -> FirebaseVisionImageMetadata.ROTATION_180
            270 -> FirebaseVisionImageMetadata.ROTATION_270
            else -> throw IllegalArgumentException(
                "Rotation must be 0, 90, 180, or 270."
            )
        }
    }
}