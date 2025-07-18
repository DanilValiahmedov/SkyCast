package com.valimade.skycast.permission.domain.model

sealed class PermissionResult {
    object Success : PermissionResult()
    object Failure : PermissionResult()
}