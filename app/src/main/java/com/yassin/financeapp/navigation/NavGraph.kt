//NavGraph = Definition aller Screens + Logik, wie man zwischen ihnen wechselt
package com.yassin.financeapp.navigation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.yassin.financeapp.ui.screens.KontenScreen
import com.yassin.financeapp.ui.screens.AboScreen
import com.yassin.financeapp.ui.screens.KatScreen
import com.yassin.financeapp.ui.screens.TransakScreen
import com.yassin.financeapp.ui.screens.ZieleScreen

sealed class Screen(val route: String) {
    object Konten : Screen("konten")
    object Abos : Screen("abo")
    object Kategorien : Screen("kategorien")
    object Transaktionen : Screen("transaktionen")
    object Sparziele : Screen("ziele")
}

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Kategorien.route,
        modifier = modifier
    ) {
        composable(Screen.Konten.route) { KontenScreen() }
        composable(Screen.Abos.route) { AboScreen() }
        composable(Screen.Kategorien.route) { KatScreen() }
        composable(Screen.Transaktionen.route) { TransakScreen() }
        composable(Screen.Sparziele.route) { ZieleScreen() }
    }
}


