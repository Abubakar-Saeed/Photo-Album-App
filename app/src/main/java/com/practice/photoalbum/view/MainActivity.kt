package com.practice.photoalbum.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.practice.photoalbum.PhotoAlbum
import com.practice.photoalbum.ui.theme.PhotoAlbumTheme
import com.practice.photoalbum.viewmodel.ImageViewModel
import com.practice.photoalbum.viewmodel.ImageViewModelFactory
import kotlin.io.encoding.Base64

class MainActivity : ComponentActivity() {

    private lateinit var myImageViewModel: ImageViewModel


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        val viewModelFactory : ImageViewModelFactory = ImageViewModelFactory((application as PhotoAlbum).repository)

        myImageViewModel = ViewModelProvider(this,viewModelFactory)[ImageViewModel::class]


        setContent {

            PhotoAlbumTheme {

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Navigation(model = myImageViewModel)

                }
            }
        }
    }
}


@Composable
fun Navigation(model : ImageViewModel){

    val controller = rememberNavController()

    NavHost(navController = controller, startDestination = "MainScreen"){

        composable(route = "MainScreen"){

            MainScreen(viewModel = model ,controller)
        }
        composable(route = "AddScreen"){

            AddScreen(controller,model)
        }
        composable(route = "UpdateScreen/{index}",
            arguments = listOf(navArgument("index"){type = NavType.IntType})
        ){navBackStackEntry ->

            val index = navBackStackEntry.arguments?.getInt("index")

            if (index != null) {
                UpdateScreen(controller,model,index)
            }
        }


    }



}