package com.cognizant.ortegapatricia.brownbagsession_uitesting.utils

import android.view.View
import androidx.compose.foundation.background
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.cognizant.ortegapatricia.brownbagsession_uitesting.R
import com.google.android.material.snackbar.Snackbar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBarWithBackButton(title: String, onBackPressed: () -> Unit) {
    TopAppBar(
        title = { Text(text = title) },
        navigationIcon = {
            BackButton(onBackPressed = onBackPressed)
        },
        modifier = Modifier
            .background(colorResource(id = R.color.teal_700))
    )
}

@Composable
fun BackButton(onBackPressed: () -> Unit) {
    IconButton(onClick = { onBackPressed() }) {
        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
    }
}

fun View.snackbar(message: String,
                  action: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_SHORT )

    action?.let {
        snackbar.setAction("Retry") {
            it()
        }
    }
    snackbar.show()
}

@Preview(showBackground = true)
@Composable
fun TopAppBarWithBackButtonPreview() {
    TopAppBarWithBackButton(title = "Title", onBackPressed = {})
}
