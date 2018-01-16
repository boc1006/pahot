package cn.pahot.business.front.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.boc.common.exception.BizException;
import com.boc.common.metatype.DTO;
import com.boc.common.metatype.impl.BaseDTO;
import com.boc.common.utils.httpclient.SimpleHttpUtils;
import com.boc.common.utils.rsa.Base64;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 微信常用API
 * Created by sujian on 2017/6/8.
 */
public class WXUitls {

    //获取微信AccessToken(基础)
    private final static String atUrl="https://api.weixin.qq.com/cgi-bin/token";

    //获取微信用户信息
    private final static String goUrl="https://api.weixin.qq.com/sns/oauth2/access_token";

    //获取微信用户详细信息
    private final static String uiUrl="https://api.weixin.qq.com/sns/userinfo";

    //获取临时二维码地址
    private final static String qrUrl="https://api.weixin.qq.com/cgi-bin/qrcode/create";

    //获取图文列表接口
    private final static String newsUrl="https://api.weixin.qq.com/cgi-bin/material/batchget_material";

    //创建自定义菜单
    private final static String menuUrl="https://api.weixin.qq.com/cgi-bin/menu/create";

    //ticket换取二维码前缀
    private final static String ticketUrl="https://mp.weixin.qq.com/cgi-bin/showqrcode";

    //获取微信鉴权jsapiticket
    private final static String jsUrl="https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=";

    //发送模板消息
    private final static String templateUrl="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";

    //获取用户UnionId
    private final static String unionIdUrl="https://api.weixin.qq.com/cgi-bin/user/info?access_token=";

    //获取关注用户列表
    private final static String userListUrl="https://api.weixin.qq.com/cgi-bin/user/get";

    //发送客服消息地址
    private final static String sendKfMsgUrl="https://api.weixin.qq.com/cgi-bin/message/custom/send";

    public static String getJsApiTicket(String accessToken) throws BizException {
        String httpUrl=jsUrl+accessToken;
        String ret= SimpleHttpUtils.httpGet(httpUrl,new HashMap());
        if (ret!=null && ret.startsWith("{")){
            JSONObject object= JSONObject.parseObject(ret);
            if (object.containsKey("ticket")){
                String jt=object.getString("ticket");
                return jt;
            }else if (object.containsKey("errcode")){
                String errcode = object.get("errcode").toString();
                if ("42001".equalsIgnoreCase(errcode)
                        || "40014".equalsIgnoreCase(errcode)
                        || "40001".equalsIgnoreCase(errcode)){
                    String errmsg = (object.containsKey("errmsg")?object.get("errmsg").toString():"no errmsg");
                    throw BizException.INSTANCE.newInstance(errmsg);
                }else{

                }
            }
        }
        return null;
    }

