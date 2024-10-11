package ru.madj0ng.effectivemobiletest.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.madj0ng.effectivemobiletest.R
import ru.madj0ng.effectivemobiletest.data.dto.VacanciesDto
import ru.madj0ng.effectivemobiletest.databinding.VacansiesItemBinding

class VacanciesAdapter(
    private val clickListener: OnClickListener,
) : RecyclerView.Adapter<VacanciesAdapter.ViewHolder>() {
    private var vacancies = mutableListOf<VacanciesDto>()

    fun setList(list: List<VacanciesDto>) {
        this.vacancies.clear()
        this.vacancies.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            VacansiesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding = binding,
            clickListener = clickListener,
        )
    }

    override fun getItemCount(): Int = vacancies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(vacancies[position])
    }

    class ViewHolder(
        private val binding: VacansiesItemBinding,
        private val clickListener: OnClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(vacancies: VacanciesDto) {
            binding.tvViewers.isVisible = (vacancies.lookingNumber != null)
            if (binding.tvViewers.isVisible) {
                binding.tvViewers.text = itemView.context.getString(
                    R.string.count_viewers,
                    vacancies.lookingNumber, "а"
                )
            }

            if (vacancies.isFavorite) {
                binding.ivFavorite.setImageResource(R.drawable.ic_favorite_active)
            } else {
                binding.ivFavorite.setImageResource(R.drawable.ic_favorite_default)
            }

            binding.tvTitle.text = vacancies.title
            binding.tvTown.isVisible = (vacancies.address != null)
            if (binding.tvTown.isVisible) binding.tvTown.text = vacancies.address?.town
            binding.tvCompany.text = vacancies.company
            binding.tvExperience.text = vacancies.experience.previewText
            binding.tvPublishedDate.text = vacancies.publishedDate

            itemView.setOnClickListener { clickListener.onItemClick(vacancies.id) }
        }
    }

    fun interface OnClickListener {
        fun onItemClick(vacanciesId: String)
    }
}