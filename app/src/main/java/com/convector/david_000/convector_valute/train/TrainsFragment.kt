package com.convector.david_000.convector_valute.train

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.convector.david_000.convector_valute.R
import com.convector.david_000.convector_valute.data.TrainItem
import com.convector.david_000.convector_valute.databinding.FragmentTrainsBinding
import com.convector.david_000.convector_valute.train.ui.TrainAdapter
import com.convector.david_000.convector_valute.train.vm.TrainsState
import com.convector.david_000.convector_valute.train.vm.TrainsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class TrainsFragment : Fragment() {

    private val trainsViewModel: TrainsViewModel by viewModels()
    private var _binding: FragmentTrainsBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTrainsBinding.inflate(layoutInflater)
        setupView()
        return binding.root
    }

    private fun setupView() {
        trainsViewModel.state
            .onEach(::handleState)
            .launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun handleState(state: TrainsState) {
        when (state) {
            TrainsState.Loading -> {
                binding.trainsProgress.isVisible = true
                binding.trainsRecycler.isVisible = false
                binding.errorView.root.isVisible = false
            }

            is TrainsState.Content -> {
                binding.trainsProgress.isVisible = false
                binding.trainsRecycler.isVisible = true
                binding.trainsRecycler.update(state.trainList)
                binding.errorView.root.isVisible = false
            }

            is TrainsState.Empty -> {
                binding.trainsProgress.isVisible = false
                binding.errorView.root.isVisible = true
                binding.trainsRecycler.isVisible = false
                binding.errorView.titleText.text = getString(R.string.some_error_title)
                binding.errorView.descriptionText.text = getString(R.string.empty_description)
            }

            is TrainsState.Error -> {
                binding.trainsProgress.isVisible = false
                binding.errorView.root.isVisible = true
                binding.trainsRecycler.isVisible = false
                binding.errorView.titleText.text = getString(R.string.some_error_title)
                binding.errorView.descriptionText.text = getString(R.string.some_error_description)
            }
        }
    }

    private fun RecyclerView.update(station: List<TrainItem>) {
        adapter = TrainAdapter(station) { pos ->
            Toast.makeText(requireContext(),station[pos].name, Toast.LENGTH_LONG).show()
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}