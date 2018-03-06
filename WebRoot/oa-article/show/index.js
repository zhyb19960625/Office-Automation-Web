$(document).ready(function() {
	$("#bar").load("/Office/bar.html", function() {
		$("#listArticle").addClass("active", function() {});
	});
	$("#articleContent").load("/Office/111.html", function() {});
});
