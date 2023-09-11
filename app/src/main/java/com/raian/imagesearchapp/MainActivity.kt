package com.raian.imagesearchapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raian.imagesearchapp.ui.theme.ImageSearchAppTheme
import com.raian.imagesearchapp.ui.theme.components.MainContent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageSearchAppTheme {
                // A surface container using the 'background' color from the theme
                MyApp {
                    MainContent()
                }
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable ()-> Unit) {
    content()
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ImageSearchAppTheme {
        Greeting("Android")
    }
}