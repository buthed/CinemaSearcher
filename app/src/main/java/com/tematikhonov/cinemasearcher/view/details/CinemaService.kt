package com.tematikhonov.cinemasearcher.view.details

import android.app.IntentService
import android.content.Intent
import android.content.IntentFilter
import android.os.Handler
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import com.google.gson.Gson
import com.tematikhonov.cinemasearcher.model.CinemaDTO
import com.tematikhonov.cinemasearcher.model.TMDB_API_CINEMA_URL
import com.tematikhonov.cinemasearcher.model.TMDB_API_KEY_VALUE
import com.tematikhonov.cinemasearcher.view.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

const val DETAILS_INTENT_FILTER = "DETAILS INTENT FILTER"
const val DETAILS_LOAD_RESULT_EXTRA = "LOAD RESULT"
const val DETAILS_ID_EXTRA = "ID "
const val DETAILS_RESPONSE_SUCCESS_EXTRA = "SUCCESS"
class CinemaService(name: String = "name"):IntentService(name) {

    private val broadcastIntent = Intent(DETAILS_INTENT_FILTER)

    override fun onHandleIntent(intent: Intent?) {
        intent?.let{
            loadCinema(it.getIntExtra(DETAILS_ID_EXTRA,20))
        }
    }

    private fun loadCinema(id: Int) {
        //val handler = Handler()
        Thread {
            try {
                val uri = URL("$TMDB_API_CINEMA_URL${id}?api_key=$TMDB_API_KEY_VALUE")
                lateinit var urlConnection: HttpsURLConnection
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.addRequestProperty(
                        "api_key", "1cdc07942d44ead0f1079d449b6760a3"
                )
                urlConnection.readTimeout = 1000
                val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val cinemaDTO: CinemaDTO = Gson().fromJson(bufferedReader, CinemaDTO::class.java)
                val output = cinemaDTO
                putLoadResult(DETAILS_RESPONSE_SUCCESS_EXTRA)
                broadcastIntent.putExtra(DETAILS_ID_EXTRA, output.id)
                broadcastIntent.putExtra(DETAILS_TITLE_EXTRA, output.title)
                broadcastIntent.putExtra(DETAILS_RELEASE_DATE_EXTRA, output.release_date)
                broadcastIntent.putExtra(DETAILS_VOTE_AVERAGE_EXTRA, output.vote_average)
                broadcastIntent.putExtra(DETAILS_BUDGET_EXTRA, output.budget)
                broadcastIntent.putExtra(DETAILS_REVENUE_EXTRA, output.revenue)
                broadcastIntent.putExtra(DETAILS_POSTER_EXTRA, output.poster_path)
                broadcastIntent.putExtra(DETAILS_BACKDROP_EXTRA, output.backdrop_path)
                broadcastIntent.putExtra(DETAILS_OVERVIEW_EXTRA, output.overview)

                LocalBroadcastManager.getInstance(this).sendBroadcast(broadcastIntent)
            } catch (e: Exception) {
            }
        }.start()
    }

    fun putLoadResult(result: String) {
        broadcastIntent.putExtra(DETAILS_LOAD_RESULT_EXTRA, result)
    }
}