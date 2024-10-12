package com.example.todolist.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.todolist.ui.theme.TodoListTheme

@Composable
fun AddTaskScreen() {
    Text("ADD TASK SCREEN")
}




@Preview(showBackground = true)
@Composable
fun AddTaskScreenPreview() {
    TodoListTheme {
        AddTaskScreen()
    }
}