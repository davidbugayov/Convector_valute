package com.convector.david_000.convector_valute

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.convector.david_000.convector_valute.autofill.AutoFillDialogFragment
import com.convector.david_000.convector_valute.databinding.FragmentMainBinding
import com.convector.david_000.convector_valute.model.RZDState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentMainBinding? = null
    private lateinit var autoFillDialogFragment: AutoFillDialogFragment
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        setupView()
        return binding.root
    }

    private fun setupView() {
        viewModel.state
            .onEach(::handleState)
            .launchIn(viewLifecycleOwner.lifecycleScope)
        binding.stationFromField.editText.doOnTextChanged { text, start, before, count ->
            if (count > 2) {
                autoFillDialogFragment = AutoFillDialogFragment()
                val bundle = Bundle()
                bundle.putString("autofill", text.toString())
                autoFillDialogFragment.arguments = bundle
                autoFillDialogFragment.show(childFragmentManager, "AutoFillDialogFragment")
            }
        }
    }

    private fun handleState(state: RZDState) {
        when (state) {
            RZDState.Loading -> {

            }
            is RZDState.Stations -> {

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}