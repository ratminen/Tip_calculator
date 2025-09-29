package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.LastBaseline
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import java.time.format.TextStyle

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TipCalculatorTheme {
                Scaffold( modifier = Modifier.fillMaxSize() ) { innerPadding ->
                    MainScreen(modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val summa = remember{mutableStateOf("")}
    val dishes = remember{mutableStateOf(value = "")}
    Column {
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Сумма заказа:",
                modifier = Modifier.padding(end = 8.dp)
            )
            TextField(
                summa.value,
                {summa.value = it},
                textStyle = androidx.compose.ui.text.TextStyle(fontSize =  28.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }
        Row (
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(
                text ="Количество блюд:",
                modifier = Modifier
            )
            TextField(
                dishes.value,
                onValueChange = { input ->
                    if (input.length <= 3 && input.all { it.isDigit() }) {
                        dishes.value = input
                    }
                },
                textStyle = androidx.compose.ui.text.TextStyle(fontSize =  28.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
            .widthIn(max = 120.dp)
            .padding(start = 8.dp)
            )
        }
        Text(
            text ="Чаевые:",
            modifier = Modifier
        )
        Text(
            text ="Скидка:",
            modifier = Modifier,
            fontSize = 20.sp,
        )

    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    TipCalculatorTheme {
        MainScreen()
    }
}