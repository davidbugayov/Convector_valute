package com.convector.david_000.convector_valute.autofill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.convector.david_000.convector_valute.autofill.ui.SuggestedAdapter
import com.convector.david_000.convector_valute.autofill.vm.AutoFillState
import com.convector.david_000.convector_valute.autofill.vm.AutoFillViewModel
import com.convector.david_000.convector_valute.data.StationItem
import com.convector.david_000.convector_valute.databinding.FragmentAutofillBinding
import com.convector.david_000.convector_valute.model.RZDState
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AutoFillDialogFragment : BottomSheetDialogFragment() {

    private val autoFillViewModel: AutoFillViewModel by viewModels()
    private var _binding: FragmentAutofillBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        _binding = FragmentAutofillBinding.inflate(layoutInflater)
        setupView()
        return binding.root
    }

    private fun setupView() {
        autoFillViewModel.state
            .onEach(::handleState)
            .launchIn(viewLifecycleOwner.lifecycleScope)
        arguments?.getString("autofill")?.let {
            binding.autoEditText.setText(it)
            autoFillViewModel.getSuggestedStation(it)
        }
        binding.autoEditText.doOnTextChanged { text, start, before, count ->
            if(count > 2) {
                autoFillViewModel.getSuggestedStation(text.toString())
            }
        }
    }

    private fun handleState(state: AutoFillState) {
        when (state) {
            AutoFillState.Loading -> {
                binding.suggestedProgress.isVisible = true
                binding.suggestedRecycler.isVisible = false
            }
            is AutoFillState.Content -> {
                binding.suggestedProgress.isVisible = false
                binding.suggestedRecycler.isVisible = true
                binding.suggestedRecycler.update(state.stationList)
            }
            is AutoFillState.Empty -> {

            }
            is AutoFillState.Error -> {

            }
        }
    }

    private fun RecyclerView.update(station: List<StationItem>) {
        adapter = SuggestedAdapter(station) { pos ->
            Toast.makeText(this.context, "HYI", Toast.LENGTH_LONG).show()
            // findNavController().safeNavigate(OnboardingStage4DialogDirections.openShortInfo(cardListData[pos].name))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}