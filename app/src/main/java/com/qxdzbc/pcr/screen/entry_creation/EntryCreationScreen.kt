package com.qxdzbc.pcr.screen.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qxdzbc.pcr.R
import com.qxdzbc.pcr.common.MBox
import com.qxdzbc.pcr.common.Ms
import com.qxdzbc.pcr.common.StateUtils.ms
import com.qxdzbc.pcr.screen.common.InputField
import com.qxdzbc.pcr.screen.main_screen.date_filter_view.MDatePickerDialog
import com.qxdzbc.pcr.ui.theme.PCRTheme
import com.qxdzbc.pcr.util.DateUtils
import java.util.*

@Composable
fun EntryCreationScreen(
    modifier: Modifier = Modifier
) {
    val detailMs: Ms<String?> = remember { ms(null) }
    val moneyMs: Ms<Double> = remember { ms(0.0) }
    val dateMs: Ms<Date> = remember { ms(Date()) }
    val openDatePickerMs = remember { ms(false) }
    Surface {
        Column(modifier = modifier.padding(horizontal =10.dp)) {
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
        }
        if(openDatePickerMs.value){
            MDatePickerDialog(currentDate = dateMs.value, onDatePick = {
                dateMs.value = it
            }, onDismiss = {
                openDatePickerMs.value=false
            })
        }
    }
}

@Preview
@Composable
fun PreviewEntryCreationScreen() {
    PCRTheme {
        EntryCreationScreen(modifier = Modifier.fillMaxWidth())
    }
}


