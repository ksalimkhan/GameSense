//declare the package.
package com.example.gamesense

//Import animation functions for horizontal sliding effects

// Import animation functions for horizontal sliding effects.
import androidx.compose.animation.AnimatedVisibility            // Allows animating the visibility of a component.
import androidx.compose.animation.slideInHorizontally          // Provides a horizontal slide-in animation.
import androidx.compose.animation.slideOutHorizontally         // Provides a horizontal slide-out animation.

// Import foundation components for layout, styling, and interaction.
import androidx.compose.foundation.background                    // Allows setting background colors.
import androidx.compose.foundation.border                       // Draws borders around components.
import androidx.compose.foundation.clickable                    // Makes components respond to click events.
import androidx.compose.foundation.interaction.MutableInteractionSource  // Tracks interactions without showing a ripple.
import androidx.compose.foundation.layout.Box                   // A container that stacks its children.
import androidx.compose.foundation.layout.Column                // Arranges components vertically.
import androidx.compose.foundation.layout.fillMaxSize             // Modifier to fill all available space.
import androidx.compose.foundation.layout.fillMaxWidth            // Modifier to make a component span full horizontal width.
import androidx.compose.foundation.layout.height                 // Modifier to set a fixed height.
import androidx.compose.foundation.layout.padding                // Adds padding inside or around a component.
import androidx.compose.foundation.layout.Spacer                 // Inserts empty space between components.
import androidx.compose.foundation.layout.widthIn                // Constrains a component's width between min and max values.
import androidx.compose.foundation.layout.Arrangement            // Provides options to arrange children in rows or columns.
import androidx.compose.foundation.layout.size                    // Allows setting a fixed size.
import androidx.compose.foundation.layout.Arrangement.SpaceBetween  // Arranges children with space between them.
import androidx.compose.foundation.layout.Row                      // Sets up the row library


// Import shape for rounded corners.
import androidx.compose.foundation.shape.RoundedCornerShape       // Provides rounded shapes for clipping components.

// Import Material3 components for UI and theming.
import androidx.compose.material3.MaterialTheme                 // Provides theming styles.
import androidx.compose.material3.Text                          // Renders text on the screen.

// Import runtime state helpers.
import androidx.compose.runtime.Composable                     // Marks functions as composable.
import androidx.compose.runtime.mutableStateOf                 // Creates a mutable state variable.
import androidx.compose.runtime.remember                     // Remembers state between recompositions.
import androidx.compose.runtime.getValue                      // Supports property delegation for reading state.
import androidx.compose.runtime.setValue                      // Supports property delegation for setting state.

// Import UI utilities for modifiers, colors, and unit dimensions.
import androidx.compose.ui.Alignment                         // Provides alignment options.
import androidx.compose.ui.Modifier                          // Modifier for tweaking layout and appearance.
import androidx.compose.ui.draw.clip                         // Clips contents to a shape.
import androidx.compose.ui.graphics.Color                    // Provides pre-defined colors.
import androidx.compose.ui.unit.dp                           // Defines dimensions in density-independent pixels.

// Define a data class for leaderboard entries.
data class LeaderboardItem(
    val position: String,   // The ranking position, e.g., "1st".
    val name: String,       // The player's or team's name.
    val points: Int         // The number of points earned.
)

