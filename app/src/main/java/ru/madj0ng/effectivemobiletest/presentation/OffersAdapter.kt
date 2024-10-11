package ru.madj0ng.effectivemobiletest.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import ru.madj0ng.effectivemobiletest.R
import ru.madj0ng.effectivemobiletest.data.dto.OfferDto
import ru.madj0ng.effectivemobiletest.databinding.OffersItemBinding
import ru.madj0ng.effectivemobiletest.presentation.models.OffersTypes

class OffersAdapter(
    private val clickListener: OnClickListener,
) : RecyclerView.Adapter<OffersAdapter.ViewHolder>() {
    private var offers = mutableListOf<OfferDto>()

    fun setList(list: List<OfferDto>) {
        this.offers.clear()
        this.offers.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            OffersItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding = binding,
            clickListener = clickListener,
        )
    }

    override fun getItemCount(): Int = offers.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(offers[position])
    }

    class ViewHolder(
        private val binding: OffersItemBinding,
        private val clickListener: OnClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(offer: OfferDto) {
            val max2 = 2
            val max3 = 2
            when (offer.id) {
                OffersTypes.NEAR_VACANCIES.id -> binding.ivOffer.apply {
                    setImageResource(OffersTypes.NEAR_VACANCIES.icon)
                    setBackgroundTintList(itemView.resources.getColorStateList(R.color.dark_blue, context.getTheme()))
                }
                OffersTypes.LEVEL_UP_RESUME.id -> binding.ivOffer.apply {
                    setImageResource(OffersTypes.LEVEL_UP_RESUME.icon)
                    setBackgroundTintList(itemView.resources.getColorStateList(R.color.dark_green, context.getTheme()))
                }
                OffersTypes.TEMPORARY_JOB.id -> binding.ivOffer.apply {
                    setImageResource(OffersTypes.TEMPORARY_JOB.icon)
                    setBackgroundTintList(itemView.resources.getColorStateList(R.color.dark_green, context.getTheme()))
                }
                else -> binding.ivOffer.apply {
                    setImageDrawable(null)
                    setBackgroundTintList(itemView.resources.getColorStateList(R.color.dark_blue, context.getTheme()))
                }
            }
            binding.tvOffer.text = offer.title.trim()
            binding.tvOfferButtom.isVisible = (offer.button != null)
            if (binding.tvOfferButtom.isVisible) {
                binding.tvOffer.maxLines = max3
                binding.tvOfferButtom.text = offer.button!!.text
            } else {
                binding.tvOffer.maxLines = max2
            }
        }
    }

    fun interface OnClickListener {
        fun onItemClick(buttonText: String)
    }
}