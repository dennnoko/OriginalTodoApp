package com.example.originaltodoapp.UIComponents

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.originaltodoapp.roomComponents.AppEntity
import com.example.originaltodoapp.roomComponents.Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun TaskList(database: Database) {
    var taskList by remember {
        mutableStateOf(emptyList<AppEntity>())
    }

    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        //Coroutineを使って非同期でデータベースからデータを取得する
        withContext(Dispatchers.IO) {
            database.appDao().getAll().collect() {
                taskList = it
            }
        }
    }

    Row(modifier = Modifier.fillMaxWidth()) {
        Spacer(modifier = Modifier.width(10.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(taskList) { task ->
                var detailScreen by remember {
                    mutableStateOf(false)
                }

                Text(
                    modifier = Modifier
                        .clickable { detailScreen = !detailScreen },
                    text = "${task.title}",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                )
                Divider()

                if(detailScreen == true) {
                    Text(
                        text = "    ${task.detail}",
                        fontSize = 20.sp,
                    )

                    Button(
                        onClick = {
                            coroutineScope.launch {
                                withContext(Dispatchers.IO) {
                                    database.appDao().delete(task)
                                }
                            }
                            detailScreen = false
                        },
                        colors = ButtonDefaults.buttonColors(Color.Gray),
                        shape = RoundedCornerShape(3.dp),
                    ) {
                        Text(text = "削除")
                    }
                }
            }
        }
    }
}