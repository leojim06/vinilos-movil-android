package com.example.vinilosapp.presentation.vm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.vinilosapp.TestUtils.Mockito
import com.example.vinilosapp.application.VinilosUniandesApplication
import com.example.vinilosapp.data.model.Album
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock

/**
 * [AlbumSearchViewModelTest] class to test search view model
 * **/
@RunWith(AndroidJUnit4::class)
class AlbumSearchViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var vm: AlbumSearchViewModel


    private val observer: Observer<List<Album>> = Mockito().mock()


    @Before
    fun setUp() {
        vm =
            AlbumSearchViewModel(VinilosUniandesApplication.provideRepository(ApplicationProvider.getApplicationContext()))
    }

    @Test
    fun test_model_sate_change() {
        vm.albums.observeForever(observer)
        vm.searchAlbums("Opera")

        org.mockito.Mockito.verify(observer).onChanged(vm.albums.value)
    }

    @Test
    fun testForReturnValues() {
        vm.albums.observeForever(observer)
        vm.searchAlbums("Opera")

        vm.albums.value?.isNotEmpty()?.let { assert(it) }
    }


}