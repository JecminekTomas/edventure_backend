package com.jecminek.edventure_backend.extensions

interface EntityToDtoExtension<T, U> {
    fun T.convertEntityToDto(): U
}