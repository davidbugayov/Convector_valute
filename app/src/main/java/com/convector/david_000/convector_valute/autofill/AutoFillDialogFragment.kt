package com.convector.david_000.convector_valute.autofill

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.convector.david_000.convector_valute.MainFragment.Companion.ADDRESS_FROM
import com.convector.david_000.convector_valute.MainFragment.Companion.ADDRESS_TO
import com.convector.david_000.convector_valute.R
import com.convector.david_000.convector_valute.autofill.ui.SuggestedAdapter
import com.convector.david_000.convector_valute.autofill.vm.AutoFillState
import com.convector.david_000.convector_valute.autofill.vm.AutoFillViewModel
import com.convector.david_000.convector_valute.data.StationItem
import com.convector.david_000.convector_valute.databinding.FragmentAutofillBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AutoFillDialogFragment : BottomSheetDialogFragment() {

    private val autoFillViewModel: AutoFillViewModel by viewModels()
    private var _binding: FragmentAutofillBinding? = null

    private val args by navArgs<AutoFillDialogFragmentArgs>()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAutofillBinding.inflate(layoutInflater)
        setupView()
        return binding.root
    }

    private fun setupView() {
        autoFillViewModel.state
            .onEach(::handleState)
            .launchIn(viewLifecycleOwner.lifecycleScope)

        binding.autoEditText.doOnTextChanged { text, _, _, _ ->
            autoFillViewModel.getSuggestedStation(text.toString().uppercase())
        }

        binding.autoEditText.setText(args.autofill)
    }

    private fun handleState(state: AutoFillState) {
        when (state) {
            AutoFillState.Loading -> {
                binding.suggestedProgress.isVisible = true
                binding.suggestedRecycler.isVisible = false
                binding.errorView.root.isVisible = false
            }

            is AutoFillState.Content -> {
                binding.suggestedProgress.isVisible = false
                binding.suggestedRecycler.isVisible = true
                binding.suggestedRecycler.update(state.stationList)
                binding.errorView.root.isVisible = false
            }

            is AutoFillState.Empty -> {
                binding.suggestedProgress.isVisible = false
                binding.errorView.root.isVisible = true
                binding.suggestedRecycler.isVisible = false
                binding.errorView.titleText.text = getString(R.string.some_error_title)
                binding.errorView.descriptionText.text = getString(R.string.empty_description)
            }

            is AutoFillState.Error -> {
                binding.suggestedProgress.isVisible = false
                binding.errorView.root.isVisible = true
                binding.suggestedRecycler.isVisible = false
                binding.errorView.titleText.text = getString(R.string.some_error_title)
                binding.errorView.descriptionText.text = getString(R.string.some_error_description)
            }
        }
    }

    private fun RecyclerView.update(station: List<StationItem>) {
        adapter = SuggestedAdapter(station) { pos ->
            val requestKey = if (args.isFrom) {
                ADDRESS_FROM
            } else {
                ADDRESS_TO
            }
            setFragmentResult(requestKey, bundleOf(requestKey to station[pos]))
            this@AutoFillDialogFragment.dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}