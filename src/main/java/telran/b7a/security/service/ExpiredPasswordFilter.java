package telran.b7a.security.service;

import java.io.IOException;
import java.security.Principal;
import java.time.LocalDate;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

import telran.b7a.accounting.dao.UserAccountRepository;
import telran.b7a.accounting.model.UserAccount;

@Service
public class ExpiredPasswordFilter extends GenericFilterBean {
	
	UserAccountRepository repository;

	@Autowired
	public ExpiredPasswordFilter(UserAccountRepository repository) {
		this.repository = repository;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		Principal principal = request.getUserPrincipal();
		if(principal != null && checkEndPoint(request.getMethod(), request.getServletPath())) {
			UserAccount userAccount = repository.findById(principal.getName()).get();
			if(userAccount.getPasswordExpDate().isBefore(LocalDate.now())) {
				response.sendError(403, "password expired");
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	private boolean checkEndPoint(String method, String path) {
		return !("Put".equalsIgnoreCase(method) && path.matches("/account/password/?"));
	}

	

}
