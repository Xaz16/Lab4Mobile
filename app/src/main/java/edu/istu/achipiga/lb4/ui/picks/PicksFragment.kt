package edu.istu.achipiga.lb4.ui.picks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.istu.achipiga.lb4.R
import edu.istu.achipiga.lb4.databinding.FragmentPicksBinding

class PicksFragment : Fragment() {

    private var _binding: FragmentPicksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPicksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardPickVyborg.setOnClickListener { navigateToPick(0) }
        binding.cardPickLadoga.setOnClickListener { navigateToPick(1) }
        binding.cardPickGatchina.setOnClickListener { navigateToPick(2) }
        binding.cardPickPushkin.setOnClickListener { navigateToPick(3) }
        binding.cardPickSosnovy.setOnClickListener { navigateToPick(4) }
    }

    private fun navigateToPick(pickIndex: Int) {
        val args = Bundle().apply {
            putInt(PickDetailFragment.ARG_PICK_INDEX, pickIndex)
        }
        findNavController().navigate(R.id.action_picks_to_pickDetail, args)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
