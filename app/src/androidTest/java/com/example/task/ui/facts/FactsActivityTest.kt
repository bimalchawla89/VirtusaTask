package com.example.task.ui.facts

import androidx.activity.ComponentActivity
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.example.task.ui.BaseInstrument
import org.junit.Before
import org.junit.Rule


@ExperimentalFoundationApi
class FactsActivityTest : BaseInstrument(){


    @get : Rule
    val composeTestRule = createAndroidComposeRule(FactsActivity::class.java)
    lateinit var activity: ComponentActivity

    @Before
    override fun setUp(){
        activity=composeTestRule.activity
    }


}