package ru.madj0ng.effectivemobiletest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.mp.KoinPlatform.getKoin
import ru.madj0ng.effectivemobiletest.R
import ru.madj0ng.effectivemobiletest.data.dto.OfferDto
import ru.madj0ng.effectivemobiletest.databinding.FragmentSearchBinding
import ru.madj0ng.effectivemobiletest.domain.favorite.CurrenFavoriteCount
import ru.madj0ng.effectivemobiletest.domain.models.VacancyModel
import ru.madj0ng.effectivemobiletest.presentation.OffersAdapter
import ru.madj0ng.effectivemobiletest.presentation.SearchViewModel
import ru.madj0ng.effectivemobiletest.presentation.VacanciesAdapter
import ru.madj0ng.effectivemobiletest.presentation.models.VacanciesUiState
import ru.madj0ng.effectivemobiletest.util.NumericDeclination

class SearchFragment : Fragment() {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SearchViewModel by viewModel()
    private var vacanciesAdapter: VacanciesAdapter? = null
    private var offersAdapter: OffersAdapter? = null
    private val declination: NumericDeclination = getKoin().get()

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

        vacanciesAdapter = VacanciesAdapter(
            { vacancyId ->
                findNavController().navigate(
                    R.id.action_searchFragment_to_vacancyDetailFragment,
                    VacancyDetailFragment.createArgs(vacancyId)
                )
            },
            {
                viewModel.toggleFavorite(it)
                (requireActivity() as CurrenFavoriteCount).changeToggleFavorite(it.isFavorite)
            }
        )
        binding.rvVacancies.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = vacanciesAdapter
        }

        offersAdapter = OffersAdapter {
            viewModel.sharingOffer(it)
        }

        binding.rvOffers.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = offersAdapter
        }

        viewModel.defaultPage()

        binding.bVacanciesButton.setOnClickListener { viewModel.nextPage() }
        binding.ivBackButton.setOnClickListener { viewModel.defaultPage() }

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

    private fun showContent(list: List<VacancyModel>, count: Int) {
        showProgressBar(false)
        binding.bVacanciesButton.text = feminine(count, R.string.button_count_vacancies_inclined)
        binding.tvTopListCount.text = feminine(count, R.string.count_vacancies_inclined)
        showVacancies(list)
    }

    private fun showProgressBar(isVisisble: Boolean) {
        binding.progressBar.isVisible = isVisisble
        binding.clVacancies.isVisible = !isVisisble
        if (binding.defaultGroup.isVisible) binding.bMap.isVisible = !isVisisble
    }

    private fun showVacancies(list: List<VacancyModel>) {
        binding.rvVacancies.isVisible = list.isNotEmpty()
        vacanciesAdapter?.setList(list)
    }

    private fun feminine(count: Int, @StringRes strId: Int): String = getString(
        strId, count, declination.feminine(count)
    )
}