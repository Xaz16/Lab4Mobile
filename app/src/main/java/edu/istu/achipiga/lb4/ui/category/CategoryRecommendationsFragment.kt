package edu.istu.achipiga.lb4.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import edu.istu.achipiga.lb4.R
import edu.istu.achipiga.lb4.data.RecommendationCategory
import edu.istu.achipiga.lb4.data.RegionRepository
import edu.istu.achipiga.lb4.databinding.FragmentCategoryRecommendationsBinding

class CategoryRecommendationsFragment : Fragment() {

    private var _binding: FragmentCategoryRecommendationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCategoryRecommendationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryId = requireArguments().getInt(ARG_CATEGORY_ID)
        val category = RecommendationCategory.fromId(categoryId)
        if (category != null) {
            (requireActivity() as AppCompatActivity).supportActionBar?.title =
                getString(category.titleResId)
        }
        val list = RegionRepository.getRecommendationsForCategory(categoryId)
        val adapter = RecommendationsAdapter(list) { recommendation ->
            val args = Bundle().apply {
                putInt(RecommendationDetailFragment.ARG_RECOMMENDATION_ID, recommendation.id)
            }
            findNavController().navigate(R.id.action_category_to_recommendationDetail, args)
        }
        binding.recyclerRecommendations.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerRecommendations.adapter = adapter
        binding.recyclerRecommendations.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_CATEGORY_ID = "categoryId"
    }
}
