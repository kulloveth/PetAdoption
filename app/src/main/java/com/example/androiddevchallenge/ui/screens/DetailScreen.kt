/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.transform.CircleCropTransformation
import com.example.androiddevchallenge.data.model.Cat
import dev.chrisbanes.accompanist.coil.CoilImage

@Composable
fun DetailsScreen(viewModel: CatsViewModel) {
    val cat = viewModel.catLiveData.observeAsState()
    Surface(color = MaterialTheme.colors.background) {
        Column(
            Modifier
                .padding(8.dp)
                .clip(RoundedCornerShape(4.dp))
                .background(color = MaterialTheme.colors.surface)
                .padding(16.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CatDetailsScreen(cat.value)
        }
    }
}
@Composable
fun CatDetailsScreen(cat: Cat?) {
    cat?.let { cat ->
        Card(modifier = Modifier
            .width(60.dp)
            .padding(bottom = 10.dp), elevation = 6.dp,backgroundColor = MaterialTheme.colors.primary) {
            Text("#${cat.fee}", style = MaterialTheme.typography.body2, color = Color.White, textAlign = TextAlign.Center)
        }

        CoilImage(
            data = cat.imgUrl,
            requestBuilder = {
                transformations(CircleCropTransformation())
            },
            contentDescription = "${cat.name}'s image",
            modifier = Modifier.size(300.dp)
        )
        Row(
            modifier = Modifier
                .padding(12.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Card(modifier = Modifier
                .padding(end = 10.dp ),  elevation = 6.dp, backgroundColor = Color.LightGray) {
                Column(modifier = Modifier
                    .padding(10.dp )) {
                    Text("Name", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text(cat.name, fontWeight = FontWeight.Thin, fontSize = 18.sp)
                }

            }
                Card(modifier = Modifier
                    .padding(end = 10.dp ),elevation = 6.dp, backgroundColor = Color.LightGray) {
                    Column(modifier = Modifier
                        .padding(10.dp )) {
                        Text("Breed", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(cat.breed, fontWeight = FontWeight.Thin, fontSize = 18.sp)
                    }

                }
                Card(modifier = Modifier
                    .padding(end = 10.dp ), elevation = 6.dp, backgroundColor = Color.LightGray) {
                    Column(modifier = Modifier
                        .padding(10.dp )) {
                        Text("Location", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Text(cat.location, fontWeight = FontWeight.Thin, fontSize = 18.sp)
                    }

                }
        }

        Divider(
            color = Color.Black,
        )
        Button(onClick = {},Modifier.padding(top=10.dp)) {
            Text(text = "Adopt")
        }
    }
}
