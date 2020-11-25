package com.udacity.shoestore.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionBinding

class InstructionFragment : Fragment() {
    private lateinit var binding: FragmentInstructionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_instruction, container, false)

        setListener()

        return binding.root
    }

    private fun setListener() {
        binding.shoeListButton.setOnClickListener { jumpToShoeList() }
    }

    private fun jumpToShoeList() {
        InstructionFragmentDirections.actionInstructionFragmentToShoeListFragment().run {
            findNavController().navigate(this)
        }
    }


}