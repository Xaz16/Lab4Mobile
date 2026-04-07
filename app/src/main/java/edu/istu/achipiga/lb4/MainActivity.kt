package edu.istu.achipiga.lb4

import android.os.Bundle
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationBarView
import edu.istu.achipiga.lb4.data.RecommendationCategory
import edu.istu.achipiga.lb4.ui.category.CategoryRecommendationsFragment
import edu.istu.achipiga.lb4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val bottomNavListener = NavigationBarView.OnItemSelectedListener { item ->
        NavigationUI.onNavDestinationSelected(item, navController)
    }

    private val isWideScreen: Boolean
        get() = resources.configuration.smallestScreenWidthDp >= SMALLEST_WIDTH_TABLET_DP

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHost.navController

        val topLevelDestinations = setOf(
            R.id.homeFragment,
            R.id.citiesFragment,
            R.id.picksFragment,
            R.id.tipsFragment,
        )

        if (isWideScreen) {
            binding.drawerLayout.setDrawerLockMode(
                DrawerLayout.LOCK_MODE_LOCKED_OPEN,
                GravityCompat.START,
            )
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }

        appBarConfiguration = if (isWideScreen) {
            AppBarConfiguration(topLevelDestinations)
        } else {
            AppBarConfiguration(topLevelDestinations, binding.drawerLayout)
        }
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        binding.bottomNav.setOnItemSelectedListener(bottomNavListener)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            syncBottomNavCheckedItem(destination.id)
        }

        binding.navView.setNavigationItemSelectedListener { item: android.view.MenuItem ->
            when (item.itemId) {
                R.id.nav_drawer_coffee -> navigateToCategory(navController, RecommendationCategory.Coffee.id)
                R.id.nav_drawer_restaurants -> navigateToCategory(navController, RecommendationCategory.Restaurants.id)
                R.id.nav_drawer_kids -> navigateToCategory(navController, RecommendationCategory.Kids.id)
                R.id.nav_drawer_parks -> navigateToCategory(navController, RecommendationCategory.Parks.id)
                R.id.nav_drawer_malls -> navigateToCategory(navController, RecommendationCategory.Malls.id)
                R.id.aboutFragment -> navController.navigate(R.id.aboutFragment)
                R.id.settingsFragment -> navController.navigate(R.id.settingsFragment)
            }
            if (!isWideScreen) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
            true
        }

        if (!isWideScreen && savedInstanceState?.getBoolean(KEY_DRAWER_OPEN) == true) {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    fun openNavigationDrawer() {
        if (!isWideScreen) {
            binding.drawerLayout.openDrawer(GravityCompat.START)
        }
    }

    fun selectBottomNavTab(@IdRes destinationId: Int) {
        binding.bottomNav.selectedItemId = destinationId
    }

    private fun syncBottomNavCheckedItem(@IdRes destinationId: Int) {
        val tabMenuId: Int? = when (destinationId) {
            R.id.homeFragment -> R.id.homeFragment
            R.id.citiesFragment, R.id.cityDetailFragment -> R.id.citiesFragment
            R.id.picksFragment, R.id.pickDetailFragment -> R.id.picksFragment
            R.id.tipsFragment, R.id.tipDetailFragment -> R.id.tipsFragment
            R.id.recommendationDetailFragment -> when (navController.previousBackStackEntry?.destination?.id) {
                R.id.pickDetailFragment -> R.id.picksFragment
                else -> null
            }
            else -> null
        }
        if (tabMenuId == null) return

        binding.bottomNav.setOnItemSelectedListener(null)
        try {
            val menu = binding.bottomNav.menu
            for (i in 0 until menu.size()) {
                val item = menu.getItem(i)
                item.isChecked = item.itemId == tabMenuId
            }
            binding.bottomNav.selectedItemId = tabMenuId
        } finally {
            binding.bottomNav.setOnItemSelectedListener(bottomNavListener)
        }
    }

    private fun navigateToCategory(navController: NavController, categoryId: Int) {
        val args = Bundle().apply {
            putInt(CategoryRecommendationsFragment.ARG_CATEGORY_ID, categoryId)
        }
        navController.navigate(R.id.categoryRecommendationsFragment, args)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if (!isWideScreen) {
            outState.putBoolean(
                KEY_DRAWER_OPEN,
                binding.drawerLayout.isDrawerOpen(GravityCompat.START),
            )
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navHost =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        return navHost.navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    private companion object {
        const val KEY_DRAWER_OPEN = "drawer_open"
        const val SMALLEST_WIDTH_TABLET_DP = 600
    }
}
