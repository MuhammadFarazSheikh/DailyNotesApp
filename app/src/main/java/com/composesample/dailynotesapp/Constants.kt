package com.composesample.dailynotesapp.activities

import androidx.lifecycle.MutableLiveData
import com.composesample.dailynotesapp.domain.remote.models.NoteData

class Constants
{
    companion object
    {
        const val ADD_TO_DAILY_NOTES_ROUTE = "AddToDailyNotes"
        const val DAILY_NOTES_LIST_ROUTE = "DailyNotesList"
        val dailyNotesList = ArrayList<NoteData>()
        val userDailyNotesListLiveData = MutableLiveData<ArrayList<NoteData>>()
    }
}
