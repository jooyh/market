<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="left-navi">
    <ul class="lst">
    	<c:forEach var="item" items="${sessionScope.admMenu}" varStatus="st">
			<c:choose>
				<c:when test="${st.index == 0}">
					<li class="active">
					<strong>${item.parMenuNm}</strong>
					<ul>
					<li><a href="${item.menuUrl}">${item.menuNm}</a></li>
				</c:when>
				<c:when test="${sessionScope.ADM_MENU[st.index-1].parMenuId != item.parMenuId}">
					</ul>
					</li>
					<li class="active">
					<strong>${item.parMenuNm}</strong>
					<ul>
					<li><a href="${item.menuUrl}">${item.menuNm}</a></li>
				</c:when>
				<c:when test="${fn:length(sessionScope.ADM_MENU)-1 == st.index}">
					<li><a href="${item.menuUrl}">${item.menuNm}</a></li>
					</ul>
		 			</li>
				</c:when>
				<c:otherwise>
					<li><a href="${item.menuUrl}">${item.menuNm}</a></li>
				</c:otherwise>
			</c:choose>
    	</c:forEach>
<!--         <li class="active">
            <strong>사용자 관리</strong>
            <ul>
                <li><a href="javascript:void(0)">허용 IP 관리</a></li>
                <li><a href="javascript:void(0)">허용 IP 관리</a></li>
                <li><a href="javascript:void(0)">허용 IP 관리</a></li>
            </ul>
        </li>
        <li class="active">
            <strong>관리자 관리</strong>
            <ul>
                <li><a href="javascript:void(0)">관리자 등록</a></li>
            </ul>
        </li> -->
    </ul>
</div>
<div class="tit-box">
    <h3>${sessionScope.nowMenuNm}</h3>
</div>