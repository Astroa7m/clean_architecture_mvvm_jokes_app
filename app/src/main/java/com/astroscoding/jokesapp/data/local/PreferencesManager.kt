package com.astroscoding.jokesapp.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class PreferencesManager (private val context: Context){

    private val Context.pref: DataStore<Preferences> by preferencesDataStore(
        name = "pref_settings"
    )

    val jokesPref = context.pref.data.map { preferences->
        val singleType = preferences[JOKE_SINGLE] ?: false
        val doubleType = preferences[JOKE_DOUBLE] ?: false
        Pair(doubleType, singleType)
    }

    suspend fun setJokesType(types: Pair<Boolean, Boolean>) {
        context.pref.edit { preferences->
            preferences[JOKE_SINGLE] = types.first
            preferences[JOKE_DOUBLE] = types.second
        }
    }

    companion object{
        private val JOKE_SINGLE = booleanPreferencesKey("single")
        private val JOKE_DOUBLE = booleanPreferencesKey("double")
    }

}