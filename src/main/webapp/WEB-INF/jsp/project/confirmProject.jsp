<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title></title>
</head>
<body>
<div id="wrapper">
    <%@ include file="../common/navi.jsp" %>
    <div id="page-wrapper" class="gray-bg">
        <%@ include file="../common/upperNavi.jsp" %>
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>Main</h2>
                <ol class="breadcrumb">
                    <li class="breadcrumb-item">
                        <a href="/main">Main</a>
                    </li>
                </ol>
            </div>
        </div>

        <div class="wrapper wrapper-content animated fadeInRight" >
            <div class="row">
                <div class="col-lg-12">
                    <div class="ibox ">
                        <div class="ibox-title">
                            <h5 id="dataInfoText"> </h5>
                            <div class="ibox-tools">
                                <button type="button" id="skipButton" class="btn btn-danger btn-xs">skip</button>
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                    <i class="fa fa-wrench"></i>
                                </a>
                                    <ul class="dropdown-menu dropdown-user">
                                        <li><a href="#" id="confirmData" class="dropdown-item">확정</a></li>
                                        <li><a href="#" id="refuseData" class="dropdown-item">반려</a></li>
                                    </ul>

                            </div>
                        </div>
                        <div class="ibox-content">
                            <div id="pointInfo">

                            </div>
                            <div class="flot-chart">
                                <div class="flot-chart-content" id="flot-line-chart-multi"></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<%@ include file="../common/lib.jsp" %>

<script>
    $(document).ready(function(){
        var x = [];
        var y = [];
        var addMarkings = [];
        var dataId;
        var pointId=1;
        var checkedUserId=0;

        getData()

        function getData(){
            $.ajax({
                type: 'get',
                url: '/project/getData/' + $.cookie("user") + '/2',
                dataType : 'json',
                contentType: 'application/json; charset=utf-8',
            }).done(function(data){
                var html = ''
                x = data.data;
                dataId = data.dataId
                addMarkings = [];
                pointId = data.point.length

                if(pointId>0){
                    checkedUserId = data.point[0].userId
                }

                for(var i = 0; i < data.markingsInfo.length; i ++) {
                    var btnClass = ""
                    if(i == data.point.length){
                        btnClass = "btn btn-w-m btn-primary"
                    }else {
                        btnClass = "btn btn-w-m btn-default"
                    }

                    html += '<button type="button" class="'+ btnClass +'" id="markinsInfo_'+ data.markingsInfo[i].id+ '">'+data.markingsInfo[i].name+'</button>'
                }

                $("#pointInfo").html(html)

                $("#dataInfoText").html(dataId + ' 번 데이터 입니다.');

                var point = data.point
                for(var i=0; i<point.length; i++) {
                    addMarkings.push(data.point[i].point)
                }
                doPlot("right");
            })
        }


        function doPlot(position) {

            var customPlot = $.plot($("#flot-line-chart-multi"), [{
                data: x,
                label: "data"
            }, {
                data: y,
                label: "markings",
                yaxis: 2
            }], {
                yaxes: [ {
                    alignTicksWithAxis: position == "right" ? 1 : null,
                    position: position,
                }],
                legend: {
                    position: 'sw'
                },
                colors: ["#999999","#FF0000"],
                borderWidth: 10,
                grid: {
                    color: "#999999",
                    hoverable: true,
                    clickable: true,
                    tickColor: "#D4D4D4",
                    borderWidth:0,
                    show:true,
                    markings: []
                },
                tooltip: true,
                tooltipOpts: {
                    content: "%s for %x was %y",
                    onHover: function(flotItem, $tooltipEl) {
                        // console.log(flotItem, $tooltipEl);
                    }
                }

            });

            if(addMarkings != undefined) {
                for (var i = 0; i < addMarkings.length; i++) {
                    customPlot.getOptions().grid.markings.push({
                        xaxis: {from: addMarkings[i], to: addMarkings[i]},
                        color: "#ff8888"
                    });
                }
                customPlot.setupGrid();
                customPlot.draw();
            }
        }

        doPlot("right");

        $("button").click(function() {
            doPlot($(this).text());
        });

        $("#flot-line-chart-multi").bind("plotclick", function (event, pos, item) {
            if (item) {
                if(addMarkings.length >= 9){
                    alert("모든 마킹이 완료된 페이지입니다.")
                }else{
                    $.ajax({
                        type: 'post',
                        url: '/project/insertMarkings',
                        dataType : 'json',
                        data : JSON.stringify({ point : item.datapoint[0], userId: $.cookie("user") , dataId: dataId, pointId:pointId + 1, pageNo: 2, checkedUserId: checkedUserId }),
                        contentType : "application/json; charset=UTF-8",
                    }).always(function(data){
                        doPlot("right");
                        if(data.status==200){
                            getData()
                        }else{

                        }
                    });

                  /*$("button").click(function() {
                        doPlot($(this).text());
                    });*/
                }

            }
        });
        $(this).bind("contextmenu", function(e) {
            e.preventDefault();
        });
        $(document).mousedown(function(e){
            console.log('working')
            if( e.button == 2 ) {
                $.ajax({
                    type: 'post',
                    url: '/project/deleteMarkings',
                    dataType : 'json',
                    data : JSON.stringify({ userId: $.cookie("user"), dataId: dataId, pointId : pointId, pageNo: 2, checkedUserId: checkedUserId}),
                    contentType : "application/json; charset=UTF-8",
                }).always(function(data){
                    doPlot("right");
                    if(data.status==200){
                        getData()
                        return false;
                    }else{

                    }
                });
            }
            return true;
        });

        $("#skipButton").on('click',function(){
            console.log('called')
            if(addMarkings.length >= 9){
                alert("모든 마킹이 완료된 페이지입니다.")
            }else{
                console.log('called2')
                $.ajax({
                    type: 'post',
                    url: '/project/insertMarkings',
                    dataType : 'json',
                    data : JSON.stringify({ point : 999, userId: $.cookie("user") , dataId: dataId, pointId:pointId + 1, pageNo: 2, checkedUserId: checkedUserId }),
                    contentType : "application/json; charset=UTF-8",
                }).always(function(data){
                    doPlot("right");
                    if(data.status==200){
                        getData()
                    }else{

                    }
                });
            }
        });

        $("#confirmData").click(function(){
            $.ajax({
                type: 'post',
                url: '/project/confirmData',
                dataType : 'json',
                data : JSON.stringify({ userId: $.cookie("user"), dataId: dataId, status: 2,checkedUserId: checkedUserId, pageNo: 2}),
                contentType : "application/json; charset=UTF-8",
            }).always(function(data){
                doPlot("right");
                if(data.status==200){
                    getData()
                    return false;
                }else{

                }
            });
        })


        $("#refuseData").click(function(){
            $.ajax({
                type: 'post',
                url: '/project/confirmData',
                dataType : 'json',
                data : JSON.stringify({ userId: $.cookie("user"), dataId: dataId, status: 99}),
                contentType : "application/json; charset=UTF-8",
            }).always(function(data){
                doPlot("right");
                if(data.status==200){
                    getData()
                    return false;
                }else{

                }
            });
        })


    });



</script>


</body>
</html>