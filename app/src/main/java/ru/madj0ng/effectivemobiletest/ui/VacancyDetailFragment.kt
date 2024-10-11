package ru.madj0ng.effectivemobiletest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.madj0ng.effectivemobiletest.databinding.FragmentVacancyDetailBinding
import ru.madj0ng.effectivemobiletest.domain.models.VacancyModel
import ru.madj0ng.effectivemobiletest.presentation.VacancyDetailViewModel

class VacancyDetailFragment : Fragment() {
    private var _binding: FragmentVacancyDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: VacancyDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVacancyDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val vacancyId = requireArguments().getString(VACANCY_ID, "")
        viewModel.loadData(vacancyId)

        viewModel.getVacancy().observe(viewLifecycleOwner, this::render)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun render(vacancy: VacancyModel) {

    }

    companion object {
        const val VACANCY_ID = "vacancy_id"
        fun createArgs(trackId: String): Bundle =
            bundleOf(
                VACANCY_ID to trackId
            )
    }
}