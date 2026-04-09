package edu.istu.achipiga.lb4.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.istu.achipiga.lb4.MainActivity
import edu.istu.achipiga.lb4.R
import edu.istu.achipiga.lb4.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardHomeCities.setOnClickListener {
            val activity = requireActivity()
            if (activity is MainActivity) {
                activity.selectBottomNavTab(R.id.citiesFragment)
            } else {
                findNavController().navigate(R.id.citiesFragment)
            }
        }
        binding.cardHomeCategories.setOnClickListener {
            val activity = requireActivity()
            if (activity is MainActivity) {
                activity.openNavigationDrawer()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
