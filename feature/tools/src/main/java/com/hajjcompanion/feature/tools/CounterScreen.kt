package com.hajjcompanion.feature.tools

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember

@Composable
fun CounterScreen() {
    val count = remember { mutableIntStateOf(0) }
    Button(onClick = { count.intValue++ }) {
        Text("Count: ${count.intValue}")
    }
}
