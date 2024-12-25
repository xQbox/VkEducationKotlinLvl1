package com.example.pictureapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.pictureapp.databinding.FragmentMainBinding
import com.example.pictureapp.recycler.PictureAdapter
import com.example.pictureapp.viewmodel.PictureViewModel

class MainFragment: Fragment() {
    companion object {
        private const val LOAD_IMAGE_COUNT = 10
        private const val INIT_IMAGE_COUNT = 20
    }
    private lateinit var viewModel: PictureViewModel
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: PictureAdapter
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[PictureViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = PictureAdapter { viewModel.loadPictures(LOAD_IMAGE_COUNT) }
        binding.recyclerView.adapter = adapter
        progressBar = binding.progressBar
        setObservers()
        viewModel.loadPictures(INIT_IMAGE_COUNT)
    }

    private fun createErrorFragment() {
        val reloadFragment = ReloadFragment()

        reloadFragment.setOnReloadMethod {
            viewModel.loadPictures(LOAD_IMAGE_COUNT)
        }

        parentFragmentManager
            .beginTransaction()
            .replace(binding.root.id, reloadFragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setObservers() {
        viewModel.loadedPictures.observe(viewLifecycleOwner) { pictures ->
            if (pictures.isNotEmpty()) {
                adapter.setPictures(pictures)
            }
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            progressBar.visibility =
                if (isLoading == true) {
                    ProgressBar.VISIBLE
                } else {
                    ProgressBar.GONE
                }
        }

        viewModel.isError.observe(viewLifecycleOwner) { state ->
            if (state) {
                createErrorFragment()
            }
        }
    }
}