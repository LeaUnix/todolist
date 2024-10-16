package com.example.todolist.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
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
fun AddTaskScreen(screen: MutableState<Int>, tasks: MutableList<ToDoTask>, fab: MutableState<Boolean>) {



    val title = remember {
        mutableStateOf("")
    }

    val description = remember {
        mutableStateOf("")
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp),
        verticalArrangement = Arrangement.Center

    ) {

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = title.value,
            onValueChange = { title.value = it},
            label =  { Text("Titre")} )


        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = description.value,
            onValueChange = { description.value = it},
            label =  { Text("Description")} )

        Button(modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp) ,colors = ButtonDefaults.buttonColors(containerColor = GreyCustom, contentColor = Color.White),

            onClick = {

            val newTask = ToDoTask(title.value, description.value)
            tasks.add(newTask)
            screen.value = 1
            fab.value = true
        }) {
            Text(text = "Ajouter")

        }

        Button(modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = GreyCustom, contentColor = Color.White),
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
fun AddTaskScreenPreview() {

    val myTasks = remember { mutableStateListOf<ToDoTask>()    }
    val screenNumber = remember { mutableIntStateOf(2) }
    val showFab = remember { mutableStateOf(false) }

    TodoListTheme {
        AddTaskScreen(screen = screenNumber, tasks = myTasks, fab = showFab)
    }
}