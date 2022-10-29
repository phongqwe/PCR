package com.qxdzbc.pcr.screen.entry_creation

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.qxdzbc.pcr.R
import com.qxdzbc.pcr.common.CommonUtils.toInt
import com.qxdzbc.pcr.common.MBox
import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.database.model.DbEntryWithTags
import com.qxdzbc.pcr.database.model.DbTag
import com.qxdzbc.pcr.screen.common.*
import com.qxdzbc.pcr.screen.main_screen.date_filter_view.MDatePickerDialog
import com.qxdzbc.pcr.state.model.Entry
import com.qxdzbc.pcr.state.model.Tag
import com.qxdzbc.pcr.ui.theme.PCRTheme
import com.qxdzbc.pcr.util.DateUtils
import java.util.*

val entryCreationScreenNavTag = "entryCreationScreenNavTag"

@Composable
fun EntryCreationScreen(
    currentTags: List<Tag>,
    onOk: (newEntry: Entry) -> Unit,
    back: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var detail: String? by remember { ms(null) }
    var moneyStr: String by remember { ms("0.0") }
    var date: Date by remember { ms(Date()) }
    var isDatePickerOpen by remember { ms(false) }
    var isTagSelectDialogOpen by remember { ms(false) }
    var isCost by remember { ms(false) }
    val selectedTagListMs: Ms<List<Tag>> = remember { ms(emptyList()) }
    var showInvalidMoneyDialog by remember {
        ms(false)
    }
    val openTagSelectDialog: () -> Unit = remember {
        {
            isTagSelectDialogOpen = true
        }
    }
    val openDatePickerDialog: () -> Unit = remember {
        {
            isDatePickerOpen = true
        }
    }
    Scaffold(
        topBar = {
            PCRTopAppBar {
                ConstraintLayout(modifier = Modifier.fillMaxWidth()) {
                    val (menuRef) = createRefs()
                    MIconButton(
                        imageVector = Icons.Default.ArrowBack,
                        onClick = back
                    )
                }
            }
        }
    ) { contentPadding ->
        Surface(
            modifier = Modifier
                .padding(contentPadding)
                .padding(top = contentPadding.calculateTopPadding() + 10.dp)
        ) {
            Column(modifier = modifier.padding(horizontal = 10.dp)) {
                MRow {
                    Text("is this a cost?")
                    MCheckbox(checked = isCost, onCheckedChange = {
                        isCost = it
                    })
                    InputField(
                        "Money amount",
                        value = moneyStr,
                        isNumber = true,
                        modifier = Modifier.fillMaxWidth(),
                        onValueChange = { newStr ->
                            moneyStr = newStr
                        }
                    )
                }
                OutlinedTextField(
                    value = DateUtils.displayDateFormat.format(date),
                    modifier = Modifier.fillMaxWidth(),
                    onValueChange = {},
                    readOnly = true,
                    label = {
                        Text("Date")
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = { openDatePickerDialog() },
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.calendar_24),
                                contentDescription = "pick date",
                                tint = MaterialTheme.colors.primary
                            )
                        }
                    },
                    interactionSource = remember { MutableInteractionSource() }.also { i ->
                        LaunchedEffect(i) {
                            i.interactions.collect {
                                if (it is PressInteraction.Release) {
                                    openDatePickerDialog()
                                }
                            }
                        }
                    }
                )

                InputField(
                    title = "Detail",
                    value = detail ?: "",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    detail = it
                }
                MBox(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .border(1.dp, Color.Gray, RoundedCornerShape(3.dp))
                ) {
                    MRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp)
                            .clickable {
                                openTagSelectDialog()
                            }
                    ) {
                        TagListView(
                            tags = selectedTagListMs.value,
                            modifier = Modifier
                                .weight(9f)
                                .padding(start = 10.dp, end = 5.dp),
                            onCloseTag = {
                                selectedTagListMs.value = selectedTagListMs.value - it
                            }
                        )
                        IconButton(
                            onClick = {
                                openTagSelectDialog()
                            }, modifier = Modifier
                                .weight(1.0f)
                                .padding(end = 5.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.tag_24),
                                contentDescription = null,
                                tint = MaterialTheme.colors.primary
                            )
                        }
                    }
                }
                Button(onClick = {
                    val money = moneyStr.toDoubleOrNull()
                    if(money!=null){
                        val newEntry = DbEntryWithTags(
                            entry = DbEntry(
                                id = UUID.randomUUID().toString(),
                                money = money,
                                detail = detail,
                                dateTime = date.time,
                                isUploaded = false.toInt(),
                                isCost = isCost.toInt(),
                            ),
                            tags = currentTags.map { it.toDbTag() }
                        )
                        onOk(newEntry)
                    }else{
                        showInvalidMoneyDialog = true
                    }
                }, modifier = Modifier.align(Alignment.End)) {
                    Text("Ok")
                }
            }
            if (isDatePickerOpen) {
                MDatePickerDialog(currentDate = date, onDatePick = {
                    date = it
                }, onDismiss = {
                    isDatePickerOpen = false
                })
            }

            if (isTagSelectDialogOpen) {
                TagPickerDialog(
                    tags = currentTags,
                    initSelectedList = selectedTagListMs.value,
                    onDone = {
                        selectedTagListMs.value = it
                    },
                    onDismiss = {
                        isTagSelectDialogOpen = false
                    }
                )
            }
            if (showInvalidMoneyDialog) {
                AlertDialog(
                    onDismissRequest = {
                        showInvalidMoneyDialog = false
                    },
                    confirmButton = {
                        Button(onClick = { showInvalidMoneyDialog = false }) {
                            Text("Ok")
                        }
                    },
                    text = {
                        Text("invalid money amount:${moneyStr}")
                    },
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewEntryCreationScreen() {
    PCRTheme {
        EntryCreationScreen(
            modifier = Modifier.fillMaxWidth(),
            currentTags = (1..4).map { DbTag.random() },
            onOk = {},
            back = {},
        )
    }
}


