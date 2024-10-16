package com.example.todolist.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.models.ToDoTask
import com.example.todolist.ui.theme.GreyCustom
import com.example.todolist.ui.theme.TodoListTheme

@Composable
fun TaskDetailScreen(screen: MutableState<Int>, selectedTask : ToDoTask,fab: MutableState<Boolean>) {

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            style = MaterialTheme.typography.titleLarge,
            text = selectedTask.title)

        Text(selectedTask.content)

        Button(
            colors = ButtonDefaults.buttonColors(containerColor = GreyCustom, contentColor = Color.White),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
            screen.value = 1
            fab.value = true
        }) {
            Text(text = "Quitter")
            
        }
    }


}




@Preview(showBackground = true)
@Composable
fun TaskDetailScreenPreview() {
    val showFab = remember { mutableStateOf(false)}
    val screenNumber = remember { mutableStateOf(2)}
    TodoListTheme {
        TaskDetailScreen(screenNumber, ToDoTask(title = "", content = ""), showFab)
    }
}