<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" href="${ctxPath}/static/images/favicon.ico" type="image/x-icon" />
    <link rel="shortcut icon" href="${ctxPath}/static/images/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" type="text/css" href="${ctxPath}/static/css/style.css">
    <link rel="stylesheet" type="text/css" href="${ctxPath}/static/css/jquery-ui.min.css">
    <link rel="stylesheet" type="text/css" href="${ctxPath}/static/css/jquery-ui-timepicker-addon.min.css">
    <script type="text/javascript" src="${ctxPath}/static/js/jquery-2.0.3.min.js" ></script>
    <script type="text/javascript" src="${ctxPath}/static/js/jquery-ui.min.js" ></script>
    <script type="text/javascript" src="${ctxPath}/static/js/jquery-ui-timepicker-addon.min.js" ></script>
    <script type="text/javascript" src="${ctxPath}/static/js/script.js" ></script>
    <script type="text/javascript" src="${ctxPath}/static/js/stupidtable.min.js" ></script>
    <script type="text/javascript" src="${ctxPath}/static/js/callback.js"  ></script>
    <title>MyRemote Controller - </title>
</head>

<body>
<div class="main">


<%@ include file="00-op-bind-errors.jsp" %>
<script type="text/javascript">
    $(document).ready(function() {
        <%@ include file="snippets/getConnectorIdsZeroAllowed.js" %>
        <%@ include file="snippets/getTransactionIds.js" %>
    });
</script>
<div class="content">
    <div class="op16-content">
<%--        <form:form id="startStop" method="post" modelAttribute="startStopParams">--%>
        <form:form action="${ctxPath}/manager/operations/${opVersion}/start" modelAttribute="startStopParams">
            <section><span>Charge Points with OCPP ${opVersion}</span></section>

            <table class="userInput">
                <tr>
                    <td style="vertical-align:top">Select one:</td>
                    <td>
                        <form:select path="chargePointSelectList" size="5" multiple="false">
                            <c:forEach items="${cpList}" var="cp">
                                <form:option value="${cp.ocppTransport};${cp.chargeBoxId};${cp.endpointAddress}" label="${cp.chargeBoxId}"/>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
        <c:forEach items="${txDetails}" var="txD">
            <tr>
                <td>${txD.key}</td>
                <td>${txD.value}</td>
            </tr>

        </c:forEach>



            </table>
            <p>The value for the key "specificKey" is: ${transactionDetails['transaction_pk']}</p>
            <br>
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
                <tr><td>ID of the Active Transaction:</td><td><form:select path="transactionId" disabled="true" /></td></tr>
                <tr><td></td><td><div class="submit-button"><input type="submit" value="Perform"></div></td></tr>
            </table>
        </form:form>
    </div>
<%--        <div class="op16-content">--%>
<%--            --%>
<%--&lt;%&ndash;            <form:form action="${ctxPath}/manager/operations/${opVersion}/stop" modelAttribute="stopParams">&ndash;%&gt;--%>
<%--                <section><span>Charge Points with OCPP ${opVersion}</span></section>--%>
<%--                <section><span>Parameters</span></section>--%>
<%--                <table class="userInput">--%>

<%--                    <tr><td></td><td><div class="submit-button"><input type="submit" value="Perform"></div></td></tr>--%>
<%--                </table>--%>
<%--            </form:form>--%>
<%--        </div>--%>

    </div>
</div>
</body>
</html>