<%--네비 영역--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="navbar-default navbar-static-side" role="navigation">
    <div class="sidebar-collapse">
        <ul class="nav metismenu" id="side-menu">
            <li class="nav-header">
                <div class="dropdown profile-element">
                    <%--<img alt="image" class="rounded-circle" />--%>
                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                        <span class="block m-t-xs font-bold" id="myId"></span>
                       <%-- <span class="text-muted text-xs block">SW Developer <b class="caret"></b></span>--%>
                    </a>
                    <ul class="dropdown-menu animated fadeInRight m-t-xs">
                       <li><a class="dropdown-item" href="/user/logout">Logout</a></li>
                    </ul>
                    <%--<ul class="dropdown-menu animated fadeInRight m-t-xs">
                        <li><a class="dropdown-item" href="profile.html">Profile</a></li>
                        <li><a class="dropdown-item" href="contacts.html">Contacts</a></li>
                        <li><a class="dropdown-item" href="mailbox.html">Mailbox</a></li>
                        <li class="dropdown-divider"></li>
                        <li><a class="dropdown-item" href="login.html">Logout</a></li>
                    </ul>--%>
                </div>
                <div class="logo-element">
                    IN+
                </div>
            </li>
            <%--<li>
                <a href="#"><i class="fa fa-th-large"></i> <span class="nav-label">Dashboards</span> <span class="fa arrow"></span></a>
                <ul class="nav nav-second-level collapse">
                    <li><a href="/dashboard">Dashboard</a></li>
                    <li><a href="/board">Board</a></li>
                  <li><a href="dashboard_3.html">Dashboard v.3</a></li>
                    <li><a href="dashboard_4_1.html">Dashboard v.4</a></li>
                    <li><a href="dashboard_5.html">Dashboard v.5 </a></li>
                </ul>
            </li>--%>
            <li>
                <a href="#"><i class="fa fa-bar-chart-o"></i> <span class="nav-label">Project</span><span class="fa arrow"></span></a>
                <ul class="nav nav-second-level" id="projectNav">
                    <c:choose>
                        <c:when test="${cookie.user.value eq 'admin'}">
                            <li><a href="/project/confirmProject">Confirm</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="/project/myProject">Marking</a></li>
                            <li><a href="/project/feedback">Feedback</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </li>
            <%--<li>
                <a href="layouts.html"><i class="fa fa-diamond"></i> <span class="nav-label">Layouts</span></a>
            </li>--%>
        </ul>
    </div>
</nav>
<%--네비 영역--%>

<script>

</script>

