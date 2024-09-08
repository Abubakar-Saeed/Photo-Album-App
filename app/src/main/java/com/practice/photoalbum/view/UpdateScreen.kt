package com.practice.photoalbum.view

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.practice.photoalbum.Converters.Companion.convertToBitmap
import com.practice.photoalbum.Converters.Companion.convertToString
import com.practice.photoalbum.model.MyImage
import com.practice.photoalbum.viewmodel.ImageViewModel


@Composable
fun UpdateScreen(navController: NavController, model : ImageViewModel, index : Int){

    val imagesState = model.getAllImage().observeAsState(initial = emptyList())
    val images = imagesState.value

    Scaffold(

        topBar = { UpdateBar(navController)},
        content = { UpdateBody(pd = it, images[index],model,navController)},



        )


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateBar(navController: NavController){


    TopAppBar(title = { Text(text = "Update Photo", style = MaterialTheme.typography.titleMedium)}
        , colors = TopAppBarDefaults.topAppBarColors(

            containerColor = Color.DarkGray,
            titleContentColor = Color.White,
            navigationIconContentColor =  Color.White
        ), navigationIcon = {

            Icon(

                imageVector = Icons.Default.ArrowBack
                ,contentDescription = null
                , modifier = Modifier.clickable {

                    navController.popBackStack()

                }

            )
        }

    )



}

@Composable
fun UpdateBody(pd : PaddingValues, image : MyImage, model : ImageViewModel,navController: NavController){

    var imageUrl by remember{ mutableStateOf<Uri?>(null) }
    var bitmap by remember{ mutableStateOf<Bitmap>(convertToBitmap(image.imageAsString)) }

    var imageTitle by remember {
        mutableStateOf(image.imageTitle)
    }
    var imagedes by remember {
        mutableStateOf(image.imageDescription)
    }



    val context = LocalContext.current

    val launcher = rememberLauncherForActivityResult(

        contract = ActivityResultContracts.GetContent()){uri ->
        imageUrl = uri
    }


    Box(
        Modifier
            .fillMaxSize()
            .padding(pd)) {

        Row (modifier = Modifier
            .padding(30.dp)
            .fillMaxWidth()) {

            imageUrl?.let {

                if (Build.VERSION.SDK_INT < 28){

                    bitmap =  MediaStore.Images.Media.getBitmap(context.contentResolver,it)

                }else{

                    val source = ImageDecoder.createSource(context.contentResolver,it)
                    bitmap = ImageDecoder.decodeBitmap(source)
                }
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {



                Image(bitmap = bitmap.asImageBitmap(), contentDescription = null , modifier = Modifier.size(200.dp))
                Button(onClick = { launcher.launch("image/*")}) {

                    Text(text = "Update Image")
                }

                Spacer(modifier = Modifier.height(10.dp))
                TextField(value = imageTitle, onValueChange = {

                    imageTitle = it
                }, label = { Text(text = "Update Title")}, modifier = Modifier
                    .padding(20.dp)
                    .width(400.dp))
                TextField(value = imagedes, onValueChange = {

                    imagedes = it
                }, label = { Text(text = "Update Description")}, modifier = Modifier
                    .padding(20.dp)
                    .width(400.dp))


                Row (modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {

                    Button(onClick = {

                        bitmap.let { it ->
                            convertToString(it)?.let {
                                MyImage(imageTitle, imageDescription = imagedes,
                                    it
                                )
                            }?.let { model.update(it.imageTitle,it.imageDescription,it.imageAsString,image.imageID) }
                        }
                        navController.navigate("MainScreen")
                    }

                    ) {


                        Text("Update")
                    }
                }

            }







        }

    }

}


