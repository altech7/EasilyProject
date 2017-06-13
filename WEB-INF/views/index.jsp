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
                <h2 class="header center"><i class="large material-icons" style="color: #fafafa; opacity: 0.87;">toc</i>
                </h2>
            </div>
        </div>
    </div>

    <div class="card invoices-card">
        <div class="card-content">
            <div class="card-options">
                <a href="${pageContext.request.contextPath}/project/form"
                   class="btn-floating btn-small waves-effect waves-light tooltipped" data-position="top"
                   data-delay="50" data-tooltip="Ajouter un projet">
                    <i class="material-icons">add</i>
                </a>
            </div>
            <span class="card-title"></span>
            <c:if test="${projects == null || projects.isEmpty()}">
                <div class="center">Aucun projet</div>
            </c:if>
            <c:if test="${!projects.isEmpty()}">
                <table class="responsive-table bordered highlight">
                    <thead>
                    <tr>
                        <td data-field="numero">Numéro</td>
                        <td data-field="trigramme">Trigramme</td>
                        <td data-field="name">Nom du projet</td>
                        <td data-field="dateEnd">Date de fin</td>
                        <td class="center" data-field="progress">Exigences</td>
                        <td class="center" data-field="progress">Progression</td>
                        <td class="center" data-field="manager">Manager</td>
                        <td data-field="actions">Actions</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${projects}" var="project">
                        <tr>
                            <td>#${project.id}</td>
                            <td>${project.trigramme}</td>
                            <td>${project.name}</td>
                            <c:if test="${project.dateEndPlanned != null}">
                                <td><fmt:formatDate value="${project.dateEndPlanned}" type="date"/></td>
                            </c:if>
                            <c:if test="${project.dateEndPlanned == null}">
                                <td>Aucune</td>
                            </c:if>
                            <td>
                                <div class="center">${project.rateRequirement}%
                                    <div class="progress">
                                        <div class="determinate pink" style="width: ${project.rateRequirement}%"></div>
                                        <span>${project.rateRequirement}%</span>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="center">${project.avancement}%
                                    <div class="progress">
                                        <div class="determinate" style="width: ${project.avancement}%"></div>
                                        <span>${project.avancement}%</span>
                                    </div>
                                </div>
                            </td>
                            <td class="center">${project.manager.trigramme}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/project/${project.id}/details"
                                   class="btn-floating btn-small waves-effect waves-light tooltipped"
                                   data-position="left"
                                   data-delay="50" data-tooltip="Voir la fiche du projet">
                                    <i class="material-icons">search</i>
                                </a>
                                <a href="${pageContext.request.contextPath}/project/${project.id}/timeline"
                                   class='btn-floating btn-small pink waves-effect waves-light tooltipped'
                                   data-activates='dropdown1' data-position="right" data-delay="50"
                                   data-tooltip="Voir le suivi du projet">
                                    <i class="material-icons">view_agenda</i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>

    <input id='showErrorMessage' type="hidden" value=${showErrorMessage}/>
</my:template>