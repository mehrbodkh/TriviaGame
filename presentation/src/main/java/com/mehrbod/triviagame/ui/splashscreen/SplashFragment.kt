package com.mehrbod.triviagame.ui.splashscreen

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.mehrbod.triviagame.R
import com.mehrbod.triviagame.databinding.SplashFragmentBinding
import com.mehrbod.triviagame.ui.splashscreen.state.SplashUIEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : Fragment() {

    private var _binding: SplashFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SplashFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)

        initEventObserver()
    }

    private fun initEventObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.uiEvents.collect { handleUIEvents(it) }
            }
        }
    }

    private fun handleUIEvents(event: SplashUIEvent) {
        when (event) {
            SplashUIEvent.NavigateToStartScreen -> navigateToStartScreen()
        }
    }

    private fun navigateToStartScreen() {
        findNavController().navigate(R.id.action_splashFragment_to_startTriviaFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}