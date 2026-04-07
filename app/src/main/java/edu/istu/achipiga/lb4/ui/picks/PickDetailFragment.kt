package edu.istu.achipiga.lb4.ui.picks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.istu.achipiga.lb4.R
import edu.istu.achipiga.lb4.data.Recommendation
import edu.istu.achipiga.lb4.data.RegionRepository
import edu.istu.achipiga.lb4.databinding.FragmentPickDetailBinding
import edu.istu.achipiga.lb4.ui.category.RecommendationDetailFragment
import edu.istu.achipiga.lb4.ui.category.RecommendationsAdapter

class PickDetailFragment : Fragment() {

    private var _binding: FragmentPickDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentPickDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val pickIndex = requireArguments().getInt(ARG_PICK_INDEX)
        val titleDesc = titleAndDetailForPick(pickIndex)
        if (titleDesc == null) {
            binding.textPickTitle.setText(R.string.list_empty)
            binding.textPickDescription.text = ""
            binding.textPickPlacesHeader.visibility = View.GONE
            binding.recyclerPickPlaces.visibility = View.GONE
            return
        }
        binding.textPickTitle.setText(titleDesc.first)
        binding.textPickDescription.setText(titleDesc.second)
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            getString(titleDesc.first)

        val linked = recommendationsForPick(pickIndex)
        if (linked.isEmpty()) {
            binding.textPickPlacesHeader.visibility = View.GONE
            binding.recyclerPickPlaces.visibility = View.GONE
            return
        }
        binding.textPickPlacesHeader.visibility = View.VISIBLE
        binding.recyclerPickPlaces.visibility = View.VISIBLE
        binding.recyclerPickPlaces.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerPickPlaces.adapter = RecommendationsAdapter(linked) { rec ->
            val args = Bundle().apply {
                putInt(RecommendationDetailFragment.ARG_RECOMMENDATION_ID, rec.id)
            }
            findNavController().navigate(
                R.id.action_pickDetail_to_recommendationDetail,
                args,
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun titleAndDetailForPick(pickIndex: Int): Pair<Int, Int>? {
        return when (pickIndex) {
            0 -> Pair(R.string.pick_vyborg_title, R.string.pick_vyborg_detail)
            1 -> Pair(R.string.pick_ladoga_title, R.string.pick_ladoga_detail)
            2 -> Pair(R.string.pick_gatchina_title, R.string.pick_gatchina_detail)
            3 -> Pair(R.string.pick_pushkin_title, R.string.pick_pushkin_detail)
            4 -> Pair(R.string.pick_sosnovy_title, R.string.pick_sosnovy_detail)
            else -> null
        }
    }

    private fun recommendationsForPick(pickIndex: Int): List<Recommendation> {
        val ids = when (pickIndex) {
            0 -> listOf(401, 405, 402, 204, 205)
            1 -> listOf(301, 302, 303, 304, 401)
            2 -> listOf(101, 102, 103, 104, 105)
            3 -> listOf(201, 203, 404, 403, 504)
            4 -> listOf(503, 504, 502, 305, 405)
            else -> emptyList()
        }
        return ids.mapNotNull { RegionRepository.getRecommendation(it) }
    }

    companion object {
        const val ARG_PICK_INDEX = "pickIndex"
    }
}
