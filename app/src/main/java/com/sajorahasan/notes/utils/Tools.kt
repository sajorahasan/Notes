package com.sajorahasan.notes.utils

import android.app.Activity
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.ColorRes
import java.lang.ref.WeakReference

object Tools {

    fun Context.hasNetwork(): Boolean? {
        var isConnected: Boolean? = false // Initial Value
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }

    val Context.screenWidth: Int
        get() = resources.displayMetrics.widthPixels

    val Context.screenHeight: Int
        get() = resources.displayMetrics.heightPixels

    fun Context.setSatatusBarColor(context: WeakReference<Activity>, @ColorRes colorResId: Int) {

        if (Build.VERSION.SDK_INT >= 21) {
            val window = context.get()?.window
            window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window?.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window?.statusBarColor = context.get()!!.resources.getColor(colorResId)
        }

    }

    fun Context.showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    fun <T> Bundle.put(key: String, value: T) {
        when (value) {
            is Boolean -> putBoolean(key, value)
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Short -> putShort(key, value)
            is Long -> putLong(key, value)
            is Byte -> putByte(key, value)
            is ByteArray -> putByteArray(key, value)
            is Char -> putChar(key, value)
            is CharArray -> putCharArray(key, value)
            is CharSequence -> putCharSequence(key, value)
            is Float -> putFloat(key, value)
            else -> throw IllegalStateException("Type of property $key is not supported")
        }
    }
}