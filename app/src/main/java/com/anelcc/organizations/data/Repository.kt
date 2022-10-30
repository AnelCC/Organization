package com.anelcc.organizations.data

import android.net.Uri
import com.anelcc.organizations.CLIENT_ID
import com.anelcc.organizations.GITHUB_AUTHORIZE
import com.anelcc.organizations.GITHUB_TOKEN
import com.anelcc.organizations.REDIRECT_URI
import net.openid.appauth.AuthorizationRequest
import net.openid.appauth.AuthorizationServiceConfiguration
import net.openid.appauth.ResponseTypeValues
import javax.inject.Inject

class Repository @Inject constructor(private val  api: ManagerService) {
    suspend fun getAllReposByUser(user: String): List<RepositoryModel> {
        val response = api.getReposByUser(user)
        RepositoryProvider.repos = response
        return response
    }

    suspend fun getAllRepos(user: String): List<RepositoryModel> {
        val response = api.getRepos(user)
        RepositoryProvider.repos = response
        return response
    }

    fun getAuthRequest(): AuthorizationRequest {
        val redirectUri = Uri.parse(REDIRECT_URI)
        val authorizeUri = Uri.parse(GITHUB_AUTHORIZE)
        val tokenUri = Uri.parse(GITHUB_TOKEN)
        val config = AuthorizationServiceConfiguration(authorizeUri, tokenUri)
        return AuthorizationRequest
            .Builder(config, CLIENT_ID, ResponseTypeValues.CODE, redirectUri)
            .setScopes("user repo admin")
            .build()
    }
}