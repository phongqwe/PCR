package com.qxdzbc.pcr.state

import com.qxdzbc.pcr.database.model.Entry

interface EntryContainer : Map<Long, Entry> {
    val allEntries: Collection<Entry>
}

class EntryContainerImp(
    private val m: Map<Long, Entry>
) : EntryContainer, Map<Long, Entry> by m {
    override val allEntries: Collection<Entry> get() = m.values
}
