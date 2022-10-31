package com.qxdzbc.pcr.screen.manage_tag_screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qxdzbc.pcr.common.MBox

@Composable
fun TagButton(
    text:String,
    modifier: Modifier = Modifier,
    onClick:()->Unit,
) {
    MBox(
        modifier = modifier
            .border(
                1.dp,
                color = MaterialTheme.colors.onSurface,
                shape = RoundedCornerShape(7.dp)
            )
            .clickable { onClick() }
    ){
        Text(text,modifier=Modifier.padding(5.dp))
    }
}

@Preview
@Composable
fun PreviewTagButton() {
    TagButton(text = "qwew") {

    }
}
