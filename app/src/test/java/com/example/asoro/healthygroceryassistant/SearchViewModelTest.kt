package com.example.asoro.healthygroceryassistant

import com.example.asoro.healthygroceryassistant.ui.search.SearchView
import org.junit.Test
import org.mockito.Mockito.mock

class SearchViewModelTest {


    private val searchView = mock(SearchView::class.java)


    /**
     * Test error handling.

     * @throws Exception
     * * 		the exception
     */
    @Test
    @Throws(Exception::class)
    fun testErrorHandling() {
//        val error = Response.error<Categories>(HttpURLConnection.HTTP_NOT_FOUND,
//                ResponseBody.create(MediaType.parse("application/json"), ""))
//        initResponse(error)
//        loadCategories()
//        checkErrorBehavior()
    }


    /**
     * Test error handling.

     * @throws Exception
     * * 		the exception
     */
    @Test
    @Throws(Exception::class)
    fun testRetrofitExceptionHandling() {
//        val observable = Observable.error(Throwable())
//        `when`(mMediaLibraryRepository.getCategoriesPageRx()).thenReturn(observable)
//        loadCategories()
//        checkErrorBehavior()
    }

}