package ui.components.screens

import android.annotation.SuppressLint
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.painterResource
import com.topic2.android.notes.R
import com.topic2.android.notes.domain.model.NEW_NOTE_ID
import com.topic2.android.notes.domain.model.NoteModel
import com.topic2.android.notes.routing.NotesRouter
import com.topic2.android.notes.routing.Screen
import com.topic2.android.notes.viewmodel.MainViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@ExperimentalMaterialApi
@Composable


fun SaveNoteScreen(viewModel: MainViewModel) {
    val noteEntry: NoteModel by viewModel.noteEntry.observeAsState(NoteModel())

    val colors: List<ColorModel> by viewModel.colors
        .observeAsState(listOf())

    val bottomDrawerState: BottomDrawerState = rememberBottomDrawerState(BottomDrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    val moveNoteToTrashDialogShownState: MutableState<Boolean> =rememberSaveable{
        mutableStateOf(false)
    }

    Scaffold(
        topBar = {
            val isEditingMode: Boolean = noteEntry.id != NEW_NOTE_ID
            SaveNoteTopAppBar(
                isEditingMode = isEditingMode,
                onBackClick = {
                    NotesRouter.navigateTo(Screen.Notes)
                },
                onSaveNoteClick = { viewModel.saveNote(noteEntry) },
                onOpenColorPickerClick = { coroutineScope.launch { bottomDrawerState.open() } },
                onDeleteNoteClick = { viewModel.moveNoteToTrash(noteEntry) }
            )
        },
        content = {
            BottomDrawer(
                drawerState = bottomDrawerState,
                drawerContent = {
                    ColorPicker(colors = colors,
                        onColorSelect = { color ->
                            val newNoteEntry = noteEntry.copy(color = color)
                            viewModel.onNoteEntryChange(newNoteEntry)
                        }
                    )
                },
                content = {
                    SaveNoteContent(
                        note = noteEntry,
                        onNoteChange = { updateNoteEntry ->
                            viewModel.onNoteEntryChange(updateNoteEntry)
                        }
                    )
                }
            )
        }
    )
}


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
@Composable
private fun SaveNoteTopAppBar(
    isEditingMode: Boolean,
    onBackClick: ()->Unit,
    onSaveNoteClick: ()->Unit,
    onOpenColorPickerClick: ()->Unit,
    onDeleteNoteClick: ()->Unit
){
    TopAppBar(
        title = {
            Text(
                text ="Save Note",
                color=MaterialTheme.colors.onPrimary
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Save Note Button",
                    tint =MaterialTheme.colors.onPrimary
                )

            }
        },
        actions = {
            IconButton(onClick = onSaveNoteClick) {
                Icon(
                    imageVector = Icons.Default.Check,
                    tint =MaterialTheme.colors.onPrimary,
                    contentDescription = "Save Note ",
                )
                if (isEditingMode){
                    IconButton(onClick = onDeleteNoteClick)
                    {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete Note Button",
                        tint =MaterialTheme.colors.onPrimary
                    )
                    }
                }

            }

            IconButton(onClick = onOpenColorPickerClick) {
                Icon(
                    painter = painterResource(
                        id = R.drawable.ic_baseline_color_lens_24 ),
                    contentDescription = "Open Color Picker Button",
                    tint =MaterialTheme.colors.onPrimary
                )

            }
        }
    )

}


@Composable
private fun SaveNoteContent(
    note: NoteModel,
    onNoteChange: (NoteModel) -> Unit
){
    Column (
        modifier = androidx.compose.ui.Modifier.fillMaxSize()
    ){
        ContentTextField(
            label = "Title",
            text = note.title,
            onTextChange = {newTitle ->
                onNoteChange.invoke(note.copy(title = newTitle))
            }
        )
        ContentTextField(
            modifier = androidx.compose.ui.Modifier
                .heightIn(max = 240.dp)
                .padding(top = 16.dp),
            label = "Body",
            text = note.content,
            onTextChange = {newTitle ->
                onNoteChange.invoke(note.copy(content = newTitle))
            }
        )

        val canBeCheckedOff: Boolean = note.isCheckedOff != null

        NoteCheckOption(
            isChecked = canBeCheckedOff,
            onCheckedChange = {canBeCheckedOffNewValue ->
                val isCheckedOff: Boolean? = if (canBeCheckedOffNewValue) false else null
                onNoteChange.invoke(note.copy(isCheckedOff = isCheckedOff))
            }
        )

        PickedColor(color = note.color)
    }

}





@Composable
private fun ContentTextField(
    modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier,
    label: String,
    text: String,
    onTextChange: (String) -> Unit
) {
    TextField(
        value = text,
        onValueChange = onTextChange,
        label = { Text(label)},
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = MaterialTheme.colors.surface
        )
    )
}
@Composable
private fun NoteCheckOption(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        androidx.compose.ui.Modifier
            .padding(8.dp)
            .padding(top = 16.dp)
    ) {
        Text(
            text = "Can note be checked off?", modifier = androidx.compose.ui.Modifier.weight(1f)
        )
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange,
            modifier = androidx.compose.ui.Modifier.padding(start = 8.dp)
        )
    }
}

@Composable
private fun PickedColor(color:ColorModel){
    Row (
        androidx.compose.ui.Modifier
            .padding(8.dp)
            .padding(top = 16.dp)
    ) {
        Text(
            text = "Picked color", modifier = androidx.compose.ui.Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
        NoteColor(
            color = Color.fromHex(color.hex),
            size = 40.dp,
            border = 1.dp,
            modifier = androidx.compose.ui.Modifier.padding(4.dp)
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
@Preview
@Composable
fun SaveNoteTopAppBarPreview(){
    SaveNoteTopAppBar(
        isEditingMode = true,
        onBackClick = { },
        onSaveNoteClick = { },
        onOpenColorPickerClick = { },
        onDeleteNoteClick = { }
    )

}
@Preview
@Composable
fun PickedColorPreview()
{
    PickedColor(ColorModel.DEFAULT)
}
@Preview
@Composable
fun NoteCheckOptionPreview()
{
    NoteCheckOption( false){}
}
@Composable
@Preview
 fun ContentTextFieldPreview(){
     ContentTextField(
         label ="Title",
         text = "",
         onTextChange ={}
     )
 }
@Preview
@Composable
fun SaveNoteContentPreview() {
    SaveNoteContent(
        note = NoteModel(title = "Title", content = "content"),
        onNoteChange = {}
    )
}