package com.alpha.ddms.common;

import io.jsonwebtoken.*;

import java.util.*;

public class JWTGenerate {

    public static String createToken(String a){
        long no = System.currentTimeMillis();
        Date dt = new Date(no);

        JwtBuilder buat = Jwts.builder()

                .setHeaderParam("type","JWT")
                .setHeaderParam("alg","HS256")

                .setId(a)
                .setIssuedAt(dt)
                .setSubject("bootcamp")
                .setIssuer("EKSAD")
                .setExpiration(new Date(System.currentTimeMillis()+60*10*1000))
                .signWith(SignatureAlgorithm.HS256,"PASSWORD");

        return buat.compact();
    }

    public static Claims validToken(String token){

        Claims claims = null;

        claims = Jwts.parser()
                .setSigningKey("PASSWORD")
                .parseClaimsJws(token)
                .getBody();
        return claims;
    }
}
