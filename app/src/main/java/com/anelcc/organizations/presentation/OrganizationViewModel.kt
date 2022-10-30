package com.anelcc.organizations.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anelcc.organizations.data.RepositoryModel
import com.anelcc.organizations.domain.AuthorizationUserCase
import com.anelcc.organizations.domain.OrganizationReposUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import net.openid.appauth.AuthorizationRequest
import javax.inject.Inject

@HiltViewModel
class OrganizationViewModel @Inject constructor(
    private val orgUserCase: OrganizationReposUserCase,
    private val authorizationUserCase: AuthorizationUserCase,
) : ViewModel() {

    private val token = MutableLiveData<String>()
    val organizationList = MutableLiveData<List<RepositoryModel>>()
    var selectedRepository: LiveData<RepositoryModel> = MutableLiveData()

    fun fetchData(query: String) {
        viewModelScope.launch {
            val repos = orgUserCase.invoke(query)
            if (repos.size > 3) {
                val newList = repos.subList(0,3)
                organizationList.postValue(newList)
            } else {
                organizationList.postValue(repos)
            }
        }
    }

    fun getAuth(): AuthorizationRequest {
        return authorizationUserCase.invoke()
    }

    fun setToken(_token: String?) {
        _token?.let { token.postValue(it) }
    }

    fun setSelectedRepository(repository: RepositoryModel) {
        (selectedRepository as? MutableLiveData)?.value = repository
    }
}