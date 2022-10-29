package com.qxdzbc.pcr.screen.main_screen.entry_view

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qxdzbc.pcr.database.model.DbTag
import com.qxdzbc.pcr.state.model.Tag
import com.qxdzbc.pcr.ui.theme.PCRTheme

@Composable
fun SmallTagView(
    tag: Tag,
    onClose:()->Unit
) {
    Box(
        modifier = Modifier
            .border(
                1.dp,
                color = MaterialTheme.colors.onSurface,
                shape = RoundedCornerShape(7.dp)
            )
    ) {
        val vp = 8.dp
        val hp = 4.dp
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = tag.name,
                modifier = Modifier
                    .padding(start = vp, top = hp, bottom = hp),
                fontSize = MaterialTheme.typography.body1.fontSize
            )
            Box(modifier = Modifier.size(30.dp).clickable {
                onClose()
            }) {
                Box(modifier = Modifier
                    .size(16.dp)
                    .align(Alignment.Center)) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "",
                        tint = MaterialTheme.colors.onSurface,
                        modifier =Modifier.align(Alignment.Center)
                    )
                }
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun PreviewSmallTagView() {
    PCRTheme(darkTheme = true) {
        Surface() {
            SmallTagView(tag = DbTag.random(), onClose = {})
        }
    }
}
