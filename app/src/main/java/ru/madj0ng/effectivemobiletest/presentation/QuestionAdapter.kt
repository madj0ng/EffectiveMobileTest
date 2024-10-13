package ru.madj0ng.effectivemobiletest.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.madj0ng.effectivemobiletest.databinding.QuestionsItemBinding

class QuestionAdapter(
    private val clickListener: OnClickListener,
) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {
    private var questions = mutableListOf<String>()

    fun setList(list: List<String>) {
        this.questions.clear()
        this.questions.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            QuestionsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(
            binding = binding,
            clickListener = clickListener,
        )
    }

    override fun getItemCount(): Int = questions.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    class ViewHolder(
        private val binding: QuestionsItemBinding,
        private val clickListener: OnClickListener,
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(question: String) {

            binding.bQuestion.text = question

            itemView.setOnClickListener { clickListener.onItemClick() }
        }
    }

    fun interface OnClickListener {
        fun onItemClick()
    }

}