package network.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking
import network.data.AccessTokenRepository.PreferencesKeys.ACCESS_TOKEN
import network.data.AccessTokenRepository.PreferencesKeys.LOGIN_STATUS

class AccessTokenRepository(private val dataStore: DataStore<Preferences>) {

    private object PreferencesKeys {
        val ACCESS_TOKEN: Preferences.Key<String> = stringPreferencesKey(name = "access_token")
        val LOGIN_STATUS: Preferences.Key<Boolean> = booleanPreferencesKey(name = "login_status")
    }

    fun getAccessToken(): String? {
        return runBlocking { dataStore.data.first()[ACCESS_TOKEN] }
    }

    suspend fun setAccessToken(token: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN] = token
        }
    }

    val getLoginStatus: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[LOGIN_STATUS] ?: false
    }

    suspend fun setLoginStatus(status: Boolean) {
        dataStore.edit { preferences -> preferences[LOGIN_STATUS] = status }
    }
}