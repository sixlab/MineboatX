package cn.sixlab.mbx.core.common.util;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;

public class HttpUtil {

    private static OkHttpClient httpClient = new OkHttpClient();

    public static Map getJson(String url){
        final Request request = new Request.Builder()
                .url(url)
                .build();
        final Call call = httpClient.newCall(request);
        try {
            Response response = call.execute();
            String result = response.body().string();
            return JsonUtil.toBean(result, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
