package com.example.todolist.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist.models.ToDoTask
import com.example.todolist.ui.theme.TodoListTheme

@Composable
fun TaskDetailScreen(screen: MutableState<Int>, selectedTask : ToDoTask) {

    Column {
        Text(selectedTask.title)
        Text(selectedTask.content)

        Button(onClick = {

            screen.value = 1

        }) {
            Text(text = "Quitter")
            
        }
    }

    Text(selectedTask.title)


}




@Preview(showBackground = true)
@Composable
fun TaskDetailScreenPreview() {
    val screenNumber = remember { mutableStateOf(2)}
    TodoListTheme {
        TaskDetailScreen(screenNumber, ToDoTask(title = "", content = ""))
    }
}