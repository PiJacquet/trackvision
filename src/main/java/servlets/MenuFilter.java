package servlets;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.AccountType;
import common.Configuration;

public class MenuFilter implements Filter {


	public void destroy() {
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		Boolean tokenFound = false;
		Cookie[] cookies = req.getCookies();

		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("TokenTV")) {
					if(Configuration.connectedAccounts.isConnected(cookie.getValue())) {
						tokenFound = true;
						if(Configuration.connectedAccounts.getAccount(cookie.getValue()).getAccount()==AccountType.Resident)
							req.setAttribute("menu", "menuResident");
						else
							req.setAttribute("menu", "menuEmployee");
						req.setAttribute("TokenTV", cookie.getValue());
					}else {
						// Outdated cookie, we remove it
						Cookie deleteToken = new Cookie("TokenTV", "");
						deleteToken.setMaxAge(0);
						res.addCookie(deleteToken);
					}
					break;
				}
			}
		}

		if(!tokenFound) {
			req.setAttribute("menu", "menuNotConnected");
		}

		chain.doFilter(request, response);	
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}

}
