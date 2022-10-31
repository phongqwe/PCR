package com.qxdzbc.pcr.screen.manage_tag_screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qxdzbc.pcr.common.MBox
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.database.model.DbTag
import com.qxdzbc.pcr.screen.common.*
import com.qxdzbc.pcr.state.model.Tag
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

const val manageTagScreenNavTag = "manageTagScreenNavTag"

@Composable
fun ManageTagScreen(
    action: ManageTagScreenAction,
    globalScope: CoroutineScope,
    tags: List<Tag>,
    back: () -> Unit,
) {
    var isCreateTagDialogOpen: Boolean by remember { ms(false) }
    Scaffold(
        topBar = {
            PCRTopAppBar {
                PCRTopAppBar {
                    MRow {
                        ScreenBackButton(back)
                        ScreenTitleText("Manage tags")
                    }
                }
            }
        },
        floatingActionButton = {
            MFloatButton {
                // TODO add tag
                isCreateTagDialogOpen=true
            }
        }
    ) { pd ->
        var isEditTagNameDialogOpen: Boolean by remember { ms(false) }

        var selectedTag: Tag? by remember { ms(null) }
        MBox(modifier = Modifier.padding(pd)) {
            LazyColumn() {
                this.items(
                    items = tags,
                    key = { tag -> tag.tagId }
                ) { tag ->
                    MBox(modifier= Modifier
                        .padding(horizontal = 10.dp)
                        .padding(top = 10.dp)){
                        TagView(tag = tag, delete = {
                            globalScope.launch {
                                action.delete(tag)
                            }
                        }, edit = {
                            selectedTag = tag
                            isEditTagNameDialogOpen = true

                        })
                    }
                }
            }
            if (isEditTagNameDialogOpen) {
                selectedTag?.also { st ->
                    EditTagDialog(
                        oldTag = st,
                        onDismiss = {
                            isEditTagNameDialogOpen = false
                        },
                        onDone = { oldTag, newTag ->
                            globalScope.launch {
                                action.edit(oldTag, newTag)
                            }
                        }
                    )
                }
            }
            if(isCreateTagDialogOpen){
                EditTagDialog(
                    oldTag = DbTag("",""),
                    onDismiss = {
                        isCreateTagDialogOpen = false
                    },
                    onDone = { _, newTag ->

                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewManageTagScreen() {
    ManageTagScreen(action = ManageTagScreenAction.forPreview, globalScope =GlobalScope, tags = listOf(
        DbTag("t1","tag 1"),
        DbTag("t2","tag 2"),
    )) {

    }
}
