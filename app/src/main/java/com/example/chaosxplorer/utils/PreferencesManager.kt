package com.example.chaosxplorer.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PreferencesManager @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    fun getJwt() = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[JWT] ?: ""
        }

    suspend fun setJWT(jwt: String) {
        context.dataStore.edit { settings ->
            settings[JWT] = jwt
        }
    }

    fun getUser() = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[USERNAME] ?: ""
        }

    suspend fun setUser(user: String) {
        context.dataStore.edit { settings ->
            settings[USERNAME] = user
        }
    }

    fun getPassword() = context.dataStore.data
        .map { preferences ->
            // No type safety.
            preferences[PASSWORD] ?: ""
        }

    suspend fun setPassword(password: String) {
        context.dataStore.edit { settings ->
            settings[PASSWORD] = password
        }
    }

    companion object {
        val JWT = stringPreferencesKey("JWT")
        val USERNAME = stringPreferencesKey("USERNAME")
        val PASSWORD = stringPreferencesKey("PASSWORD")
    }
}