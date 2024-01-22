<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${ctxPath}/static/css/style.css">
    <link rel="stylesheet" type="text/css" href="${ctxPath}/static/css/jquery-ui.min.css">
    <link rel="stylesheet" type="text/css" href="${ctxPath}/static/css/jquery-ui-timepicker-addon.min.css">
    <title>Remote Controller</title>
</head>
<body>
<div class="main">
    <div class="top-banner">
    </div>
    <div class="op16-content">
        <form:form action="${ctxPath}/manager/operations/${opVersion}/RemoteStartTransaction" modelAttribute="params">
            <section><span>Charge Points with OCPP ${opVersion}</span></section>
            <%@ include file="00-cp-single.jsp" %>
            <section><span>Parameters</span></section>
            <table class="userInput">
                <tr>
                    <td>Connector ID:</td>
                    <td><form:select path="connectorId" disabled="true"/></td>
                </tr>
                <tr>
                    <td>OCPP ID Tag:</td>
                    <td>
                        <form:select path="idTag">
                            <form:options items="${idTagList}"/>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td></td>
                    <td>
                        <div class="submit-button"><input type="submit" value="Perform"></div>
                    </td>
                </tr>
            </table>
        </form:form>



    </div>
</div>



</body>
</html>

<%@ include file="00-header.jsp" %>
<%@ include file="00-op-bind-errors.jsp" %>
<script type="text/javascript">
    $(document).ready(function() {
        <%@ include file="snippets/getConnectorIdsZeroAllowed.js" %>
    });
</script>
<div class="content">
    <div class="left-menu">
        <ul>
            <li><a href="${ctxPath}/manager/operations/${opVersion}/ChangeAvailability">Change Availability</a></li>
            <li><a href="${ctxPath}/manager/operations/${opVersion}/ChangeConfiguration">Change Configuration</a></li>
            <li><a href="${ctxPath}/manager/operations/${opVersion}/ClearCache">Clear Cache</a></li>
            <li><a href="${ctxPath}/manager/operations/${opVersion}/GetDiagnostics">Get Diagnostics</a></li>
            <li><a class="highlight" href="${ctxPath}/manager/operations/${opVersion}/RemoteStartTransaction">Remote Start Transaction</a></li>
            <li><a href="${ctxPath}/manager/operations/${opVersion}/RemoteStopTransaction">Remote Stop Transaction</a></li>
            <li><a href="${ctxPath}/manager/operations/${opVersion}/Reset">Reset</a></li>
            <li><a href="${ctxPath}/manager/operations/${opVersion}/UnlockConnector">Unlock Connector</a></li>
            <li><a href="${ctxPath}/manager/operations/${opVersion}/UpdateFirmware">Update Firmware</a></li>
            <hr>
            <li><a href="${ctxPath}/manager/operations/${opVersion}/ReserveNow">Reserve Now</a></li>
            <li><a href="${ctxPath}/manager/operations/${opVersion}/CancelReservation">Cancel Reservation</a></li>
            <li><a href="${ctxPath}/manager/operations/${opVersion}/DataTransfer">Data Transfer</a></li>
            <li><a href="${ctxPath}/manager/operations/${opVersion}/GetConfiguration">Get Configuration</a></li>
            <li><a href="${ctxPath}/manager/operations/${opVersion}/GetLocalListVersion">Get Local List Version</a></li>
            <li><a href="${ctxPath}/manager/operations/${opVersion}/SendLocalList">Send Local List</a></li>
            <hr>
            <li><a href="${ctxPath}/manager/operations/${opVersion}/TriggerMessage">Trigger Message</a></li>
            <li><a href="${ctxPath}/manager/operations/${opVersion}/GetCompositeSchedule">Get Composite Schedule</a></li>
            <li><a href="${ctxPath}/manager/operations/${opVersion}/ClearChargingProfile">Clear Charging Profile</a></li>
            <li><a href="${ctxPath}/manager/operations/${opVersion}/SetChargingProfile">Set Charging Profile</a></li>
        </ul>
    </div>
    <div class="op16-content">
        <form:form action="${ctxPath}/manager/operations/${opVersion}/RemoteStartTransaction" modelAttribute="params">
            <section><span>Charge Points with OCPP ${opVersion}</span></section>
            <%@ include file="00-cp-single.jsp" %>
            <section><span>Parameters</span></section>
            <table class="userInput">
                <tr><td>Connector ID:</td>
                    <td><form:select path="connectorId" disabled="true"/></td>
                </tr>
                <tr><td>OCPP ID Tag:</td>
                    <td>
                        <form:select path="idTag">
                            <form:options items="${idTagList}" />
                        </form:select>
                    </td>
                </tr>
                <tr><td></td><td><div class="submit-button"><input type="submit" value="Perform"></div></td></tr>
            </table>
        </form:form>
        <div class="op16-content">
            <form:form action="${ctxPath}/manager/operations/${opVersion}/RemoteStopTransaction" modelAttribute="params">
                <section><span>Charge Points with OCPP ${opVersion}</span></section>
                <%@ include file="00-cp-single.jsp" %>
                <section><span>Parameters</span></section>
                <table class="userInput">
                    <tr><td>ID of the Active Transaction:</td><td><form:select path="transactionId" disabled="true" /></td></tr>
                    <tr><td></td><td><div class="submit-button"><input type="submit" value="Perform"></div></td></tr>
                </table>
            </form:form>
        </div></div>

    </div></div>
<%@ include file="00-footer.jsp" %>