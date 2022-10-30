package com.anelcc.organizations.presentation.list

import com.anelcc.organizations.data.RepositoryModel


interface OnSelectedItem {
    fun OnSelectedItem(onSelectedItem: RepositoryModel)
}