$(document).ready(function() {
	$("#bar").load("/Office/bar.html", function() {
		$("#student").addClass("active", function() {});
	});
});