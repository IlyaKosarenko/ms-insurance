package com.university.msinsurance.config;

import com.nimbusds.jwt.JWTParser;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;

import java.text.ParseException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomJwtDecoder implements JwtDecoder {

    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            var parsedJwt = JWTParser.parse(token);
            Map<String, Object> headers = new LinkedHashMap<>(parsedJwt.getHeader().toJSONObject());
            Map<String, Object> claims = new LinkedHashMap<>(parsedJwt.getJWTClaimsSet().getClaims());
            if (claims.get(JwtClaimNames.IAT) instanceof Date) {
                var iat = ((Date) claims.get(JwtClaimNames.IAT)).toInstant();
                claims.put(JwtClaimNames.IAT, iat);
            }
            if (claims.get(JwtClaimNames.EXP) instanceof Date) {
                var exp = ((Date) claims.get(JwtClaimNames.EXP)).toInstant();
                claims.put(JwtClaimNames.EXP, exp);
            }
            return Jwt.withTokenValue(parsedJwt.getParsedString())
                    .headers(h -> h.putAll(headers))
                    .claims(c -> c.putAll(claims))
                    .build();
        } catch (ParseException e) {
            throw new JwtException("Provided token is not valid");
        } catch (Exception ex) {
            throw new JwtException("Error during parse token");
        }
    }
}

