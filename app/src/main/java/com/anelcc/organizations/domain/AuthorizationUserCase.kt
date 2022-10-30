package com.anelcc.organizations.domain

import com.anelcc.organizations.data.Repository
import javax.inject.Inject

class AuthorizationUserCase @Inject constructor(private val repository: Repository) {
    operator fun invoke() = repository.getAuthRequest()
}