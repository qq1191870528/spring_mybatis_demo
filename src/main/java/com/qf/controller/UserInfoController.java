package com.qf.controller;

import com.qf.dto.SearchUserInfoDTO;
import com.qf.pojo.MenuInfo;
import com.qf.pojo.UserInfo;
import com.qf.service.UserInfoService;
import com.qf.vo.UserInfoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by DELL on 2019/7/30.
 */
//声明这个类是一个控制器,同时因为包含了@Component注解，这个类也是一个组件，能被spring扫描并加载到容器中
//RestController不但是一个控制器，还是一个符合restful风格的网络接口
@RestController
public class UserInfoController {

    //自动注入
    @Autowired
    UserInfoService userInfoService;

    //请求映射
    @RequestMapping("loginCheck")
    //该方法的返回值(无论类型),都以json字符串返回.
//    @ResponseBody
    //@RequestBody:声明一个对象，凡是请求过来参数的name和这个对象属性名相同的话，框架就进行封装
    //@RequestParam:声明这是一个请求的参数
    public Object loginCheck(@RequestBody UserInfo userInfo, HttpSession session){
        System.out.println(userInfo);
        UserInfo userInfo1 = userInfoService.loginCheck(userInfo);
        System.out.println(userInfo);
        //登录成功
        if(userInfo1!=null){
            session.setAttribute("userInfo",userInfo1);
            //缓存
            List<MenuInfo> menuInfoList = userInfoService.userLoginInit(userInfo);
            session.setAttribute("menuInfoList",menuInfoList);
        }else{
        //登录失败
        }

        return userInfo1!=null;
    }

    @RequestMapping("registerUserInfo")
//    @ResponseBody
    public Object registerUserInfo(@RequestBody UserInfo userInfo){
        System.out.println(userInfo);
        boolean register = userInfoService.register(userInfo);
        return register;
    }

    @RequestMapping(value = "listAllUserInfo",method = RequestMethod.POST)
    public Object listAllUserInfo(@RequestBody SearchUserInfoDTO searchUserInfoDTO){
        return this.userInfoService.getAllUserInfo(searchUserInfoDTO);
    }

    @RequestMapping("removeUserInfoById")
    public Object removeUserInfoById(@RequestParam int userid){
        System.out.println("id="+userid);
        return this.userInfoService.removeUserInfoById(userid);
    }

    @RequestMapping("getUserInfoById")
    public Object getUserInfoById(@RequestParam int userid){
        System.out.println("id="+userid);
        return this.userInfoService.getUserInfoById(userid);
    }
    @RequestMapping("editUserInfoById")
    public boolean editUserInfoById(@RequestBody UserInfo userInfo){
        System.out.println(userInfo);
        return this.userInfoService.editUserInfoById(userInfo);
    }

    @RequestMapping("initMenuList")
    public Object initMenuList(HttpSession session){
        return session.getAttribute("menuInfoList");
    }


    @RequestMapping("pathVariableTest/{uname}-{pwd}")
    public Object pathVariableTest(@PathVariable("uname") String uname,@PathVariable("pwd") String pwd){
        System.out.println(uname+":"+pwd);
        return uname+":"+pwd;
    }


    @RequestMapping("getCookieValue")
    public Object getCookieValue(@CookieValue(name = "name",required = false) String username){
        System.out.println(username);
        return username;
    }

    @RequestMapping("getRequestHeaderValue")
    public Object getRequestHeaderValue(@RequestHeader(value = "Content-Length")String host){
        System.out.println(host);
        return host;
    }


    @ResponseBody
    @RequestMapping("vailidationDemo")
    public Object validationDemo(@Valid @RequestBody UserInfoVO userInfoVO, BindingResult bindingResult, ModelMap modelMap){
        if(bindingResult.hasErrors()){
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError error:
                 allErrors) {
                System.out.println(error);

                modelMap.put(error.getObjectName(),error.toString());
            }
            return "验证失败";
        }
        return "验证成功";
    }

    @ResponseBody
    @RequestMapping("test/paramEncode")
    public Object paramEncode(@RequestParam String username){
        System.out.println(username);
        return username;
    }
}
