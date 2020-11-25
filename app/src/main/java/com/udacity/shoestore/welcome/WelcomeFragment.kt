package com.udacity.shoestore.welcome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {
    private lateinit var binding: FragmentWelcomeBinding
    private val args: WelcomeFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_welcome, container, false)
        binding.welcomeText.text = getString(R.string.welcome_text, args.username)
        setListener()
        return binding.root
    }

    private fun setListener() {
        binding.instructionsButton.setOnClickListener { jumpToInstructions() }
    }

    private fun jumpToInstructions() {
        WelcomeFragmentDirections.actionWelcomeFragmentToInstructionFragment().run {
            findNavController().navigate(this)
        }
    }

}