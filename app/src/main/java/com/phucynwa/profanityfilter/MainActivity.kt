package com.phucynwa.profanityfilter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.phucynwa.profanityfilter.profanity.Dictionary
import com.phucynwa.profanityfilter.profanity.IProfanityFilter
import com.phucynwa.profanityfilter.profanity.PlainDictionary
import com.phucynwa.profanityfilter.profanity.ProfanityFilter
import com.phucynwa.profanityfilter.ui.theme.ProfanityFilterTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
                        BasicTextField(
                            value = textState.value,
                            onValueChange = {
                                textState.value = it
                            }
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
