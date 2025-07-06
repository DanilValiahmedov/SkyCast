package com.valimade.skycast.weather.ui.screenelement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.valimade.skycast.weather.ui.model.item.DataInfoItem

@Composable
fun WeatherInfoGrid(
    listItem: List<DataInfoItem>,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
    ) {

        for(row in listItem.chunked(2)) {
            Column (
                modifier = Modifier.padding(horizontal = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            )  {
                for (item in row) {
                    WeatherInfoItem(item)

                    Spacer(modifier = Modifier.height(16.dp))
                }

            }
        }

    }
}