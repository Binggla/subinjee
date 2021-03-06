package com.yedam.myserver.common;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.yedam.myserver.users.vo.UserVO;

// 로그인 처리 -> handler(추가 작업, session에 데이터 추가) -> 페이지 포워드
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	private static final Logger logger = LoggerFactory.getLogger(LoginSuccessHandler.class); // 로거 선언
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		UserVO vo = (UserVO) authentication.getPrincipal(); // 사용자 ID
		logger.info("handler==========" + vo.toString()); // 로그 출력
		request.getSession().setAttribute("suser", vo);
		response.sendRedirect("top.jsp");
	}
	
}
