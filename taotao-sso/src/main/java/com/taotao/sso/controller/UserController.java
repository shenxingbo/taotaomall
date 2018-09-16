package com.taotao.sso.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbUser;
import com.taotao.sso.service.UserService;
import com.taotao.utils.ExceptionUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;

    @RequestMapping("check/{param}/{type}")
    @ResponseBody
    public Object checkData(@PathVariable String param, @PathVariable Integer type, String callback) {

        TaotaoResult result = null;
        if (StringUtils.isBlank(param)) {
            result = TaotaoResult.build(400, "校验内容不能为空");
        }

        if (type == null) {
            result = TaotaoResult.build(400, "校验内容类型不能为空");
        }

        if (type != 1 && type != 2 && type != 3) {
            result = TaotaoResult.build(400, "校验内容错误");
        }

        if (null != result) {
            if (null != callback) {
                MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
                mappingJacksonValue.setJsonpFunction(callback);
                return mappingJacksonValue;
            } else {
                return result;
            }
        }

        try {
            result = service.checkData(param, type);
            return result;

        } catch (Exception e) {
            result = TaotaoResult.build(400, ExceptionUtil.getStackTrace(e));
        }

        if (null != callback) {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(result);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        } else {
            return result;
        }
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createUser(TbUser user) {
        try {
            TaotaoResult result = service.createUser(user);
            return result;
        }catch (Exception e) {
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult userLogin(String username, String password) {
        try {
            TaotaoResult result = service.userLogin(username, password);
            return result;
        }catch (Exception e) {
            e.printStackTrace();
            return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }
    }

    @RequestMapping("/token/{token}")
    @ResponseBody
    public Object getUserByToken(@PathVariable String token ,String callback) {
        TaotaoResult taotaoResult = null;
        try {
            taotaoResult = service.getUserByToken(token);
        }catch (Exception e) {
            e.printStackTrace();
            taotaoResult = TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
        }

        //判断是否jsonp调用
        if(StringUtils.isBlank(callback)) {
            return taotaoResult;
        }else {
            MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(taotaoResult);
            mappingJacksonValue.setJsonpFunction(callback);
            return mappingJacksonValue;
        }

    }


}
