package com.codex.evntr.Event

import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.beust.klaxon.Klaxon
    import com.codex.evntr.API.ApiFullResponse
import com.codex.evntr.API.Event
import com.codex.evntr.API.EventGoing
import com.codex.evntr.API_URL
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.codex.evntr.database.EventDAO


class Repository {
    lateinit var eventDao: EventDAO


    fun getAllEvents(queue: RequestQueue, callback: (List<Event>?) ->  Unit) {
        val stringRequest = StringRequest(
            Request.Method.GET,
            API_URL,
            { response ->
                val fetched = Klaxon().parse<ApiFullResponse?>(response)
                if (fetched != null) {
                    callback(fetched.result.event)
                    saveToDB(fetched.result.event)
                }

            },
            { error ->
                callback(null)
            }
        )

        queue.add(stringRequest)
    }

    fun getEventByID(queue: RequestQueue, wantedEventID: String, callback: (List<Event>?) ->  Unit) {
        val stringRequest = StringRequest(
            Request.Method.GET,
            API_URL,
            { response ->
                val fetched = Klaxon().parse<ApiFullResponse?>(response)
                if (fetched != null) {
                    callback(fetched.result.event)
                }

            },
            { error ->
                callback(null)
            }
        )

        queue.add(stringRequest)
    }

    fun getGoingbyID(id: String, callback: (Event) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
           var event = eventDao.getEvent(id)
            callback(event)
        }
            .start()
    }



    fun updateDB(result: List<Event>) {
        CoroutineScope(Dispatchers.IO).launch {
            for (event in result) {
                eventDao.updateEvents(event)
            }

        }.start()
    }

    fun saveToDB(result: List<Event>) {
        CoroutineScope(Dispatchers.IO).launch {
                for (event in result) {
                    eventDao.addEvent(event)
                }

        }.start()
    }

    fun goingToDB(eventGoing: EventGoing) {
        CoroutineScope(Dispatchers.IO).launch {
            eventDao.going(eventGoing)
        }
        .start()
    }

    fun getGoing(callback: (List<EventGoing>) -> Unit) {
        CoroutineScope(Dispatchers.IO).launch {
            var events = eventDao.getGoingEvents()
            callback(events)
        }
            .start()
    }
}