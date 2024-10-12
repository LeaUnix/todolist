    package com.example.todolist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.todolist.models.ToDoTask
import com.example.todolist.screens.AddTaskScreen
import com.example.todolist.screens.TaskDetailScreen
import com.example.todolist.screens.TaskListScreen
import com.example.todolist.ui.theme.Purple40
import com.example.todolist.ui.theme.Purple80
import com.example.todolist.ui.theme.TodoListTheme
import java.util.UUID

    class MainActivity : ComponentActivity() {
        @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            AppContent()

        }
    }
}

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable

    fun AppContent(){
        val myTasks = remember { mutableStateListOf<ToDoTask>() }
        val screenNumber = remember { mutableStateOf(1) }
        val selectedTask = remember { mutableStateOf<ToDoTask?>(null)}
        Scaffold(
            topBar = {

                TopAppBar(title = { Text(text = "Todo List")},
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = Purple80,
                        titleContentColor = Color.White,
                    )
                    )
            },

            floatingActionButton = {
                FloatingActionButton(onClick = {
                    val randomUUID = UUID.randomUUID().toString()
                    myTasks.add( ToDoTask(randomUUID,"Apprendre JetPack Compose"))
                },
                    containerColor = Purple40,
                    contentColor = Color.White

                ){

                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add" )

                }

            }

        ) {
           Box(modifier = Modifier.padding(it)) {

               AnimatedVisibility(visible = screenNumber.value == 1) {
                   TaskListScreen(screenNumber, myTasks, selectedTask)

               }

               AnimatedVisibility(visible = screenNumber.value == 2) {
                   if(selectedTask.value != null) {
                       TaskDetailScreen(screenNumber,selectedTask.value!!)
                   }

               }


           }
        }


    }


