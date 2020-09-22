package com.example.yasinpdf.view

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.yasinpdf.CurrentLoc
import com.example.yasinpdf.FileState
import com.example.yasinpdf.PdfFragment
import com.example.yasinpdf.R
import com.github.barteksc.pdfviewer.PDFView

class MainActivity : AppCompatActivity() {
    lateinit var pdfView: PDFView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(FilesFragment())
    }

    private fun addFragment(fragment: Fragment) {
        val bundle = Bundle()
        bundle.putString("pathKey", "home")
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.container_main, fragment).commit()
    }

    fun replaceFragment(string: String, fileState: FileState) {
        val bundle = Bundle()
        bundle.putString("pathKey", string)
        val frg: Fragment = when (fileState) {
            FileState.Folder -> FilesFragment()
            FileState.PDF -> PdfFragment()
        }
        frg.arguments = bundle
        supportFragmentManager.beginTransaction().replace(R.id.container_main, frg)
            .addToBackStack(null).commit()

    }

    override fun onBackPressed() {
        super.onBackPressed()
        CurrentLoc.goBackwardAddress()
    }
}