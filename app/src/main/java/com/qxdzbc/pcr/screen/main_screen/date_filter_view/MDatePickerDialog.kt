package com.qxdzbc.pcr.screen.main_screen.date_filter_view

import android.app.DatePickerDialog
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.util.Calendar
import java.util.Date

@Composable
fun MDatePickerDialog(
    currentDate:Date,
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
            val calendar = Calendar.getInstance().apply {
                set(year,month,dayOfMonth)
            }
            val newDate =calendar.time
            onDatePick(newDate)
            onDismiss()
        },
        cc.get(Calendar.YEAR),cc.get(Calendar.MONTH),cc.get(Calendar.DAY_OF_MONTH)
    )
    dialog.setOnDismissListener { onDismiss() }
    dialog.show()
}
