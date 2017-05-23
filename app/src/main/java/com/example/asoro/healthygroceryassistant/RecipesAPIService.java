package com.example.asoro.healthygroceryassistant;

import com.example.asoro.healthygroceryassistant.model.Hits;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;



public interface RecipesAPIService {
	String APPLICATION_KEY ="976284a8a71cb2297532bb75787b5c4c";
	String APPLICATION_ID = "f187c826";

	String URL = "https://api.edamam.com/";

	@GET("search")
	Observable<Response<Hits>> getRecipes(@Query("app_id") String appId, @Query("app_key") String appKey,
			@Query("q") String query, @Query("diet") String diet, @Query("to") int number);


}
