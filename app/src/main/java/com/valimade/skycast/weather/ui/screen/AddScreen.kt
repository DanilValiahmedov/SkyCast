package com.valimade.skycast.weather.ui.screen

import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.valimade.skycast.R
import com.valimade.skycast.ui.theme.borderColor
import com.valimade.skycast.ui.theme.inputColor
import com.valimade.skycast.ui.theme.primaryColor
import com.valimade.skycast.weather.ui.components.SuggestionItem
import com.valimade.skycast.weather.ui.viewmodel.AddViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun AddScreen() {
    val context = LocalContext.current
    val viewModel: AddViewModel = koinViewModel()
    val addState by viewModel.addState.collectAsState()

    LaunchedEffect(addState.isError) {
        if (addState.isError) {
            Toast.makeText(context, addState.errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

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
            value = addState.requestText,
            onValueChange = {
                viewModel.changeRequestText(it)
                viewModel.getForwardGeocoding()
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

        if (addState.namePlaces.isNotEmpty() && addState.requestText.isNotEmpty()) {
            if(addState.isLoading) {
                CircularProgressIndicator(color = primaryColor)
            }  else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                ) {
                    LazyColumn {
                        items(addState.namePlaces) {
                            SuggestionItem(
                                name = it,
                                onClick = {
                                    viewModel.changeRequestText(it)
                                }
                            )
                            if (it != addState.namePlaces.lastOrNull()) {
                                HorizontalDivider(color = borderColor,)
                            }
                        }
                    }
                }
            }
        }
    }
}