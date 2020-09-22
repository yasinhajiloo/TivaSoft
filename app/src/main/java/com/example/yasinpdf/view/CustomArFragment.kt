package com.example.yasinpdf.view

import com.google.ar.core.Config
import com.google.ar.core.Session
import com.google.ar.sceneform.ux.ArFragment
import java.io.IOException

class CustomArFragment : ArFragment() {
    override fun getSessionConfiguration(session: Session?): Config? {
        val config = Config(session)
        config.updateMode = Config.UpdateMode.LATEST_CAMERA_IMAGE
        try {
            (activity as CameraActivity?)?.loadDb(session, config)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        arSceneView.setupSession(session)
        return config
    }
}