package com.mehrbod.triviagame.ui.summery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mehrbod.domain.model.summery.Summery
import com.mehrbod.triviagame.databinding.SummeryFragmentBinding
import com.mehrbod.triviagame.ui.summery.state.SummeryUIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SummeryFragment : Fragment() {

    private var _binding: SummeryFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: SummeryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = SummeryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(SummeryViewModel::class.java)

        initStateObserver()
    }

    private fun initStateObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.uiState.collect {
                    when (it) {
                        SummeryUIState.Empty -> { }
                        is SummeryUIState.ShowSummery -> showSummery(it.summery)
                    }
                }
            }
        }
    }

    private fun showSummery(summery: Summery) = with(binding) {
        correctText.text = summery.numberOfCorrectAnswers.toString()
        incorrectText.text = summery.numberOfIncorrectAnswers.toString()
        unansweredText.text = summery.numberOfUnansweredQuestions.toString()
        totalText.text = summery.totalQuestions.toString()
        scoreText.text = summery.score
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}