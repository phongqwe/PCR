package com.qxdzbc.pcr.state.container

import com.qxdzbc.pcr.state.model.Entry

abstract class AbsEntryContainer(
    private val m: Map<String, Entry>,
) : EntryContainer,Map<String, Entry> by m{
    override val allEntries: List<Entry>
        get() = m.values.toList()

}
