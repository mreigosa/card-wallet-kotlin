package com.mreigar.cardwalletapp.addcard

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.mreigar.cardwalletapp.R
import com.mreigar.cardwalletapp.camera.CameraManager
import com.mreigar.cardwalletapp.camera.CameraXManager
import com.mreigar.cardwalletapp.camera.CardAnalyzerListener
import kotlinx.android.synthetic.main.fragment_scan_card.*

class ScanCardFragment : Fragment(), CardAnalyzerListener {

    companion object {
        const val REQUEST_PERMISSIONS_CODE = 1001
    }

    private lateinit var cameraManager: CameraManager

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_scan_card, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cameraManager = CameraXManager(requireContext(), cameraPreview, viewLifecycleOwner, this)

        if (hasCameraPermission()) cameraManager.startCamera() else requestCameraPermission()
    }

    override fun onCardScanned(cardData: ScannedCardData) {
        findNavController().apply {
            previousBackStackEntry?.savedStateHandle?.set("scannedCardKey", cardData)
            popBackStack()
        }
    }

    override fun onError(e: Exception) {
        Snackbar.make(cameraLayout, "Error scanning card: ${e.message}", Snackbar.LENGTH_LONG).show()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == REQUEST_PERMISSIONS_CODE) {
            if (hasCameraPermission()) cameraManager.startCamera() else findNavController().popBackStack()
        }
    }

    private fun requestCameraPermission() = requestPermissions(arrayOf(Manifest.permission.CAMERA), REQUEST_PERMISSIONS_CODE)
    private fun hasCameraPermission(): Boolean = ActivityCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
}