package com.example.jetpack_compose_prac

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_prac.ui.theme.JetpackcomposepracTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MyScreenContent()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit){
    JetpackcomposepracTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            //modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}

@Composable
fun MyScreenContent(names: List<String> = List(1000) {"Hello Android $it"}){
    var counterState by remember {
        mutableStateOf(0)
    }
    Column(
        modifier = Modifier.fillMaxHeight()
    ) {
        NamesList(names = names, modifier = Modifier.weight(1f))
        Counter(
            count = counterState,
            updateCount = {
                newCount -> counterState = newCount
            }
        )
        if (counterState > 5){
            Text(text = "I love to count!")
        }
    }
}

@Composable
fun Counter(count: Int, updateCount: (Int) -> Unit){
    Button(onClick = { updateCount(count + 1) }) {
        Text(text = "I've been clicked $count times")
    }
}


/*
@Composable
fun Counter(){
    var counter by remember {
        mutableStateOf(0)
    }
    Button(onClick = { counter++ }) {
            Text(text = "I've been clicked $counter times")
    }
}

 */

@Composable
fun NamesList(names: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
    ){
        items(items = names){
            Greeting(name = it)
            Divider()
        }
    }
}

@Composable
fun Greeting(name: String) {
    var isSelected by remember {
        mutableStateOf(false)
    }
    val targetColor by animateColorAsState(
        targetValue = if(isSelected) MaterialTheme.colors.primary else Color.Transparent,
        animationSpec = tween(durationMillis=4000)
    )
    Surface(color = targetColor){
        Text(
            text = "Hello $name!",
            modifier = Modifier
                .clickable {
                    isSelected = !isSelected
                }
                .padding(16.dp)
        )
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MyApp {
        MyScreenContent()
    }
}