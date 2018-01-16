package com.boc.common.web.permissions;

import com.boc.common.utils.token.DES;

public class DefaultTokenEncrypt implements TokenEncryption {

	@Override
	public String encrypt(String ...args) {
		try {
			StringBuffer enc = new StringBuffer();
			for(String str:args) {
				enc.append(str).append(":");
			}
			return DES.encrypt(enc.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public boolean decrypt(String token) {
		String decToken = null;
		try {
			decToken = DES.decrypt(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decToken != null && decToken.split(":").length>0;
	}

	@Override
	public String getUsername(String token) {
		String decToken = null;
		try {
			decToken = DES.decrypt(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decToken.split(":")[1];
	}
	
	@Override
	public String getDecryptString(String token, int idx) {
		String decToken = null;
		try {
			decToken = DES.decrypt(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decToken.split(":")[idx];
	}

	@Override
	public String getPasswd(String token) {
		String decToken = null;
		try {
			decToken = DES.decrypt(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decToken.split(":")[2];
	}

	@Override
	public String getUserid(String token) {
		String decToken = null;
		try {
			decToken = DES.decrypt(token);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decToken.split(":")[0];
	}

	@Override
	public String encryptStr(String str) {
		// TODO Auto-generated method stub
		try {
			return DES.encrypt(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	public String decryptStr(String str) {
		// TODO Auto-generated method stub
		try {
			return DES.decrypt(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	
	

}
