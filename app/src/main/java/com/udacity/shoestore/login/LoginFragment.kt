package com.udacity.shoestore.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.databinding.FragmentLoginBinding


class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    companion object {
        private const val ANONYMOUS_USERNAME = "Anonymous"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        setListeners()
        return binding.root
    }

    private fun setListeners() {
        val onClickListener: (View) -> Unit = { navigateToWelcomeScreen(getEnteredUsername()) }
        binding.loginButton.setOnClickListener(onClickListener)
        binding.registerButton.setOnClickListener(onClickListener)
    }

    private fun getEnteredUsername(): String {
        binding.loginNameText.text.toString().run {
            return if (this.isNotBlank()) this else ANONYMOUS_USERNAME
        }
    }

    private fun navigateToWelcomeScreen(username: String) {
        LoginFragmentDirections.actionLoginFragmentToWelcomeFragment(username)
            .run {
                findNavController().navigate(this)
            }
    }

}