package com.emm.tasty.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.emm.tasty.adapters.RecipeAdapter
import com.emm.tasty.databinding.FragmentHomeBinding
import com.emm.tasty.models.RecipeModel
import com.emm.tasty.utils.infinityScroll
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    private val adapter: RecipeAdapter by lazy { RecipeAdapter(::onItemClick) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.homeViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvRecipe.adapter = adapter
        setupInfinityScroll()
    }

    private fun setupInfinityScroll() = with(binding) {
        rvRecipe.infinityScroll(
            isLoading = { viewModel.homeViewState.value.showLoading },
            loadMoreItems = { viewModel.loadMoreRecipesFromScroll() },
            hideKeyboardOnScroll = true
        )
    }

    private fun onItemClick(model: RecipeModel) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(model)
        findNavController().navigate(action)
    }

}