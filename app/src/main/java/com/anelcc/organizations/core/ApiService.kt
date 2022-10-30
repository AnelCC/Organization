package com.anelcc.organizations.core

import com.anelcc.organizations.data.RepositoryModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("users/{user}/repos?per_page=100")
    fun getReposByUser(@Path("user") user: String?): Call<List<RepositoryModel>?>?

    @GET("users/{org}/repos?per_page=100")
    fun getRepoList(@Path("org") user: String?): Call<List<RepositoryModel>?>?
}