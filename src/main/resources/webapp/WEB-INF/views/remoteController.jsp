<%@ include file="00-header.jsp" %>
<%@ include file="00-op-bind-errors.jsp" %>
<script type="text/javascript">
    $(document).ready(function() {
        <%@ include file="snippets/getConnectorIdsZeroAllowed.js" %>
    });
</script>
<div class="content">
    <div class="op16-content">
        <form:form action="${ctxPath}/manager/operations/${opVersion}/RemoteStartTransaction" modelAttribute="start_params">
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
            <form:form action="${ctxPath}/manager/operations/${opVersion}/RemoteStopTransaction" modelAttribute="stop_params">
                <section><span>Charge Points with OCPP ${opVersion}</span></section>
                <%@ include file="00-cp-single.jsp" %>
                <section><span>Parameters</span></section>
                <table class="userInput">
                    <tr><td>ID of the Active Transaction:</td><td><form:select path="transactionId" disabled="true" /></td></tr>
                    <tr><td></td><td><div class="submit-button"><input type="submit" value="Perform"></div></td></tr>
                </table>
            </form:form>
        </div>

    </div>
</div>
</body>
</html>