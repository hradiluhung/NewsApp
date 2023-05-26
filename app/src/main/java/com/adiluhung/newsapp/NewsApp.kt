package com.adiluhung.newsapp

import android.content.Intent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.adiluhung.newsApp.R
import com.adiluhung.newsapp.ui.navigation.NavigationItem
import com.adiluhung.newsapp.ui.navigation.Screen
import com.adiluhung.newsapp.ui.screen.about.AboutScreen
import com.adiluhung.newsapp.ui.screen.favorite.FavoriteScreen
import com.adiluhung.newsapp.ui.screen.home.HomeScreen
import com.adiluhung.newsapp.ui.theme.NewsTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewsApp(
    modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val context = LocalContext.current

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        when (currentRoute) {
                            Screen.Home.route -> stringResource(R.string.app_name)
                            Screen.Favorite.route -> stringResource(R.string.favorite)
                            else -> stringResource(R.string.about)
                        },
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
            )

        },
        bottomBar = {
            BottomBar(navController)
        }, modifier = modifier
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navigateToDetail = { news ->
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.NEWS_ID, news)
                    context.startActivity(intent)
                })
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(navigateToDetail = { news ->
                    val intent = Intent(context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.NEWS_ID, news)
                    context.startActivity(intent)
                })
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController, modifier: Modifier = Modifier
) {
    NavigationBar(modifier = modifier) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home,
                contentDescription = "home_page"
            ),
            NavigationItem(
                title = stringResource(R.string.menu_favorite),
                icon = Icons.Default.Favorite,
                screen = Screen.Favorite,
                contentDescription = "favorite_page"
            ),
            NavigationItem(
                title = stringResource(R.string.menu_about),
                icon = Icons.Default.AccountCircle,
                screen = Screen.About,
                contentDescription = "about_page"
            ),
        )

        navigationItems.forEachIndexed { _, item ->
            NavigationBarItem(icon = {
                Icon(
                    imageVector = item.icon,
                    contentDescription = item.contentDescription
                )
            },
                label = { Text(item.title) },
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun JetHeroesAppPreview() {
    NewsTheme {
        NewsApp()
    }
}