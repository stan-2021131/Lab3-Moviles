package com.example.lab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.remember
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lab3.ui.theme.Lab3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab3Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "",
                        modifier = Modifier.padding(innerPadding)
                    )
                    GreetingImage()
                    AgregarTarea()
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        modifier = modifier
    )
}

@Composable
fun AgregarTarea(){
    var elementos by remember { mutableStateOf(listOf("Hacer formulario de física", "Comprar pan")) }
    var tarea by remember { mutableStateOf("") }
    Row(
        modifier = Modifier.padding(vertical = 40.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = tarea,
            onValueChange = {tarea = it},
            singleLine = false,
            label = { Text(stringResource(R.string.agregarTarea)) },
            modifier = Modifier.width(270.dp).padding(15.dp)
        )
        Button(
            onClick = {
                if(tarea.isNotEmpty()){
                    elementos = elementos + tarea
                    tarea = ""
                }
            }
        ) {
            Text(stringResource(R.string.botonAgregar))
        }
    }

    ListaElementos(elementos)

}

@Composable
fun GreetingImage( modifier: Modifier = Modifier){
    val image = painterResource(R.drawable.fondopanda)
    Box(modifier){
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.6F,
            modifier = Modifier.fillMaxSize()
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab3Theme {
        GreetingImage()
        Greeting("Android")
    }
}



@Composable
fun ListaElementos(elementos: List<String>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(vertical = 120.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(elementos) { elemento ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Text(
                    text = elemento,
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}