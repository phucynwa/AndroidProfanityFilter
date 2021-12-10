package com.phucynwa.profanity.filter.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.OutlinedTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.phucynwa.profanity.filter.sample.theme.ProfanityFilterTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val profanityFilter = LocalProfanityFilter.current
            ProfanityFilterTheme { // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(Modifier.fillMaxSize()) {
                        val textState = remember {
                            mutableStateOf(TextFieldValue(""))
                        }
                        Text(text = "Hello")
                        OutlinedTextField(
                            value = textState.value,
                            onValueChange = { textState.value = it }
                        )
                        Text(text = profanityFilter.censor(textState.value.text))
                    }
                }
            }
        }
    }

    companion object {
        private const val PROFANITY_FILE_PATH = "bad_words_en_us.txt"
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProfanityFilterTheme {
        Greeting("Android")
    }
}
