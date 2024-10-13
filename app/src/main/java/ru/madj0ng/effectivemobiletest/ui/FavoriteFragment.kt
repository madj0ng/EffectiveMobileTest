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
import org.koin.androidx.viewmodel.ext.android.activityViewModel
import org.koin.mp.KoinPlatform
import ru.madj0ng.effectivemobiletest.R
import ru.madj0ng.effectivemobiletest.databinding.FragmentFavoriteBinding
import ru.madj0ng.effectivemobiletest.domain.favorite.CurrenFavoriteCount
import ru.madj0ng.effectivemobiletest.domain.models.VacancyModel
import ru.madj0ng.effectivemobiletest.presentation.FavoriteViewModel
import ru.madj0ng.effectivemobiletest.presentation.VacanciesAdapter
import ru.madj0ng.effectivemobiletest.presentation.models.VacanciesUiState
import ru.madj0ng.effectivemobiletest.util.NumericDeclination

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FavoriteViewModel by activityViewModel<FavoriteViewModel>()
    private var favoritesAdapter: VacanciesAdapter? = null
    private val declination: NumericDeclination = KoinPlatform.getKoin().get()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favoritesAdapter = VacanciesAdapter(
            {vacancyId ->
                findNavController().navigate(
                    R.id.action_favoriteFragment_to_vacancyDetailFragment,
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
            adapter = favoritesAdapter
        }

        viewModel.loadData()

        viewModel.getFavorites().observe(viewLifecycleOwner, this::render)
        viewModel.getFavoriteCount().observe(viewLifecycleOwner, this::render)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun render(state: VacanciesUiState) {
        when (state) {
            is VacanciesUiState.Content -> showContent(state.list, state.count)
            is VacanciesUiState.Loading -> showProgressBar(true)
        }
    }

    private fun render(count: Int) {
        (requireActivity() as CurrenFavoriteCount).setFavoriteCount(count)
    }

    private fun showContent(list: List<VacancyModel>, count: Int) {
        binding.tvTopListCount.isVisible = count != 0
        if (binding.tvTopListCount.isVisible) binding.tvTopListCount.text =
            feminine(count, R.string.count_vacancies_inclined)
        showFavorites(list)
    }

    private fun showFavorites(list: List<VacancyModel>) {
        binding.rvVacancies.isVisible = list.isNotEmpty()
        favoritesAdapter?.setList(list)
    }

    private fun feminine(count: Int, @StringRes strId: Int): String = getString(
        strId, count, declination.feminine(count)
    )

    private fun showProgressBar(isVisisble: Boolean) {
    }
}