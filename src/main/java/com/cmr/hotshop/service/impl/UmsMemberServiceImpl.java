package com.cmr.hotshop.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.cmr.hotshop.service.RedisService;
import com.cmr.hotshop.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Resource
    private RedisService redisService;

    @Value("${spring.redis.key.prefix.authCode}")
    private static String REDIS_KEY_PREFIX_AUTH_CODE;

    @Value("${spring.redis.key.expire}")
    private static Long AUTH_CODE_EXPIRE_SECONDS;

    @Override
    public String generateAuthCode(String telephone) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        //验证码绑定手机号并存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
        return sb.toString();
    }

    @Override
    public boolean verifyAuthCode(String telephone, String authcode) {
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        return StringUtils.equals(realAuthCode, authcode);
    }
}
