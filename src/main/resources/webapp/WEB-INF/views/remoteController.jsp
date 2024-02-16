<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <link rel="icon" href="${ctxPath}/static/images/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${ctxPath}/static/images/favicon.ico" type="image/x-icon"/>
    <%--    <link rel="stylesheet" type="text/css" href="${ctxPath}/static/css/style.css">--%>
    <%--    <link rel="stylesheet" type="text/css" href="${ctxPath}/static/css/jquery-ui.min.css">--%>
    <%--    <link rel="stylesheet" type="text/css" href="${ctxPath}/static/css/jquery-ui-timepicker-addon.min.css">--%>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <script type="text/javascript" src="${ctxPath}/static/js/jquery-2.0.3.min.js"></script>
    <script type="text/javascript" src="${ctxPath}/static/js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="${ctxPath}/static/js/jquery-ui-timepicker-addon.min.js"></script>
    <script type="text/javascript" src="${ctxPath}/static/js/script.js"></script>
    <script type="text/javascript" src="${ctxPath}/static/js/stupidtable.min.js"></script>
    <script type="text/javascript" src="${ctxPath}/static/js/callback.js"></script>
    <%--    <script type="text/javascript" src="${ctxPath}/static/js/remoteController.js"></script>--%>
    <link rel="stylesheet" type="text/css" href="${ctxPath}/static/css/gw.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.min.js"
            integrity="sha384-cuYeSxntonz0PPNlHhBs68uyIAVpIIOZZ5JqeqvYYIcEL727kskC66kF92t6Xl2V"
            crossorigin="anonymous"></script>
    <title>MyRemote Controller - </title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
</head>

<body>
<%@ include file="00-op-bind-errors.jsp" %>
<script charset="utf-8" type="text/javascript">
    $(document).ready(function () {
        <%@ include file="snippets/getConnectorIdsZeroAllowed.js" %>
        <%@ include file="snippets/remoteController.js" %>
    });
</script>
<section class="visually-hidden"><span>Charge Points with OCPP ${opVersion}</span></section>
<div class="container-fluid main-cont">
    <div>
        <img class="logo" src="${ctxPath}/static/images/Asuka_logo.png" atl="Asuka_logo"/>
    </div>
    <div>
        <div class="p-container">
            <div class="fs-1 ocpp-gw text-light">OCPP Gateway Demo</div>
            <div class="gw_wrapper fs-6 fw-normal p-0 text-light">
                <div class="t-bg fw-bold">Gateway ID:</div>
                <div id="gw_id" class="t-bg b-space">J23P000094</div>
            </div>

            <img class="product" src="${ctxPath}/static/images/gw.png" atl="Product"/>
        </div>
    </div>
    <form:form class="me-3" modelAttribute="startStopParams">
        <fieldset class="bg-light mt-4 ms-5 form-wrapper shadow">
            <div class="mb-3 bg-light">
                <form:select path="chargePointSelectList" size="1" multiple="false" class="form-select">
                    <c:forEach items="${cpList}" var="cp">
                        <form:option class="text-secondary"
                                     value="${cp.ocppTransport};${cp.chargeBoxId};${cp.endpointAddress}"
                                     label="${cp.chargeBoxId}"/>
                    </c:forEach>
                </form:select>
            </div>
            <div class="mb-3 bg-light">
                <form:select class="form-select" path="idTag">
                    <form:options class="text-secondary" items="${idTagList}"/>
                </form:select>
            </div>
            <div class="time_status bg-light fs-2 d-flex justify-content-between">瞬時電力 :<span id="power"
                                                                                                      class="power">${power}</span>
            </div>
            <div class="wrapper">
                <h1 class="visually-hidden">Stopwatch</h1>
                <h2 class="visually-hidden">Vanilla JavaScript Stopwatch</h2>
                <p class="display-1 text-center timer"><span id="hours">${hours}</span>:<span
                        id="minutes">${minutes}</span>:<span id="seconds">${seconds}</span></p>
            </div>
                <%--            <div>--%>
                <%--                <button id="button-start">Start</button>--%>
                <%--                <button id="button-stop">Stop</button>--%>
                <%--                <button id="button-reset">Reset</button>--%>
                <%--            </div>--%>
            <div id="transactionId" class="visually-hidden">${transactionId}</div>
            <div class="time_status bg-light fs-2 d-flex justify-content-between status">状態 :<span id="status"
                                                                                                       class="status-content fs-1">${status}</span>
            </div>
        </fieldset>
    </form:form>
    <div id="stopwatch_controls" class="d-flex justify-content-between mt-5">
        <label id="start" class="btn btn-lg btn-primary btn-ps shadow-lg" data-toggle="modal"
               data-target="startConfirm">Start</label>
        <label id="stop" class="btn btn-danger btn-lg btn-pe shadow-lg" data-toggle="modal" data-target="stopConfirm">Stop</label>
    </div>

    <!-- Button trigger modal -->


    <!-- Modal -->

</div>
</body>
</html>