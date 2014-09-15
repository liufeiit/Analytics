$(function() {
	var line_data = {
		data : data,
		color : "#3c8dbc"
	};
	$.plot("#line-analytis", [ line_data ], {
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
			label : label
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
					var x = item.datapoint[0].toFixed(0), y = item.datapoint[1].toFixed(2);
					
					$("#line-chart-tooltip").html(item.series.label + " " + tip_start + x + tip_end + " : " + y)
					
					.css({top : item.pageY + 5, left : item.pageX + 5}).fadeIn(300);
				} else {
					$("#line-chart-tooltip").hide();
				}
			});
	
});