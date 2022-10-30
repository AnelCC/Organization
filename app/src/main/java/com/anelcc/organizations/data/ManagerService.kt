package com.anelcc.organizations.data

import com.anelcc.organizations.core.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ManagerService @Inject constructor(private val retrofit: ApiService) {
    suspend fun getReposByUser(user: String) : List<RepositoryModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.getReposByUser(user)
            response?.execute()?.body() ?: emptyList()
        }
    }
    suspend fun getRepos(org: String) : List<RepositoryModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.getRepoList(org)
            response?.execute()?.body() ?: emptyList()
        }
    }
}