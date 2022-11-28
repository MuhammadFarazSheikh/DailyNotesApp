package com.composesample.dailynotesapp.utils

import android.content.Intent
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.lifecycle.MutableLiveData
import com.composesample.dailynotesapp.AppClass
import com.composesample.dailynotesapp.AppClass.Companion.appContext
import com.composesample.dailynotesapp.R
import com.composesample.dailynotesapp.activities.Constants.Companion.dailyNotesList
import com.composesample.dailynotesapp.activities.Constants.Companion.userDailyNotesListLiveData
import com.composesample.dailynotesapp.activities.DashboardScreen
import com.composesample.dailynotesapp.activities.utils.PreferenceDataStore
import com.composesample.dailynotesapp.domain.repository.models.UserData
import com.composesample.dailynotesapp.utils.Keys.Companion.FIREBASE_DAILY_NOTES_COLLECTION
import com.composesample.dailynotesapp.utils.Keys.Companion.FIREBASE_DAILY_NOTES_LIST
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

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
            documentSnapshot?.data?.get("FullName").toString(),
            documentSnapshot?.data?.get("Email").toString(),
            documentSnapshot?.data?.get("Password").toString(),
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

fun addNoteToFirebase(
    mutableStateLoader: MutableState<Boolean>,
    mutableStateCallFirebase: MutableState<Boolean>,
    coroutineScope: CoroutineScope,
    noteText: String
)
{
    coroutineScope.launch {
        PreferenceDataStore.getUserData(appContext).collectLatest { userData ->
            userData?.email?.let {
                dailyNotesList.add(noteText)
                Firebase
                    .firestore
                    .collection(FIREBASE_DAILY_NOTES_COLLECTION)
                    .document(userData.email).set(
                        hashMapOf(
                            FIREBASE_DAILY_NOTES_LIST to dailyNotesList
                        )
                    ).addOnCanceledListener {
                        mutableStateLoader.value = false
                        mutableStateCallFirebase.value = false
                    }.addOnSuccessListener {
                        mutableStateLoader.value = false
                        mutableStateCallFirebase.value = false
                    }.addOnFailureListener {
                        mutableStateLoader.value = false
                        mutableStateCallFirebase.value = false
                    }.addOnCompleteListener {
                        mutableStateLoader.value = false
                        mutableStateCallFirebase.value = false
                    }
            }
        }
    }
}

fun getUserDailyNotes(
    coroutineScope: CoroutineScope,
    mutableStateLoader: MutableState<Boolean>
)
{
    coroutineScope.launch {
        PreferenceDataStore.getUserData(appContext).collectLatest {userData->
            userData?.email?.let {
                Firebase
                    .firestore
                    .collection(FIREBASE_DAILY_NOTES_COLLECTION)
                    .document(userData.email)
                    .get()
                    .addOnSuccessListener { documentSnapshot ->
                        mutableStateLoader.value = false
                        userDailyNotesListLiveData.value= documentSnapshot.get(FIREBASE_DAILY_NOTES_LIST) as ArrayList<String>?
                        userDailyNotesListLiveData?.value?.let {
                            dailyNotesList.clear()
                            dailyNotesList.addAll(it)
                        }
                    }.addOnCanceledListener {
                        mutableStateLoader.value = false
                    }.addOnCompleteListener {
                        mutableStateLoader.value = false
                    }.addOnFailureListener {
                        mutableStateLoader.value = false
                    }
            }
        }
    }
}

fun deleteDailyNote(
    coroutineScope: CoroutineScope,
    mutableStateLoader: MutableState<Boolean>,
    noteListIndex:Int
)
{
    coroutineScope.launch {
        PreferenceDataStore.getUserData(appContext).collectLatest { userData ->
            userData?.email?.let { email ->
                dailyNotesList.removeAt(noteListIndex)
                Firebase
                    .firestore
                    .collection(FIREBASE_DAILY_NOTES_COLLECTION)
                    .document(email).set(
                        hashMapOf(
                            FIREBASE_DAILY_NOTES_LIST to dailyNotesList
                        )
                    ).addOnCanceledListener {
                        mutableStateLoader.value = false
                    }.addOnSuccessListener {
                        mutableStateLoader.value = false
                        userDailyNotesListLiveData.value = dailyNotesList
                    }.addOnFailureListener {
                        mutableStateLoader.value = false
                    }.addOnCompleteListener {
                        mutableStateLoader.value = false
                    }
            }
        }
    }
}