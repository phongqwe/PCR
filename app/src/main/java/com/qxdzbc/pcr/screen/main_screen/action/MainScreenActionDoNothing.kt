package com.qxdzbc.pcr.screen.main_screen.action

import com.github.michaelbull.result.Ok
import com.qxdzbc.pcr.common.Rs
import com.qxdzbc.pcr.err.ErrorReport
import com.qxdzbc.pcr.state.container.filter.EntryFilter
import com.qxdzbc.pcr.state.model.Tag
import java.util.*

object MainScreenActionDoNothing : MainScreenAction {
    override fun switchTheme(isDark: Boolean) {
    }

    override fun filter(filter: EntryFilter) {
    }

    override fun createEntryAndWriteToDb(
        date: Date,
        money: Double,
        detail: String?,
        tags: List<Tag>,
        isCost: Boolean
    ): Rs<Unit, ErrorReport> {
        return Ok(Unit)
    }
}
