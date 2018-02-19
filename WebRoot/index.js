$(document).ready(function() {
	$("#bar").load("/Office/bar.html", function() {
		$("#index").addClass("active", function() {});
	});
});