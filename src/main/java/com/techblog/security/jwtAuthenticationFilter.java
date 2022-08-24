package com.techblog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.techblog.exceptions.CategoryNotAvailableException;
import com.techblog.exceptions.jwtException;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

@Component
public class jwtAuthenticationFilter extends OncePerRequestFilter {

	@Autowired
	private JwtTokenUtil tokenUtil;
	@Autowired
	private CustomUserDetailsService customUserDetailService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// we will get token from the request where key is authorization.
		String rtheader = request.getHeader("Authorization");
		// System.out.println("OOOOOOOOOOOO "+rtheader+" OOOOOOOOOOO");
		// OOOOOOOOOOOO Bearer
		// eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJZYXNpckBnbWFpbC5jb20iLCJleHAiOjE2NjAzMjc4NDQsImlhdCI6MTY2MDMwOTg0NH0.pYokSWivsfwSHSz7blBnD88mOzOAtsDCMzcU8g2Ci_o9tMY30lgZKamaUSeqWKzJ4haw3RKt0gCMvfiuzbg6uw
		// OOOOOOOOOOO

		String userName = null;
		String jwttoken = null;

		if (rtheader != null && rtheader.startsWith("Bearer")) {
			// remove the Bearer from the requst token
			jwttoken = rtheader.substring(7);
			try {
				// and get thr username from the token
				userName = this.tokenUtil.getUsernameFromToken(jwttoken);

			} catch (IllegalArgumentException e) {
				System.out.println("unable to get jwt token");
			} catch (ExpiredJwtException e) {
				System.out.println("jwt token has expired");
			} catch (MalformedJwtException e) {
				System.out.println("Invalid token");
			}

			UserDetails userDetails = this.customUserDetailService.loadUserByUsername(userName);
			if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

				UserDetails loadUserByUsername = this.customUserDetailService.loadUserByUsername(userName);
				if (this.tokenUtil.validateToken(jwttoken, userDetails)) {
					UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
							userDetails, null, userDetails.getAuthorities());
					authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				} else {
					System.out.println("token does not validated");
				}

			} else {
				System.out.println("token does not validated");
			}

		} else {
			System.out.println("token does not validated");
		}

		filterChain.doFilter(request, response);

	}

}