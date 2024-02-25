package ui.components

import android.graphics.ColorFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.*
import androidx.compose.material.icons.filled.Menu
//import androidx.compose.material.icons.filled.Home
//import androidx.compose.material.icons.filled.Delete

//import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
//import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


import androidx.compose.foundation.layout.*








@Composable
@Preview
private fun AppDrawerHeader(){
    Row(modifier = Modifier.fillMaxWidth()){
        Image(imageVector = Icons.Filled.Menu,
            contentDescription = "Drawer Header Icon",
            colorFilter = androidx.compose.ui.graphics.ColorFilter
                .tint(MaterialTheme.colors.onSurface),
            modifier = Modifier.padding(16.dp)
        )
        Text(text = "Заметки",
            modifier = Modifier
                .align(alignment = Alignment.CenterVertically))
    }

}

