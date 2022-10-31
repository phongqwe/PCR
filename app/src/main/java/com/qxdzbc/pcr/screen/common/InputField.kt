package com.qxdzbc.pcr.screen.common

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qxdzbc.pcr.ui.theme.PCRTheme

@Composable
fun InputField(
    title: String,
    modifier: Modifier = Modifier,
    isNumber: Boolean=false,
    value: String = "",
    isError: Boolean = false,
    onValueChange: (String) -> Unit,
) {
    OutlinedTextField(
        isError=isError,
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = MaterialTheme.colors.primary,
            unfocusedBorderColor = Color.Gray
        ),
        label = {
            Text(title)
        },
        keyboardOptions = if(isNumber){
            KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            )
        } else{
            KeyboardOptions.Default
        }
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewInputField() {
    PCRTheme() {
        InputField(title = "Detail", value = "detail abc 123", onValueChange = {}, isNumber = false)
    }
}
