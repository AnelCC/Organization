package com.anelcc.organizations.data

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class RepositoryModel(
    @SerializedName("id")
    @Expose
    var id: Int? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("full_name")
    @Expose
    var fullName: String? = null,

    @SerializedName("description")
    @Expose
    var description: String? = null,

    @SerializedName("stargazers_count")
    @Expose
    var stargazersCount: Int? = null,

    @SerializedName("visibility")
    @Expose
    var visibility: String? = null
) {

    override fun toString(): String {
        return "Repo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}'
    }
}