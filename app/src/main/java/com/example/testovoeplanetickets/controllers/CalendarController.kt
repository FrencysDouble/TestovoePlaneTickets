package com.example.testovoeplanetickets.controllers

import android.content.Context
import android.content.Intent
import android.provider.CalendarContract


class CalendarController(private val context: Context) {

    fun openCalendar() {
        val intent = Intent(Intent.ACTION_INSERT).apply {
            data = CalendarContract.Events.CONTENT_URI
            putExtra(CalendarContract.Events.TITLE, "My Event")
            putExtra(CalendarContract.Events.EVENT_LOCATION, "Event Location")
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, System.currentTimeMillis())
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, System.currentTimeMillis() + 60 * 60 * 1000)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        context.startActivity(intent)
    }
}