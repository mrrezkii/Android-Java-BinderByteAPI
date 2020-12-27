package com.esd.expeditioninfo.Network;

import com.esd.expeditioninfo.Pojo.BinderByteResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("track")
    Call<BinderByteResponse> tracking(@Query("api_key") String api_key,
                                      @Query("courier") String courier,
                                      @Query("awb") String awb);
}
