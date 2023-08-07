package com.example.originaltodoapp.UIComponents

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.example.originaltodoapp.roomComponents.Database
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskMenu(database: Database, onButtonClicked: () -> Unit) {
    var titleText by remember { mutableStateOf("") }
    var detailTxt by remember { mutableStateOf("") }

    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.LightGray),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        TextField(
            value = titleText,
            label = { Text(text = "タイトル") },
            onValueChange = { titleText = it },
            modifier = Modifier
                .padding(30.dp, 10.dp)
        )

        TextField(
            value = detailTxt,
            label = { Text(text = "詳細") },
            onValueChange = { detailTxt = it },
            modifier = Modifier
                .padding(30.dp, 10.dp)
        )

        Row(
            //modifier = Modifier.fillMaxWidth(),
        ) {
            Button(
                modifier = Modifier
                    .size(120.dp, 40.dp),
                onClick = { onButtonClicked() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                shape = RectangleShape,
            ) {
                Text(text = "閉じる")
            }

            Spacer(modifier = Modifier.width(20.dp))

            Button(
                modifier = Modifier
                    .size(120.dp, 40.dp),
                onClick = {
                    GlobalScope.launch {
                        if(titleText != ""){
                            database.addTask(titleText, detailTxt)
                            titleText = ""
                            detailTxt = ""
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black),
                shape = RectangleShape,
            ) {
                Text(text = "追加")
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
    }
}