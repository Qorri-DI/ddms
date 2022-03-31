package com.alpha.ddms.Utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class GenerateJwt {


    public static  String createToken (String user){
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        JwtBuilder builder = Jwts.builder()
                //set Header
                .setHeaderParam("type","JWT")
                .setHeaderParam("alg","HS256")
                //set Payload
                .setId(user)
                .setIssuedAt(now)
                .setSubject("bootcamp")
                .setIssuer("EKSAD")
                .setExpiration(new Date(System.currentTimeMillis()+15*1000))
                .signWith(SignatureAlgorithm.HS256,"PASSWORD");

        return builder.compact();
    }

    public static Claims validateToken(String token){

        Claims claims = null;

        claims = Jwts.parser()
                .setSigningKey("PASSWORD")
                .parseClaimsJws(token)
                .getBody();

        return  claims;


    }

}
