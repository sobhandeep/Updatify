package com.sobhandeep.updatify.domain.instances.app_entry

import com.sobhandeep.updatify.domain.manager.LocalManager

class SaveAppEntry(

    private val localUserManager: LocalManager
) {

    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}