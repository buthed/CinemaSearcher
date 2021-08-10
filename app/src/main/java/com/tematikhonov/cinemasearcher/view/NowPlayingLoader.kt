package com.tematikhonov.cinemasearcher.view

import android.os.Handler
import com.google.gson.Gson
import com.tematikhonov.cinemasearcher.model.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class NowPlayingLoader(
        private val listener: NowPlayingLoaderListener,
        private val result: List<CinemaDTO>
) {
    fun loadNowPlayingList() {
        val handler = Handler()
        Thread {
            try {
                val uri = URL("$TMDB_API_UPCOMING_URL$TMDB_API_KEY_VALUE")
                lateinit var urlConnection: HttpsURLConnection
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.addRequestProperty(
                        "api_key", "1cdc07942d44ead0f1079d449b6760a3"
                )
                urlConnection.readTimeout = 1000
                val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                val nowPlayingDTO: NowPlayingDTO = Gson().fromJson(bufferedReader, NowPlayingDTO::class.java)
                handler.post(Runnable { listener.onLoaded(nowPlayingDTO ) })
            } catch (e: Exception) {
                handler.post(Runnable { listener.onFailed(e) })
            }
        }.start()
    }

}

interface NowPlayingLoaderListener {
    fun onLoaded(nowPlayingDTO: NowPlayingDTO)
    fun onFailed(throwable: Throwable)
}
