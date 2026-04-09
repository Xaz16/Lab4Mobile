package edu.istu.achipiga.lb4.ui.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import edu.istu.achipiga.lb4.R
import edu.istu.achipiga.lb4.data.RegionRepository
import edu.istu.achipiga.lb4.databinding.FragmentRecommendationDetailBinding

class RecommendationDetailFragment : Fragment() {

    private var _binding: FragmentRecommendationDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentRecommendationDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recommendationId = requireArguments().getInt(ARG_RECOMMENDATION_ID)
        val rec = RegionRepository.getRecommendation(recommendationId)
        if (rec == null) {
            binding.textTitle.setText(R.string.list_empty)
            binding.textDescription.text = ""
            binding.imagePlacePhoto.setImageDrawable(null)
            return
        }
        binding.imagePlacePhoto.setImageResource(rec.photoResId)
        binding.imagePlacePhoto.imageTintList = null
        binding.textTitle.setText(rec.titleResId)
        binding.textDescription.setText(rec.descriptionResId)
        (requireActivity() as AppCompatActivity).supportActionBar?.title =
            getString(rec.titleResId)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val ARG_RECOMMENDATION_ID = "recommendationId"
    }
}
