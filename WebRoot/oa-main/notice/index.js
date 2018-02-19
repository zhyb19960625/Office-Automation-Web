$(document).ready(function() {
	$("#bar").load("/Office/bar.html", function() {
		$("#notice").addClass("active", function() {});
	});
});