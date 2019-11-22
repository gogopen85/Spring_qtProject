<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet"  type="text/css" href="/resources/css/bootstrap.min.css" >
    <link rel="stylesheet" type="text/css" href="/resources/font-awesome/css/font-awesome.css" >
    <link rel="stylesheet" type="text/css" href="/resources/css/plugins/iCheck/custom.css" >
    <link rel="stylesheet" type="text/css" href="/resources/css/animate.css" >
    <link rel="stylesheet" type="text/css" href="/resources/css/style.css" >
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>회원가입</title>
</head>
<body class="gray-bg">

<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>
            <img src="/resources/images/220px-Ajou_Univ_Ui.png">
        </div>
        <h3>회원 가입</h3>
        <p>사용을 위해 회원가입을 해주세요.</p>
        <form class="m-t" id="register">
            <div class="form-group">
                <input type="text" class="form-control" id="username" name="username" placeholder="사용자 아이디" required="">
            </div>
            <div class="form-group">
                <input type="text" class="form-control" id="name" name="name" placeholder="사용자명" required="">
            </div>
            <div class="form-group">
                <input type="email" class="form-control" id="email" name="email" placeholder="Email" required="">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="password" name="password" placeholder="비밀번호" required="">
            </div>
            <div class="form-group">
                <input type="password" class="form-control" id="passwordConfirmation" name="passwordConfirmation" placeholder="비밀번호 확인" required="">
            </div>

            <button type="button" id ="registerSubmit" class="btn btn-primary block full-width m-b">등록</button>
            <p class="text-muted text-center"><small>Already have an account?</small></p>
            <a class="btn btn-sm btn-white btn-block" href="login">Login</a>
        </form>
    </div>
</div>
<script type="text/javascript" src="/resources/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="/resources/js/popper.min.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="/resources/js/plugins/iCheck/icheck.min.js"></script>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script>
    $(document).ready(function(){
        $('.i-checks').iCheck({
            checkboxClass: 'icheckbox_square-green',
            radioClass: 'iradio_square-green',
        });

        $('#registerSubmit').click(function(){
            var returnVal = true;
            if($('#password').val() != $('#passwordConfirmation').val()){
                alert('비밀번호를 다시 확인해주세요');
                returnVal = false;
            }
            var data_ = $('#register').serializeObject();
            $.ajax({
                type: 'post',
                url: '/User/register',
                dataType : 'json',
                contentType: 'application/json; charset=utf-8',
                data : data_,
            }).always(function(data){
                if(data.status==200){
                    alert(data.responseText);
                    window.location.replace('/User/login');
                }else{
                    alert(data.responseText);
                }
            });
            return returnVal
        });

    });
</script>
</body>
</html>