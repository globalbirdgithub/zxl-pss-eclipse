<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Highcharts Example</title>
<style type="text/css">
${demo.css}
</style>
<script type="text/javascript">
	$(function() {
		$.get('purchasebillItem_chartsJson.action?${pageContext.request.queryString}',function(data){
			$('#container').highcharts({
								chart : {
									plotBackgroundColor : null,
									plotBorderWidth : 1,//null,
									plotShadow : false
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
										dataLabels : {
											enabled : true,
											format : '<b>{point.name}</b>: {point.percentage:.1f} %',
											style : {
												color : (Highcharts.theme && Highcharts.theme.contrastTextColor)
														|| 'black'
											}
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
	<script src="../../js/highcharts.js"></script>
	<script src="../../js/modules/exporting.js"></script>

	<div id="container"
		style="min-width: 310px; height: 400px; max-width: 600px; margin: 0 auto"></div>

</body>
</html>

