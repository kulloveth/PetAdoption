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

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ContentAlpha
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.navigate
import coil.transform.CircleCropTransformation
import dev.chrisbanes.accompanist.coil.CoilImage

@ExperimentalFoundationApi
@Composable
fun ListScreen(navController: NavController, viewModel: CatsViewModel) {
    val cats = viewModel.catsLiveData.observeAsState()
    LazyVerticalGrid(
        cells = GridCells.Fixed(2),
        modifier = Modifier.padding(10.dp),
        state = rememberLazyListState()
    ) {
        items(cats.value ?: emptyList()) { cat ->
            Column(
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(4.dp))
                    .padding(10.dp)
                    .border(2.dp, Color.Black, RectangleShape)
                    .clickable(
                        onClick = {
                            viewModel.catById(cat.id)
                            navController.navigate("details") {
                                launchSingleTop = true
                            }
                        }
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                CoilImage(
                    data = cat.imgUrl,
                    contentDescription = "${cat.name}'s image",
                    requestBuilder = {
                        transformations(CircleCropTransformation())
                    },
                    modifier = Modifier
                        .size(100.dp)
                        .padding(top = 10.dp)
                )

                Text(
                    cat.name,
                    fontWeight = FontWeight.Bold,
                )
                CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                    Text(
                        cat.breed, style = MaterialTheme.typography.body2,
                        modifier = Modifier
                            .padding(bottom = 10.dp)
                    )
                }
            }
        }
    }
}
