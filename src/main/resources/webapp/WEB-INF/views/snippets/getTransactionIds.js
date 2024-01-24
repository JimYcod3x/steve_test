$("#chargePointSelectList").change(function() {
	var cp = $(this).find("option:selected").text();
	$.getJSON("${ctxPath}/manager/ajax/" + cp + "/transactionIds", function(data) {
		console.log(data);
		var options = "";
		$.each(data, function() {
			options += "<option value='" + this + "'>" + this + "</option>";
		});
		var select = $("#transactionId");
		select.prop("disabled", false);
		select.html(options);
	});
		$.getJSON("${ctxPath}/manager/ajax/" + cp + "/transactionDetails" + data, function(data1) {
			console.log(data1);
			// var options = "";
			// $.each(data, function() {
			// 	options += "<option value='" + this + "'>" + this + "</option>";
			// });
			// var select = $("#transactionId");
			// select.prop("disabled", false);
			// select.html(options);
		}

	);
});

$("#chargePointSelectList").change(function() {
	var cp = $(this).find("option:selected").text();
	$.getJSON("${ctxPath}/manager/ajax/" + cp + "/transactionDetails", function(data) {
		console.log(data);
		var options = "";
		$.each(data, function() {
			options += "<option value='" + this + "'>" + this + "</option>";
		});
		var select = $("#transactionId");
		select.prop("disabled", false);
		select.html(options);
	});
});

