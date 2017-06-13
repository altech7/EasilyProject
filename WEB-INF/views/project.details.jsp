<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<my:template action="index">

    <div class="parallax-container">
        <div>
            <div class="parallax">
                <img src="${pageContext.request.contextPath}/assets/images/background-signin.jpg">
            </div>
            <div>
                <h2 class="header center"><i class="large material-icons" style="color: #fafafa; opacity: 0.87;">description</i></h2>
            </div>
            <div style="padding-top: 40px">
                <div class="col s12 m12 l4">
                    <div class="card stats-card" style="opacity: 0.82">
                        <div class="card-content">
                            <span style="color: #26a69a;" class="card-title">Nom du projet</span>
                            <span class="stats-counter">${project.name}</span>
                        </div>
                    </div>
                </div>
                <div class="col s12 m12 l4">
                    <div class="card stats-card" style="opacity: 0.82">
                        <div class="card-content">
                            <span style="color: #26a69a;" class="card-title">Trigramme</span>
                            <span class="stats-counter" style="margin-left: 155px">${project.trigramme}</span>
                        </div>
                    </div>
                </div>
                <div class="col s12 m12 l4">
                    <div class="card stats-card" style="opacity: 0.82">
                        <div class="card-content">
                            <span style="color: #26a69a;" class="card-title">Manager</span>
                            <span class="stats-counter">${project.manager.trigramme} (${project.manager.firstName})</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div style="padding-bottom: 30px"></div>

    <div class="row">
        <div class="col s12">
            <div class="card">
                <div class="card-content">
                    <div class="card-options">Date de fin : <span style="color: #26a69a;"><fmt:formatDate value="${project.dateEndPlanned}" type="date"/></span></div>
                    <span class="card-title">Statistiques</span>
                    <div class="center" style="font-size: 18px; padding-bottom: 20px;"> Avancement du projet
                        <div class="progress m-t-md">
                            <div class="determinate" style="width: ${project.avancement}%"></div>
                        </div>
                        <div>${project.avancement}%</div>
                    </div>
                    <li class="divider"></li>
                    <div class="center" style="font-size: 18px; padding-bottom: 20px; padding-top: 16px"> Couverture des
                        exigences
                        <div class="progress m-t-md">
                            <div class="determinate pink" style="width: ${project.rateRequirement}%"></div>
                        </div>
                        <div>${project.rateRequirement}%</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="card invoices-card">
        <div class="card-content">
            <div class="card-options">
                <a href="${pageContext.request.contextPath}/jalon/form/project/${project.id}"
                   class="btn-floating btn-small waves-effect waves-light tooltipped" data-position="top"
                   data-delay="50" data-tooltip="Ajouter un jalon">
                    <i class="material-icons">add</i>
                </a>
            </div>
            <span class="card-title">Liste des jalons</span>
            <c:if test="${jalons == null || jalons.isEmpty()}">
                <div class="center">Aucun jalon</div>
            </c:if>
            <c:if test="${!jalons.isEmpty()}">
                <table class="responsive-table bordered highlight">
                    <thead>
                    <tr>
                        <td data-field="numero">Numéro</td>
                        <td class="center" data-field="livrable">Livrable</td>
                        <td class="center" data-field="state">Etat</td>
                        <td class="center" data-field="dateLivraisonReal">Date de livraison</td>
                        <td class="center" data-field="avancement">Avancement</td>
                        <td class="center" data-field="manager">Manager</td>
                        <td class="center" data-field="actions">Actions</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${jalons}" var="jalon">
                        <tr>
                            <td>#${jalon.id}</td>
                            <td class="center">${jalon.label}</td>
                            <c:if test="${jalon.etat == 'NOT_FINISH'}">
                                <td class="center"><span style="color: #ee6e73;">Pas commencé</span>
                                </td>
                            </c:if>
                            <c:if test="${jalon.etat == 'STARTED'}">
                                <td class="center"><span style="color: #ee6e73;">En cours</span>
                                </td>
                            </c:if>
                            <c:if test="${jalon.etat == 'FINISHED'}">
                                <td class="center"><span style="color: #26a69a;">Terminé</span>
                                </td>
                            </c:if>
                            <c:if test="${jalon.etat == 'STARTED'}">
                                <td class="center">Théorique : <fmt:formatDate value="${jalon.dateLivraisonPlanned}"
                                                                               type="date"/></td>
                            </c:if>
                            <c:if test="${jalon.etat == 'NOT_FINISH'}">
                                <c:if test="${jalon.dateLivraisonPlanned != null}">
                                    <td class="center">Théorique : <fmt:formatDate value="${jalon.dateLivraisonPlanned}"
                                                                                   type="date"/></td>
                                </c:if>
                                <c:if test="${jalon.dateLivraisonPlanned == null}">
                                    <td class="center">Aucune</td>
                                </c:if>
                            </c:if>
                            <c:if test="${jalon.etat == 'FINISHED'}">
                                <c:if test="${jalon.dateLivraisonReal != null}">
                                    <td class="center">Réelle : <fmt:formatDate value="${jalon.dateLivraisonReal}"
                                                                                type="date"/></td>
                                </c:if>
                                <c:if test="${jalon.dateLivraisonReal == null}">
                                    <td class="center">
                                        <a href="${pageContext.request.contextPath}/jalon/${jalon.id}/deliver/form"
                                           class="btn-floating btn-small waves-effect waves-light pink tooltipped"
                                           data-position="top" data-delay="50"
                                           data-tooltip="Ajouter une date de livraison réelle">
                                            <i class="material-icons">av_timer</i>
                                        </a>
                                    </td>
                                </c:if>
                            </c:if>
                            <td>
                                <div class="center">${jalon.avancement}%
                                    <div class="progress">
                                        <div class="determinate" style="width: ${jalon.avancement}%"></div>
                                        <span>${jalon.avancement}%</span>
                                    </div>
                                </div>
                            </td>
                            <td class="center">${jalon.manager.trigramme}</td>
                            <td class="center">
                                <a href="${pageContext.request.contextPath}/jalon/${jalon.id}/tasks"
                                   class="btn-floating btn-small waves-effect waves-light tooltipped"
                                   data-position="left" data-delay="50" data-tooltip="Voir les tâches">
                                    <i class="material-icons">search</i>
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:if>
        </div>
    </div>

    <div style="padding-bottom: 10px"></div>

    <div class="card invoices-card">
        <div class="card-content">
            <div class="card-options">
                <a href="${pageContext.request.contextPath}/requirement/form/${project.id}"
                   class="btn-floating btn-small waves-effect waves-light tooltipped" data-position="top"
                   data-delay="50" data-tooltip="Ajouter une exigence">
                    <i class="material-icons">add</i>
                </a>
            </div>
            <span class="card-title">Liste des exigences</span>
            <c:if test="${requirements == null || requirements.isEmpty()}">
                <div class="center">Aucune exigence</div>
            </c:if>
            <c:if test="${!requirements.isEmpty()}">
                <table class="responsive-table bordered highlight">
                    <thead>
                    <tr>
                        <td data-field="numero">Numéro</td>
                        <td data-field="name">Exigence</td>
                        <td data-field="fonctionnal">Fonctionnel</td>
                        <td data-field="TypeRequirement">Type d'exigence</td>
                        <td data-field="tasks">Tâches</td>
                        <td data-field="actions">Actions</td>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requirements}" var="requirement">
                        <tr>
                            <td>#${requirement.id}</td>
                            <td>${requirement.description}</td>
                            <c:if test="${requirement.isFonctionnal == true}">
                                <td>Oui</td>
                            </c:if>
                            <c:if test="${requirement.isFonctionnal == false}">
                                <td>Non</td>
                            </c:if>
                            <c:if test="${requirement.typeRequirement.libelle != null}">
                                <td>${requirement.typeRequirement.libelle}</td>
                            </c:if>
                            <c:if test="${requirement.typeRequirement.libelle == null}">
                                <td>Aucune</td>
                            </c:if>
                            <td>${requirement.tasks.size()}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/requirement/${requirement.id}/tasks"
                                   class="btn-floating btn-small waves-effect waves-light tooltipped"
                                   data-position="left" data-delay="50" data-tooltip="Voir les tâches">
                                    <i class="material-icons">search</i>
                                </a>
                                <a href="${pageContext.request.contextPath}/task/form/${requirement.id}"
                                   class="btn-floating btn-small waves-effect waves-light pink tooltipped"
                                   data-position="right" data-delay="50" data-tooltip="Ajouter une tâche">
                                    <i class="material-icons">add</i>
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
    <div style="padding-bottom: 30px"></div>
</my:template>
