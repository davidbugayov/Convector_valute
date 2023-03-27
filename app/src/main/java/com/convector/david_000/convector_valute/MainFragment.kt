package com.convector.david_000.convector_valute

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.convector.david_000.convector_valute.data.CurrencyItem
import com.convector.david_000.convector_valute.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(layoutInflater)
        setupView()
        return binding.root
    }

    private fun setupView(){
        binding.recyclerCurrency.init(listOf(CurrencyItem("хуй","хуй123"), CurrencyItem("ху1й","хуй11123")))
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    private fun RecyclerView.init(currencies: List<CurrencyItem>) {
//        layoutManager = Lin(requireContext(), snapHelper)
//
//        val decor = OffsetItemDecoration(requireContext().convertDPtoPX(240f).roundToInt(), requireContext())
//        addItemDecoration(decor)

        adapter = CurrenciesAdapter(currencies) { pos ->
            Toast.makeText(this@MainFragment.context,"HYI",  LENGTH_LONG).show()
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