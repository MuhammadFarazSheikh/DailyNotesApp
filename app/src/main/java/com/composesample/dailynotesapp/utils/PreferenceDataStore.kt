package com.composesample.dailynotesapp.activities.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.composesample.dailynotesapp.domain.repository.models.UserData
import com.composesample.dailynotesapp.utils.Keys.Companion.IS_USER_LOGGED_IN
import com.composesample.dailynotesapp.utils.Keys.Companion.PREFERENCE_DATA_STORE_NAME
import com.composesample.dailynotesapp.utils.Keys.Companion.USER_EMAIL
import com.composesample.dailynotesapp.utils.Keys.Companion.USER_FULL_NAME
import com.composesample.dailynotesapp.utils.Keys.Companion.USER_PASSWORD
import com.google.gson.Gson
import kotlinx.coroutines.flow.map

object PreferenceDataStore
{
    private val Context.dataStore:DataStore<Preferences> by preferencesDataStore(PREFERENCE_DATA_STORE_NAME)
    private val isUserLoggedIn = booleanPreferencesKey(IS_USER_LOGGED_IN)
    private val userEmail = stringPreferencesKey(USER_EMAIL)
    private val userPassword = stringPreferencesKey(USER_PASSWORD)
    private val userFullName = stringPreferencesKey(USER_FULL_NAME)

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
    suspend fun saveUserData(name:String,email:String,password:String,context:Context)
    {
        context.dataStore.edit { preferencesDataStore->
            preferencesDataStore[userFullName] = name
            preferencesDataStore[userEmail] = email
            preferencesDataStore[userPassword] = password
        }
    }

    /*GET USER DATA*/
    fun getUserData(context: Context)=context.dataStore.data.map { data->
        UserData(data[userFullName]!!,data[userEmail]!!,data[userPassword]!!)
    }
}