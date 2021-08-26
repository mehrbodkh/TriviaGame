package com.mehrbod.triviagame.ui.questions

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mehrbod.domain.model.question.PhotoQuestion
import com.mehrbod.domain.model.question.TextQuestion
import com.mehrbod.triviagame.databinding.QuestionsFragmentBinding
import com.mehrbod.triviagame.ui.questions.state.QuestionsUIState
import com.mehrbod.triviagame.ui.questions.state.TimerState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalTime
@AndroidEntryPoint
class QuestionsFragment : Fragment() {

    private var _binding: QuestionsFragmentBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: QuestionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = QuestionsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(QuestionsViewModel::class.java)

        initQuestionStateObserver()
        initTimeStateObserver()
    }

    private fun initQuestionStateObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.questionsUiState.collect { handleQuestionUiState(it) }
            }
        }
    }

    private fun initTimeStateObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.timerState.collect { handleTimerState(it) }
            }
        }
    }

    private fun handleQuestionUiState(state: QuestionsUIState) {
        when (state) {
            QuestionsUIState.Loading -> showLoading()
            is QuestionsUIState.ShowErrorState -> showErrorState(state.message)
            is QuestionsUIState.ShowPhotoQuestion -> showPhotoQuestion(state.question)
            is QuestionsUIState.ShowTextQuestion -> showTextQuestion(state.question)
        }
    }

    private fun handleTimerState(state: TimerState) {
        when (state) {
            TimerState.Empty -> { }
            is TimerState.UpdateTimeLeft -> updateTimer(state.time, state.totalTime)
        }
    }

    private fun updateTimer(time: Duration, totalTime: Duration) = with(binding) {
        timerBar.apply {
            visibility = View.VISIBLE
            progress = time.inWholeSeconds.toInt()
            max = totalTime.inWholeSeconds.toInt()
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showErrorState(message: String) {
        hideLoading()
    }

    private fun showPhotoQuestion(question: PhotoQuestion) {
        hideLoading()
    }

    private fun showTextQuestion(question: TextQuestion) {
        hideLoading()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}