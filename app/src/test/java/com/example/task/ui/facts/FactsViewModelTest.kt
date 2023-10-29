package com.example.task.ui.facts

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.task.TestDataClassGenerator
import com.example.task.util.NO_INTERNET_CONNECTION
import com.example.task.util.coroutines.CoroutineTestRule
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class FactsViewModelTest {
     val testDataClassGenerator: TestDataClassGenerator = TestDataClassGenerator()

    private lateinit var viewModelUnderTest: FactsViewModel

    private lateinit var factsUseCase: FactsUseCase

    @ExperimentalCoroutinesApi
    @get:Rule
     val mainCoroutineRule = CoroutineTestRule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        factsUseCase = mockk<FactsUseCase>()
        viewModelUnderTest = FactsViewModel(factsUseCase)
    }

    @Test
    fun `facts,success full response,return success`() = runBlocking{
        //Given
        val response = testDataClassGenerator.getSuccessFactsResponse();
        coEvery {
            factsUseCase.getFacts()
        } returns response
        //when
        viewModelUnderTest.getFactsData()
        //then
        val result = viewModelUnderTest.factsData.value!!.data
        assertEquals(response.data, result)
    }

    @Test
    fun `facts,fail response,return failure`() = runBlocking{
        //Given
        val response = testDataClassGenerator.getFailedFactsResponse();
        coEvery {
            factsUseCase.getFacts()
        } returns response
        //when
        viewModelUnderTest.getFactsData()
        //then
        val result = viewModelUnderTest.factsData.value!!.data!!.status
        assertEquals(false, result)
    }


    @Test
    fun `facts, Data Error No network,assert showErrorDialog`() {
        //Given
        val response = testDataClassGenerator.getNoNetworkError();
        coEvery {
            factsUseCase.getFacts()
        } returns response
        //when
        viewModelUnderTest.getFactsData()
        //then
        val result = viewModelUnderTest.showMessageDialog.value
        assertEquals("Something went Wrong", result!!.errorDescription)
    }
}