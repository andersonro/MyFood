package com.example.myfood.ui.home

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myfood.R
import com.example.myfood.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupViews()

        homeViewModel.text.observe(viewLifecycleOwner) {
            Log.e("HOME", activity.toString())
        }
        return root
    }

    private fun setupViews() {
        val tab_layout = binding.addTab
        val view_pager = binding.addViewpage
        view_pager.isUserInputEnabled = true
        val adapter = TabViewPageAdapter(this)
        view_pager.adapter = adapter

        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            tab.text = getString(adapter.tabs[position])
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class TabViewPageAdapter(fa: Fragment) : FragmentStateAdapter(fa){
    val tabs = arrayOf(R.string.restaurants, R.string.marketplaces, R.string.drinks)
    val fragments = arrayOf(MarketplaceFragment(), MarketplaceFragment(), MarketplaceFragment())

    override fun getItemCount() = fragments.size

    override fun createFragment(position: Int): Fragment {
        return fragments[position]
    }
}

class MarketplaceFragment: Fragment(){

}