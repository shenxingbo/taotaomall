package com.taotao.sso.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbUserMapper;
import com.taotao.pojo.TbUser;
import com.taotao.pojo.TbUserExample;
import com.taotao.sso.dao.JedisClient;
import com.taotao.sso.service.UserService;
import com.taotao.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private TbUserMapper userMapper;

    @Autowired
    JedisClient jedisClient;

    @Value("${REDIS_USER_SESSION}")
    private String REDIS_USER_SESSION_KEY;

    @Value("${SSO_SESSION_EXPIRE}")
    private Integer SSO_SESSION_EXPIRE;

    @Override
    public TaotaoResult checkData(String content, Integer type) {

        TbUserExample example = new TbUserExample();
        //1 2 3 分别代表着 username phone  email

        TbUserExample.Criteria criteria = example.createCriteria();

        if (1 == type) {
            criteria.andUsernameEqualTo(content);
        }else if (2 == type) {
            criteria.andPhoneEqualTo(content);
        }else if (3 == type) {
            criteria.andEmailEqualTo(content);
        }


        List<TbUser> list =  userMapper.selectByExample(example);
        if (list == null || list.size() == 0) {
            return TaotaoResult.ok(true);
        }else {
            return TaotaoResult.ok(false);
        }

    }

    @Override
    public TaotaoResult createUser(TbUser user) {
        user.setUpdated(new Date());
        user.setCreated(new Date());
        user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
        userMapper.insert(user);
        return TaotaoResult.ok();
    }

    @Override
    public TaotaoResult userLogin(String username, String password) {

        TbUserExample example = new TbUserExample();
        TbUserExample.Criteria criteria = example.createCriteria();
        criteria.andUsernameEqualTo(username);
        List<TbUser> list = userMapper.selectByExample(example);
        if (null == list || list.size() == 0) {
            return TaotaoResult.build(400, "用户名或者密码错误");
        }

        TbUser user = list.get(0);

        System.out.println(user.getPassword());
        if (!DigestUtils.md5DigestAsHex(password.getBytes()).equals(user.getPassword())) {
            return TaotaoResult.build(400, "用户名或者密码错误");
        }
        //下面生成一个token
        String token = UUID.randomUUID().toString();
        //下面把用户的信息写入redis
        user.setPassword(null);
        jedisClient.set(REDIS_USER_SESSION_KEY+ ":" + token, JsonUtils.objectToJson(user));
        //设置session的过期时间
        jedisClient.expire(REDIS_USER_SESSION_KEY+ ":" + token, SSO_SESSION_EXPIRE);
        //下面要返回token
        return TaotaoResult.ok(token);

    }

    @Override
    public TaotaoResult getUserByToken(String token) {
        String json = jedisClient.get(REDIS_USER_SESSION_KEY+":" + token);

        if (StringUtils.isBlank(json)) {
            return TaotaoResult.build(400, "已过期，请重新登陆");
        }

        //更新过期时间
        jedisClient.expire(REDIS_USER_SESSION_KEY+":" + token, SSO_SESSION_EXPIRE);

        return TaotaoResult.ok(JsonUtils.jsonToPojo(json, TbUser.class ) );

    }
}
