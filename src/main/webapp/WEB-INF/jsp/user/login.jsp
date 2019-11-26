<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <title>로그인</title>
</head>
<body class="gray-bg">


    <div class="loginColumns animated fadeInDown">
        <div class="row">

            <div class="col-md-6 font-bold" align="center">
                <br/>
                <img src="/resources/images/220px-Ajou_Univ_Ui.png" >

                <div class="row">
                    <div class="col-md-12"  align="center">
                        Department of Biomedical Informatics, <br/> Ajou University School of Medicine
                    </div>

                </div>
            </div>
            <div class="col-md-6">
                <div class="ibox-content">
                    <form class="m-t" id="loginForm">
                        <div class="form-group">
                            <input id="username" name="username" class="form-control" placeholder="Username" required="">
                        </div>
                        <div class="form-group">
                            <input id="password" name="password" type="password" class="form-control" placeholder="Password" required="">
                        </div>
                        <button type="button" id="loginSubmit" class="btn btn-primary block full-width m-b">Login</button>

                        <a href="#">
                            <small>Forgot password?</small>
                        </a>

                        <p class="text-muted text-center">
                            <small>Do not have an account?</small>
                        </p>
                        <a class="btn btn-sm btn-white btn-block" href="/signUp">Create an account</a>
                    </form>
                    <p class="m-t">
                        <small>Inspinia we app framework base on Bootstrap 3 &copy; 2014</small>
                    </p>
                </div>
            </div>
        </div>
        <hr/>
    </div>
</body>
<script type="text/javascript" src="/resources/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="/resources/js/popper.min.js"></script>
<script type="text/javascript" src="/resources/js/bootstrap.js"></script>
<script type="text/javascript" src="/resources/js/plugins/iCheck/icheck.min.js"></script>
<script type="text/javascript" src="/resources/js/common.js"></script>
<script type="text/javascript" src="/resources/js/jquery.cookie.js"></script>

<script>
    $(document).ready(function(){

        $('#loginSubmit').click(function(){
            var returnVal = true;

            var data_ = $('#loginForm').serializeObject();
            console.log(data_)
            $.ajax({
                type: 'post',
                url: '/user/login',
                dataType : 'json',
                contentType: 'application/json; charset=utf-8',
                data : data_,
            }).always(function(data){
                if(data.status==200){
                    window.location.replace('/main');
                }else{

                }
            });
            return returnVal
        });

    });
</script>
</body>
</html>