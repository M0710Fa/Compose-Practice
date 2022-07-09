package com.example.compose_practice

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_practice.ui.theme.ComposePracticeTheme
import com.example.compose_practice.ui.theme.Message
import com.example.compose_practice.ui.theme.SampleData

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePracticeTheme() {
                Conversation(messages = SampleData.conversationSample)
            }
        }
    }
}

@Composable
fun Conversation(messages: List<Message>){
    LazyColumn{
        items(messages){ message ->
           MessageCard(message = message)
        }
    }
}

@Composable
fun MessageCard(message: Message){
    ComposePracticeTheme() {
        Row(modifier = Modifier.padding(all = 8.dp)) {
            OverLapImage(
                foreId = R.drawable.ic_launcher_foreground,
                backId = R.drawable.ic_launcher_background
            )
            Spacer(modifier = Modifier.width(8.dp))

            var isExpanded by remember { mutableStateOf(false) }

            Column(
                modifier = Modifier.clickable { isExpanded= !isExpanded }
            ) {
                Text(
                    text = message.author,
                    style = MaterialTheme.typography.subtitle2
                )
                Spacer(modifier = Modifier.height(4.dp))
                Surface(shape = MaterialTheme.shapes.medium, elevation = 1.dp) {
                    Text(
                        text = message.msg,
                        modifier = Modifier.padding(all = 4.dp),
                        maxLines = if(isExpanded) Int.MAX_VALUE else 1,
                        style = MaterialTheme.typography.body2
                    )
                }
            }
        }
    }
}

@Composable
fun OverLapImage(foreId: Int, backId: Int){
    Box(
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
    ) {
        Image(
            painter = painterResource(id = backId),
            contentDescription = "back",
        )
        Image(
            painter = painterResource(id = foreId),
            contentDescription = "fore",
        )
    }
}

@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    ComposePracticeTheme() {
        MessageCard(
            message = Message("Colleague", "Hey, take a look at Jetpack Compose, it's great!")
        )
    }
}