package com.convector.david_000.convector_valute

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.convector.david_000.convector_valute.data.StationItem
import com.convector.david_000.convector_valute.databinding.FragmentMainBinding
import com.convector.david_000.convector_valute.model.RZDState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class MainFragment : Fragment() {
    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentMainBinding? = null
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
        binding.cityField.doOnTextChanged { text, start, before, count ->
            if(count > 2){
                viewModel.getSuggestedStation(text.toString())
            }
        }
    }

    private fun handleState(state: RZDState) {
        when (state) {
            RZDState.Loading -> {

            }
            is RZDState.Stations -> {
                state.stations.let { stations ->
                    binding.recyclerCurrency.init(stations)
                }

            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun RecyclerView.init(station: List<StationItem>) {
//        layoutManager = Lin(requireContext(), snapHelper)
//
//        val decor = OffsetItemDecoration(requireContext().convertDPtoPX(240f).roundToInt(), requireContext())
//        addItemDecoration(decor)

        adapter = CurrenciesAdapter(station) { pos ->
            Toast.makeText(this@MainFragment.context, "HYI", LENGTH_LONG).show()
            // findNavController().safeNavigate(OnboardingStage4DialogDirections.openShortInfo(cardListData[pos].name))
        }

        //snapHelper.attachToRecyclerView(this)

//        addOnScrollListener(object : RecyclerView.OnScrollListener() {
//            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//                super.onScrollStateChanged(recyclerView, newState)
//                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
//                    val pos = getCurrentPos()
//                    val item = cardListData[pos]
//                    binding.intPic.setImageResource(item.icon)
//                }
//            }
//        })
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}