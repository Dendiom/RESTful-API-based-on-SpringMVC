package com.lucky.socialnetwork.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.lucky.socialnetwork.bean.exception.CustomException;
import com.lucky.socialnetwork.constant.ExceptionCode;
import java.util.Date;

public class JWTUtil {

    public static final int EXPIRE = 5 * 60 * 1000 * 1000;

    private static final String SECRET = "IFDSLGFFOGFDVDFGEIKSNUJRAEUHDFNKO";
    private static final String ISSUER = "cloud";
    private static final String SUBJECT = "all_user";
    private static final String UID = "uid";

    public static String createToken(String uid) {
        Algorithm algorithm = Algorithm.HMAC256(SECRET);
        return JWT.create()
                .withIssuer(ISSUER)
                .withSubject(SUBJECT)
                .withClaim(UID, uid)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRE))
                .withIssuedAt(new Date(System.currentTimeMillis()))
                .sign(algorithm);
    }

    public static String verify(String token) throws Exception{
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .withSubject(SUBJECT)
                    .build();

            DecodedJWT jwt = verifier.verify(token);
            return jwt.getClaim(UID).asString();
        } catch (JWTVerificationException exception) {
            throw new CustomException(ExceptionCode.JWT_ERROR);
        }
    }
}
