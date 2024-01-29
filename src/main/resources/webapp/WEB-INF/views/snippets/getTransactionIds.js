$("#chargePointSelectList").change(function() {
	var cp = $(this).find("option:selected").text();

	// First AJAX request to get transaction IDs
	$.getJSON("${ctxPath}/manager/ajax/" + cp + "/transactionIds", function(data) {
		console.log(data);

		var options = "";
		$.each(data, function() {
			options += "<option value='" + this + "'>" + this + "</option>";
		});

		var select = $("#transactionId");
		select.prop("disabled", false);
		select.html(options);

		// Check if data has at least one element before making the second AJAX request
		if (data.length > 0) {
			// Capture the first transactionId from the data
			var firstTransactionId = data[0];
			console.log(firstTransactionId);
		}
			var url = "${ctxPath}/manager/ajax/" + cp + "/transactionDetails/";
			console.log("URL being used:", url);
			// Second AJAX request using the captured transactionId
			$.getJSON(url, function(data1) {
				console.log(data1);

				// Your logic with data1
			});

	})


	var start = {
		connectorId: $("#connectorId").text(),
		idTag: $("#idTag").text()
	};
	console.log(start);
	$.post("${ctxPath}/manager/operations/${opVersion}/start", start, function(data) {

	}, "json").fail(function(xhr, status, error){

	});
});


// $("#chargePointSelectList").change(function() {
// 	var cp = $(this).find("option:selected").text();
// 	$.getJSON("${ctxPath}/manager/ajax/" + cp + "/transactionDetails", function(data) {
// 		console.log(data);
// 		var options = "";
// 		$.each(data, function() {
// 			options += "<option value='" + this + "'>" + this + "</option>";
// 		});
// 		var select = $("#transactionId");
// 		select.prop("disabled", false);
// 		select.html(options);
// 	});
// });

