package com.qxdzbc.pcr.screen.entry_creation

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qxdzbc.pcr.R
import com.qxdzbc.pcr.common.CommonUtils.toInt
import com.qxdzbc.pcr.common.MBox
import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.database.model.DbEntry
import com.qxdzbc.pcr.database.model.DbEntryWithTags
import com.qxdzbc.pcr.database.model.DbTag
import com.qxdzbc.pcr.screen.common.InputField
import com.qxdzbc.pcr.screen.common.MRow
import com.qxdzbc.pcr.screen.common.TagListView
import com.qxdzbc.pcr.screen.common.TagPickerDialog
import com.qxdzbc.pcr.screen.main_screen.date_filter_view.MDatePickerDialog
import com.qxdzbc.pcr.state.model.Entry
import com.qxdzbc.pcr.state.model.Tag
import com.qxdzbc.pcr.ui.theme.PCRTheme
import com.qxdzbc.pcr.util.DateUtils
import java.util.*

@Composable
fun EntryCreationScreen(
    tags: List<Tag>,
    onOk: (newEntry: Entry) -> Unit,
    modifier: Modifier = Modifier
) {
    val detailMs: Ms<String?> = remember { ms(null) }
    val moneyMs: Ms<Double> = remember { ms(0.0) }
    val dateMs: Ms<Date> = remember { ms(Date()) }
    val openDatePickerMs = remember { ms(false) }
    val isTagSelectionDialogOpenMs = remember { ms(false) }
    val isCostMs = remember {
        ms(false)
    }
    val selectedTagListMs: Ms<List<Tag>> = remember { ms(emptyList()) }
    Surface {
        Column(modifier = modifier.padding(horizontal = 10.dp)) {
            OutlinedTextField(
                value = DateUtils.displayDateFormat.format(dateMs.value),
                modifier = Modifier.fillMaxWidth(),
                onValueChange = {},
                readOnly = true,
                label = {
                    Text("Date")
                },
                trailingIcon = {
                    IconButton(
                        onClick = { openDatePickerMs.value = true },
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.calendar_24),
                            contentDescription = "pick date",
                            tint = MaterialTheme.colors.primary
                        )
                    }
                }
            )
            InputField(
                "Money",
                value = moneyMs.value.toString(),
                isNumber = true,
                modifier = Modifier.fillMaxWidth()
            ) { newStr ->
                val oldVl = moneyMs.value
                moneyMs.value = newStr.toDoubleOrNull() ?: oldVl
            }
            InputField(
                title = "Detail",
                value = detailMs.value ?: "",
                modifier = Modifier.fillMaxWidth()
            ) {
                detailMs.value = it
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
                ) {
                    TagListView(
                        tags = selectedTagListMs.value,
                        modifier = Modifier
                            .weight(9f)
                            .padding(start = 10.dp, end = 5.dp),
                        onCloseTag = {
                            //TODO on close tag
                            selectedTagListMs.value = selectedTagListMs.value - it
                        }
                    )

                    IconButton(
                        onClick = {
                            isTagSelectionDialogOpenMs.value = true
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
                val newEntry = DbEntryWithTags(
                    entry = DbEntry(
                        id = UUID.randomUUID().toString(),
                        money = moneyMs.value,
                        detail = detailMs.value,
                        dateTime = dateMs.value.time,
                        isUploaded = false.toInt(),
                        isCost = isCostMs.value.toInt(),
                    ),
                    tags = tags.map { it.toDbTag() }
                )
                onOk(newEntry)
            }, modifier = Modifier.align(Alignment.End)) {
                Text("Ok")
            }
        }
        if (openDatePickerMs.value) {
            MDatePickerDialog(currentDate = dateMs.value, onDatePick = {
                dateMs.value = it
            }, onDismiss = {
                openDatePickerMs.value = false
            })
        }

        if (isTagSelectionDialogOpenMs.value) {
            TagPickerDialog(
                tags = tags,
                initSelectedList = selectedTagListMs.value,
                onDone = {
                    selectedTagListMs.value = it
                },
                onDismiss = {
                    isTagSelectionDialogOpenMs.value = false
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewEntryCreationScreen() {
    PCRTheme {
        EntryCreationScreen(
            modifier = Modifier.fillMaxWidth(),
            tags = (1..4).map { DbTag.random() },
            onOk = {}
        )
    }
}


