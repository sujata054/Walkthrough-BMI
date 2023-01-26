package com.example.bmi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bmi.ui.theme.BmiTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BmiTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                ) {
                    Bmi()
                }
            }
        }
    }
}

@Composable
fun Bmi() {
    var heightInput: String by remember { mutableStateOf("0") }
    var weightInput: String by remember { mutableStateOf("0") }
    val height = heightInput.toFloatOrNull() ?: 0.0f
    val weight = weightInput.toIntOrNull() ?: 0
    val bmi = if (weight > 0 && height >0) weight / (height * height) else 0.0
    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "Body mass index",
            fontSize = 24.sp,
            color = MaterialTheme.colors.primary,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth().padding(top = 16.dp, bottom = 16.dp)
        )
        OutlinedTextField(
            value = heightInput,
            onValueChange = {heightInput = it.replace(',','.')},
            label = { Text("Height")},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = weightInput,
            onValueChange = {weightInput = it.replace(',','.')},
            label = { Text("Weight")},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )
        Text(text = stringResource(R.string.result, String.format("%.2f",bmi).replace(',','.')))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BmiTheme {
        Bmi()
    }
}