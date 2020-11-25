package com.udacity.shoestore.shoelist

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.udacity.shoestore.R
import com.udacity.shoestore.SharedShoeStoreViewModel
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.databinding.ShoeListRowBinding
import com.udacity.shoestore.model.Shoe

class ShoeListFragment : Fragment() {
    private lateinit var binding: FragmentShoeListBinding
    private lateinit var viewModel: SharedShoeStoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(SharedShoeStoreViewModel::class.java)

        setFabListener()
        observeShoeList()
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.options_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, Navigation.findNavController(this.activity!!, R.id.nav_host_fragment))
                || super.onOptionsItemSelected(item)
    }


    private fun observeShoeList() {
        viewModel.shoeList.observe(viewLifecycleOwner, Observer {
            binding.adapter = ShoeListBindingAdapter(context!!, this, it)
        })
    }

    private fun setFabListener() {
        binding.addNewShoeFab.setOnClickListener {
            ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment().run {
                findNavController().navigate(this)
            }
        }
    }

}