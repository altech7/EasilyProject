<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<my:template action="index">

    <div class="parallax-container">
        <div>
            <div class="parallax">
                <img src="${pageContext.request.contextPath}/assets/images/background-signin.jpg">
            </div>
            <div>
                <h2 class="header center"><i class="large material-icons" style="color: #fafafa; opacity: 0.87;">supervisor_account</i>
                </h2>
            </div>
        </div>
    </div>

    <div class="card invoices-card">
        <div class="card-content">
            <div class="card-options">
                <a href="${pageContext.request.contextPath}/client/form"
                   class="btn-floating btn-small waves-effect waves-light tooltipped" data-position="top"
                   data-delay="50" data-tooltip="Ajouter une ressource">
                    <i class="material-icons">add</i>
                </a>
            </div>
            <span class="card-title"></span>
            <c:if test="${clients == null || clients.isEmpty()}">
                <div class="center">Aucune ressource</div>
            </c:if>
            <c:if test="${!clients.isEmpty()}">
                <table class="responsive-table bordered highlight">
                    <thead>
                    <tr>
                        <td data-field="numero">Numéro</td>
                        <td data-field="trigramme">Nom</td>
                        <td data-field="name">Prénom</td>
                        <td data-field="trigramme">Trigramme</td>
                        <td data-field="name">email</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${clients}" var="client">
                        <tr>
                            <td>#${client.id}</td>
                            <td>${client.firstName}</td>
                            <td>${client.lastName}</td>
                            <td>${client.trigramme}</td>
                            <td>${client.email}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>

</my:template>