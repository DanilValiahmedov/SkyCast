package com.valimade.skycast.weather.ui.screenelement

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valimade.skycast.ui.theme.secondaryColor

@Composable
fun FullWeatherItem(
    name: String,
    value: String,
    units: String,
) {
    if(!(value == "0.0" || value == "0" || value == "null")) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "$name: ",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = "$value $units",
                color = secondaryColor,
                fontSize = 14.sp,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
    }

}