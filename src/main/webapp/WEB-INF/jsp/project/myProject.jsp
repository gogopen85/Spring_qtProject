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
                            <h5>Multiple Axes Line Chart Example </h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                    <i class="fa fa-wrench"></i>
                                </a>
                                <ul class="dropdown-menu dropdown-user">
                                    <li><a href="#" class="dropdown-item">Config option 1</a>
                                    </li>
                                    <li><a href="#" class="dropdown-item">Config option 2</a>
                                    </li>
                                </ul>
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
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

        getData()

        function getData(){
            $.ajax({
                type: 'get',
                url: '/project/getData/' + $.cookie("user"),
                dataType : 'json',
                contentType: 'application/json; charset=utf-8',
            }).done(function(data){
                x = data.data;
                var point = data.point
                for(var i=0; i<point.length; i++) {
                    addMarkings.push(point[i])
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

                // $.plot($("#flot-line-chart-multi").getOptions().grid.markings.push({ xaxis: { from: item.datapoint[0], to: item.datapoint[0] }, color: "#ff8888" }))
                // add = [[item.datapoint[0] , item.datapoint[1] ], [item.datapoint[0] + 0.1 , item.datapoint[1]]]

                $.ajax({
                    type: 'post',
                    url: '/project/insertMarkings/',
                    dataType : 'json',
                    data : JSON.stringify({ point : item.datapoint[0], userId: $.cookie("user")}),
                    contentType : "application/json; charset=UTF-8",
                }).always(function(data){
                    doPlot("right");
                    if(data.status==200){
                        getData()
                    }else{

                    }
                });

                $("button").click(function() {
                    doPlot($(this).text());
                });
            }
        });
    });



</script>


</body>
</html>