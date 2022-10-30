package com.anelcc.organizations.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.anelcc.organizations.R
import com.anelcc.organizations.data.RepositoryModel
import com.anelcc.organizations.databinding.FragmentOrganizationListBinding
import com.anelcc.organizations.presentation.OrganizationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FragmentOrganizationList : Fragment(), OnSelectedItem {
    private val viewModel: OrganizationViewModel by activityViewModels()
    private lateinit var  organizationsRecycleAdapter: OrganizationsRecycleAdapter
    private var _binding: FragmentOrganizationListBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentOrganizationListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host)


        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                viewModel.fetchData(query)
                return false
            }
            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    viewModel.fetchData(newText)
                }
                return false
            }
        })

        binding.organizationListRecycleView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            organizationsRecycleAdapter = OrganizationsRecycleAdapter(emptyList(), this@FragmentOrganizationList)
            adapter = organizationsRecycleAdapter
        }

        viewModel.organizationList.observe(viewLifecycleOwner) {
            updateInfo(it)
        }
    }

    private fun updateInfo(organizationList: List<RepositoryModel>) {
        if (organizationList.isEmpty()) {
            displayMessage("Sorry we did not find info, please try again")
        }
        organizationsRecycleAdapter.setData(organizationList)
        binding.organizationListRecycleView.adapter?.notifyDataSetChanged()
    }

    private fun displayMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    override fun OnSelectedItem(repository: RepositoryModel) {
        viewModel.setSelectedRepository(repository)
        navController.navigate(R.id.action_organizationListFragment_to_detailFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    companion object {
    }

}