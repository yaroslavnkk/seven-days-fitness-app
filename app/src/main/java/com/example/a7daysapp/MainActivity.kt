package com.example.a7daysapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a7daysapp.data.Day
import com.example.a7daysapp.data.ListExercises.days
import com.example.a7daysapp.ui.theme._7DaysAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            _7DaysAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.secondary
                ) {
                    SevenDaysApp()
                }
            }
        }
    }
}
@Composable
fun SevenDaysApp(){
    Scaffold(
        topBar = { TopBarApp() }
    ) {it ->
        LazyColumn(contentPadding = it) {
            items(days) {
                CardLayout(
                    day = it,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CardLayout(
    day: Day,
    modifier: Modifier = Modifier){
    var isClicked by remember {
        mutableStateOf(false)
    }
    Card(
        onClick = { isClicked = !isClicked },
        modifier = modifier
            .clip(MaterialTheme.shapes.medium)
            .padding(16.dp)) {
        Column (
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioLowBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ){
            DayInfo(day = day.day, dayActivity = day.activity)
            Spacer(modifier = Modifier.height(16.dp))
            DayImage(image = day.image)
            if (isClicked) {
                DayDescription(description = day.description)
            }
        }
    }
}

@Composable
fun DayInfo(
    day : String,
    @StringRes dayActivity : Int,
    modifier: Modifier = Modifier
){
    Column(
        horizontalAlignment = Alignment.Start,
        modifier = modifier
            .padding(start = 8.dp)
    ) {
        Text(
            text = day,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = stringResource(dayActivity),
            fontSize = 24.sp,
            fontFamily = FontFamily.Default
        )
    }
}

@Composable
fun DayImage(
    @DrawableRes image : Int,
    modifier: Modifier = Modifier
){
    Image(
        painter = painterResource(image),
        contentDescription = null,
        modifier = modifier
            .padding(start = 8.dp, end = 8.dp)
            .size(350.dp),
        contentScale = ContentScale.Crop
    )
}

@Composable
fun DayDescription(
    @StringRes description : Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(description),
            style = MaterialTheme.typography.bodyLarge
            )
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarApp(modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.app_name),
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.secondary
                )
            }
        },
        modifier = modifier.height(84.dp)
    )
}

@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun AppPreview(){
    _7DaysAppTheme {
        SevenDaysApp()
    }
}