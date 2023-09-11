package com.raian.imagesearchapp.ui.theme.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.raian.imagesearchapp.ui.theme.network.models.Hit


private const val TAG = "MainContent"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainContent(viewModel: MainViewModel = hiltViewModel()) {
    val query: MutableState<String> = remember {
        mutableStateOf("")
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.padding(8.dp)) {
            OutlinedTextField(
                value = query.value, onValueChange = {
                    query.value = it
                    viewModel.getImageList(query.value)
                },
                enabled = true,
                singleLine = true,
                leadingIcon = {
                    Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },
                label = { Text(text = "Search here...") },
                modifier = Modifier.fillMaxWidth()
            )

            if (viewModel.list.value.isLoading) {
                Log.d(TAG, "MainContent: loading")
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            if (viewModel.list.value.error.isNotBlank()) {
                Log.d(TAG, "MainContent: error")
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(modifier = Modifier.fillMaxSize(), text = viewModel.list.value.error)
                }
            }

            if (viewModel.list.value.data.isNotEmpty()) {
                LazyVerticalGrid(columns = GridCells.Fixed(2)) {
                    viewModel.list.value.data?.let { it ->
                        items(it) {
                            MainContentItem(hit = it)
                            Log.d(TAG, "MainContent: $it")
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun MainContentItem(hit: Hit) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(200.dp)
    ) {
        Image(
            painter = rememberImagePainter(data = hit.largeImageURL),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}