<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Highcharts Example</title>
<style type="text/css">
${
	demo
	.css
}
</style>
<script type="text/javascript">
	$(function() {
		//使用el获取
		$.get('purchasebillItem_chartsJson.action?${pageContext.request.queryString}',function(data){
			$('#container').highcharts({
				chart : {
					type : 'pie',
					options3d : {
						enabled : true,
						alpha : 45,
						beta : 0
					}
				},
				title : {
					text : 'Browser market shares at a specific website, 2014'
				},
				tooltip : {
					pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
				},
				plotOptions : {
					pie : {
						allowPointSelect : true,
						cursor : 'pointer',
						depth : 35,
						dataLabels : {
							enabled : true,
							format : '{point.name}'
						}
					}
				},
				series : [ {
					type : 'pie',
					name : 'Browser share',
					data : data
				} ]
			});
		});
	});
</script>
</head>
<body>
	<div id="container" style="height: 400px"></div>
</body>
</html>