    public static String getAccessToken(String params){
        String httpUrl = atUrl +"?grant_type=client_credential&"+params;
        String result=SimpleHttpUtils.httpGet(httpUrl,new HashMap());
        if (result!=null){
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.containsKey("access_token")){
                return jsonObject.getString("access_token");
            }
        }
        return null;
    }

    public static String getAccessToken(String appId,String appsecret){
        return getAccessToken("appid="+appId+"&secret="+appsecret);
    }

    /**
     * 网页授权获取openId,accesstoken(用户信息获取accessToken与基础的accessToken不同)
     * @param appId   公众号appId
     * @param appsecret   公众号appsecret
     * @param code      授权code
     * @return
     */
    public static Map<String,Object> getWxUser(String appId,String appsecret,String code){
        Map<String,Object> params = new HashMap<>();
        params.put("appid",appId);
        params.put("secret",appsecret);
        params.put("code",code);
        params.put("grant_type","authorization_code");
        String result = SimpleHttpUtils.httpGet(goUrl,params);
        if(result!=null){
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.containsKey("openid")){
                Map<String,Object> retMap = new HashMap<>();
                retMap.put("openid",jsonObject.getString("openid"));
                retMap.put("access_token",jsonObject.getString("access_token"));
                retMap.put("scope",jsonObject.getString("scope"));
                return retMap;
            }else{
                return null;
            }
        }
        return null;
    }

    public static String getWxUserOpenId(String appId,String appsecret,String code){
        Map<String,Object> result = getWxUser(appId,appsecret,code);
        if (result!=null && result.containsKey("openid")){
            return result.get("openid").toString();
        }
        return null;
    }

    /**
     * 获取微信用户UnionId(公众号)
     * @param accessToken 用户accessToken
     * @param openId 用户OpenId
     * @return Map 用户UnionId
     */
    public static Map<String,Object> getWxUnionId(String accessToken,String openId) throws BizException{
        //?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN
        Map<String,Object> params = new HashMap<>();
        params.put("access_token",accessToken);
        params.put("openid",openId);
        params.put("lang","zh_CN");
        String result = SimpleHttpUtils.httpGet(uiUrl,params);
        if (result!=null){
            JSONObject jsonObject= JSONObject.parseObject(result);
            if (jsonObject.containsKey("unionid")){
                if (jsonObject.containsKey("openid")) {
                    Map map = new HashMap<>();
                    if (jsonObject.containsKey("subscribe")) {
                        map.put("subscribe", jsonObject.get("subscribe"));
                    }
                    if (jsonObject.containsKey("nickname")) {
                        String nickname = jsonObject.get("nickname").toString();
                        map.put("nickname", Base64.encode(nickname.getBytes(Charset.forName("utf-8"))));
                    }
                    if (jsonObject.containsKey("openid")) {
                        map.put("openid", jsonObject.get("openid"));
                    }
                    if (jsonObject.containsKey("unionid")) {
                        map.put("unionid", jsonObject.get("unionid"));
                    }
                    if (jsonObject.containsKey("country")) {
                        map.put("country", jsonObject.get("country"));
                    }
                    if (jsonObject.containsKey("headimgurl")) {
                        map.put("headimgurl", jsonObject.get("headimgurl"));
                    }
                    if (jsonObject.containsKey("province")) {
                        map.put("province", jsonObject.get("province"));
                    }
                    if (jsonObject.containsKey("city")) {
                        map.put("city", jsonObject.get("city"));
                    }
                    if (jsonObject.containsKey("sex")) {
                        map.put("sex", jsonObject.get("sex"));
                    }
                    return map;
                }
            }else if (jsonObject.containsKey("errcode")){
                String errcode = jsonObject.get("errcode").toString();
                if ("42001".equalsIgnoreCase(errcode)
                        || "40014".equalsIgnoreCase(errcode)
                        || "40001".equalsIgnoreCase(errcode)){
                    String errmsg = (jsonObject.containsKey("errmsg")?jsonObject.get("errmsg").toString():"no errmsg");
                    throw BizException.INSTANCE.newInstance(errmsg);
                }
            }
        }
        return null;
    }

    /**
     * 获取临时二维码(关注扫描)
     * accessToken
     * sceneId
     * expire   过期时间
     * @return Map
     */
    /*public static Map<String,Object> getQrScene(String accessToken,Integer sceneId,Integer expire) throws BizException{
        //?access_token=TOKENPOST
        String body="{\"expire_seconds\":"+expire+",\"action_name\":\"QR_SCENE\",\"action_info\":{\"scene\":{\"scene_id\":"+sceneId+"}}}";
        String result = WxHttpPostUtils.getResult(qrUrl+"?access_token="+accessToken,null,body);
        if (result!=null){
            JSONObject jsonObject=new JSONObject(result);
            if (jsonObject.has("ticket")){
                Map<String,Object> retMap=new HashMap<>();
                retMap.put("ticket",jsonObject.getString("ticket"));
                retMap.put("url",jsonObject.getString("url"));
                return retMap;
            }else if (jsonObject.has("errcode")){
                String errcode = jsonObject.get("errcode").toString();
                if ("42001".equalsIgnoreCase(errcode)
                        || "40014".equalsIgnoreCase(errcode)
                        || "40001".equalsIgnoreCase(errcode)){
                    String errmsg = (jsonObject.has("errmsg")?jsonObject.get("errmsg").toString():"no errmsg");
                    AccessTokenInvaildException atie = new AccessTokenInvaildException(errmsg);
                    PluginFactory.getLogPlugin().error(WXUitls.class,"getQrScene>"+jsonObject.toString(),atie);
                    throw atie;
                }else{
                    PluginFactory.getLogPlugin().info(WXUitls.class,"getQrScene>"+jsonObject.toString());
                }
            }
        }
        return null;
    }*/

    /**
     * 获取永久二维码
     * @param accessToken 基础accessToken
     * @param sceneId 场景值ID
     * @return Map
     *             ticket
     *             url
     * @throws BizException
     */
    /*public static Map<String,Object> getLimitQrScene(String accessToken,Integer sceneId) throws BizException{
        String body = "{\"action_name\": \"QR_LIMIT_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": "+sceneId+"}}}";
        String result = WxHttpPostUtils.getResult(qrUrl+"?access_token="+accessToken,null,body);
        if (result!=null){
            JSONObject jsonObject=new JSONObject(result);
            if (jsonObject.has("ticket")){
                Map<String,Object> retMap=new HashMap<>();
                retMap.put("ticket",jsonObject.getString("ticket"));
                retMap.put("url",jsonObject.getString("url"));
                return retMap;
            }else if (jsonObject.has("errcode")){
                String errcode = jsonObject.get("errcode").toString();
                if ("42001".equalsIgnoreCase(errcode)
                        || "40014".equalsIgnoreCase(errcode)
                        || "40001".equalsIgnoreCase(errcode)){
                    String errmsg = (jsonObject.containsKey("errmsg")?jsonObject.get("errmsg").toString():"no errmsg");
                    throw BizException.INSTANCE.newInstance(errmsg);
                }else{
                    PluginFactory.getLogPlugin().info(WXUitls.class,"getLimitQrScene>"+jsonObject.toString());
                }
            }
        }
        return null;
    }*/

    //获取微信图文详情
    /*public static Map<String,Object> getNewsList(String accessToken,Integer offset,Integer count) throws AccessTokenInvaildException{
        String result = WxHttpPostUtils.getResult(newsUrl+"?access_token="+accessToken,
                null,WXDataUtils.createNewsRequestData(offset,count));
        if (result!=null){
            JSONObject jsonObject=new JSONObject(result);
            Map<String,Object> retMap = new HashMap<>();
            if (jsonObject.has("item")){
                JSONArray array=jsonObject.getJSONArray("item");
                if (array.length()>0){
                    //有图文详情
                    //获取第一个
                    JSONObject item=array.getJSONObject(0);
                    retMap.put("media_id",item.getString("media_id"));
                    JSONObject content = item.getJSONObject("content");
                    JSONArray newsItem = content.getJSONArray("news_item");
                    List<Map<String,Object>> itemList = new LinkedList<>();
                    int len = newsItem.length();
                    for(int iter=0;iter<len;iter++){
                        Map<String,Object> itemMap = new HashMap<>();
                        JSONObject itemObject=newsItem.getJSONObject(iter);
                        itemMap.put("Title",itemObject.getString("title"));
                        itemMap.put("Description",itemObject.getString("digest"));
                        itemMap.put("PicUrl",itemObject.getString("thumb_url"));
                        itemMap.put("Url",itemObject.getString("url"));
                        itemList.add(itemMap);
                    }
                    retMap.put("itemList",itemList);
                }
            }else if (jsonObject.has("errcode")){
                String errcode = jsonObject.get("errcode").toString();
                if ("42001".equalsIgnoreCase(errcode)
                        || "40014".equalsIgnoreCase(errcode)
                        || "40001".equalsIgnoreCase(errcode)){
                    String errmsg = (jsonObject.has("errmsg")?jsonObject.get("errmsg").toString():"no errmsg");
                    AccessTokenInvaildException atie = new AccessTokenInvaildException(errmsg);
                    PluginFactory.getLogPlugin().error(WXUitls.class,"getNewsList>"+jsonObject.toString(),atie);
                    throw atie;
                }else{
                    PluginFactory.getLogPlugin().info(WXUitls.class,"getNewsList>"+jsonObject.toString());
                }
            }
            return retMap;
        }
        return null;
    }*/

    /**
     * 创建微信公众号自定义菜单
     * @param accessToken 基础accessToken
     * @param jsonMenu 菜单
     * @return boolean
     */
    public static boolean createMenu(String accessToken,String jsonMenu) throws BizException{
        String result = WxHttpPostUtils.getResult(menuUrl+"?access_token="+accessToken,
                null,jsonMenu);
        if (result!=null){
            JSONObject object= JSONObject.parseObject(result);
            String errcode = object.get("errcode").toString();
            if ("0".equals(errcode)){
                return true;
            }else if ("42001".equalsIgnoreCase(errcode)
                    || "40014".equalsIgnoreCase(errcode)
                    || "40001".equalsIgnoreCase(errcode)){
                String errmsg = (object.containsKey("errmsg")?object.get("errmsg").toString():"no errmsg");
                throw BizException.INSTANCE.newInstance(errmsg);
            }else{
            }
        }
        return false;
    }

     public static String getQrUrl(String ticket){
     return ticketUrl+"?ticket="+ticket;
     }

     /**
     * 发送模板消息
     * touser	是	接收者openid
         template_id	是	模板ID
         url	否	模板跳转链接
         miniprogram	否
         跳小程序所需数据，不需跳小程序可不用传该数据
         appid	是
         所需跳转到的小程序appid（该小程序appid必须与发模板消息的公众号是绑定关联关系）
         pagepath	是
         所需跳转到小程序的具体页面路径，支持带参数,（示例index?foo=bar）
         data	是	模板数据
     * @param accessToken 基础access_token
     * @param touser       发送的用户OpenId
     * @param templateId   模板ID
     * @param url           模板跳转链接
     * @param miniProgram  小程序配置
     * @param data          模板数据
     * @return boolean    true/false
     */
    /*public static boolean sendTemplate(String accessToken, String touser, String templateId, String url, WXMap miniProgram, WXMap data) throws BizException{
        String requestUrl = templateUrl+accessToken;
        WXMap map = new WXMap()
                .put("touser",touser)
                .put("template_id",templateId)
                .put("url",url)
                .put("miniprogram",miniProgram)
                .put("data",data);
        String result= WxHttpPostUtils.getResult(requestUrl,null, net.sf.json.JSONObject.fromObject(map).toString());
        if (result!=null){
            JSONObject object = new JSONObject(result);
            if (object.has("errmsg")){
                String errcode = object.get("errcode").toString();
                if ("42001".equalsIgnoreCase(errcode)
                        || "40014".equalsIgnoreCase(errcode)
                        || "40001".equalsIgnoreCase(errcode)){
                    String errmsg = (object.has("errmsg")?object.get("errmsg").toString():"no errmsg");
                    AccessTokenInvaildException atie = new AccessTokenInvaildException(errmsg);
                    PluginFactory.getLogPlugin().error(WXUitls.class,"sendTemplate>"+object.toString(),atie);
                    throw atie;
                }
                PluginFactory.getLogPlugin().info(WXUitls.class,"sendTemplate>"+object.toString());
                return "ok".equals(object.getString("errmsg"));
            }
        }
        return false;
    }*/

    /**
     * 获取用户UnionId
     * @param accessToken 基础at
     * @param openId 用户openId
     * @return String
     *              UnionId
     * @throws BizException
     */
    public static String loadUnionIdByOpenId(String accessToken,String openId) throws BizException{
        Map<String,Object> userInfo = loadUserInfoByOpenId(accessToken,openId);
        if (userInfo.containsKey("unionid")){
            return userInfo.get("unionid").toString();
        }
        return "";
    }

    /**
     * 获取用户NickName
     * @param accessToken 基础at
     * @param openId 用户openId
     * @return String
     *              NickName
     * @throws BizException
     */
    public static String loadNickNameByOpenId(String accessToken,String openId) throws BizException{
        Map<String,Object> userInfo = loadUserInfoByOpenId(accessToken,openId);
        if (userInfo.containsKey("nickname")){
            return userInfo.get("nickname").toString();
        }
        return "";
    }

    /**
     * {
     "subscribe": 1,
     "openid": "o6_bmjrPTlm6_2sgVt7hMZOPfL2M",
     "nickname": "Band",
     "sex": 1,
     "language": "zh_CN",
     "city": "广州",
     "province": "广东",
     "country": "中国",
     "headimgurl":  "http://wx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4
     eMsv84eavHiaiceqxibJxCfHe/0",
     "subscribe_time": 1382694957,
     "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
     "remark": "",
     "groupid": 0,
     "tagid_list":[128,2]
     }
     * @param accessToken
     * @param openId
     * @return
     * @throws BizException
     */
    public static Map<String,Object> loadUserInfoByOpenId(String accessToken,String openId) throws BizException{
        String loadUrl = unionIdUrl+accessToken+"&openid="+openId;
        String result = SimpleHttpUtils.httpGet(loadUrl,new HashMap());
        if (result!=null){
            JSONObject object = JSONObject.parseObject(result);
            if (object.containsKey("openid")){
                Map map = new HashMap<>();
                if (object.containsKey("subscribe")){
                  map.put("subscribe",object.get("subscribe"));
                }
                if (object.containsKey("nickname")){
                    String nickname = object.get("nickname").toString();
                  map.put("nickname",Base64.encode(nickname.getBytes(Charset.forName("utf-8"))));
                }
                if (object.containsKey("openid")){
                  map.put("openid",object.get("openid"));
                }
                if (object.containsKey("unionid")){
                  map.put("unionid",object.get("unionid"));
                }
                if (object.containsKey("country")){
                    map.put("country",object.get("country"));
                }
                if (object.containsKey("headimgurl")){
                    map.put("headimgurl",object.get("headimgurl"));
                }
                if (object.containsKey("province")){
                    map.put("province",object.get("province"));
                }
                if (object.containsKey("city")){
                    map.put("city",object.get("city"));
                }
                if (object.containsKey("sex")){
                    map.put("sex",object.get("sex"));
                }
                return map;
            }else if (object.containsKey("errcode")){
                String errcode = object.get("errcode").toString();
                if ("42001".equalsIgnoreCase(errcode)
                        || "40014".equalsIgnoreCase(errcode)
                        || "40001".equalsIgnoreCase(errcode)){
                    String errmsg = (object.containsKey("errmsg")?object.get("errmsg").toString():"no errmsg");
                    throw BizException.INSTANCE.newInstance(errmsg);
                }else{
                }
            }
        }
        return new HashMap<>();
    }

    private static List<String> getAllOpenId(JSONArray array){
        List<String> list = new LinkedList<>();
        if (array!=null){
            Integer len = array.size();
            for (int iter = 0; iter < len; iter++) {
                list.add(array.getString(iter));
            }
        }
        return list;
    }

    /**
     * 获取关注用户openId
     * @param accessToken 基础accessToken
     * @param nextOpenId 起始openId
     * @return WXMap
     *             total      总数
     *             count      本次返回条数
     *             openIdList openId列表
     *             netOpenid  下次请求起始
     * @throws BizException
     */
    public static DTO mpUserList(String accessToken,String nextOpenId) throws BizException{
        String url = userListUrl+"?access_token="+accessToken;
        if (nextOpenId!=null && !"empty".equalsIgnoreCase(nextOpenId)){
            url += "&next_openid="+nextOpenId;
        }
        String result = SimpleHttpUtils.httpGet(url,new HashMap());
        if (result!=null){
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.containsKey("total")){
                DTO ret = new BaseDTO();
                ret.put("total",jsonObject.getIntValue("total"));
                ret.put("count",jsonObject.getIntValue("count"));
                ret.put("openIdList",getAllOpenId(jsonObject.getJSONArray("data")));
                ret.put("nextOpenid",jsonObject.getString("next_openid"));
                return ret;
            }else if (jsonObject.containsKey("errcode")){
                String errcode = jsonObject.get("errcode").toString();
                if ("42001".equalsIgnoreCase(errcode)
                        || "40014".equalsIgnoreCase(errcode)
                        || "40001".equalsIgnoreCase(errcode)){
                    String errmsg = (jsonObject.containsKey("errmsg")?jsonObject.get("errmsg").toString():"no errmsg");
                    throw BizException.INSTANCE.newInstance(errmsg);
                }else{
                }
            }
        }
        return new BaseDTO();
    }

    public static boolean sendKfMsg(String accessToken,String postData) throws BizException {
        String httUrl = sendKfMsgUrl+"?access_token="+accessToken;
        String result=WxHttpPostUtils.getResult(httUrl,null,postData);
        if (result!=null){
            JSONObject jsonObject = JSONObject.parseObject(result);
            if (jsonObject.containsKey("errcode")){
                String errcode = jsonObject.get("errcode").toString();
                if ("0".equalsIgnoreCase(errcode)){
                    return true;
                }
                if ("42001".equalsIgnoreCase(errcode)
                        || "40014".equalsIgnoreCase(errcode)
                        || "40001".equalsIgnoreCase(errcode)){
                    String errmsg = (jsonObject.containsKey("errmsg")?jsonObject.get("errmsg").toString():"no errmsg");
                    throw BizException.INSTANCE.newInstance(errmsg);
                }else{
                }
            }
        }
        return false;
    }

    public static boolean sendKfListNewsMsg(String accessToken,String openid,List<DTO> wxMaps){
        StringBuffer buffer = new StringBuffer();
        boolean first = true;
        for (DTO wxMap : wxMaps) {
            if (!first){
                buffer.append(",");
            }else{
                first = false;
            }
            buffer.append("{\"title\":\""+wxMap.getAsString("title")+"\",\"description\":\""+wxMap.get("desc")+"\",\"url\":\""+wxMap.getString("url")+"\",\"picurl\":\""+wxMap.getString("picurl")+"\"}");
        }
        String newsMsg = "{\"touser\":\""+openid+"\",\"msgtype\":\"news\",\"news\":{\"articles\": ["+buffer.toString()+"]}}";
        return sendKfMsg(accessToken,newsMsg);
    }

    public static boolean sendKfNewsMsg(String accessToken,String openid,String mediaId){
        String newsMsg = "{\"touser\":\""+openid+"\",\"msgtype\":\"mpnews\",\"mpnews\":{\"media_id\":\""+mediaId+"\"}}";
        return sendKfMsg(accessToken,newsMsg);
    }

    public static boolean sendKfMusicMsg(String accessToken,String openid,String title,String desc,String musicurl,String hqmusicurl,String tmediaId){
        String musicMsg="{\"touser\":\""+openid+"\",\"msgtype\":\"music\",\"music\":{\"title\":\""+title+"\",\"description\":" +
                "\""+desc+"\",\"musicurl\":\""+musicurl+"\",\"hqmusicurl\":\""+hqmusicurl+"\",\"thumb_media_id\":\""+tmediaId+"\"}}";
        return sendKfMsg(accessToken,musicMsg);
    }

    public static boolean sendKfVideoMsg(String accessToken,String openid,String mediaId,String tmediaId,String title,String desc){
        String videoMsg="{\"touser\":\""+openid+"\",\"msgtype\":\"video\",\"video\":{\"media_id\":\""+mediaId+"\",\"thumb_media_id\":\""+tmediaId+"\",\"title\":\""+title+"\",\"description\":\""+desc+"\"}}";
        return sendKfMsg(accessToken,videoMsg);
    }

    public static boolean sendKfVoiceMsg(String accessToken,String openId,String  mediaId){
        String voiceMsg = "{\"touser\":\""+openId+"\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\""+mediaId+"\"}}";
        return sendKfMsg(accessToken,voiceMsg);
    }

    public static boolean sendKfImgMsg(String accessToken,String openId,String  mediaId){
        String imgMsg = "{\"touser\":\""+openId+"\",\"msgtype\":\"image\",\"image\":{\"media_id\":\""+mediaId+"\"}}";
        return sendKfMsg(accessToken,imgMsg);
    }

    public static boolean sendKfTextMsg(String accessToken,String openId,String text) throws BizException{
        String textMsg = "{\"touser\":\""+openId+"\",\"msgtype\":\"text\",\"text\":{\"content\":\""+text+"\"}}";
        return sendKfMsg(accessToken,textMsg);
    }
}
