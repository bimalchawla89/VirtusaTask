package com.example.task.data.network

import android.content.Context
import io.mockk.mockk
import io.mockk.spyk
import org.junit.Before
import org.junit.Test

class AppApiHelperTest {

    lateinit var sut: AppApiHelper
    val mContextMock = mockk<Context>()

    @Before
    fun setUp() {
        sut = spyk(AppApiHelper(mContextMock))
    }

    @Test
    fun test(){
    }
}