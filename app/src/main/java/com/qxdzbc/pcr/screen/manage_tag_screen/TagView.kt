package com.qxdzbc.pcr.screen.manage_tag_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.qxdzbc.pcr.R
import com.qxdzbc.pcr.common.MBox
import com.qxdzbc.pcr.database.model.DbTag
import com.qxdzbc.pcr.screen.common.MIconButton
import com.qxdzbc.pcr.screen.common.MRow
import com.qxdzbc.pcr.state.model.Tag
import com.qxdzbc.pcr.state.model.WriteState

@Composable
fun TagView(
    tag: Tag,
    delete: () -> Unit,
    edit: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(elevation = 10.dp, modifier = modifier.fillMaxWidth()) {
        ConstraintLayout(modifier = Modifier.padding(5.dp)) {
            val (nameRef, editRef, deleteRef,
            statusRef) = createRefs()

            Text(tag.name, modifier = Modifier
                .constrainAs(nameRef) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .padding(start = 10.dp))
            MBox(modifier = Modifier
                .constrainAs(statusRef) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(deleteRef.start)
                }.padding(end=5.dp)){
                when (tag.writeState) {
                    WriteState.WritePending -> {
                        MIconButton(
                            painter = painterResource(R.drawable.backup_icon_24),
                            onClick = {
                                // TODO upload single tag
                            },
                            tint = Color.Red
                        )
                    }
                    else -> {
                        Icon(
                            painter = painterResource(R.drawable.cloud_done_24),
                            contentDescription = null,
                            tint = MaterialTheme.colors.primary,
                        )
                    }
                }
            }

            TagButton(text = "Delete", modifier = Modifier.constrainAs(deleteRef) {
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
                end.linkTo(editRef.start)
            }) {
                delete()
            }

            TagButton(text = "Edit", modifier = Modifier
                .constrainAs(editRef) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .padding(start = 5.dp)) {
                edit()
            }
        }
    }
}

@Preview
@Composable
fun PreviewTagView() {
    TagView(
        tag = DbTag("id", "Tag 1"),
        delete = {},
        edit = {}
    )
}
