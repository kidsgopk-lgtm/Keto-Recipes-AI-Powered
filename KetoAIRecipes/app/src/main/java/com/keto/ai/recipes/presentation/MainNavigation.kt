package com.keto.ai.recipes.presentation

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Insights
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

sealed class Screen(val route: String, val label: String, val icon: ImageVector) {
    object Preferences : Screen("preferences", "Preferences", Icons.Filled.Settings)
    object CookingMode : Screen("cookingmode", "Cooking", Icons.Filled.Kitchen)
    object BatchCooking : Screen("batchcooking", "Batch", Icons.Filled.Restaurant)
    object SymptomLog : Screen("symptomlog", "Symptoms", Icons.Filled.Favorite)
    object KetoScience : Screen("ketoscience", "Keto Science", Icons.Filled.School)
    object ExpertQA : Screen("expertqa", "Expert Q&A", Icons.Filled.QuestionAnswer)
    object RestaurantGuide : Screen("restaurantguide", "Restaurants", Icons.Filled.Place)
    object MenuDecoder : Screen("menudecoder", "Menu Decoder", Icons.Filled.MenuBook)
    object FamilyManagement : Screen("familymanagement", "Family", Icons.Filled.Group)
    object OfflineMode : Screen("offlinemode", "Offline", Icons.Filled.CloudOff)
    object DataExport : Screen("dataexport", "Export", Icons.Filled.Backup)
    // Add icons for each section
    object Recipes : Screen("recipes", "Recipes", Icons.Filled.Home)
    object MealPlans : Screen("mealplans", "Meal Plans", Icons.Filled.CalendarToday)
    object Progress : Screen("progress", "Progress", Icons.Filled.TrendingUp)
    object Import : Screen("import", "Import", Icons.Filled.AddAPhoto)
    object Analytics : Screen("analytics", "Analytics", Icons.Filled.Insights)
    object Challenges : Screen("challenges", "Challenges", Icons.Filled.Flag)
    object Messaging : Screen("messaging", "Chat", Icons.Filled.Chat)
    object Profile : Screen("profile", "Profile", Icons.Filled.Person)
    object Forum : Screen("forum", "Forum", Icons.Filled.Forum)
    object Rating : Screen("rating", "Rate", Icons.Filled.Star)
}

@Composable
fun MainNavigation() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) {
        // Add NavHost and screens here
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        Screen.Recipes,
        Screen.MealPlans,
        Screen.Progress,
        Screen.Import,
        Screen.Analytics,
        Screen.Preferences,
        Screen.Profile,
        Screen.FamilyManagement,
        Screen.Challenges,
        Screen.Messaging,
        Screen.Forum,
        Screen.Rating,
        Screen.CookingMode,
        Screen.BatchCooking,
        Screen.SymptomLog,
        Screen.KetoScience,
        Screen.ExpertQA,
        Screen.RestaurantGuide,
        Screen.MenuDecoder,
        Screen.OfflineMode,
        Screen.DataExport
    )
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                onClick = { navController.navigate(screen.route) }
            )
        }
    }
}

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        androidx.navigation.compose.NavHost(
            navController = navController,
            startDestination = Screen.Recipes.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            androidx.navigation.compose.composable(Screen.Recipes.route) { /* RecipeListScreen() */ }
            androidx.navigation.compose.composable(Screen.MealPlans.route) { /* MealPlanCalendar() */ }
            androidx.navigation.compose.composable(Screen.Progress.route) { /* ProgressChart() */ }
            androidx.navigation.compose.composable(Screen.Import.route) { com.keto.ai.recipes.presentation.RecipeImportScreen(onRecipeImported = { /* TODO: handle imported recipe */ }) }
            androidx.navigation.compose.composable(Screen.Analytics.route) {
                val analyticsEngine = com.keto.ai.recipes.domain.AnalyticsEngine()
                val trends = analyticsEngine.getTrends(emptyList(), emptyList())
                val insights = analyticsEngine.getInsights(emptyList(), emptyList())
                val suggestions = analyticsEngine.getSuggestions(emptyList(), emptyList())
                Column(Modifier.padding(16.dp)) {
                    Text("Trends: $trends")
                    Text("Insights: $insights")
                    Text("Suggestions:")
                    suggestions.forEach { Text("- $it") }
                    com.keto.ai.recipes.presentation.AIAssistant(prompt = "Ask AI for personalized analytics, trends, or advice.")
                }
            }
            androidx.navigation.compose.composable(Screen.Preferences.route) { com.keto.ai.recipes.presentation.PreferencesScreen() }
            androidx.navigation.compose.composable(Screen.CookingMode.route) { com.keto.ai.recipes.presentation.CookingModeScreen(recipeId = "currentRecipeId") }
            androidx.navigation.compose.composable(Screen.BatchCooking.route) { com.keto.ai.recipes.presentation.BatchCookingScreen(recipeId = "currentRecipeId") }
            androidx.navigation.compose.composable(Screen.SymptomLog.route) { com.keto.ai.recipes.presentation.SymptomLogScreen() }
            androidx.navigation.compose.composable(Screen.KetoScience.route) { com.keto.ai.recipes.presentation.KetoScienceScreen() }
            androidx.navigation.compose.composable(Screen.ExpertQA.route) { com.keto.ai.recipes.presentation.ExpertQAScreen() }
            androidx.navigation.compose.composable(Screen.RestaurantGuide.route) { com.keto.ai.recipes.presentation.RestaurantGuideScreen() }
            androidx.navigation.compose.composable(Screen.MenuDecoder.route) { com.keto.ai.recipes.presentation.MenuDecoderScreen() }
            androidx.navigation.compose.composable(Screen.FamilyManagement.route) { com.keto.ai.recipes.presentation.FamilyManagementScreen() }
            androidx.navigation.compose.composable(Screen.OfflineMode.route) { com.keto.ai.recipes.presentation.OfflineModeScreen() }
            androidx.navigation.compose.composable(Screen.DataExport.route) { com.keto.ai.recipes.presentation.DataExportScreen() }
            androidx.navigation.compose.composable(Screen.Challenges.route) { com.keto.ai.recipes.presentation.ChallengeScreen() }
            androidx.navigation.compose.composable(Screen.Messaging.route) { com.keto.ai.recipes.presentation.MessagingScreen(userId = "currentUserId") }
            androidx.navigation.compose.composable(Screen.Profile.route) { com.keto.ai.recipes.presentation.ProfileScreen() }
            androidx.navigation.compose.composable(Screen.Forum.route) {
                val forumViewModel = androidx.lifecycle.viewmodel.compose.viewModel<com.keto.ai.recipes.domain.CommunityForumViewModel>()
                com.keto.ai.recipes.presentation.CommunityForumScreen(
                    posts = forumViewModel.posts.value.orEmpty(),
                    onPost = { forumViewModel.addPost(it) }
                )
            }
            androidx.navigation.compose.composable(Screen.Rating.route) { com.keto.ai.recipes.presentation.RecipeRatingScreen(recipeId = "currentRecipeId", userId = "currentUserId") }
        }
        }
    }
