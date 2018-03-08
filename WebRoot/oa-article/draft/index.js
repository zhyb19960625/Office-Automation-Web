$(document).ready(function() {
	onLoad();
	$("#bar").load("/Office/bar.html", function() {
		$("#draftArticle").addClass("active", function() {});
	});
});

function onLoad() {
	$.post("/Office/article/...", {}, function(result) {
		var tbody = "";
		var data = result.article;
		for(i=0;i<result.number;i++) {
			tbody = tbody + getTableLine(data[i],i+1);
		}
		$("#mainBody").html(tbody, function() {});
	});
}

function getTableLine(perline,no) {
	var trLine = "<tr><td>"+no+"</td><td>"+perline.title+"</td><td>"+perline.author+"</td><td>"+perline.createtime+"</td><td></td></tr>"
	return trLine;
}