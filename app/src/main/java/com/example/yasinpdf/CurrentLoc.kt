package com.example.yasinpdf

import java.lang.StringBuilder

object CurrentLoc {
    private val listTracker = mutableListOf("Files")

    fun goBackwardAddress(): String {
        return if (listTracker.size > 1) {
            listTracker.removeAt(listTracker.lastIndex)
            val fullAddress = StringBuilder()
            for (item in listTracker) {
                fullAddress.append(item)
            }
            fullAddress.toString()
        } else {
            listTracker[0]
        }
    }

    fun goForwardAddress(filename: String): String {
        listTracker.add(filename)
        val fullAddress = StringBuilder()
        for (item in listTracker) {
            fullAddress.append(item)
        }
        return fullAddress.toString()
    }
}