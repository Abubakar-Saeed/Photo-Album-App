package com.practice.photoalbum.view

import android.graphics.Bitmap
import androidx.compose.animation.core.tween

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.ExperimentalFoundationApi


import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar

import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxState
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.contentColorFor
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.room.Delete
import com.practice.photoalbum.Converters.Companion.convertToBitmap
import com.practice.photoalbum.model.MyImage
import com.practice.photoalbum.viewmodel.ImageViewModel
import com.practice.protask.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds


@Composable
fun MainScreen(viewModel: ImageViewModel, navController: NavController){

    val imagesState = viewModel.getAllImage().observeAsState(initial = emptyList())
    val images = imagesState.value

    Scaffold(
        topBar = { AppBar()},
        content = { Body(pd = it, imageData = images, navController,viewModel)},
        floatingActionButton = { FloatingActionButton(onClick = {

            navController.navigate("AddScreen")

        }) {

            Icon(

                imageVector = Icons.Default.Add,
                contentDescription = null
            )
        }}

    )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(){


    TopAppBar(title = { Text(text = "Photo Album", style = MaterialTheme.typography.titleMedium) }
    , colors = TopAppBarDefaults.topAppBarColors(

        containerColor =  Color.DarkGray,
        titleContentColor = Color.White
    )

    )


}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Body(pd: PaddingValues, imageData: List<MyImage>, navController: NavController,viewModel: ImageViewModel ) {



    LazyColumn(modifier = Modifier.padding(pd)) {

        items(

            count = imageData.size,
            itemContent = {image ->

                        Card(
                            modifier = Modifier

                                .padding(5.dp)
                                .fillMaxWidth()
                                .height(150.dp), colors = CardDefaults.cardColors(
                                containerColor = Color.Magenta,
                                contentColor = Color.White

                            )
                        ) {


                            Row (modifier = Modifier

                                .fillMaxSize()
                                .padding(20.dp), verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start){

                                Image(

                                    bitmap = convertToBitmap(imageData[image].imageAsString).asImageBitmap(),
                                    contentDescription = null,
                                    modifier = Modifier.fillMaxHeight().width(100.dp)
                                )

                                Row ( modifier = Modifier.fillMaxSize()){


                                    Column (modifier = Modifier.fillMaxHeight().padding(horizontal = 20.dp), verticalArrangement = Arrangement.Center){
                                        Text(text = imageData[image].imageTitle, style = MaterialTheme.typography.titleLarge)
                                        Spacer(modifier = Modifier.height(2.dp))
                                        Text(text = imageData[image].imageDescription, style = MaterialTheme.typography.bodyMedium, modifier = Modifier.width(150.dp))
                                    }

                                    Row (verticalAlignment = Alignment.Top, horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxSize()){


                                        IconButton(onClick = {

                                            viewModel.delete(imageData[image])

                                        }
                                        ) {

                                            Icon(

                                                imageVector = Icons.Default.Delete,
                                                contentDescription = null
                                            )
                                        }


                                        IconButton(onClick = {

                                            navController.navigate("UpdateScreen/${image}")
                                        }) {

                                            Icon(

                                                imageVector = Icons.Default.Edit,
                                                contentDescription = null,

                                                )
                                        }

                                }

                                }


                            }

                        }
                    })

            }




}
