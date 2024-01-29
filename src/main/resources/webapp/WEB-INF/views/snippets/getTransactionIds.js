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


	// connectorId = $("#connectorId").text();
	// idTag = $("#idTag").text();

	var csrfToken = $("input[name='_csrf']").val();
	var start = {
		connectorId: $("#connectorId").text(),
		idTag: $("#idTag").text()
	};
	var stop = {transactionId: $("#transcationId").text()};


	console.log("${ctxPath}/manager/operations/${opVersion}/start/"+connectorId+"/"+idTag);

	var csrfToken = $("input[name='_csrf']").val();

// Make the AJAX POST request
	$.ajax({
		url: "${ctxPath}/manager/operations/${opVersion}/start",
		type: 'POST',
		headers: {
			'X-CSRF-TOKEN': csrfToken  // Include the CSRF token in the request headers
		},
		data: start,
		success: function(data) {
			// Handle success response
			console.log("Success:", data);
		},
		error: function(xhr, status, error) {
			// Handle error response
			console.error("Error:", error);
		}
	});

	// $.post("${ctxPath}/manager/operations/${opVersion}/start/"+connectorId+"/"+idTag, function(data) {
	//
	// }, "json").fail(function(xhr, status, error){
	// 	// Failure callback function
	// 	console.error("Request failed:", error);
	// 	console.error("Status:", status);
	// 	console.error("Error details:", xhr.responseText);
	// });
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

