package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
    var summa by remember { mutableStateOf("") }
    var dishes by remember { mutableStateOf("") }
    var sliderPosition by remember { mutableStateOf(0f) }
    var selectedDiscountPercent by remember { mutableStateOf(0) }

    LaunchedEffect (dishes) {
        val count = dishes.toIntOrNull() ?: 0
        selectedDiscountPercent = when {
            count in 1..2 -> 3
            count in 3..5 -> 5
            count in 6..10 -> 7
            count > 10 -> 10
            else -> 0
        }
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Сумма заказа:",
                modifier = Modifier.padding(end = 8.dp)
            )
            TextField(
                value = summa,
                onValueChange = { summa = it },
                textStyle = androidx.compose.ui.text.TextStyle(fontSize = 28.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )
            )
        }

        Row(
            verticalAlignment = Alignment.Bottom,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Количество блюд:",
                modifier = Modifier.padding(end = 8.dp)
            )
            TextField(
                value = dishes,
                onValueChange = { input ->
                    if (input.length <= 3 && input.all { it.isDigit() }) {
                        dishes = input
                    }
                },
                textStyle = androidx.compose.ui.text.TextStyle(fontSize = 28.sp),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = TextFieldDefaults.colors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
                modifier = Modifier
                    .widthIn(max = 120.dp)
            )
        }

        Column {
            Text(text = "Чаевые:")
            Slider(
                value = sliderPosition,
                valueRange = 0f..25f,
                steps = 24,
                onValueChange = { sliderPosition = it },
                modifier = Modifier.fillMaxWidth()
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "0", fontSize = 16.sp)
                Text(text = "25", fontSize = 16.sp)
            }
        }

        Row(
            modifier = Modifier.widthIn(max = 400.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            Text(
                text = "Скидка:",
                fontSize = 20.sp,
                modifier = Modifier.padding(top = 4.dp)
            )

            DiscountRadioButton(
                text = "3%",
                isSelected = selectedDiscountPercent == 3,
                enabled = false
            )
            DiscountRadioButton(
                text = "5%",
                isSelected = selectedDiscountPercent == 5,
                enabled = false
            )
            DiscountRadioButton(
                text = "7%",
                isSelected = selectedDiscountPercent == 7,
                enabled = false
            )
            DiscountRadioButton(
                text = "10%",
                isSelected = selectedDiscountPercent == 10,
                enabled = false
            )
        }
    }
}

@Composable
fun DiscountRadioButton(text: String, isSelected: Boolean, enabled: Boolean) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
        modifier = Modifier.width(IntrinsicSize.Min)
    ) {
        RadioButton(
            selected = isSelected,
            onClick = null,
            enabled = enabled
        )
        Text(
            text = text,
            modifier = Modifier.padding(top = 4.dp),
            fontSize = 16.sp
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