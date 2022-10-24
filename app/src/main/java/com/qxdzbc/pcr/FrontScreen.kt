package com.qxdzbc.pcr

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.qxdzbc.pcr.ui.theme.PCRTheme

@Composable
fun FrontScreen() {
    Surface {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
        ) {
            val (title,subTitle,loginBut) = createRefs()
            Text(
                text = "PCR",
                fontSize = 60.sp,
                color = MaterialTheme.colors.onSurface,
                fontWeight = FontWeight.W900,
                modifier = Modifier.constrainAs(title){
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            Text(
                text ="personal cost recorder",
                fontSize=15.sp,
                color = MaterialTheme.colors.onSurface,
                modifier = Modifier.constrainAs(subTitle){
                    top.linkTo(title.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )
            Button(
                onClick = {},
                modifier = Modifier
                    .constrainAs(loginBut){
                        top.linkTo(subTitle.bottom)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .width(150.dp)
            ) {
                Text("Login")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FrontScreenPreview() {
    PCRTheme(darkTheme = true) {
        FrontScreen()
    }
}
