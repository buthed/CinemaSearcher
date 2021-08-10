package com.tematikhonov.cinemasearcher.view.details

import android.os.Build
import android.os.Handler
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import com.tematikhonov.cinemasearcher.model.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

class CinemaLoader(
        private val listener: CinemaLoaderListener,
        private val movie_id: Int
) {
    fun loadCinema() {
        val handler = Handler()
        Thread {
            try {
                val uri = URL("https://api.themoviedb.org/3/movie/${movie_id}?api_key=${TMDB_API_KEY_VALUE}")
                lateinit var urlConnection: HttpsURLConnection
                    urlConnection = uri.openConnection() as HttpsURLConnection
                    urlConnection.requestMethod = "GET"
                    urlConnection.addRequestProperty(
                            "api_key", "1cdc07942d44ead0f1079d449b6760a3"
                    )
                    urlConnection.readTimeout = 1000
                    val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                    val cinemaDTO: CinemaDTO = Gson().fromJson(bufferedReader, CinemaDTO::class.java)
                    handler.post(Runnable { listener.onLoaded(cinemaDTO) })
                } catch (e: Exception) {
                    handler.post(Runnable { listener.onFailed(e) })
                }
        }.start()
    }
}

interface CinemaLoaderListener {
    fun onLoaded(cinemaDTO: CinemaDTO)
    fun onFailed(throwable: Throwable)
}
