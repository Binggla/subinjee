<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
</head>
<body>

<h1>로그인 페이지</h1>
${param.error}

<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
  로그인 에러입니다.<br>
  예외 타입：${SPRING_SECURITY_LAST_EXCEPTION.getClass().name}<br>
  메시지：${SPRING_SECURITY_LAST_EXCEPTION.message}<br>
  <c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session"/>
</c:if>
<form action="processLogin" method="post">
	<!-- get을 제외한 모든 요청 방법에는 csrf input이 있어야 한다. -->
	<sec:csrfInput/>
	<!-- <input type="hidden" name="_csrf" value="8eec53d8-2811-4dbb-b82c-c9ca1a9ddee2" /> -->
  <dl>
    <dt>
      로그인 ID
    </dt>
    <dd>
      <input type="text" name="id">
    </dd>
    <dt>
      패스워드
    </dt>
    <dd>
      <input type="password" name="password">
    </dd>
  </dl>
  <button>로그인</button>
</form>
</body>
</html>
