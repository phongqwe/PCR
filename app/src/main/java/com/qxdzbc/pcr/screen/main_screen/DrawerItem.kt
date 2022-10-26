package com.qxdzbc.pcr.screen.main_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@Composable
fun DrawerItem(
    modifier: Modifier = Modifier,
    content: @Composable ()->Unit,
) {
    Box(
        modifier = modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .padding(start = 30.dp, top = 15.dp, bottom = 15.dp)
    ){
        content()
    }
}

@Composable
fun ClickableDrawerItem(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable ()->Unit,
) {
    DrawerItem(modifier = modifier.clickable { onClick() }){

            content()

    }
}
@Composable
fun ClickableDrawerItem(
    name: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    ClickableDrawerItem(content = {
        Text(name, fontSize = 17.sp)
    } , onClick = onClick, modifier = modifier)
}

@Preview(showBackground = true)
@Composable
fun PreviewDrawerItem() {
    Surface() {
        ClickableDrawerItem(name = "item", onClick = {})
    }
}
