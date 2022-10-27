package com.qxdzbc.pcr.state.container

import com.qxdzbc.pcr.state.model.Tag

abstract class AbsTagContainer(private val m: Map<String, Tag>):
    TagContainer,
    Map<String, Tag> by m {
    override val allTags: List<Tag>
        get() = this.m.values.toList()
}
