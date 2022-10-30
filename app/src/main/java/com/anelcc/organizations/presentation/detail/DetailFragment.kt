package com.anelcc.organizations.presentation.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.anelcc.organizations.databinding.FragmentDetailBinding
import com.anelcc.organizations.presentation.OrganizationViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    private val viewModel: OrganizationViewModel by activityViewModels()
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val currentRepo = viewModel.selectedRepository.value
        currentRepo?.let {
            binding.organizationName.text = it.name ?: "UNKNOWN"
            binding.organizationFullName.text = currentRepo.fullName ?: "UNKNOWN"
            binding.organizationDescription.text = it.description ?: "UNKNOWN"
            binding.organizationRate.text = it.stargazersCount.toString()
            binding.organizationRepoType.text =it.visibility ?: "UNKNOWN"
        }
    }
}