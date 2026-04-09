package edu.istu.achipiga.lb4.ui.tips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.istu.achipiga.lb4.R
import edu.istu.achipiga.lb4.databinding.FragmentTipsBinding

class TipsFragment : Fragment() {

    private var _binding: FragmentTipsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTipsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardTipTransport.setOnClickListener { navigateToTip(0) }
        binding.cardTipWeather.setOnClickListener { navigateToTip(1) }
        binding.cardTipNature.setOnClickListener { navigateToTip(2) }
        binding.cardTipTicket.setOnClickListener { navigateToTip(3) }
    }

    private fun navigateToTip(tipIndex: Int) {
        val args = Bundle().apply {
            putInt(TipDetailFragment.ARG_TIP_INDEX, tipIndex)
        }
        findNavController().navigate(R.id.action_tips_to_tipDetail, args)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
