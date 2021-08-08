package com.kelbek.airport.web

import org.springframework.stereotype.Service
import org.springframework.web.util.WebUtils
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Service
class AuthCookieService {
    final val JWT_SECRET = "r4u7x!A%C*F-JaNdRgUkXp2s5v8y/B?E(G+KbPeShVmYq3t6w9z\$C&F)J@McQfTj"

    final val TOKEN_COOKIE = "Authorization"
    final val TOKEN_PREFIX = "Bearer "
    final val TOKEN_TYPE = "JWT"
    final val TOKEN_ISSUER = "secure-api"
    final val TOKEN_AUDIENCE = "secure-app"

    final val TOKEN_PARAM_KEY_ROLES = "rol"
    final val TOKEN_PARAM_KEY_TYPE = "typ"

    final val TOKEN_LIFETIME_MILLISECOND = 864000000
    final val TOKEN_LIFETIME_SECOND = TOKEN_LIFETIME_MILLISECOND / 1000

    fun create(httpServletResponse: HttpServletResponse, name: String?, value: String?) {
        val cookie = Cookie(name, value)
//        cookie.isHttpOnly = true
        cookie.maxAge = TOKEN_LIFETIME_SECOND
        cookie.domain = "airport.local"
        cookie.path = "/"
        httpServletResponse.addCookie(cookie)
    }

    fun clear(httpServletResponse: HttpServletResponse, name: String?) {
        val cookie = Cookie(name, null)
        cookie.path = "/"
//        cookie.isHttpOnly = true
        cookie.maxAge = 0
        cookie.domain = "airport.local"
        httpServletResponse.addCookie(cookie)
    }

    fun getValue(httpServletRequest: HttpServletRequest?, name: String?): String? {
        val cookie = WebUtils.getCookie(httpServletRequest!!, name!!)
        return cookie?.value
    }
}