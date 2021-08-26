package com.mehrbod.triviagame.ui.questions

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mehrbod.domain.model.question.PhotoQuestion
import com.mehrbod.domain.model.question.TextQuestion
import com.mehrbod.triviagame.R
import com.mehrbod.triviagame.databinding.QuestionsFragmentBinding
import com.mehrbod.triviagame.ui.questions.state.QuestionsUIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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

        initStateObserver()
    }

    private fun initStateObserver() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.questionsUiState.collect { handleQuestionUiState(it) }
            }
        }
    }

    private fun handleQuestionUiState(state: QuestionsUIState) {
        when (state) {
            QuestionsUIState.Loading -> showLoading()
            is QuestionsUIState.ShowErrorState -> showErrorState(state.message)
            is QuestionsUIState.ShowPhotoQuestion -> showPhotoQuestion(state.question, state.timeInMillis)
            is QuestionsUIState.ShowTextQuestion -> showTextQuestion(state.question, state.timeInMillis)
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

    private fun showPhotoQuestion(question: PhotoQuestion, time: Long) {
        hideLoading()
    }

    private fun showTextQuestion(question: TextQuestion, time: Long) {
        hideLoading()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}