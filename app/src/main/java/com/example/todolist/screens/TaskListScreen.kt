package com.example.todolist.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.todolist.components.AlertDialogCustom
import com.example.todolist.models.ToDoTask
import com.example.todolist.ui.theme.TodoListTheme

@Composable
fun TaskListScreen(screen: MutableState<Int> ,tasks: MutableList<ToDoTask>, selectedTask : MutableState<ToDoTask?>,fab: MutableState<Boolean>) {

    val showDialog = remember {
        mutableStateOf(false)
    }

    LazyColumn {
         items(tasks){ task ->
             Row(modifier = Modifier
                 .fillMaxWidth()
                 .padding(8.dp),
                 horizontalArrangement = Arrangement.SpaceBetween,
                 verticalAlignment = Alignment.CenterVertically
             ) {

                 Text(text = task.title)

                 Row {

                     IconButton(onClick = {
                         selectedTask.value = task
                         screen.value = 2
                         fab.value = false

                     }) {
                         Icon(Icons.Default.Info, contentDescription = "")

                     }
                     IconButton(onClick = {
                            selectedTask.value = task
                             showDialog.value = true
                     }) {

                         Icon(Icons.Default.Delete, contentDescription = "Supprimer")

                     }
                 }

             }

         }
    }

    if (showDialog.value) {
        AlertDialogCustom(
            onDismissRequest = {
                showDialog.value = false
            },
            onConfirmation = {
                tasks.remove(selectedTask.value)
                showDialog.value = false
            },
            dialogTitle = "Suppression",
            dialogText = "Voulez vous supprimer cette tâche ?   "
        )
    }


}

@Preview(showBackground = true)
@Composable
fun TaskListScreenPreview() {

    val showFab = remember { mutableStateOf(false) }
    val myTasks = remember { mutableStateListOf<ToDoTask>() }
    val screenNumber = remember { mutableStateOf(2) }
    val selectedTask = remember { mutableStateOf<ToDoTask?>(null)}

    TodoListTheme {
        TaskListScreen(screenNumber,myTasks,selectedTask, showFab)
    }
}