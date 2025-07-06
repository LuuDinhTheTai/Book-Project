package com.me.bookproject.util;

import jakarta.servlet.http.Cookie;
import org.springframework.stereotype.Component;

@Component
public class CookieUtil {
  
  public Cookie createTokenCookie(String name, String token) {
    Cookie tokenCookie = new Cookie(name, token);
    tokenCookie.setHttpOnly(true);
    tokenCookie.setPath("/");
    tokenCookie.setMaxAge(60 * 60 * 24); // 1 ngày
    return tokenCookie;
  }
}
