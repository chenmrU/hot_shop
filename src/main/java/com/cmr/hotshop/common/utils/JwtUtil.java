package com.cmr.hotshop.common.utils;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 * @author chenmengrui
 * @Description: Jwt工具类
 * @date 2019/12/26 15:48
 */
@Slf4j
@Component
public class JwtUtil {

    private static final String CLAIM_KEY_USERNAME = "sub";

    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * 生成token
     * @param claims
     * @return
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    /**
     * 从token获取payload
     * @param token
     * @return
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("JWT验证失败：{}", token);
        }
        return claims;
    }

    /**
     * 生成token的过期时间
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * 从token获取用户名
     * @param token
     * @return
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = this.getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 校验token是否合法
     * @param token
     * @param userDetails
     * @return
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = this.getUserNameFromToken(token);
        return StrUtil.equals(username, userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 从token获取过期时间
     * @param token
     * @return
     */
    private Date getExpiredDataFromToken(String token) {
        Claims claims = this.getClaimsFromToken(token);
        return claims.getExpiration();
    }

    /**
     * 判断token是否过期
     * @param token
     * @return
     */
    private boolean isTokenExpired(String token) {
        Date date = this.getExpiredDataFromToken(token);
        return date.before(new Date());
    }

    /**
     * 判断token是否可以被刷新
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * 刷新token
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFromToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

}
