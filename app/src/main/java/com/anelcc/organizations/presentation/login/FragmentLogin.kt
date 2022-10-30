package com.anelcc.organizations.presentation.login

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.anelcc.organizations.CLIENT_SECRET
import com.anelcc.organizations.R
import com.anelcc.organizations.databinding.FragmentLoginBinding
import com.anelcc.organizations.presentation.OrganizationViewModel
import dagger.hilt.android.AndroidEntryPoint
import net.openid.appauth.AuthorizationException
import net.openid.appauth.AuthorizationResponse
import net.openid.appauth.AuthorizationService
import net.openid.appauth.ClientSecretBasic


@AndroidEntryPoint
class FragmentLogin : Fragment() {
    private val viewModel: OrganizationViewModel by activityViewModels()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var service: AuthorizationService
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        service = AuthorizationService(requireContext())
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host)

        binding.githubLoginBtn.setOnClickListener {
            githubAuth()
        }
    }

    private fun githubAuth() {
        displayLoading(true)
        val intent = service.getAuthorizationRequestIntent(viewModel.getAuth())
        launcher.launch(intent)
    }

    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK) {
                it.data?.let { data ->
                    val ex = AuthorizationException.fromIntent(data)
                    val result = AuthorizationResponse.fromIntent(data)

                    if (ex != null) {
                        displayError()
                    } else {
                        requestToken(result)
                    }
                }
            } else {
                displayError()
            }
        }

    private fun requestToken(result: AuthorizationResponse?) {
        val secret = ClientSecretBasic(CLIENT_SECRET)
        val tokenRequest = result?.createTokenExchangeRequest()
        service.performTokenRequest(tokenRequest!!, secret) { res, exception ->
            if (exception != null) {
                displayError("Error: ${exception.error}")
            } else {
                val token = res?.accessToken
                onSuccessLogin(token)
            }
        }
    }

    private fun displayLoading(isVisible: Boolean) {
        binding.progressContainer.visibility =
            if (isVisible) View.VISIBLE else View.GONE
        binding.progressCircular.visibility=
            if (isVisible) View.VISIBLE else View.GONE
    }

    private fun displayError(error: String = "") {
        displayLoading(false)
        Toast.makeText(context, resources.getText(R.string.errorMessage, error), Toast.LENGTH_LONG).show()
    }

    private fun onSuccessLogin(token: String?) {
        displayLoading(false)
        viewModel.setToken(token)
        navController.navigate(R.id.action_LoginFragment_to_organizationListFragment)
    }
}