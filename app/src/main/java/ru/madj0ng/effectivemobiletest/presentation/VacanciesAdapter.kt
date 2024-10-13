package ru.madj0ng.effectivemobiletest.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import org.koin.mp.KoinPlatform.getKoin
import ru.madj0ng.effectivemobiletest.R
import ru.madj0ng.effectivemobiletest.databinding.VacansiesItemBinding
import ru.madj0ng.effectivemobiletest.domain.models.VacancyModel
import ru.madj0ng.effectivemobiletest.util.NumericDeclination

class VacanciesAdapter(
    private val itemClickListener: OnItemListener,
    private val favoriteClickListener: OnFavoriteListener,
) : RecyclerView.Adapter<VacanciesAdapter.ViewHolder>() {
    private var vacancies = mutableListOf<VacancyModel>()

    fun setList(list: List<VacancyModel>) {
        this.vacancies.clear()
        this.vacancies.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            VacansiesItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding = binding,
            itemClickListener = itemClickListener,
            favoriteClickListener = favoriteClickListener,
            declination = getKoin().get()
        )
    }

    override fun getItemCount(): Int = vacancies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(vacancies[position])
    }

    class ViewHolder(
        private val binding: VacansiesItemBinding,
        private val itemClickListener: OnItemListener,
        private val favoriteClickListener: OnFavoriteListener,
        private val declination: NumericDeclination
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(vacancy: VacancyModel) {
            binding.tvViewers.isVisible = (vacancy.lookingNumber != 0)
            if (binding.tvViewers.isVisible) {
                binding.tvViewers.text = itemView.context.getString(
                    R.string.count_viewers,
                    vacancy.lookingNumber, declination.masculine(vacancy.lookingNumber)
                )
            }

            binding.ivFavorite.setImageResource(
                if (vacancy.isFavorite) R.drawable.ic_favorite_active else R.drawable.ic_favorite_default
            )

            binding.tvTitle.text = vacancy.title

            binding.tvSalary.isVisible = vacancy.salary.full.isNotBlank()
            if (binding.tvSalary.isVisible) binding.tvSalary.text = vacancy.salary.full

            binding.tvTown.text = vacancy.address.town
            binding.tvCompany.text = vacancy.company

            binding.tvExperience.text = vacancy.experience.previewText

            binding.tvPublishedDate.text =
                itemView.context.getString(R.string.published_date, vacancy.publishedDate)

            itemView.setOnClickListener { itemClickListener.onClick(vacancy.id) }
            binding.ivFavorite.setOnClickListener { favoriteClickListener.onClick(vacancy) }
        }
    }

    fun interface OnItemListener {
        fun onClick(vacanciesId: String)
    }

    fun interface OnFavoriteListener {
        fun onClick(vacancy: VacancyModel)
    }
}