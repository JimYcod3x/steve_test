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
        <form:form modelAttribute="params">
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

        <form:form action="${ctxPath}/manager/operations/${opVersion}/RemoteStopTransaction" modelAttribute="params">
            <section><span>Charge Points with OCPP ${opVersion}</span></section>
            <%@ include file="00-cp-single.jsp" %>
            <section><span>Parameters</span></section>
            <table class="userInput">
                <tr>
                    <td>ID of the Active Transaction:</td>
                    <td><form:select path="transactionId" disabled="true"/></td>
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