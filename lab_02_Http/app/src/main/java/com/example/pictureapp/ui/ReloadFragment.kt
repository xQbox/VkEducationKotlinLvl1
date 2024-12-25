package com.example.pictureapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pictureapp.databinding.ReloadFragmentBinding

class ReloadFragment: Fragment() {
    private lateinit var binding: ReloadFragmentBinding
    private lateinit var onReload: () -> Unit

    // Dependency Injection
    fun setOnReloadMethod(onReloadCallback: () -> Unit) {
        onReload = onReloadCallback
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ReloadFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnReload.setOnClickListener {
            onReload()
            parentFragmentManager.popBackStack()
        }
    }
}