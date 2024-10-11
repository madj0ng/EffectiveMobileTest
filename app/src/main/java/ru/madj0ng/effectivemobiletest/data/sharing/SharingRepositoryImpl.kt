package ru.madj0ng.effectivemobiletest.data.sharing

import android.content.Context
import android.content.Intent
import android.net.Uri
import ru.madj0ng.effectivemobiletest.domain.sharing.SharingRepository

class SharingRepositoryImpl(
    private val context: Context,
    private val intent: Intent
) : SharingRepository {
    override fun openLink(urlString: String) {
        val url = Uri.parse(urlString)
        intent.setAction(Intent.ACTION_VIEW)
        intent.setData(url)
        startIntent(intent)
    }

    private fun startIntent(intent: Intent) {
        try {
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            context.startActivity(intent)
        } catch (err: Exception) {
            val error = ""
        }
    }
}