package ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.ColumnScopeInstance.weight
import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color

import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.topic2.android.notes.R
//import androidx.compose.foundation.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import com.topic2.android.notes.domain.model.NoteModel
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.unit.min
import com.topic2.android.notes.theme.rwGreen

//import androidx.compose.ui.modifier.modifierLocalOf


@Composable
fun Note(
    note: NoteModel,
    onNoteClick: (NoteModel) -> Unit = {},
    onNoteCheckedChange: (NoteModel) -> Unit = {}

) {
    val backgroundShape: Shape = RoundedCornerShape(4.dp)
    Row(modifier = Modifier
        .padding(8.dp)
        .shadow(1.dp, backgroundShape)
        .fillMaxWidth()
        .heightIn(min = 64.dp)
        .background(Color.White, backgroundShape)

    ) {

        NoteColor(modifier = Modifier
            .align(Alignment.CenterVertically)
            .padding(start = 16.dp, end = 16.dp), rwGreen, 40.dp, 1.dp)

        Column(modifier = Modifier
            .weight(1f)
            .align(Alignment.CenterVertically)
        ) {

            Text(text = "Заголовок", maxLines = 1)
            Text(text = "Содержимое", maxLines = 1)
        }

        Checkbox(
            checked = false,
            onCheckedChange = {},
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        )
    }
}


@Preview
@Composable
private fun  NotePreview()
{
    Note(note = NoteModel(
        1,
        "Заметка 1",
        "Содержимое 1",
        null))
}

