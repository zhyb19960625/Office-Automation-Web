$(document).ready(function() {
	$("#bar").load("/Office/bar.html", function() {
		$("#college").addClass("active", function() {});
	});
});