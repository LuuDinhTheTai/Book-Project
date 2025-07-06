package com.me.bookproject.security.jwt;

import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.text.ParseException;
import java.util.Objects;

@Component
public class CustomJwtDecoder implements JwtDecoder {
  
  @Value("${jwt.signerKey}")
  private String signerKey;
  
  @Autowired
  private JwtUtil jwtUtil;
  
  private NimbusJwtDecoder nimbusJwtDecoder = null;
  
  @Override
  public Jwt decode(String token) throws JwtException {
    
    try {
      var response = jwtUtil.introspect(token);
      
      if (!response) throw new JwtException("Token invalid");
    } catch (JOSEException | ParseException e) {
      throw new JwtException(e.getMessage());
    }
    
    if (Objects.isNull(nimbusJwtDecoder)) {
      SecretKeySpec secretKeySpec = new SecretKeySpec(signerKey.getBytes(), "HS512");
      nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(secretKeySpec)
                                 .macAlgorithm(MacAlgorithm.HS512)
                                 .build();
    }
    
    return nimbusJwtDecoder.decode(token);
  }
}
