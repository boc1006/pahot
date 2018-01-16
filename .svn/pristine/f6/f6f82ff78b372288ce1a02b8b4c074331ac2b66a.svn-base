package cn.pahot.business.front.utils;

import com.boc.common.utils.httpclient.SimpleHttpParam;
import com.boc.common.utils.httpclient.SimpleHttpResult;

import java.util.HashMap;
import java.util.Map;

import static com.boc.common.utils.httpclient.SimpleHttpUtils.httpRequest;

/**
 * Created by sujian on 2017/6/8.
 */
public class WxHttpPostUtils {
    /**
     *
     * @param url
     * @param postData
     * @return
     */
    public static String getResult(String url, Map<String,Object> params,String postData){
        SimpleHttpParam param = new SimpleHttpParam(url);
        param.setCharSet("utf-8");
        param.setMethod("POST");
        if (null!=params){
            param.setParameters(params);
        }
//        param.addHeader("Content-Type","application/json");
//        param.addHeader("Content-Length",""+postData.length());
        param.setPostData(postData);
        SimpleHttpResult result = httpRequest(param);
        if(result==null || !result.isSuccess()){
            return null;
        }else{
            return result.getContent();
        }
    }

    public static Map<String,Object> urlToParams(String state){
        Map<String,Object> map = new HashMap();
        for (String str : state.split("&")) {
            String arr[] = str.split("=");
            if (arr.length==2){
                map.put(arr[0],arr[1]);
            }
        }
        return map;
    }
}
