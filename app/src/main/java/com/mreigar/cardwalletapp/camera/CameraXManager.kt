package com.mreigar.cardwalletapp.camera

import android.content.Context
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

class CameraXManager(
    private val context: Context,
    private val previewView: PreviewView,
    private val lifecycleOwner: LifecycleOwner,
    private val listener: CardAnalyzerListener
) : CameraManager {

    private lateinit var cameraExecutor: ExecutorService

    companion object {
        private const val RATIO_4_3_VALUE = 4.0 / 3.0
        private const val RATIO_16_9_VALUE = 16.0 / 9.0
    }

    override fun startCamera() {
        cameraExecutor = Executors.newSingleThreadExecutor()
        bindCameraUseCases()
    }

    private fun bindCameraUseCases() {
        //val metrics = DisplayMetrics().also { viewFinder.display.getRealMetrics(it) }
        //val screenAspectRatio = aspectRatio(metrics.widthPixels, metrics.heightPixels)

        //val rotation = viewFinder.display.rotation
        val cameraProviderFuture = ProcessCameraProvider.getInstance(context)

        cameraProviderFuture.addListener(Runnable {
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()

            val imageAnalyzer = ImageAnalysis.Builder()
                //.setTargetAspectRatio(screenAspectRatio)
                //.setTargetRotation(rotation)
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                .build()
                .also {
                    it.setAnalyzer(cameraExecutor, CardAnalyzer(90, listener)) //harcoded to work in portrait mode
                }

            val imageCapture = ImageCapture.Builder()
                //.setTargetAspectRatio(screenAspectRatio)
                //.setTargetRotation(rotation)
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY)
                .build()



            bindPreview(cameraProvider, imageCapture, imageAnalyzer)

        }, ContextCompat.getMainExecutor(context))
    }

    fun bindPreview(
        cameraProvider: ProcessCameraProvider,
        imageCapture: ImageCapture,
        imageAnalyzer: ImageAnalysis
    ) {
        var preview: Preview = Preview.Builder().build()

        var cameraSelector: CameraSelector = CameraSelector.Builder()
            .requireLensFacing(CameraSelector.LENS_FACING_BACK)
            .build()

        var camera = cameraProvider.bindToLifecycle(lifecycleOwner, cameraSelector, preview, imageCapture, imageAnalyzer)

        preview.setSurfaceProvider(previewView.createSurfaceProvider(camera.cameraInfo))
    }

    private fun aspectRatio(width: Int, height: Int): Int {
        val previewRatio = max(width, height).toDouble() / min(width, height)
        if (abs(previewRatio - RATIO_4_3_VALUE) <= abs(previewRatio - RATIO_16_9_VALUE)) {
            return AspectRatio.RATIO_4_3
        }
        return AspectRatio.RATIO_16_9
    }
}