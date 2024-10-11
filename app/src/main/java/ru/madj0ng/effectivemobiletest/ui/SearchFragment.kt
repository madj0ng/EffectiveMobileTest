package ru.madj0ng.effectivemobiletest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.madj0ng.effectivemobiletest.R
import ru.madj0ng.effectivemobiletest.data.dto.OfferDto
import ru.madj0ng.effectivemobiletest.data.dto.VacanciesDto
import ru.madj0ng.effectivemobiletest.databinding.FragmentSearchBinding
import ru.madj0ng.effectivemobiletest.presentation.OffersAdapter
import ru.madj0ng.effectivemobiletest.presentation.SearchViewModel
import ru.madj0ng.effectivemobiletest.presentation.VacanciesAdapter
import ru.madj0ng.effectivemobiletest.presentation.models.VacanciesUiState

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModel()
    private var vacanciesAdapter: VacanciesAdapter? = null
    private var offersAdapter: OffersAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        vacanciesAdapter = VacanciesAdapter {
//            findNavController().navigate(R.id.action_searchFragment_to_vacancyDetailFragment, )
        }
        binding.rvVacancies.apply {
            layoutManager =
                LinearLayoutManager(requireContext())
            adapter = vacanciesAdapter
        }

        offersAdapter = OffersAdapter {
//                findNavController().navigate(R.id.action_searchFragment_to_vacancyDetailFragment)
        }

        binding.rvOffers.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = offersAdapter
        }

        binding.bVacanciesButton.setOnClickListener { viewModel.nextPage() }
        binding.ivBackButton.setOnClickListener { viewModel.backPage() }

        viewModel.getOffers().observe(viewLifecycleOwner) {
            binding.rvOffers.isVisible = it.isNotEmpty()
            offersAdapter?.setList(it)
        }

        viewModel.getOffers().observe(viewLifecycleOwner, this::render)
        viewModel.getVacancies().observe(viewLifecycleOwner, this::render)
        viewModel.getDefault().observe(viewLifecycleOwner, this::render)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun render(isDeafult: Boolean) {
        binding.defaultGroup.isVisible = isDeafult
        if (isDeafult) {
            binding.etSearch.setHint(R.string.search_hint_post)
        } else {
            binding.etSearch.setHint(R.string.search_hint_text)
        }
        binding.vacanciesGroup.isVisible = !isDeafult
        binding.bVacanciesButton.isVisible = !isDeafult
    }

    private fun render(list: List<OfferDto>) {
        binding.rvOffers.isVisible = list.isNotEmpty()
        offersAdapter?.setList(list)
    }

    private fun render(state: VacanciesUiState) {
        when (state) {
            is VacanciesUiState.Content -> showContent(state.list, state.count)
            is VacanciesUiState.Loading -> showProgressBar(true)
        }
    }

    private fun showContent(list: List<VacanciesDto>, count: Int) {
        showProgressBar(false)
        showButtonCount(count)
        showTopCount(count)
        showVacancies(list)
    }

    private fun showProgressBar(isVisisble: Boolean) {
        binding.progressBar.isVisible = isVisisble
        binding.clVacancies.isVisible = !isVisisble
    }

    private fun showVacancies(list: List<VacanciesDto>) {
        binding.rvVacancies.isVisible = list.isNotEmpty()
        vacanciesAdapter?.setList(list)
    }

    private fun showButtonCount(count: Int) {
        binding.bVacanciesButton.text = getString(
            R.string.button_count_vacancies_inclined,
            count, "й"
        )
    }

    private fun showTopCount(count: Int) {
        binding.tvTopListCount.text = getString(
            R.string.count_vacancies_inclined,
            count, "й"
        )
    }
}