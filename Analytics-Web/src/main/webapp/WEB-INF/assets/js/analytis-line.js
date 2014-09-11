$(function() {
	
	/*for (var i = 0; i < 14; i += 0.5) {
		sin.push([ i, Math.sin(i) ]);
	}*/
	var line_data1 = {
		data : sin,
		color : "#3c8dbc"
	};
	$.plot("#line-analytis", [ line_data1 ], {
		grid : {
			hoverable : true,
			borderColor : "#f3f3f3",
			borderWidth : 1,
			tickColor : "#f3f3f3"
		},
		series : {
			shadowSize : 0,
			lines : {
				show : true
			},
			points : {
				show : true
			},
			label : "统计"
		},
		lines : {
			fill : false,
			color : [ "#3c8dbc" ]
		},
		yaxis : {
			show : true,
		},
		xaxis : {
			show : true
		}
	});
	
	$("<div class='tooltip-inner' id='line-chart-tooltip'></div>").css({
		position : "absolute",
		display : "none",
		opacity : 0.8
	}).appendTo("body");
	
	$("#line-analytis").bind("plothover", function(event, pos, item) {
				if (item) {
					var x = item.datapoint[0].toFixed(2), y = item.datapoint[1].toFixed(2);
					$("#line-chart-tooltip")
					.html(item.series.label + " of " + x + " = " + y)
					.css({top : item.pageY + 5, left : item.pageX + 5}).fadeIn(200);
				} else {
					$("#line-chart-tooltip").hide();
				}
			});
	
});
