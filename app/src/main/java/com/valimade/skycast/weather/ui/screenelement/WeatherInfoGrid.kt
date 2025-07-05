package com.valimade.skycast.weather.ui.screenelement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valimade.skycast.weather.ui.model.item.DataInfoItem

@Composable
fun WeatherInfoGrid(
    listItem: List<DataInfoItem>,
) {
    Column {

        for(row in listItem.chunked(2)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            )  {
                for (item in row) {
                    WeatherInfoItem(item)
                }

            }

            Spacer(modifier = Modifier.height(16.dp))
        }

    }
}