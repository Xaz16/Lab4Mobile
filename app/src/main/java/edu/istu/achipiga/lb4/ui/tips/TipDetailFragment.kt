package edu.istu.achipiga.lb4.ui.tips

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import edu.istu.achipiga.lb4.R
import edu.istu.achipiga.lb4.databinding.FragmentTipDetailBinding

class TipDetailFragment : Fragment() {

    private var _binding: FragmentTipDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentTipDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val tipIndex = requireArguments().getInt(ARG_TIP_INDEX)
        val titleDesc = tipTitleAndDescription(tipIndex)
        if (titleDesc == null) {
            binding.textTipTitle.setText(R.string.list_empty)
            binding.textTipDescription.text = ""
            return
        }
        binding.textTipTitle.setText(titleDesc.first)
        binding.textTipDescription.setText(titleDesc.second)
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            getString(titleDesc.first)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_TIP_INDEX = "tipIndex"

        fun tipTitleAndDescription(tipIndex: Int): Pair<Int, Int>? {
            return when (tipIndex) {
                0 -> Pair(R.string.tip_transport_title, R.string.tip_transport_desc)
                1 -> Pair(R.string.tip_weather_title, R.string.tip_weather_desc)
                2 -> Pair(R.string.tip_nature_title, R.string.tip_nature_desc)
                3 -> Pair(R.string.tip_ticket_title, R.string.tip_ticket_desc)
                else -> null
            }
        }
    }
}
