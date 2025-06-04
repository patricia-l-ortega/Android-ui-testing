package com.cognizant.ortegapatricia.brownbagsession_uitesting.jetpackcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.cognizant.ortegapatricia.brownbagsession_uitesting.R
import com.cognizant.ortegapatricia.brownbagsession_uitesting.utils.CornerRadius
import com.cognizant.ortegapatricia.brownbagsession_uitesting.xmllayout.HomeXMLActivity
import com.cognizant.ortegapatricia.brownbagsession_uitesting.xmllayout.LoginXMLActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UITestingView()
        }
    }
}

@Composable
fun UITestingView() {
    val context = LocalContext.current
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 48.dp)
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            shape = RoundedCornerShape(CornerRadius.XS),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.white)),
            border = BorderStroke(1.dp, colorResource(id = R.color.black)),
            onClick = { context.startActivity(Intent(context, HomeActivity::class.java)) }
        ) {
            Text(
                text = "Jetpack Compose Layout",
                color = colorResource(id = R.color.black)
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            shape = RoundedCornerShape(CornerRadius.XS),
            colors = ButtonDefaults.buttonColors(colorResource(id = R.color.white)),
            border = BorderStroke(1.dp, colorResource(id = R.color.black)),
            onClick = { context.startActivity(Intent(context, HomeXMLActivity::class.java)) }
        ) {
            Text(
                text = "XML Layout",
                color = colorResource(id = R.color.black)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UITestingPreview() {
    UITestingView()
}