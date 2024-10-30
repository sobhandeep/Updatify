package com.sobhandeep.updatify.domain.instances.app_entry

import com.sobhandeep.updatify.domain.manager.LocalManager
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(

    private val localUserManager: LocalManager
) {

    operator fun invoke(): Flow<Boolean>{
        return localUserManager.readAppEntry()
    }
}