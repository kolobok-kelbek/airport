package com.kelbek.airport.security.filter

import com.kelbek.airport.web.AuthCookieService
import io.jsonwebtoken.*
import io.jsonwebtoken.security.SecurityException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.net.URLDecoder
import java.net.URLEncoder
import java.util.stream.Collectors
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class JwtAuthorizationFilter(
    authenticationManager: AuthenticationManager,
    private val authCookieService: AuthCookieService,
) : BasicAuthenticationFilter(authenticationManager) {
    private val log: Logger = LoggerFactory.getLogger(this::class.java)

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authentication = getAuthentication(request)
        if (authentication == null) {
            filterChain.doFilter(request, response)
            return
        }
        SecurityContextHolder.getContext().authentication = authentication
        filterChain.doFilter(request, response)
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val encodeToken: String? = authCookieService.getValue(request, authCookieService.TOKEN_COOKIE)

        if (encodeToken == null || encodeToken.isEmpty()) {
            return null
        }

        val token = URLDecoder.decode(encodeToken, Charsets.UTF_8)

        if (token.isEmpty() || !token.startsWith(authCookieService.TOKEN_PREFIX)) {
            return null
        }

        try {
            val parsedToken: Jws<Claims> = Jwts.parser()
                    .setSigningKey(authCookieService.JWT_SECRET.toByteArray(Charsets.UTF_8))
                    .parseClaimsJws(token.replace(authCookieService.TOKEN_PREFIX, ""))

            log.info("что-то пришло getAuthentication " + parsedToken)
            val username = parsedToken.body.subject
            val authorities = (parsedToken.body[authCookieService.TOKEN_PARAM_KEY_ROLES] as List<*>?)
                    ?.stream()
                    ?.map { authority: Any? -> SimpleGrantedAuthority(authority as String?) }
                    ?.collect(Collectors.toList())
            if (null != username && !username.isEmpty()) {
                return UsernamePasswordAuthenticationToken(username, null, authorities)
            }
        } catch (exception: ExpiredJwtException) {
            log.warn("Request to parse expired JWT : {} failed : {}", token, exception.message)
        } catch (exception: UnsupportedJwtException) {
            log.warn(
                    "Request to parse unsupported JWT : {} failed : {}", token, exception.message
            )
        } catch (exception: MalformedJwtException) {
            log.warn("Request to parse invalid JWT : {} failed : {}", token, exception.message)
        } catch (exception: SecurityException) {
            log.warn(
                    "Request to parse JWT with invalid signature : {} failed : {}",
                    token,
                    exception.message
            )
        } catch (exception: IllegalArgumentException) {
            log.warn(
                    "Request to parse empty or null JWT : {} failed : {}", token, exception.message
            )
        }

        return null
    }
}