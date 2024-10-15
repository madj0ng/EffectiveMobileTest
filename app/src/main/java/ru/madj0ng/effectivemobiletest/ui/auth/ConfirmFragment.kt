package ru.madj0ng.effectivemobiletest.ui.auth

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.madj0ng.effectivemobiletest.R
import ru.madj0ng.effectivemobiletest.databinding.FragmentConfirmBinding
import ru.madj0ng.effectivemobiletest.presentation.auth.LoginViewModel

class ConfirmFragment : Fragment() {
    private val loginViewModel: LoginViewModel by viewModel()
    private var _binding: FragmentConfirmBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfirmBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val codeButton = binding.bEnterConfirm

        val email = requireArguments().getString(LOGIN_EMAIL)
        binding.tvConfirmationEmail.text = getString(R.string.send_email_to, email)

        binding.bEnterConfirm.setOnClickListener {
            navigateToMain()
        }

        val afterTextNum1Listener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                codeChange()
            }
        }
        binding.etNum1.addTextChangedListener(afterTextNum1Listener)

        val afterTextNum2Listener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                codeChange()
            }
        }
        binding.etNum2.addTextChangedListener(afterTextNum2Listener)

        val afterTextNum3Listener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                codeChange()
            }
        }
        binding.etNum3.addTextChangedListener(afterTextNum3Listener)

        val afterTextNum4Listener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                codeChange()
            }
        }
        binding.etNum4.addTextChangedListener(afterTextNum4Listener)

        loginViewModel.codeFormState.observe(viewLifecycleOwner,
            Observer { loginFormState ->
                if (loginFormState == null) {
                    return@Observer
                }
                codeButton.isEnabled = loginFormState.isDataValid
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun codeChange() {
        loginViewModel.codeDataChanged(
            binding.etNum1.text.toString(),
            binding.etNum2.text.toString(),
            binding.etNum3.text.toString(),
            binding.etNum4.text.toString(),
        )
    }

    private fun navigateToMain() {
        val navHostFragment =
            requireActivity().supportFragmentManager.findFragmentById(R.id.fcvMain) as NavHostFragment
        val navController = navHostFragment.navController
        navController.popBackStack(R.id.loginFragment, true)
        navController.navigate(R.id.searchFragment)
    }

    companion object {
        const val LOGIN_EMAIL = "login_email"
        fun createArgs(login_email: String): Bundle =
            bundleOf(
                LOGIN_EMAIL to login_email
            )
    }
}

