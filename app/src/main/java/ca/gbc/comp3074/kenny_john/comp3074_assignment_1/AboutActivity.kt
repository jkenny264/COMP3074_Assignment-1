package ca.gbc.comp3074.kenny_john.comp3074_assignment_1

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import ca.gbc.comp3074.kenny_john.comp3074_assignment_1.ui.theme.COMP3074_Assignment1Theme

class AboutActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            COMP3074_Assignment1Theme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text("About") },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Color.LightGray
                            ),
                            actions = {
                                Button(onClick = {
                                    finish()
                                }) {
                                    Text("Back")
                                }
                            }
                        )
                    }
                ) { innerPadding ->
                    About(
                        modifier = Modifier
                            .padding(innerPadding)

                    )
                }
            }
        }
    }
}



@Composable
fun About(modifier: Modifier) {

    Column(
        modifier,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(
            text = "Name: John Kenny",
            modifier = modifier

        )
        Text(
            text = "Student ID: 101577733",
            modifier = modifier
        )

    }

}




@Preview(showBackground = true)
@Composable
fun AboutPreview() {
    COMP3074_Assignment1Theme {
        About(modifier = Modifier.padding())
    }
}