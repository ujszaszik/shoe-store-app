package com.udacity.shoestore.shoedetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.udacity.shoestore.R
import com.udacity.shoestore.SharedShoeStoreViewModel
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.keyboard.HideKeyboardListener
import com.udacity.shoestore.model.Shoe
import com.udacity.shoestore.model.ValidationResult

class ShoeDetailFragment : Fragment() {
    private lateinit var binding: FragmentShoeDetailBinding
    private lateinit var viewModel: SharedShoeStoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_detail, container, false)
        binding.lifecycleOwner = this

        viewModel = ViewModelProvider(requireActivity()).get(SharedShoeStoreViewModel::class.java)
        binding.viewModel = viewModel

        binding.shoe = Shoe.initial()
        setupUI()

        return binding.root
    }

    private fun setupUI() {
        setHideKeyboardListener()
        observeShoeCreation()
        observeShoeDataValidity()
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun setHideKeyboardListener() {
        binding.shoeDetailLayout.setOnTouchListener(HideKeyboardListener)
    }

    private fun observeShoeCreation() {
        viewModel.shoeToSave.observe(
            viewLifecycleOwner,
            Observer {
                if (!viewModel.isShoeAlreadySent()) {
                    viewModel.addNewShoe(it)
                    navigateToShoeList(it)
                }
            })
    }

    private fun navigateToShoeList(shoe: Shoe) {
        ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment(shoe).run {
            viewModel.setShoeAsAlreadySent()
            findNavController().navigate(this)
        }
    }

    private fun navigateBackIfCancelled() {
        ShoeDetailFragmentDirections.actionShoeDetailFragmentToShoeListFragment().run {
            viewModel.resetValidity()
            findNavController().navigate(this)
        }
    }

    private fun observeShoeDataValidity() {
        viewModel.shoeValueValidity.observe(viewLifecycleOwner, Observer { validationResult ->
            when (validationResult) {
                ValidationResult.Incomplete -> showErrorBar(getString(R.string.incomplete_shoe_data))
                ValidationResult.Existing -> showErrorBar(getString(R.string.existing_shoe_data))
                ValidationResult.Cancelled -> navigateBackIfCancelled()
            }
        })
        viewModel.resetValidity()
    }

    private fun showErrorBar(message: String) {
        Snackbar.make(
            binding.shoeDetailLayout,
            message,
            Snackbar.LENGTH_SHORT
        ).show()
    }

}