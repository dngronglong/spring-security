package com.springsecurity.demo;

/**
 * 全局登录使用用户信息（单例模式）
 */
public class SysUser {

    private String userName;

    private String role;

    private String realName;

    private String email;

    private String tel;

    private static volatile SysUser sysUser=null;

    private SysUser(String username,String role,String realName,String email,String tel){
        this.email=email;
        this.userName=username;
        this.realName=realName;
        this.role=role;
        this.tel=tel;
    }

    public String getUserName() {
        return userName;
    }


    public String getRole() {
        return role;
    }


    public String getRealName() {
        return realName;
    }


    public String getEmail() {
        return email;
    }


    public String getTel() {
        return tel;
    }


    public static SysUser getSysUser() {
        return sysUser;
    }

    public static void setSysUser(SysUser sysUser) {
        SysUser.sysUser = sysUser;
    }

    public static SysUser getInstance(String username,String role,String realName,String email,String tel){
        if (sysUser==null){
            synchronized (SysUser.class){
                sysUser=new SysUser(username,role,realName,email,tel);
            }
        }
        return sysUser;
    }
}
