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
import java.util.UUID

const val manageTagScreenNavTag = "manageTagScreenNavTag"

@Composable
fun ManageTagScreen(
    action: ManageTagScreenAction,
    executionScope: CoroutineScope,
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
                isCreateTagDialogOpen=true
            }
        }
    ) { pd ->

        var isEditTagNameDialogOpen: Boolean by remember { ms(false) }
        var selectedTag: Tag? by remember { ms(null) }

        MBox(modifier = Modifier.padding(pd)) {
            LazyColumn {
                this.items(
                    items = tags,
                    key = { tag -> tag.tagId }
                ) { tag ->
                    MBox(modifier= Modifier
                        .padding(horizontal = 10.dp)
                        .padding(top = 10.dp)){
                        TagView(tag = tag, delete = {
                            executionScope.launch {
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
                            executionScope.launch {
                                action.edit(oldTag, newTag)
                            }
                        }
                    )
                }
            }
            if(isCreateTagDialogOpen){
                EditTagDialog(
                    oldTag = DbTag(UUID.randomUUID().toString(),""),
                    onDismiss = {
                        isCreateTagDialogOpen = false
                    },
                    onDone = { _, newTag ->
                        executionScope.launch {
                            action.addTag(newTag)
                        }
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewManageTagScreen() {
    ManageTagScreen(action = ManageTagScreenAction.forPreview, executionScope =GlobalScope, tags = listOf(
        DbTag("t1","tag 1"),
        DbTag("t2","tag 2"),
    )) {

    }
}
