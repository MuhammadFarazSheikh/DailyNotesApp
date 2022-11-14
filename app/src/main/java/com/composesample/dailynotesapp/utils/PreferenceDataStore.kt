package com.composesample.dailynotesapp.activities.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.composesample.dailynotesapp.models.UserData
import com.composesample.dailynotesapp.utils.Keys.Companion.IS_USER_LOGGED_IN
import com.composesample.dailynotesapp.utils.Keys.Companion.PREFERENCE_DATA_STORE_NAME
import com.composesample.dailynotesapp.utils.Keys.Companion.USER_DATA
import com.google.gson.Gson
import kotlinx.coroutines.flow.map


object PreferenceDataStore
{
    private val Context.dataStore:DataStore<Preferences> by preferencesDataStore(PREFERENCE_DATA_STORE_NAME)
    private val isUserLoggedIn = booleanPreferencesKey(IS_USER_LOGGED_IN)
    private val userData = stringPreferencesKey(USER_DATA)

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

    /*SAVE USER DATA AFTER LOGIN*/
    suspend fun saveUserData(userDataLoggedIn:String,context:Context)
    {
        context.dataStore.edit { preferencesDataStore->
            preferencesDataStore[userData] = userDataLoggedIn
        }
    }

    /*GET USER DATA*/
    fun getUserData(context: Context)=context.dataStore.data.map { data->
        Gson().fromJson(data[userData],UserData::class.java)?: UserData("","","")
    }
}