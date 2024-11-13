package com.example.chaosxplorer.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.example.chaosxplorer.views.AlertDialogClass

@Composable
fun AlertWithMessage(alertDialogClass: AlertDialogClass) {
    AlertDialog(
        title = {
            Text(text = alertDialogClass.title)
        },
        text = {
            Text(text = alertDialogClass.message)
        },
        onDismissRequest = {
            alertDialogClass.dismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    alertDialogClass.positiveButton()
                }
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    alertDialogClass.negativeButton()
                }
            ) {
                Text("Dismiss")
            }
        }
    )
}