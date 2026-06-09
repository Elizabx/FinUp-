package com.finup.app.session

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("finup_prefs", Context.MODE_PRIVATE)

    fun saveUserId(userId: Int) {
        prefs.edit().putInt("user_id", userId).apply()
    }

    fun getUserId(): Int? {
        val id = prefs.getInt("user_id", -1)
        return if (id == -1) null else id
    }

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}