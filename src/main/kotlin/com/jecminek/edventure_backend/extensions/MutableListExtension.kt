package com.jecminek.edventure_backend.extensions

interface EntityMutableListToDtoMutableListConverter <T, U>{
    fun convertMutableLists(list: MutableList<T>): MutableList<U>
}
