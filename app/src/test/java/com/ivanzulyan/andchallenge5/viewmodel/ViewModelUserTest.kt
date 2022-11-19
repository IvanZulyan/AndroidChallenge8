package com.ivanzulyan.andchallenge5.viewmodel

import com.ivanzulyan.andchallenge5.model.DataUser
import com.ivanzulyan.andchallenge5.model.ResponseUserItem
import com.ivanzulyan.andchallenge5.network.RestfulUser
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Call

class ViewModelUserTest {
    lateinit var service: RestfulUser

    @Before
    fun setUp() {
        service = mockk()
    }

    @Test
    fun getAllUser() : Unit = runBlocking {

        val responGetAllUser = mockk<Call<List<ResponseUserItem>>>()

        every {
            runBlocking {
                service.getAllUser()
            }
        } returns responGetAllUser

        val result = service.getAllUser()

        verify {
            runBlocking {
                service.getAllUser()
            }
        }
        Assert.assertEquals(result,responGetAllUser)
    }

    @Test
    fun postUser() : Unit = runBlocking {

        val responPostUser = mockk<Call<ResponseUserItem>>()

        every {
            runBlocking {
                service.postUser(DataUser("Ivan Zulyan","ivanzp","1331","20","Bekasi"))
            }
        } returns responPostUser

        val result = service.postUser(DataUser("Ivan Zulyan","ivanzp","1331","20","Bekasi"))

        verify {
            runBlocking {
                service.postUser(DataUser("Ivan Zulyan","ivanzp","1331","20","Bekasi"))
            }
        }
        Assert.assertEquals(result,responPostUser)
    }

    @Test
    fun updateUser() : Unit = runBlocking {

        val responUpdateUser = mockk<Call<ResponseUserItem>>()

        every {
            runBlocking {
                service.updateUser("13",DataUser("Ivan Zulyan","ivanzp","1331","20","Bekasi"))
            }
        } returns responUpdateUser

        val result = service.updateUser("13",DataUser("Ivan Zulyan","ivanzp","1331","20","Bekasi"))

        verify {
            runBlocking {
                service.updateUser("13",DataUser("Ivan Zulyan","ivanzp","1331","20","Bekasi"))
            }
        }
        Assert.assertEquals(result,responUpdateUser)
    }

    @Test
    fun getUserById() : Unit = runBlocking {

        val responGetUserById = mockk<Call<ResponseUserItem>>()

        every {
            runBlocking {
                service.getUserById("13")
            }
        } returns responGetUserById

        val result = service.getUserById("13")

        verify {
            runBlocking {
                service.getUserById("13")
            }
        }
        Assert.assertEquals(result,responGetUserById)
    }
}