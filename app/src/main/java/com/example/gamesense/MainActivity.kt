package com.example.gamesense

// Import Android OS and activity classes.
import android.os.Bundle                             // Bundle for saving instance state.
import androidx.activity.ComponentActivity          // Base class for activities using Compose.
import androidx.activity.compose.setContent         // Sets the Compose UI as the activity content.

// Import Compose layout utilities.
import androidx.compose.foundation.layout.Box         // A layout that stacks its children.
import androidx.compose.foundation.layout.fillMaxSize  // Modifier to fill the maximum available space.
import androidx.compose.ui.Alignment                   // Provides alignment constants.

// Import Material3 for theming.
import androidx.compose.material3.MaterialTheme        // Provides Material theme for styling.
import androidx.compose.runtime.Composable            // Marks a function as a composable function.

class MainActivity : ComponentActivity() {          // MainActivity, the app's entry point.
    override fun onCreate(savedInstanceState: Bundle?) { // onCreate is called when the activity is created.
        super.onCreate(savedInstanceState)           // Call the superclass implementation.
        setContent {                                 // Set the Compose content for this activity.
            MaterialTheme {                         // Wrap content in a MaterialTheme to apply consistent styling.
                CombinedScreen()                    // Call the combined screen composable.
            }
        }
    }
}

// A composable that combines the search bar (top-left) and the leaderboard (top-right) on one screen.
@Composable
fun CombinedScreen() {
    // Use a Box to overlay multiple components, allowing us to position them in different corners.
    Box(modifier = androidx.compose.ui.Modifier.fillMaxSize()) {

        // Place the leaderboard screen in the top-right corner.
        Box(modifier = androidx.compose.ui.Modifier.align(Alignment.TopEnd)) {
            LeaderboardScreen()                      // Call the leaderboard screen composable from Leaderboard.kt.
        }
    }
}