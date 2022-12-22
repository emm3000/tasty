package com.emm.tasty.fragments.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.emm.tasty.adapters.RecipeAdapter
import com.emm.tasty.databinding.FragmentHomeBinding
import com.emm.tasty.models.RecipeModel
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
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvRecipe.adapter = adapter
    }

    private fun onItemClick(model: RecipeModel) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailFragment(model)
        findNavController().navigate(action)
    }

}