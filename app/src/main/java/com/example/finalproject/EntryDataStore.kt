package com.example.finalproject

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class EntryDataStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("entryToken")
        private val JOURNAL_ENTRY_KEY = stringPreferencesKey("journal_entry")
    }

    val getJournalEntry: Flow<String> = context.dataStore.data.map { preferences ->
        preferences[JOURNAL_ENTRY_KEY] ?: ""
    }

    suspend fun saveEntry(entry: String) {
        context.dataStore.edit { preferences ->
            preferences[JOURNAL_ENTRY_KEY] = entry
        }
    }
}