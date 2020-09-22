package com.example.yasinpdf.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yasinpdf.*
import java.io.File


class FilesFragment : Fragment(), RvItemClickListener {

    private lateinit var rv: RecyclerView
    private lateinit var rvAdapter: FileRecyclerViewAdapter
    private lateinit var path: String
    private val TAG = FilesFragment::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_files, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (arguments != null) {
            path = arguments!!.getString("pathKey", "-1")
            Log.d(TAG, path)
        }

        rv = view.findViewById(R.id.rv_files)

        var itemList: Array<String>? = null
        if (::path.isInitialized) {
            if (path == "home") {
                itemList = context?.assets?.list("Files")
            } else {
                itemList = context?.assets?.list(path)
            }
            Log.d(TAG, "onViewCreated: ${itemList?.size}")
        }

        if (itemList != null) {
            val orgList = mutableListOf<FileInfo>()
            for (item in itemList) {
                if (item.takeLast(4) == ".pdf") {
                    orgList.add(FileInfo(item.substring(0, item.length - 4), FileState.PDF))
                } else {
                    orgList.add(FileInfo(item, FileState.Folder))
                }
            }
            rvAdapter = FileRecyclerViewAdapter(orgList, this)
        }
        rv.apply {
            adapter = rvAdapter
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
    }

    override fun onClick(fileInfo: FileInfo) {
        val activity = activity as MainActivity
        activity.replaceFragment(
            CurrentLoc.goForwardAddress("/" + fileInfo.fullName),
            fileInfo.fileState
        )
    }
}