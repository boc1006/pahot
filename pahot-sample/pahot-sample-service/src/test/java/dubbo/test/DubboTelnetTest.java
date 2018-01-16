package dubbo.test;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.alibaba.dubbo.common.utils.PojoUtils;
import com.alibaba.dubbo.common.utils.ReflectUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.boc.common.metatype.DTO;
import com.boc.common.metatype.impl.BaseDTO;
import com.boc.common.metatype.impl.SessionDTO;

import cn.pahot.sample.entity.UserEntity;
import cn.pahot.sample.facade.SampleFacade;

public class DubboTelnetTest {
	
	public static void main(String[] ar) {
		String message = "cn.pahot.sample.facade.SampleFacade.doBussiness({\"id\":\"100\"})";
        int i = message.indexOf("(");
        if (i < 0 || !message.endsWith(")")) {
            //return "Invalid parameters, format: service.method(args)";
        }
        String method = message.substring(0, i).trim();
        String args = message.substring(i + 1, message.length() - 1).trim();
        i = method.lastIndexOf(".");
        if (i >= 0) {
            method = method.substring(i + 1).trim();
        }
        List<Object> list=null;
        try {
            list = JSON.parseArray("[" + args + "]", Object.class);
        } catch (Throwable t) {
            //return "Invalid json argument, cause: " + t.getMessage();
        }
        Method[] methods = SampleFacade.class.getMethods();
        for (Method m : methods) {
            if ( m.getName().equals(method)) {
            	Object[] array = PojoUtils.realize(list.toArray(), m.getParameterTypes(), m.getGenericParameterTypes());
            	for(int idx = 0 ;idx < m.getParameterTypes().length ; idx ++) {
            		Class<?> cla = m.getParameterTypes()[idx];
            		if(cla.getName().equals("com.boc.common.metatype.DTO")) {
            			SessionDTO<String, String> dto = JSONObject.parseObject(array[idx].toString(), SessionDTO.class);
            			array[idx] = dto;
            			System.out.println("====>"+JSONObject.toJSONString(dto));
            		}
            	}
            	System.out.println(array);
            }
        }
        DTO<String,String> dto = new BaseDTO();
        dto.put("id", "1");
        UserEntity ue = com.boc.common.utils.PojoUtils.realize(dto, UserEntity.class);
        System.out.println(JSONObject.toJSONString(ue));
        System.out.println(com.boc.common.utils.PojoUtils.realize(ue, DTO.class));
//        isMatch(UserEntity.class.getMethods().,list);
	}
	
	private static boolean isMatch(Class<?>[] types, List<Object> args) {
        if (types.length != args.size()) {
            return false;
        }
        for (int i = 0; i < types.length; i++) {
            Class<?> type = types[i];
            Object arg = args.get(i);
            if (ReflectUtils.isPrimitive(arg.getClass())) {
                if (!ReflectUtils.isPrimitive(type)) {
                    return false;
                }
            } else if (arg instanceof Map) {
                String name = (String) ((Map<?, ?>) arg).get("class");
                Class<?> cls = arg.getClass();
                if (name != null && name.length() > 0) {
                    cls = ReflectUtils.forName(name);
                }
                if (!type.isAssignableFrom(cls)) {
                    return false;
                }
            } else if (arg instanceof Collection) {
                if (!type.isArray() && !type.isAssignableFrom(arg.getClass())) {
                    return false;
                }
            } else {
                if (!type.isAssignableFrom(arg.getClass())) {
                    return false;
                }
            }
        }
        return true;
    }
}
