$(document).ready(function() {
	$("#bar").load("/Office/bar.html", function() {
		$("#draftArticle").addClass("active", function() {});
	});
});