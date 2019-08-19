package com.qf.vo;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by DELL on 2019/8/7.
 */
public class UserInfoVO implements Serializable{

    @Length(min = 8,max = 16,message = "用户名不合法")
    String username;

    @Length(min = 8,max = 16,message = "密码长度不合法")
    String password;

    @NotBlank(message = "账户名不能为空或空字符串")
    String accountName;

    @Email(message = "邮箱地址不合法")
    String email;

    @Pattern(regexp="^1[3-9]\\d{9}$",message = "手机号码不合法")
    String mobile;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
