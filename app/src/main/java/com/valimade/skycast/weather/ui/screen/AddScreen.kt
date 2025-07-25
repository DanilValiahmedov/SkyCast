package com.valimade.skycast.weather.ui.screen

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valimade.skycast.R
import com.valimade.skycast.ui.theme.borderColor
import com.valimade.skycast.ui.theme.inputColor
import com.valimade.skycast.ui.theme.primaryColor
import com.valimade.skycast.weather.ui.components.SuggestionItem

@Composable
fun AddScreen() {
    var text by remember { mutableStateOf("") }
    val suggestions = listOf("Москва", "Минск", "Псков", "Монреаль")
    var isNotSelected by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.icon),
            contentDescription = "Иконка",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(48.dp),
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Выберите ниже населенный пункт, где вы хотите отслеживать погоду",
            color = primaryColor,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier
                .background(inputColor, shape = RoundedCornerShape(12.dp))
                .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = borderColor,
                unfocusedBorderColor = borderColor,
            ),
            shape = RoundedCornerShape(12.dp),
            placeholder = {
                Text(
                    text = "Название населенного пункта",
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            },
        )

        if (isNotSelected && text.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
            ) {

                LazyColumn {
                    items(suggestions) {
                        SuggestionItem(
                            name = it,
                            onClick = {
                                text = it
                                isNotSelected = false
                            }
                        )
                        if (it != suggestions.lastOrNull()) {
                            HorizontalDivider(color = borderColor,)
                        }
                    }
                }
            }
        }
    }
}