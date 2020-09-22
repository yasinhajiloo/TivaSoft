package com.example.yasinpdf.view

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.yasinpdf.R
import com.google.ar.core.AugmentedImage
import com.google.ar.core.AugmentedImageDatabase
import com.google.ar.core.Config
import com.google.ar.core.Session
import com.google.ar.sceneform.FrameTime
import java.io.IOException

class CameraActivity : AppCompatActivity() {
    private var customArFragment: CustomArFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_camera)
        customArFragment =
            supportFragmentManager.findFragmentById(R.id.arFragment) as CustomArFragment?
        customArFragment!!.arSceneView.scene.addOnUpdateListener { frameTime: FrameTime ->
            onUpdate(
                frameTime
            )
        }
        customArFragment!!.planeDiscoveryController.hide()
        customArFragment!!.planeDiscoveryController.setInstructionView(null)
    }

    private fun onUpdate(frameTime: FrameTime) {
        val frame = customArFragment!!.arSceneView.arFrame
        val images = frame!!.getUpdatedTrackables(
            AugmentedImage::class.java
        )
        for (augmentedImage in images) {
            if (augmentedImage.trackingMethod == AugmentedImage.TrackingMethod.FULL_TRACKING) {
                if (augmentedImage.name == "police.png") {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }

    }

    @Throws(IOException::class)
    fun loadDb(session: Session?, config: Config) {
        val augmentedImageDatabase =
            AugmentedImageDatabase.deserialize(session, resources.openRawResource(R.raw.myimages))
        config.augmentedImageDatabase = augmentedImageDatabase
    }
}