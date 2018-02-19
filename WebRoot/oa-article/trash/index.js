$(document).ready(function() {
	$("#bar").load("/Office/bar.html", function() {
		$("#trashArticle").addClass("active", function() {});
	});
});