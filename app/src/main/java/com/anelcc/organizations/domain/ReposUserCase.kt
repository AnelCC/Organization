package com.anelcc.organizations.domain

import com.anelcc.organizations.data.Repository
import com.anelcc.organizations.data.RepositoryModel
import javax.inject.Inject

class ReposUserCase @Inject constructor(private val repository: Repository) {
    suspend operator fun invoke(user: String) = sortByStars(repository.getAllReposByUser(user))

    private fun sortByStars(repos: List<RepositoryModel>): List<RepositoryModel> {
        val sortedList = repos.let { list ->
            list.sortedByDescending { it.stargazersCount }
        }
        return sortedList
    }
}