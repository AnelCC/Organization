package com.anelcc.organizations.domain

import com.anelcc.organizations.data.Repository
import com.anelcc.organizations.data.RepositoryModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


internal class OrganizationReposUserCaseTest {

    @RelaxedMockK
    private lateinit var repository: Repository

    lateinit var userCase: OrganizationReposUserCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        userCase = OrganizationReposUserCase(repository)
    }

    @Test
    fun shouldAllReposBeCalledWithAnEmptyList() = runBlocking {
        coEvery { repository.getAllRepos("anelcc") } returns emptyList()

        userCase.invoke("anelcc")

        coVerify {
            repository.getAllRepos("anelcc")
        }
    }


    @Test
    fun shouldReturnListFromRepositoryAndMatchTheSize() = runBlocking {
        val repositoryList = listOf(
            RepositoryModel(1, "Monster","AnelCC/Mosters", "Repo with Mvvm", 100, "public"),
            RepositoryModel(2, "Login","AnelCC/Login", "Repo with Compose", 80, "public"))

        coEvery { repository.getAllRepos("anelcc") } returns repositoryList

        val userCaseReposList = userCase.invoke("anelcc")

        coVerify(exactly = 1) { repository.getAllRepos("anelcc") }
        assert(userCaseReposList.size == repositoryList.size)
    }


    @Test
    fun shouldReturnTheCorrectIdWhenIsSortedByDescending() = runBlocking {
        val repositoryList = listOf(
            RepositoryModel(1, "Monster","AnelCC/Mosters", "Repo with Mvvm", 100, "public"),
            RepositoryModel(2, "Login","AnelCC/Login", "Repo with Compose", 80, "public"),
            RepositoryModel(3, "Login2","AnelCC/Login2", "Repo with Compose", 130, "public"))

        coEvery { repository.getAllRepos("anelcc") } returns repositoryList

        val userCaseReposList = userCase.invoke("anelcc")

        coVerify(exactly = 1) { repository.getAllRepos("anelcc") }

        assert(userCaseReposList.get(0).id == repositoryList[2].id)
        assert(userCaseReposList.get(1).id == repositoryList[0].id)
        assert(userCaseReposList.get(2).id == repositoryList[1].id)
    }
}