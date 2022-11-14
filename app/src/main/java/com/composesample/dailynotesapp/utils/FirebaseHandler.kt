package com.composesample.dailynotesapp.utils

import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.MutableState
import com.composesample.dailynotesapp.AppClass
import com.composesample.dailynotesapp.AppClass.Companion.appContext
import com.composesample.dailynotesapp.R
import com.composesample.dailynotesapp.activities.DashboardScreen
import com.composesample.dailynotesapp.activities.utils.PreferenceDataStore
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun verifyUserLoginWithFirebase(
    mutableStateLoader: MutableState<Boolean>,
    mutableStateCallFirebase: MutableState<Boolean>,
    coroutineScope: CoroutineScope,
    firebaseUserDocumentPath:String
)
{
    Firebase
        .firestore
        .collection(Keys.FIREBASE_USERS_COLLECTION)
        .document(firebaseUserDocumentPath)
        .get()
        .addOnCompleteListener {
            mutableStateCallFirebase.value = false
        }.addOnCanceledListener {
            mutableStateCallFirebase.value = false
        }.addOnFailureListener {
            mutableStateCallFirebase.value = false
        }.addOnSuccessListener { documentSnapshot->
            mutableStateLoader.value = false
            mutableStateCallFirebase.value = false

            if(documentSnapshot.exists())
            {
                openDashboard(coroutineScope,documentSnapshot)
            }
            else
            {
                mutableStateLoader.value = false
                mutableStateCallFirebase.value = false
                Toast.makeText(appContext, appContext.getString(R.string.text_user_not_exist),Toast.LENGTH_LONG).show()
            }
        }
}

fun openDashboard(
    coroutineScope: CoroutineScope,
    documentSnapshot:DocumentSnapshot
)
{
    coroutineScope.launch {
        PreferenceDataStore.setIsUserLoggedIn(true, AppClass.appContext)
        PreferenceDataStore.saveUserData(
            Gson().toJson(documentSnapshot.data),
            AppClass.appContext
        )
        AppClass.appContext.startActivity(
            Intent(
                AppClass.appContext,
                DashboardScreen::class.java
            ).apply {
                flags =
                    Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            })
    }
}

fun registerUserWithFirebase(
    mutableStateLoader: MutableState<Boolean>,
    mutableStateCallFirebase: MutableState<Boolean>,
    coroutineScope: CoroutineScope,
    fullName:String,
    email:String,
    password:String
)
{
    Firebase
        .firestore
        .collection(Keys.FIREBASE_USERS_COLLECTION)
        .document(email+"-"+password).set(
            hashMapOf(
                "Email" to email,
                "Password" to password,
                "FullName" to fullName
            )
        )
        .addOnCanceledListener {
            mutableStateCallFirebase.value = false
        }.addOnSuccessListener {
            verifyUserLoginWithFirebase(mutableStateLoader,mutableStateCallFirebase,coroutineScope,email+"-"+password)
        }.addOnCompleteListener {
            mutableStateCallFirebase.value = false
        }.addOnFailureListener {
            mutableStateCallFirebase.value = false
        }
}