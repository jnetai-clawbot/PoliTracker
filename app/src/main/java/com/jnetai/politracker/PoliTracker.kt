package com.jnetai.politracker

import android.app.Application
import com.jnetai.politracker.data.PoliTrackerDatabase

class PoliTracker : Application() {
    val database by lazy { PoliTrackerDatabase.getInstance(this) }
}