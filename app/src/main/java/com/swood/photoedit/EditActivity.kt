package com.swood.photoedit

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter
import com.swood.photoedit.ui.theme.PhotoEditTheme

class EditActivity: ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PhotoEditTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                    ) {
                    var uriString: String?

                    uriString = intent.extras?.getString("imageUri")
                    if(uriString == null)
                        uriString = intent.extras?.get(Intent.EXTRA_STREAM).toString()
                    val uri = Uri.parse(uriString)

                    EditImage(uri = uri)
                }
            }
        }
    }
}

@Composable
fun EditImage(uri: Uri) {
    val painter = rememberAsyncImagePainter(model = uri)

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painter, contentDescription = null)
    }
}