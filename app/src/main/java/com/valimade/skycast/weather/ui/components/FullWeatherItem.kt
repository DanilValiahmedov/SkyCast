package com.valimade.skycast.weather.ui.components

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
import com.valimade.skycast.weather.ui.model.item.DataInfoItem

@Composable
fun FullWeatherItem(
    item: DataInfoItem,
) {
    if(!(item.value == "0.0" || item.value == "0" || item.value == "null")) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "${item.name}: ",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = "${item.value} ${item.units}",
                color = secondaryColor,
                fontSize = 14.sp,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
    }

}