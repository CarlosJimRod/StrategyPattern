package com.cjimenezro.strategypattern.features.superHeroes.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.cjimenezro.strategypattern.R
import com.cjimenezro.strategypattern.app.data.serialization.GsonSerialization
import com.cjimenezro.strategypattern.databinding.FragmentSuperHeroeBinding
import com.cjimenezro.strategypattern.features.home.MainActivity
import com.cjimenezro.strategypattern.features.superHeroes.data.SuperHeroeDataRepository
import com.cjimenezro.strategypattern.features.superHeroes.data.local.SuperHeroeLocalDataSource
import com.cjimenezro.strategypattern.features.superHeroes.data.remote.SuperHeroesApiClient
import com.cjimenezro.strategypattern.features.superHeroes.data.remote.SuperHeroesRemoteDataSource
import com.cjimenezro.strategypattern.features.superHeroes.data.strategy.DataStrategy
import com.cjimenezro.strategypattern.features.superHeroes.data.strategy.DeleteLocalStrategy
import com.cjimenezro.strategypattern.features.superHeroes.data.strategy.LocalStrategy
import com.cjimenezro.strategypattern.features.superHeroes.data.strategy.RemoteLocalStrategy
import com.cjimenezro.strategypattern.features.superHeroes.data.strategy.RemoteStrategy
import com.cjimenezro.strategypattern.features.superHeroes.domain.GetSuperHeroeUseCase
import com.cjimenezro.strategypattern.features.superHeroes.domain.SuperHeroe
import com.cjimenezro.strategypattern.features.superHeroes.presentation.adapter.SuperHeroesListAdapter
import com.faltenreich.skeletonlayout.Skeleton
import com.faltenreich.skeletonlayout.applySkeleton

class SuperHeroesListFragment : Fragment() {

    private var _binding: FragmentSuperHeroeBinding? = null
    private val binding get() = _binding!!

    val args: SuperHeroesListFragmentArgs by navArgs()

    private var strategy:DataStrategy=RemoteStrategy(
        SuperHeroesRemoteDataSource(
            SuperHeroesApiClient()
        )
    )

    private lateinit var skeleton: Skeleton

    val viewModel: SuperHeroesListViewModel by lazy {
        SuperHeroesListViewModel(
            GetSuperHeroeUseCase(
                SuperHeroeDataRepository(strategy)
            )
        )
    }

    private val superHeroApadter = SuperHeroesListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuperHeroeBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.apply {
            list.layoutManager = LinearLayoutManager(
                this@SuperHeroesListFragment.context,
                LinearLayoutManager.VERTICAL,
                false
            )
            list.adapter = superHeroApadter
            layoutList.toolbar.apply {
                setNavigationIcon(R.drawable.ic_back)
                setNavigationOnClickListener {
                    findNavController().navigateUp()
                }
                title = getString(R.string.fragment_list_title)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        strategy=args.strategy.let {
            when(it){
                "remote"-> RemoteStrategy(SuperHeroesRemoteDataSource(SuperHeroesApiClient()))
                "local"-> LocalStrategy(SuperHeroeLocalDataSource(requireContext(),GsonSerialization()))
                "remote_local"-> RemoteLocalStrategy(SuperHeroeLocalDataSource(requireContext(),GsonSerialization()),SuperHeroesRemoteDataSource(SuperHeroesApiClient()))
                "delete_local"-> DeleteLocalStrategy(SuperHeroeLocalDataSource(requireContext(),GsonSerialization()))
                else -> {
                    RemoteStrategy(SuperHeroesRemoteDataSource(SuperHeroesApiClient()))
                }
            }
        }
        setupObservers()
        skeleton = binding.list.applySkeleton(R.layout.view_super_heore_item, 8)
        viewModel.loadSuperHeroe()
    }

    private fun setupObservers() {
        val observer = Observer<SuperHeroesListViewModel.UiState> {
            if (it.isLoading) {
                showLoading()
            } else {
                hideLoading()
            }
            it.errorApp?.apply {
            }

            it.superHeroe?.apply {
                bind(this)
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun showLoading() {
        skeleton.showSkeleton()
    }

    private fun hideLoading() {
        skeleton.showOriginal()
    }

    private fun bind(superHeroes: List<SuperHeroe>) {
        superHeroApadter.submitList(superHeroes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}