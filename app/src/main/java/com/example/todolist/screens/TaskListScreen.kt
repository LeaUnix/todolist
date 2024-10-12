package com.example.todolist.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist.models.ToDoTask
import com.example.todolist.ui.theme.TodoListTheme

@Composable
fun TaskListScreen(screen: MutableState<Int> ,tasks: MutableList<ToDoTask>, selectedTask : MutableState<ToDoTask?>) {
    LazyColumn {
         items(tasks){ task ->
             Row(verticalAlignment = Alignment.CenterVertically) {

                 Text(text = task.title)

                 IconButton(onClick = {
                     selectedTask.value = task
                     screen.value = 2

                 }) {
                     Icon(Icons.Default.Info, contentDescription = "")

                 }
                IconButton(onClick = {
                    tasks.remove(task)
                }) {

                    Icon(Icons.Default.Delete, contentDescription = "Supprimer")

                }

             }

         }
    }
}




@Preview(showBackground = true)
@Composable
fun TaskListScreenPreview() {

    val myTasks = remember { mutableStateListOf<ToDoTask>() }
    val screenNumber = remember { mutableStateOf(2) }
    val selectedTask = remember { mutableStateOf<ToDoTask?>(null)}

    TodoListTheme {
        TaskListScreen(screenNumber,myTasks,selectedTask)
    }
}