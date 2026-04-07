package edu.istu.achipiga.lb4.ui.cities

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.istu.achipiga.lb4.data.City
import edu.istu.achipiga.lb4.databinding.ItemCityBinding

class CitiesAdapter(
    private val cities: List<City>,
    private val onCityClick: (City) -> Unit,
) : RecyclerView.Adapter<CitiesAdapter.CityViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = ItemCityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    override fun getItemCount(): Int = cities.size

    inner class CityViewHolder(
        private val binding: ItemCityBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(city: City) {
            binding.textCityName.setText(city.nameResId)
            binding.textCityDescription.setText(city.descriptionResId)
            binding.root.setOnClickListener { onCityClick(city) }
        }
    }
}
