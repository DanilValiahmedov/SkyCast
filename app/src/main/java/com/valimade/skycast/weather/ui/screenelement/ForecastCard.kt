package com.valimade.skycast.weather.ui.screenelement

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valimade.skycast.weather.ui.model.item.DataInfoItem

@Composable
fun ForecastCard(
    item: DataInfoItem,
    color: Color,
) {
    Column(
        modifier = Modifier
            .background(color, shape = RoundedCornerShape(24.dp))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Spacer(modifier = Modifier.height(8.dp))

        WeatherInfoItem(item)

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = item.time,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = item.date,
            color = Color.White,
            fontSize = 14.sp,
        )

        Spacer(modifier = Modifier.height(8.dp))
    }
}