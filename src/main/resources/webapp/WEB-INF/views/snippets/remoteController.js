$(document).ready(function () {
    if ($("#seconds").text() === '') {
        $("#seconds").text('00')
    }
    if ($("#minutes").text() === '') {
        $("#minutes").text('00')
    }
    if ($("#hours").text() === '') {
        $("#hours").text('00')
    }
    if ($("#power").text() === '') {
        $("#power").text('0 W')
    }

    var eventSource = new EventSource('${ctxPath}/manager/fetch-update')
    eventSource.onmessage = function (event) {
        var eventData = JSON.parse(event.data);
        var currentSeconds = $("#seconds").text(); // Retrieve the current text content
        var currentMinutes = $("#minutes").text(); // Retrieve the current text content
        var currentHours = $("#hours").text();
        var currentPower = $("#power").text();

        var power = eventData.power;
        var status = eventData.status;
        var seconds = eventData.seconds;
        var minutes = eventData.minutes;
        var hours = eventData.hours;

        $("#status").text(status);

        if (status === "Charging") {
            $("#seconds").text(seconds);
            $("#minutes").text(minutes);
            $("#hours").text(hours);
            $("#power").text(power);

        } else if (status === "Finished") {
            $("#seconds").text(currentSeconds);
            $("#minutes").text(currentMinutes);
            $("#hours").text(currentHours);
            $("#power").text(currentPower);
        }

        // Log the data to the console
        console.log("Received power:", power);
        console.log("Received status:", status);
    };

    eventSource.onerror = function (error) {
        console.error("Error occurred: ", error);
    };


});


window.onload = function () {
    var seconds = 0;
    var minutes = 0;
    var hours = 0;
    var appendSeconds = document.getElementById("seconds")
    var appendMinutes = document.getElementById("minutes")
    var appendHours = document.getElementById("hours")

    var buttonStart = document.getElementById('button-start');
    var buttonStop = document.getElementById('button-stop');
    var buttonReset = document.getElementById('button-reset');
    var Interval;

    // buttonStart.onclick = function () {
    //     event.preventDefault()
    //     clearInterval(Interval);
    //     Interval = setInterval(startTimer, 1000);
    // }
    //
    // buttonStop.onclick = function () {
    //     event.preventDefault()
    //     clearInterval(Interval);
    // }
    //
    //
    // buttonReset.onclick = function () {
    //     event.preventDefault()
    //     clearInterval(Interval);
    //     seconds = "00";
    //     minutes = "00";
    //     hours = "00"
    //     appendSeconds.textContent = seconds;
    //     appendMinutes.textContent = minutes;
    //     appendHours.textContent = hours;
    // }

    function startTimer() {
        seconds++;
        appendSeconds.textContent = "0" + seconds
        if (seconds > 9) {
            appendSeconds.textContent = seconds
        }
        if (seconds >= 60) {
            appendSeconds.textContent = 0;
            minutes++;
            // if (displayMinutes <= 9) {
            //     appendMinutes.html("0" + minutes);
            // } else {
            //     appendMinutes.html(minutes);
            // }
        }

    }

    startandStop();

};

// function handleStatusUpdate(status) {
//     if (status === "Charging") {
//         Interval = setInterval(startTimer, 1000);
//         console.log(Interval)
//         // location.reload()// Call startTimer every 500 milliseconds
//     } else if (status === "Finishing") {
//         clearInterval(Interval);
//         console.log(Interval)
//         // location.reload()
//         // Reload the page if status is "Finish"
//     }
// }

function startandStop() {
    $('#start').click(function (event) {

        var start = {
            connectorId: 1,
            idTag: $("#idTag").find("option:selected").text(),
            chargePointSelectList: $("#chargePointSelectList").find("option:selected").val()
        };
        var csrfToken = $("input[name='_csrf']").val();

        // for (var i = 0; i < 2; i++) {
            // location.reload()
            $.ajax({
                url: "${ctxPath}/manager/operations/${opVersion}/RemoteStartTransaction",
                type: 'POST',
                headers: {
                    'X-CSRF-TOKEN': csrfToken
                },
                xhrFields: {
                    withCredentials: true
                },
                data: start,
                success: function (data) {
                    $('#startConfirm').modal('show')
                    console.log("Success:", data);
                },
                error: function (xhr, status, error) {
                    console.error("Error:", error);
                }
            });

        // }
    });

    $('#stop').click(function (event) {
        var stop = {
            transactionId: $("#transactionId").text(),
            chargePointSelectList: $("#chargePointSelectList").find("option:selected").val()
        };
        var csrfToken = $("input[name='_csrf']").val();

        // for (var i = 0; i < 2; i++) {
        //     location.reload()
            $.ajax({
                url: "${ctxPath}/manager/operations/${opVersion}/RemoteStopTransaction",
                type: 'POST',
                headers: {
                    'X-CSRF-TOKEN': csrfToken
                },
                xhrFields: {
                    withCredentials: true
                },
                data: stop,
                success: function (data) {
                    $('#stopConfirm').modal('show')
                    console.log("Success:", data);
                },
                error: function (xhr, status, error) {
                    console.error("Error:", error);
                }

            });
        // }
    });

}

var currentAction;

// Function to set the confirmation message and action
function confirmAction(action) {
    currentAction = action;
    if (action === 'start') {
        document.getElementById('confirmMessage').innerText = 'Are you sure you want to start the operation?';
    } else if (action === 'stop') {
        document.getElementById('confirmMessage').innerText = 'Are you sure you want to stop the operation?';
    }
    $('#confirmModal').modal('show');
}

// Function to execute the confirmed action
function executeAction() {
    // Perform the action based on the currentAction variable
    if (currentAction === 'start') {
        // Perform start operation
        alert('Operation started!');
    } else if (currentAction === 'stop') {
        // Perform stop operation
        alert('Operation stopped!');
    }
    // Hide the modal
    $('#confirmModal').modal('hide');
}


// function startTimer() {
//     var seconds = 0;
//     var minutes = 0;
//     var hours = 0;
//     var appendSeconds = $("#seconds");
//     var displaySeconds = appendSeconds.text();
//     // displaySeconds = seconds
//     var appendMinutes = $("#minutes");
//     var displayMinutes = appendMinutes.text();
//     // displayMinutes = minutes
//     var appendHours = $("#hours");
//     var displayHours = appendHours.text();
//     // displayHours = hours
//     // displaySeconds++;
//     console.log("second" + displaySeconds)
//     // displaySeconds = seconds
//     // if (displaySeconds <= 9) {
//     //     appendSeconds.innerHTML = "0" + seconds;
//     // } else {
//     //     appendSeconds.innerHTML = displaySeconds;
//     // }
//
//     if (displaySeconds >= 60) {
//         displaySeconds = 0;
//         // displayMinutes++;
//         console.log("Minutes" + displayMinutes)
//         // if (displayMinutes <= 9) {
//         //     appendMinutes.html("0" + minutes);
//         // } else {
//         //     appendMinutes.html(minutes);
//         // }
//     }
//
//     if (displayMinutes >= 60) {
//         displayMinutes = 0;
//         // displayHours++;
//         console.log("Hours" + displayHours)
//         // if (hours <= 9) {
//         //     appendHours.html("0" + hours) ;
//         // } else {
//         //     appendHours.html(hours);
//         // }
//     }
//     $("#seconds").text(pad(displaySeconds));
//     $("#minutes").text(pad(displayMinutes));
//     $("#hours").text(pad(displayHours));
// }

function pad(val) {
    return val < 10 ? "0" + val : val;
}
