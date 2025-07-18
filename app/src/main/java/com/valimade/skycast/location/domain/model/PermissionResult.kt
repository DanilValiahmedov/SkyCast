package com.valimade.skycast.location.domain.model

sealed class PermissionResult {
    object Success : PermissionResult()
    object Failure : PermissionResult()
}