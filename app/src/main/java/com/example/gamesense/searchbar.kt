//Declare the package for the organizing the composable functionality
package com.example.gamesense

// Import animation functions for showing/hiding components with slide animations.
import androidx.compose.animation.AnimatedVisibility            // Enables animated showing/hiding of a component.
import androidx.compose.animation.slideInVertically              // Provides a vertical slide-in animation.
import androidx.compose.animation.slideOutVertically             // Provides a vertical slide-out animation.

// Import foundational layout and interactivity functions.
import androidx.compose.foundation.background                    // Allows setting background colors for components.
import androidx.compose.foundation.clickable                     // Makes components respond to click events.
import androidx.compose.foundation.layout.Column                  // Arranges children vertically in a column.
import androidx.compose.foundation.layout.fillMaxWidth            // Modifier to make a component fill maximum width.
import androidx.compose.foundation.layout.padding                 // Adds padding around or within a component.
import androidx.compose.foundation.layout.widthIn                 // Constrains the width of a component between specified min and max values.
import androidx.compose.foundation.layout.height                  // Sets a fixed height for a component.
import androidx.compose.foundation.shape.RoundedCornerShape       // Provides rounded corner shapes for clipping components.

// Import Material3 components for UI styling and text fields.
import androidx.compose.material3.MaterialTheme                  // Provides access to theming (colors, typography, etc.).
import androidx.compose.material3.OutlinedTextField              // A text input field with an outlined border.
import androidx.compose.material3.Text                           // Displays text on the screen.

// Import runtime state handling utilities.
import androidx.compose.runtime.Composable                       // Marks a function as a composable function.
import androidx.compose.runtime.mutableStateOf                   // Creates a mutable state variable.
import androidx.compose.runtime.remember                       // Remembers state across recompositions.
import androidx.compose.runtime.getValue                        // Enables using delegation to read state.
import androidx.compose.runtime.setValue                        // Enables using delegation to write state.

// Import UI utilities for modifiers, colors, and dimensions.
import androidx.compose.ui.Modifier                             // Modifier to modify the layout, appearance, or behavior of components.
import androidx.compose.ui.graphics.Color                        // Provides pre-defined colors.
import androidx.compose.ui.unit.dp                               // Defines dimensions using density-independent pixels.

@Composable
fun SearchBarWithDropDown() {                                 // This composable creates a search bar with a drop-down list.
    var query by remember { mutableStateOf("") }             // Declare a mutable state variable 'query' and initialize it with an empty string.

    // Create a list of clubs to search from.
    val clubs = listOf(
        "Barcelona (LaLiga)",                                // Club example.
        "Real Madrid (LaLiga)",
        "Bayern Munich (Bundesliga)",
        "Manchester City (Premier League)",
        "Liverpool (Premier League)",
        "PSG (Ligue 1)"
    )

    // Filter the list of clubs based on the text entered by the user.
    // Only return clubs that contain the query (case-insensitive) and if the query is not blank.
    val filteredClubs = clubs.filter {
        it.contains(query, ignoreCase = true) && query.isNotBlank()
    }

    // Arrange the search bar and drop-down list vertically.
    Column(
        modifier = Modifier
            .padding(16.dp)                                // Apply 16 dp padding around the column.
            .widthIn(min = 150.dp, max = 250.dp)              // Constrain the column's width between 150 dp and 250 dp.
    ) {
        // Display an outlined text field for search input.
        OutlinedTextField(
            value = query,                                  // Bind the value to the query state.
            onValueChange = { query = it },                   // Update the query state when the user types.
            label = { Text("Search club...") },               // Show a label inside the text field.
            modifier = Modifier
                .fillMaxWidth()                             // Make the text field fill the maximum width available.
                .height(56.dp)                              // Set the height to 56 dp (to match the leaderboard toggle).
        )

        // Animate the appearance of the drop-down list when there are filtered results.
        AnimatedVisibility(
            visible = filteredClubs.isNotEmpty(),           // The drop-down is visible only if there is at least one match.
            enter = slideInVertically(initialOffsetY = { -it }), // Slide in from above when appearing.
            exit = slideOutVertically(targetOffsetY = { -it })     // Slide out upward when disappearing.
        ) {
            // Display the filtered clubs in a drop-down column.
            Column(
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(4.dp)) // Set a white background with rounded corners.
                    .padding(8.dp)                        // Add 8 dp internal padding for spacing.
            ) {
                // For each filtered club, display it as clickable text.
                filteredClubs.forEach { club ->
                    Text(
                        text = club,                        // Display the club name.
                        style = MaterialTheme.typography.bodyMedium, // Apply a medium body text style from the theme.
                        modifier = Modifier
                            .fillMaxWidth()                 // Make the text fill the available width.
                            .clickable { query = club }      // When clicked, update the search query with this club name.
                            .padding(vertical = 4.dp)         // Add 4 dp vertical padding between each club.
                    )
                }
            }
        }
    }
}


