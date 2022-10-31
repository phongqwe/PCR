package com.qxdzbc.pcr.screen.manage_tag_screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.qxdzbc.pcr.common.MBox
import com.qxdzbc.pcr.database.model.DbTag
import com.qxdzbc.pcr.screen.common.MRow
import com.qxdzbc.pcr.state.model.Tag

@Composable
fun TagView(
    tag: Tag,
    delete: () -> Unit,
    edit: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(elevation = 10.dp, modifier = modifier.fillMaxWidth()) {
        ConstraintLayout(modifier = Modifier.padding(5.dp)) {
            val (nameRef, editRef, deleteRef) = createRefs()

            Text(tag.name, modifier = Modifier
                .constrainAs(nameRef) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
                .padding(start = 10.dp))

            TagButton(text = "Delete", modifier = Modifier.constrainAs(deleteRef) {
                end.linkTo(editRef.start)
                top.linkTo(parent.top)
                bottom.linkTo(parent.bottom)
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
