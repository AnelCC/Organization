package com.anelcc.organizations.presentation.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.anelcc.organizations.R
import com.anelcc.organizations.data.RepositoryModel
import com.anelcc.organizations.databinding.ItemOrganizationBinding


class OrganizationsRecycleAdapter (private var organizationList: List<RepositoryModel>, val onSelectedItem: OnSelectedItem) : RecyclerView.Adapter<OrganizationsRecycleAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_organization, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val image = organizationList[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int = organizationList.size

    fun setData(newOrganizationList: List<RepositoryModel>) {
        organizationList = newOrganizationList
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemOrganizationBinding.bind(itemView)

        fun bind(organization: RepositoryModel) {
            binding.organizationName.text = organization.name
            binding.organizationDescription.text = organization.description
            binding.organizationRate.text = "Stars: ${organization.stargazersCount.toString()}"
            binding.employeeCardView.setOnClickListener {
                onSelectedItem.OnSelectedItem(organization)
            }
        }
    }

    companion object {
        private val TAG = OrganizationsRecycleAdapter::class.simpleName
    }
}
