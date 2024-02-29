package ui.components.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.topic2.android.notes.domain.model.ColorModel
import com.topic2.android.notes.util.fromHex
import ui.components.NoteColor
import java.lang.reflect.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth








@Composable
private fun ColorPicker(
    colors: List<ColorModel>,
    onColorSelect: (ColorModel) -> Unit
){
    Column (
        modifier = androidx.compose.ui.Modifier.fillMaxWidth()
    ){
        Text(
            text = "Color picker",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = androidx.compose.ui.Modifier.padding(8.dp)

        )
        LazyColumn(
            modifier = androidx.compose.ui.Modifier.fillMaxWidth()
        ){
            items(
                colors.size
            ){
                    itemIdex ->
                val color = colors[itemIdex]
                ColorItem(
                    color = color,
                    onColorSelect = onColorSelect
                )
            }
        }
    }

}

@Composable
fun ColorItem(
    color: ColorModel,
    onColorSelect: (ColorModel) -> Unit
){
    Row(
        modifier = androidx.compose.ui.Modifier
            .fillMaxWidth()
            .clickable(
                onClick = {
                    onColorSelect(color)
                }
            )
    ){
        NoteColor(
            modifier = androidx.compose.ui.Modifier
                .padding(10.dp),
            color = Color.fromHex(color.hex),
            size = 90.dp,
            border = 2.dp
        )
        Text(
            text = color.name,
            fontSize = 22.sp,
            modifier = androidx.compose.ui.Modifier
                .padding(horizontal = 16.dp)
                .align(Alignment.CenterVertically)
        )

    }
}

@Preview
@Composable
fun ColorItemPreview(){
    ColorItem(ColorModel.DEFAULT){}
}

@Preview
@Composable
fun ColorPickerPreview(){
    ColorPicker(
        colors = listOf(
            ColorModel.DEFAULT,
            ColorModel.DEFAULT,
            ColorModel.DEFAULT
        )
    ){}
}