package edu.istu.achipiga.lb4.ui.cities

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import edu.istu.achipiga.lb4.R
import edu.istu.achipiga.lb4.data.RegionRepository
import edu.istu.achipiga.lb4.databinding.FragmentCityDetailBinding

class CityDetailFragment : Fragment() {

    private var _binding: FragmentCityDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCityDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cityId = requireArguments().getInt(ARG_CITY_ID)
        val city = RegionRepository.getCity(cityId)
        if (city == null) {
            binding.textCityTitle.setText(R.string.list_empty)
            binding.textCityDescription.text = ""
            return
        }
        binding.textCityTitle.setText(city.nameResId)
        binding.textCityDescription.setText(city.descriptionResId)
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            getString(city.nameResId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_CITY_ID = "cityId"
    }
}
