package com.qxdzbc.pcr.screen

import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.err.OtherErrors

/**
 * Display multiple errors dialog. When the dialog is discarded, remove the err from the state.
 */
@Composable
fun ErrDisplay(
    errorList:List<ErrorReport>,
    removeErr:(err:ErrorReport)->Unit,
    modifier: Modifier = Modifier,
    testTag:String="testTag",
) {
    for((i,err) in errorList.withIndex()){
        AlertDialog(
            onDismissRequest = { removeErr(err) },
            title = {
                Text(text="Error")
            },
            text ={
                Text(text=err.toString())
            },
            confirmButton = {
                Button(onClick = {removeErr(err) }) {
                    Text("Ok")
                }
            },
            modifier=modifier
                .testTag(testTag)
            ,
        )
    }
}


@Preview
@Composable
fun ErrDisplayReview(){
    val l = listOf(
        OtherErrors.UnableToLogin.report(),
//        OtherErrors.UnableToLogin.report(),
    )
        ErrDisplay(
            errorList =l,
            removeErr = {},
            testTag = "A"
        )
}
