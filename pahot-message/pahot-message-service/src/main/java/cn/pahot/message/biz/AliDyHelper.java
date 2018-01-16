package cn.pahot.message.biz;

import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

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
public class AliDyHelper {
	// 产品名称:云通信短信API产品,开发者无需替换
	private static final String product = "Dysmsapi";
	// 产品域名,开发者无需替换
	private static final String domain = "dysmsapi.aliyuncs.com";

	// TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	private static final String accessKeyId = "23588105";
	private static final String accessKeySecret = "ba2e9eeca31c76f7384ae911315c74ca";
	private static IAcsClient acsClient;

	private static AliDyHelper instance;

	private AliDyHelper() throws ClientException {
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		// 初始化acsClient,暂不支持region化
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		acsClient = new DefaultAcsClient(profile);
	}

	public static AliDyHelper getInstance() throws ClientException{
		if (instance == null) {
			synchronized (AliDyHelper.class) {
				if (instance == null) {
					instance = new AliDyHelper();
				}
			}
		}
		return instance;
	}
	
	/**
	 * 发送短信
	 * @param templateId
	 * @param phones
	 * @param content
	 * @param signName
	 * @return
	 * @throws ServerException
	 * @throws ClientException
	 */
	public SendSmsResponse sendSms(String templateId,String phones,String content,String signName) throws ServerException, ClientException {
		//组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(phones);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateId);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        request.setTemplateParam(content);

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
//        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        return sendSmsResponse;
	}
	
	public static void main(String[] args) {
		JSONObject jo = new JSONObject();
		jo.put("goodsName", "测试商品");
		jo.put("supShopName", "供货商");
		jo.put("salShopName", "销售商");
		jo.put("num", "1");
		jo.put("money", "12.8元");
		jo.put("orderCode", "10000000");
		jo.put("payDate", "2017-12-08");
		try {
			AliDyHelper.getInstance().sendSms("SMS_114845010", "13458585957", jo.toJSONString(), "耙货");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
