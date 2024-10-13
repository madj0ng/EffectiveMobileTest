package ru.madj0ng.effectivemobiletest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.mp.KoinPlatform
import ru.madj0ng.effectivemobiletest.R
import ru.madj0ng.effectivemobiletest.databinding.FragmentVacancyDetailBinding
import ru.madj0ng.effectivemobiletest.presentation.QuestionAdapter
import ru.madj0ng.effectivemobiletest.presentation.VacancyDetailViewModel
import ru.madj0ng.effectivemobiletest.presentation.models.VacancyInfo
import ru.madj0ng.effectivemobiletest.util.NumericDeclination

class VacancyDetailFragment : Fragment() {
    private var _binding: FragmentVacancyDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel: VacancyDetailViewModel by viewModel()
    private val declination: NumericDeclination = KoinPlatform.getKoin().get()
    private var questionAdapter: QuestionAdapter? = null

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

        questionAdapter = QuestionAdapter { }

        binding.rvQuestions.apply {
            layoutManager =
                LinearLayoutManager(requireContext())
            adapter = questionAdapter
        }

        viewModel.getVacancy().observe(viewLifecycleOwner, this::render)
        viewModel.getQuestion().observe(viewLifecycleOwner, this::render)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun render(list: List<String>) {
        questionAdapter?.setList(list)
    }

    private fun render(vacancy: VacancyInfo) {
        binding.tvTitle.text = vacancy.title
        binding.tvSalary.isVisible = vacancy.salary.isNotBlank()
        if (binding.tvSalary.isVisible) binding.tvSalary.text = vacancy.salary
        binding.tvSchedules.text = vacancy.schedules
        binding.ivFavorite.apply {
            if (vacancy.isFavorite) {
                setImageResource(R.drawable.ic_favorite_active)
            } else {
                setImageResource(R.drawable.ic_favorite_default)
            }
        }
        binding.tvCompany.text = vacancy.company
        binding.tvAddressStreet.text = vacancy.addressFull
        binding.tvExperience.text =
            context?.getString(
                R.string.experience_of_detail,
                vacancy.experience
            )

        binding.ivApplied.text =
            context?.getString(
                R.string.count_of_responses,
                vacancy.appliedNumber,
                declination.masculine(vacancy.appliedNumber),
                declination.plural(vacancy.appliedNumber),
            )
        binding.ivLooking.text =
            context?.getString(
                R.string.count_viewers_now,
                vacancy.lookingNumber,
                declination.masculine(vacancy.lookingNumber),
            )
        binding.tvDescription.text = vacancy.description
        binding.tvResponsibilities.text = vacancy.responsibilities
        binding.tvResponsibilities.text = vacancy.responsibilities
    }

    companion object {
        const val VACANCY_ID = "vacancy_id"
        fun createArgs(trackId: String): Bundle =
            bundleOf(
                VACANCY_ID to trackId
            )
    }
}