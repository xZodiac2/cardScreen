package com.ilya.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            cardScreen("1234 2345 3456 4567", "ILYA ZHILENKOW" , "05/30" , "VISA", "123")
        }
    }
}

@Composable
fun card(content: @Composable ColumnScope.() -> Unit) {
    Card(
        elevation = CardDefaults.cardElevation(5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Black, contentColor = Color.White),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth()
            .height(200.dp),
        content = content
    )
}

@Composable
fun cardScreen(cardNumber: String, name: String, validityPeriod: String, producer: String, cvv: String) {
    var showFront by remember { mutableStateOf(true) }
    
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        bankCard(cardNumber, name, validityPeriod, producer, cvv, showFront)
        Button(
            colors = ButtonDefaults.buttonColors(containerColor = Color.Gray),
            onClick = {
                showFront = when (showFront) {
                    true -> false
                    else -> true
                }
            }) {
            Text(text = "Показать CVV")
        }
    }
}

@Composable
fun bankCard(cardNumber: String, name: String, validityPeriod: String, producer: String, cvv: String, showFront: Boolean) {
    if (showFront) {
        frontOfBankCard(cardNumber, name, validityPeriod, producer)
    } else {
        backOfBankCard(cvv)
    }
}

@Composable
fun backOfBankCard(cvv: String) {
    card {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
            
        ) {
            Text("CVV: $cvv", fontSize = 22.sp)
        }
    }
}

@Composable
fun frontOfBankCard(cardNumber: String, name: String, validityPeriod: String, producer: String) {
    card {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.5f)
                    .background(Color.Black)
            ) {
                Text(text = cardNumber, fontSize = 22.sp)
            }
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.BottomStart
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column {
                        Text(text = "Имя")
                        Text(text = name)
                    }
                    Column {
                        Text(text = "Действует до")
                        Text(text = validityPeriod)
                    }
                    Column {
                        Text(text = producer)
                    }
                }
            }
            
        }
    }
}



