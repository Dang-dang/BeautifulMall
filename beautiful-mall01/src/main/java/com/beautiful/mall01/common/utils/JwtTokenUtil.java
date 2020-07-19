package com.beautiful.mall01.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
@Slf4j
public class JwtTokenUtil {

    private static final String CLAIM_KEY_USERNAME="sub";
    private static final String CLAIM_KEY_CREATED="created";

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     *
     * 生成jwt token
     */
    public String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generteExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    /**
     *
     *根据token解析出JWT中负载
     */
    public Claims getClaimFromToken(String token){
        Claims claims=null;
        try {
            claims=Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e){
            log.info("JWT 解析失败");
        }
        return claims;
    }

    /**
     * 生成token过期时间
     */
    public Date generteExpirationDate(){
        return new Date(System.currentTimeMillis()+expiration*1000);
    }

    /**
     * 从token中获取用户名
     */
    public String getUserNameFromToken(String token){
        Claims claims = getClaimFromToken(token);
        String userName = claims.getSubject();
        return userName;
    }

    /**
     * 验证token是否还有效
     */
    public boolean validateToken(String token, UserDetails userDetails){
        String userName = getUserNameFromToken(token);
        return userName.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * 判断token是否过期
     */
    public boolean isTokenExpired(String token){
        Date expiredDateFromToken = getExpiredDateFromToken(token);
        return expiredDateFromToken.before(new Date());
    }

    /**
     * 从token中获取过期时间
     */
    public Date getExpiredDateFromToken(String token){
        Claims claims = getClaimFromToken(token);
        Date expiration = claims.getExpiration();
        return expiration;
    }

    /**
     * 判断token是否可以刷新
     */
    public boolean canRefresh(String token){
        return !isTokenExpired(token);
    }

    /**
     * 根据用户信息生成token
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * 刷新token
     */
    public String refreshToken(String token){
        Claims claims = getClaimFromToken(token);
        claims.put(CLAIM_KEY_CREATED,new Date());
        String s = generateToken(claims);
        return s;
    }
}
