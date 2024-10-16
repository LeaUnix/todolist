    package com.example.todolist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.example.todolist.ui.theme.BluePrimary

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
        var showFab = remember { mutableStateOf(true)
        }

        Scaffold(
            topBar = {

                TopAppBar(title = { Text(text = "Todo List")},
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = BluePrimary,
                        titleContentColor = Color.White
                    )
                    )
            },

            floatingActionButton = {

                if(showFab.value){

                FloatingActionButton(onClick = {
                    screenNumber.value = 3
                    showFab.value = false

                },
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    contentColor = Color.White

                ){

                    Icon(imageVector = Icons.Default.Add, contentDescription = "Add" )

                }
                }



            }

        ) {
           Box(modifier = Modifier.padding(it)) {

               AnimatedVisibility(visible = screenNumber.value == 1,

                   enter = fadeIn() + slideInVertically() ,
                   exit = fadeOut() + slideOutVertically()
               ) {
                   TaskListScreen(screenNumber, myTasks, selectedTask,showFab)

               }

               AnimatedVisibility(visible = screenNumber.value == 2,
                   enter = fadeIn() + slideInVertically() ,
                   exit = fadeOut() + slideOutVertically()
               ) {
                   if(selectedTask.value != null) {
                       TaskDetailScreen(screenNumber,selectedTask.value!!,showFab)
                   }

               }

               AnimatedVisibility(visible = screenNumber.value == 3,
                   enter = fadeIn() + slideInVertically() ,
                   exit = fadeOut() + slideOutVertically()
               ) {
                   AddTaskScreen(screen = screenNumber, tasks = myTasks, fab = showFab)


               }


           }
        }


    }


