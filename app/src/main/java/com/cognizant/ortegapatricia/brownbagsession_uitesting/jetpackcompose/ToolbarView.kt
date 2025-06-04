package com.cognizant.ortegapatricia.brownbagsession_uitesting.jetpackcompose

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.cognizant.ortegapatricia.brownbagsession_uitesting.R
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolbarView(
    title: String,
    onBackPressed: () -> Unit,
    onRightButtonClick: () -> Unit = {},
    isRightButtonEnabled: Boolean = true,
    backgroundColor: Color = colorResource(id = R.color.white),
    contentColor: Color = colorResource(id = R.color.black)
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = contentColor,
                style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = contentColor
                )
            }
        },
        actions = {
            if (isRightButtonEnabled) {
                IconButton(onClick = onRightButtonClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_done_24),
                        contentDescription = "Right Button",
                        tint = contentColor
                    )
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = backgroundColor,
            navigationIconContentColor = contentColor,
            actionIconContentColor = contentColor
        )
    )
}

@Preview
@Composable
fun ToolbarPreview() {
    ToolbarView(
        title = "Toolbar Title",
        onBackPressed = { /* Handle back press */ },
        onRightButtonClick = { /* Handle right button click */ }
    )
}