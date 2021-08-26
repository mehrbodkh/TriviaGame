package com.mehrbod.triviagame.ui.startscreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.mehrbod.triviagame.R
import com.mehrbod.triviagame.databinding.StartTriviaFragmentBinding
import com.mehrbod.triviagame.ui.startscreen.state.StartGameUIEvent
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StartTriviaFragment : Fragment() {

    private var _binding: StartTriviaFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: StartTriviaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = StartTriviaFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(StartTriviaViewModel::class.java)

        initEventObserver()
        initClickListeners()
    }

    private fun initEventObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.uiEvents.collect { handleUIEvents(it) }
            }
        }
    }

    private fun initClickListeners() = with(binding) {
        startButton.setOnClickListener {
            viewModel.onStartButtonClicked()
        }
    }

    private fun handleUIEvents(event: StartGameUIEvent) {
        when (event) {
            StartGameUIEvent.NavigateToGame -> navigateToGame()
        }
    }

    private fun navigateToGame() {
        findNavController().navigate(R.id.action_startTriviaFragment_to_questionsFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}