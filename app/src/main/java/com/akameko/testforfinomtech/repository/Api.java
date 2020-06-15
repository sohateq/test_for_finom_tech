package com.akameko.testforfinomtech.repository;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface Api {
    @GET("latest")
    Single<Latest> loadRates();
}
