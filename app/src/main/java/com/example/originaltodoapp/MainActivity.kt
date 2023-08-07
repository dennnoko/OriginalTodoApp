package com.example.originaltodoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.originaltodoapp.UIComponents.AddTaskMenu
import com.example.originaltodoapp.UIComponents.TaskList
import com.example.originaltodoapp.roomComponents.Database
import com.example.originaltodoapp.ui.theme.OriginalTodoAppTheme

class MainActivity : ComponentActivity() {
    private lateinit var appDatabase: Database
    override fun onCreate(savedInstanceState: Bundle?) {
        //database初期化
        appDatabase = Database.getdb(this)

        super.onCreate(savedInstanceState)
        setContent {
            OriginalTodoAppTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(appDatabase)
                }
            }
        }
    }
}

@Composable
fun MainScreen(database: Database) {
    var addTaskMenu by remember { mutableStateOf(false) }

    Column(modifier = Modifier
        .fillMaxWidth()
        .horizontalScroll(rememberScrollState())
    ) {
        Row() {
            Button(
                onClick = { addTaskMenu = !addTaskMenu },
                modifier = Modifier
                    .padding(5.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color.Green, contentColor = Color.Black),
                shape = RectangleShape,
            ) {
                Text(
                    text = "Add Task",
                    fontWeight = FontWeight.Bold,
                )
            }
        }

        if(addTaskMenu == true) {
            AddTaskMenu(database) {
                addTaskMenu = false
            }
        }

        TaskList(database = database)
    }
}
