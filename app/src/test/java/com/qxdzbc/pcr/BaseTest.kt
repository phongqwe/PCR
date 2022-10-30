package com.qxdzbc.pcr

import com.qxdzbc.pcr.TestSample
import org.junit.Before

open class BaseTest {
    lateinit var ts: TestSample

    @Before
    fun b() {
        ts = TestSample()
    }
}
