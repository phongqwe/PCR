package com.qxdzbc.pcr.screen.main_screen.date_filter_view

import android.app.DatePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.qxdzbc.pcr.util.DateUtils
import java.util.*

@Composable
fun MDatePickerDialog(
    currentDate:Date,
    dateType: DateType,
    onDatePick:(newDate:Date)->Unit,
    onDismiss:()->Unit,
) {
    val ctx = LocalContext.current
    val cc = Calendar.getInstance().apply {
        time = currentDate
    }
    val dialog=DatePickerDialog(
        ctx,
        { view, year, month, dayOfMonth ->
            val newDate =when(dateType) {
                DateType.Start -> DateUtils.createDateAtStart(year, month, dayOfMonth)
                DateType.End-> DateUtils.createDateAtEnd(year, month, dayOfMonth)
                else->DateUtils.createDate(year, month, dayOfMonth)
            }
            onDatePick(newDate)
            onDismiss()
        },
        cc.get(Calendar.YEAR),cc.get(Calendar.MONTH),cc.get(Calendar.DAY_OF_MONTH)
    )
    dialog.setOnDismissListener { onDismiss() }
    dialog.show()
}
