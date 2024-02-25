package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.topic2.android.notes.R
//import androidx.compose.foundation.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.res.colorResource

//import androidx.compose.ui.modifier.modifierLocalOf


@Composable
@Preview
fun Note() {
    Row(modifier = Modifier.fillMaxWidth()) {

        Box(
            modifier = Modifier
                .size(40.dp)
                .background(colorResource(id = R.color.purple_200))


        )

        Column(modifier = Modifier.weight(1f)) {

            Text(text = "Заголовок", maxLines = 1)
            Text(text = "Содержимое", maxLines = 1)
        }

        Checkbox(
            checked = false,
            onCheckedChange = {},
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}


@Preview
@Composable
private fun  NotePreview()
{
    Note()
}

