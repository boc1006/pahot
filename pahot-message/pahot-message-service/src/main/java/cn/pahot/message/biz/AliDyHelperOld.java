package cn.pahot.message.biz;

import com.alibaba.fastjson.JSONObject;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;

/**
 * 阿里大鱼短信发送实现
 * <p>@Title: AliDyHelper.java 
 * <p>@Package cn.pahot.message.biz 
 * <p>@Description: TODO
 * <p>@author huangjie hj87080234@gmail.com   
 * <p>@date 2017年12月8日 下午7:52:29 
 * <p>@version V1.0
 * <p>Copyright © boc group.All Rights Reserved.
 */
public class AliDyHelperOld {
	private static final TaobaoClient client = new DefaultTaobaoClient("http://gw.api.taobao.com/router/rest", "23588105", "ba2e9eeca31c76f7384ae911315c74ca");
	
	/**
	 * 发送短信 
	 * @param templateId
	 * @param phones
	 * @param content
	 * @param signName
	 * @return
	 */
	public static AlibabaAliqinFcSmsNumSendResponse sendSms(String templateId,String phones,String content,String signName) {
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("123456");
        req.setSmsType("normal");
        req.setSmsFreeSignName(signName);
        req.setRecNum(phones);
        req.setSmsTemplateCode(templateId);
        req.setSmsParam(content);
        try {
			AlibabaAliqinFcSmsNumSendResponse res = client.execute(req);
			return res; 
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void main(String[] args) {
		JSONObject jo = new JSONObject();
		jo.put("code", "102400");
		jo.put("product", "耙货");
		sendSms("SMS_89150021","13458585957",jo.toJSONString(),"耙货");
	}
}
