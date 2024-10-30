package com.sobhandeep.updatify.domain.manager

import kotlinx.coroutines.flow.Flow

interface LocalManager {

    suspend fun saveAppEntry()

    fun readAppEntry(): Flow<Boolean>
}