package com.convector.david_000.convector_valute

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.convector.david_000.convector_valute.autofill.AutoFillDialogFragment
import com.convector.david_000.convector_valute.core.getParcel
import com.convector.david_000.convector_valute.data.StationItem
import com.convector.david_000.convector_valute.databinding.FragmentMainBinding
import com.convector.david_000.convector_valute.model.RZDState
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentMainBinding? = null
    private lateinit var autoFillDialogFragment: AutoFillDialogFragment
    private val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
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
        setFromField()
        setToField()
        setDateField()
    }

    private fun handleState(state: RZDState) {
        when (state) {
            RZDState.Loading -> {

            }

            is RZDState.Stations -> {

            }
        }
    }

    private fun setFromField() {
        setFromResult()
        binding.stationFromField.subtitleTextView.text = getString(R.string.from)
        binding.stationFromField.editText.doAfterTextChanged {
            if ((it?.length ?: 0) > 2 && binding.stationFromField.editText.tag == null) {
                findNavController().navigate(MainFragmentDirections.openAutofill(true,it.toString().uppercase()))
            }
        }
    }

    private fun setFromResult() {
        setFragmentResultListener(ADDRESS_FROM) { key, bundle ->
            val result = bundle.getParcel(ADDRESS_FROM) as? StationItem

            Handler(Looper.getMainLooper()).postDelayed({
                binding.stationFromField.editText.apply {
                    tag = "Set value TextChange doesn't call"
                    setText(result?.name.toString())
                    tag = null
                }
            }, 222L)
        }

    }
    private fun setToField() {
        setToResult()
        binding.stationToField.subtitleTextView.text = getString(R.string.to)
        binding.stationToField.editText.doAfterTextChanged {
            if ((it?.length ?: 0) > 2 && binding.stationToField.editText.tag == null) {
                findNavController().navigate(MainFragmentDirections.openAutofill(false,it.toString().uppercase()))
            }
        }
    }

    private fun setToResult() {
        setFragmentResultListener(ADDRESS_TO) { _, bundle ->
            val result = bundle.getParcel(ADDRESS_TO) as? StationItem

            Handler(Looper.getMainLooper()).postDelayed({
                binding.stationToField.editText.apply {
                    tag = "Set value TextChange doesn't call"
                    setText(result?.name.toString())
                    tag = null
                }
            }, 222L)
        }
    }

    private fun setDateField(){
        binding.dateField.setOnClickListener {
            val datePickerBuilder: MaterialDatePicker.Builder<Long> = MaterialDatePicker
                .Builder
                .datePicker()
                .setTitleText(getString(R.string.departure_date))
            val datePicker = datePickerBuilder.build()
            datePicker.show(parentFragmentManager, "DATE_PICKER")

            datePicker.addOnPositiveButtonClickListener {
                val date = sdf.format(it)
                binding.dateField.setText(date)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object{
        const val ADDRESS_FROM  = "address_from"
        const val ADDRESS_TO  = "address_to"
    }
}