// Composable function for the leaderboard panel.
// 'visible' controls whether the panel is shown.
// 'backgroundColor' parameter lets you customize the background color (default is LightGray).
@Composable
fun LeaderboardPanel(visible: Boolean, backgroundColor: Color = Color.LightGray) {
    // Sample leaderboard data; in a real app, this might come from a ViewModel or network call.
    val leaderboardData = listOf(
        LeaderboardItem("1st", "ABC", 400),              // First ranked item.
        LeaderboardItem("2nd", "DEF", 300),              // Second ranked.
        LeaderboardItem("3rd", "GHI", 200),              // Third ranked.
        LeaderboardItem("4th", "JKL", 100)               // Fourth ranked.
    )

    // Animate the visibility of the leaderboard panel with a horizontal slide.
    AnimatedVisibility(
        visible = visible,                                // The panel is visible if 'visible' is true.
        enter = slideInHorizontally(initialOffsetX = { it }),   // Slide in from the right.
        exit = slideOutHorizontally(targetOffsetX = { it })       // Slide out to the right.
    ) {
        // A vertical Column that holds the panel content.
        Column(
            modifier = Modifier
                .widthIn(min = 180.dp, max = 250.dp)      // Constrain the width.
                // Use the provided background color with rounded corners.
                .background(backgroundColor, shape = RoundedCornerShape(6.dp))
                .padding(12.dp)                           // Add padding inside the panel.
        ) {
            // Header text for the leaderboard.
            Text(
                text = "Leaderboard",                   // Title text.
                style = MaterialTheme.typography.titleMedium  // Use the theme's title style.
            )
            Spacer(modifier = Modifier.height(8.dp))      // Add vertical space below the header.
            // Loop through each leaderboard entry.
            leaderboardData.forEach { item ->
                // Each leaderboard entry is displayed in a Box with a border.
                Box(
                    modifier = Modifier
                        .fillMaxWidth()                   // Box occupies full width.
                        .padding(vertical = 4.dp)           // Space out entries vertically.
                        .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp)) // Add a border around the entry.
                        .padding(8.dp)                      // Add internal padding inside the box.
                ) {
                    // Arrange the ranking/name and points in a row.
                    Row(
                        modifier = Modifier.fillMaxWidth(),         // Row fills the box width.
                        horizontalArrangement = Arrangement.SpaceBetween // Distribute children with space between.
                    ) {
                        // Concatenate and display the position and name.
                        Text("${item.position} ${item.name}")
                        // Display the points with a "PT" suffix.
                        Text("${item.points} PT")
                    }
                }
            }
        }
    }
}

// A higher-level composable that provides a toggle button for the leaderboard
// and an overlay to dismiss it when the user taps outside.
@Composable
fun LeaderboardScreen() {
    // Create a mutable state variable 'showLeaderboard' to track the panel's visibility.
    var showLeaderboard by remember { mutableStateOf(false) }

    // Use a Box to layer components on top of each other.
    Box(modifier = Modifier.fillMaxSize()) {
        // Create a toggle button for the leaderboard, placed in the top-right corner.
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)                   // Position the button at the top-right.
                .padding(16.dp)                            // Add padding from the edges.
                .height(56.dp)                             // Set its height to 56 dp (to match the search bar).
                .clip(RoundedCornerShape(4.dp))             // Clip the button with rounded corners.
                .background(MaterialTheme.colorScheme.primary) // Set the button background color from the theme.
                .clickable { showLeaderboard = !showLeaderboard } // Toggle the panel visibility when clicked.
                .padding(horizontal = 12.dp),               // Add horizontal padding for text spacing.
            contentAlignment = Alignment.Center             // Center the button content.
        ) {
            // Display text on the toggle button.
            Text(
                text = "Leaderboard",                     // Button label.
                color = Color.White,                        // White text for contrast.
                style = MaterialTheme.typography.bodyMedium // Use a medium body text style.
            )
        }

        // If the leaderboard panel is currently visible, display an invisible full-screen overlay.
        // Tapping on this overlay will dismiss the leaderboard.
        if (showLeaderboard) {
            Box(
                modifier = Modifier
                    .fillMaxSize()                         // Make the overlay cover the entire screen.
                    .clickable(                            // Make the overlay clickable.
                        onClick = { showLeaderboard = false }, // Dismiss the leaderboard when clicked.
                        indication = null,                  // Disable the default ripple effect.
                        interactionSource = remember { MutableInteractionSource() } // Use an interaction source.
                    )
            )
        }

        // Place the leaderboard panel in the top-right corner. This panel appears above the overlay.
        Box(modifier = Modifier.align(Alignment.TopEnd)) {
            // Call the leaderboard panel, passing in the current visibility state and (optionally) a background color.
            LeaderboardPanel(visible = showLeaderboard, backgroundColor = Color.LightGray)
        }
    }
}

