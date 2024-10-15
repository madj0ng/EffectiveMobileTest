package ru.madj0ng.effectivemobiletest.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import org.koin.android.ext.android.getKoin
import ru.madj0ng.effectivemobiletest.R
import ru.madj0ng.effectivemobiletest.domain.favorite.CurrenFavoriteCount
import ru.madj0ng.effectivemobiletest.domain.favorite.FavoriteInteractor
import ru.madj0ng.effectivemobiletest.domain.models.Resource

class RootActivity : CurrenFavoriteCount, AppCompatActivity() {
    private val bottomNavigationView: BottomNavigationView by lazy { findViewById(R.id.bottomNavigationView) }

    private var notificationView: View? = null
    private var itemView: BottomNavigationItemView? = null

    private var favoriteCount: Int = 0

    @SuppressLint("RestrictedApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fcvMain) as NavHostFragment
        val navController = navHostFragment.navController

        bottomNavigationView?.setupWithNavController(navController)

        itemView = bottomNavigationView?.getChildAt(1) as? BottomNavigationItemView
        notificationView = LayoutInflater.from(this)
            .inflate(R.layout.favorite_notification_item, itemView, true)
        bottomNavigationView?.addView(notificationView)

        val favoriteInteractor: FavoriteInteractor = getKoin().get()
        lifecycleScope.launch {
            favoriteInteractor.getFavorites().collect {
                if (it is Resource.Success) setFavoriteCount(it.size)
            }
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment, R.id.confirmFragment, R.id.vacancyDetailFragment -> {
                    bottomNavigationView.isVisible = false
                }

                else -> bottomNavigationView.isVisible = true
            }
        }
    }

    override fun changeToggleFavorite(isFavorite: Boolean) {
        if (isFavorite) {
            setFavoriteCount(if (this.favoriteCount > 0) (this.favoriteCount - 1) else 0)
        } else {
            setFavoriteCount(this.favoriteCount + 1)
        }
    }

    override fun setFavoriteCount(count: Int) {
        this.favoriteCount = count
        val notificationText = notificationView?.findViewById<TextView>(R.id.tvNotificationItem)
        notificationText?.isVisible = count > 0
        if (count > 0) {
            notificationText?.text = count.toString()
        }
    }
}