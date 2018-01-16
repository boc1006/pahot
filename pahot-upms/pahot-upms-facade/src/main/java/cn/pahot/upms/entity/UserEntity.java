package cn.pahot.upms.entity;

import java.io.Serializable;

public class UserEntity  implements Serializable{
    private Integer id;
    private String username;
    private String passwd;
    private Integer orgid;
    private String name;
    private String state;
    private String birthday;
    private String remark;
    private Short sex;
    private String phone;
    private Integer aa01;
    private Long aa02;
    private Integer ab01;
    private Long ab02;

    public Integer getId() {
        return id;
    }

    public UserEntity setId(Integer id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPasswd() {
        return passwd;
    }

    public UserEntity setPasswd(String passwd) {
        this.passwd = passwd;
        return this;
    }

    public Integer getOrgid() {
        return orgid;
    }

    public UserEntity setOrgid(Integer orgid) {
        this.orgid = orgid;
        return this;
    }

    public String getName() {
        return name;
    }

    public UserEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getState() {
        return state;
    }

    public UserEntity setState(String state) {
        this.state = state;
        return this;
    }

    public String getBirthday() {
        return birthday;
    }

    public UserEntity setBirthday(String birthday) {
        this.birthday = birthday;
        return this;
    }

    public String getRemark() {
        return remark;
    }

    public UserEntity setRemark(String remark) {
        this.remark = remark;
        return this;
    }

    public Short getSex() {
        return sex;
    }

    public UserEntity setSex(Short sex) {
        this.sex = sex;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public UserEntity setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public Integer getAa01() {
        return aa01;
    }

    public UserEntity setAa01(Integer aa01) {
        this.aa01 = aa01;
        return this;
    }

    public Long getAa02() {
        return aa02;
    }

    public UserEntity setAa02(Long aa02) {
        this.aa02 = aa02;
        return this;
    }

    public Integer getAb01() {
        return ab01;
    }

    public UserEntity setAb01(Integer ab01) {
        this.ab01 = ab01;
        return this;
    }

    public Long getAb02() {
        return ab02;
    }

    public UserEntity setAb02(Long ab02) {
        this.ab02 = ab02;
        return this;
    }
}
