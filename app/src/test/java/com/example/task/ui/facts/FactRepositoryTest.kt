package com.example.task.ui.facts

import com.example.task.TestDataClassGenerator
import com.example.task.data.network.ApiHelper
import com.example.task.data.network.Resource
import com.example.task.data.network.model.FactResponse
import com.example.task.data.repos.FactRepository
import com.example.task.util.NO_INTERNET_CONNECTION
import com.example.task.util.coroutines.TestDispatcherProvider
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test


class FactRepositoryTest {


    lateinit var apiHelper: ApiHelper

    lateinit var repoUnderTest: FactRepository

    protected val testDataClassGenerator: TestDataClassGenerator = TestDataClassGenerator()

    @Before
    fun setTup() {
        apiHelper = mockk(relaxUnitFun = true)
        val appDispatcher = TestDispatcherProvider()
        repoUnderTest = FactRepository(appDispatcher, apiHelper)
    }

    @Test
    fun `facts , success response`() {
        //Given
        coEvery {
            apiHelper.getFacts()
        } returns testDataClassGenerator.getSuccessFactsResponse()

        //when
        var result: Resource<FactResponse>? = null
        runBlocking  {
            result =  repoUnderTest.getFacts()
        }
        //then
        Assert.assertEquals(true, result!!.data!!.status)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `facts ,response false`() {
        //Given
        coEvery {
            apiHelper.getFacts()
        } returns testDataClassGenerator.getFailedFactsResponse()
        //when
        var result: Resource<FactResponse>? = null

        runBlockingTest  {
            result =  repoUnderTest.getFacts()
        }
        //then
        Assert.assertEquals("Something went Wrong", result!!.errorDescription)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `facts , no network ,assert no network error description`() {

        //Given
        coEvery {
            apiHelper.getFacts()
        } returns testDataClassGenerator.getNoNetworkError() as Resource<FactResponse>
        //when
        var result: Resource<FactResponse>? = null
        runBlocking  {
            result =  repoUnderTest.getFacts()
        }
        //then
        Assert.assertEquals(NO_INTERNET_CONNECTION, result!!.errorDescription)
    }
}