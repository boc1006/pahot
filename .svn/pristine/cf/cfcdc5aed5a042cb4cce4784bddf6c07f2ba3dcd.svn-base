package cn.pahot.upms.enums;

import com.boc.common.web.enums.MenuEnums;

public enum UPMSMenuEnum implements MenuEnums {
    ;
    private String uri;
    private String code;

    private UPMSMenuEnum(String uri) {
        this.uri = uri;
    }
    private UPMSMenuEnum(MenuEnums uri, String code) {
        this.uri = uri.getUri();
        this.code = code;
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public String getCode() {
        return code;
    }
}
