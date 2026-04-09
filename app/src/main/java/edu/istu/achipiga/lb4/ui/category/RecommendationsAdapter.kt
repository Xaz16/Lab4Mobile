package edu.istu.achipiga.lb4.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.istu.achipiga.lb4.data.Recommendation
import edu.istu.achipiga.lb4.databinding.ItemRecommendationBinding

class RecommendationsAdapter(
    private val recommendations: List<Recommendation>,
    private val onItemClick: (Recommendation) -> Unit,
) : RecyclerView.Adapter<RecommendationsAdapter.RecommendationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecommendationViewHolder {
        val binding =
            ItemRecommendationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecommendationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecommendationViewHolder, position: Int) {
        holder.bind(recommendations[position])
    }

    override fun getItemCount(): Int = recommendations.size

    inner class RecommendationViewHolder(
        private val binding: ItemRecommendationBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(recommendation: Recommendation) {
            binding.imagePlacePhoto.setImageResource(recommendation.photoResId)
            binding.imagePlacePhoto.imageTintList = null
            binding.textTitle.setText(recommendation.titleResId)
            binding.textDescription.setText(recommendation.descriptionResId)
            binding.root.setOnClickListener { onItemClick(recommendation) }
        }
    }
}
