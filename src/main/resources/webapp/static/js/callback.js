    $(document).ready(function () {
    $("#startForm").submit(function (event) {
        // Prevent the default form submission
        event.preventDefault();

        // Serialize the form data
        var formData = $(this).serialize();

        // Make an AJAX POST request
        $.ajax({
            type: "POST",
            url: "${ctxPath}/manager/remoteController",
            data: formData,
            success: function (response) {
                // Handle the success response event
                console.log(response);

                // Example: Update the UI or show a notification
                $("#remoteResult").text(response);
            },
            error: function (error) {
                // Handle the error, if any
                console.error("Error: " + error.responseText);
            }
        });
    });
});