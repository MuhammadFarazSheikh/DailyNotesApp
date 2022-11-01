package com.composesample.dailynotesapp.activities.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.composesample.dailynotesapp.activities.Constants.Companion.IS_USER_LOGGED_IN
import com.composesample.dailynotesapp.activities.Constants.Companion.PREFERENCE_DATA_STORE_NAME
import kotlinx.coroutines.flow.map


object PreferenceDataStore
{
    private val Context.dataStore:DataStore<Preferences> by preferencesDataStore(PREFERENCE_DATA_STORE_NAME)
    private val isUserLoggedIn = booleanPreferencesKey(IS_USER_LOGGED_IN)

    /*SET USER SESSION STATE AS BOOLEAN*/
    suspend fun setIsUserLoggedIn(type:Boolean,context:Context)
    {
        context.dataStore.edit { preferencesDataStore->
            preferencesDataStore[isUserLoggedIn] = type
        }
    }

    /*CHECK USER SESSION IF IT IS LOGGEDIN*/
    fun checkIsUserLoggedIn(context: Context)=context.dataStore.data.map { data->
        data[isUserLoggedIn]?:false
    }
}