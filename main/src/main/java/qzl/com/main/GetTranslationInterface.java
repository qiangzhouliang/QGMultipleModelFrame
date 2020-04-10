package qzl.com.main;


import java.util.Map;

import io.reactivex.Observable;
import qzl.com.main.mvp.model.HomeBean;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface GetTranslationInterface {
    // 注解里传入网络请求的部分URL地址
    @GET("index")
    // getCall()是接受网络请求数据的方法
    Observable<HomeBean> getCall(@QueryMap Map<String, String> map);
}