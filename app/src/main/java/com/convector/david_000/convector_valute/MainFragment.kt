package com.convector.david_000.convector_valute

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.convector.david_000.convector_valute.autofill.AutoFillDialogFragment
import com.convector.david_000.convector_valute.databinding.FragmentMainBinding
import com.convector.david_000.convector_valute.model.RZDState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.math.abs


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
        autoFillDialogFragment = AutoFillDialogFragment()

        setFragmentResultListener("address") { key, bundle ->
            val result = bundle.getString("address_key")
            Toast.makeText(requireContext(), result.toString(), Toast.LENGTH_LONG).show()
            Handler(Looper.getMainLooper()).postDelayed({
                binding.stationFromField.editText.apply {
                    tag = "Set value TextChange doesn't call"
                    setText(result.toString())
                    tag = null
                }
            }, 222L)
        }

        binding.stationFromField.editText.doAfterTextChanged {
            if ((it?.length ?: 0) > 2 && binding.stationFromField.editText.tag == null) {
                val bundle = Bundle()
                bundle.putString("autofill", it.toString().uppercase())
                autoFillDialogFragment.arguments = bundle
                autoFillDialogFragment.show(parentFragmentManager, "AutoFillDialogFragment")
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