$(document).ready(function() {
	$("#bar").load("/Office/bar.html", function() {
		$("#announcement").addClass("active", function() {});
	});
});