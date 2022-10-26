package com.qxdzbc.pcr.screen.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorFilter.Companion.tint
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.qxdzbc.pcr.ui.theme.PCRTheme

@Composable
fun UserInfo(
    modifier: Modifier = Modifier
) {
    Row(modifier=modifier.fillMaxWidth().padding(top =30.dp, bottom = 30.dp, start=30.dp)) {
        Image(
            imageVector = Icons.Outlined.AccountCircle,
            contentDescription = "user avatar",
            modifier = Modifier.size(50.dp),
            colorFilter = tint(MaterialTheme.colors.onSurface)
        )
        Column {
            Text("user name")
            Text("user@email.com")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewUserInfo(){
    PCRTheme(darkTheme = true) {
        Surface {
            UserInfo()
        }
    }
}