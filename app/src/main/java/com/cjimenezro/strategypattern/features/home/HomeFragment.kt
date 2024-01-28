package com.cjimenezro.strategypattern.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cjimenezro.strategypattern.databinding.FragmentHomeBinding

class HomeFragment : Fragment(){

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setupView()
        return binding.root
    }

    private fun setupView() {
        binding.apply {
            botonRemoto.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionFromFragmentHomeToFragmentList("remote"))
            }
            botonLocal.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionFromFragmentHomeToFragmentList("local"))
            }
            botonRemotoLocal.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionFromFragmentHomeToFragmentList("remote_local"))
            }
            botonDelete.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionFromFragmentHomeToFragmentList("delete_local"))
            }
        }
    }
}