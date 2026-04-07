package edu.istu.achipiga.lb4.ui.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import edu.istu.achipiga.lb4.R
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import edu.istu.achipiga.lb4.data.RegionRepository
import edu.istu.achipiga.lb4.databinding.FragmentCitiesBinding

class CitiesFragment : Fragment() {

    private var _binding: FragmentCitiesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCitiesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cities = RegionRepository.getCities()
        val adapter = CitiesAdapter(cities) { city ->
            val args = Bundle().apply {
                putInt(CityDetailFragment.ARG_CITY_ID, city.id)
            }
            findNavController().navigate(R.id.action_cities_to_cityDetail, args)
        }
        binding.recyclerCities.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerCities.adapter = adapter
        binding.recyclerCities.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
