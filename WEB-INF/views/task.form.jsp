<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<my:template action="index">
    <my:title title="Création d'une nouvelle tâche"></my:title>

    <div class="row">
        <div class="col s12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title">Projet : ${requirement.project.name} / Exigence : ${requirement.description}</span>

                    <div class="row">
                        <form:form id="taskForm" class="col s12" action="/EasilyProject/task/create/${requirement.id}"
                                   commandName="task">

                            <div class="row">
                                <div class="input-field col s12">
                                    <form:input id="label" required="true" class="validate" type="text" path="label"
                                                length="255" maxlength="255"/>
                                    <label for="label" data-error="Champs requis" data-success="">Sujet</label>
                                </div>
                                <span><form:errors path="label" cssClass="materialize-red-text"/></span>
                            </div>

                            <div class="row">
                                <div class="input-field col s12">
                                    <form:textarea id="description" required="true"
                                                   class="validate materialize-textarea" type="text" path="description"
                                                   length="255" maxlength="255"/>
                                    <label for="description" data-error="Champs requis" data-success="">Description de
                                        la tâche</label>
                                </div>
                                <span><form:errors path="description" cssClass="materialize-red-text"/></span>
                            </div>

                            <div class="row">
                                <div class="input-field col s12">
                                    <form:select name="manager" path="manager.id" required="true">
                                        <option value="" disabled selected>Choisissez une ressource</option>
                                        <c:forEach items="${managers}" var="manager">
                                            <option value="${manager.id}">${manager.trigramme}</option>
                                        </c:forEach>
                                    </form:select>
                                    <label>Manager</label>
                                    <span><form:errors path="manager" cssClass="materialize-red-text"/></span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="input-field col s6">
                                    <form:input id="dateStart" required="true" class="datepicker" type="date"
                                                path="dateStart"/>
                                    <label for="dateStart" data-error="Champs requis" data-success="">Date de
                                        début (théorique)</label>
                                </div>
                                <span><form:errors path="dateStart" cssClass="materialize-red-text"/></span>

                                <div class="input-field col s6">
                                    <form:input id="charge" required="true" class="validate" type="number"
                                                path="charge"/>
                                    <label for="charge" data-error="Ce champ n'est pas valide"
                                           data-success="">Charge</label>
                                </div>
                                <span><form:errors path="charge" cssClass="materialize-red-text"/></span>
                            </div>

                            <div class="row">
                                <div class="input-field col s12">
                                    <form:select id="jalonSelect" name="jalon" path="jalon.id" required="true">
                                        <option value="" disabled selected>Choisissez un jalon</option>
                                        <c:forEach items="${jalons}" var="jalon">
                                            <option value="${jalon.id}">#${jalon.id} - ${jalon.label}</option>
                                        </c:forEach>
                                    </form:select>
                                    <label>Jalon
                                        <span class="waves-effect waves-light tooltipped"
                                              data-position="top" data-delay="50"
                                              data-tooltip="Dans le cas où l'exigence possède déjà une tâche, les prochaines tâches devront avoir le même jalon. Par conséquent le jalon affiché sera celui de la première tâche ajoutée.">
                                            <i class="material-icons">info_outline</i>
                                        </span>
                                    </label>
                                    <span><form:errors path="jalon" cssClass="materialize-red-text"/></span>
                                </div>
                            </div>

                            <div class="row">
                                <div class="input-field col s12" id="test">
                                    <form:select id="previousTask" name="previousTask" path="previousTask.id">
                                        <option value="" disabled selected>Choisissez une tâche précédente</option>
                                        <c:forEach items="${previousTasks}" var="previousTask">
                                            <option value="${previousTask.id}">#${previousTask.id}
                                                - ${previousTask.label}</option>
                                        </c:forEach>
                                    </form:select>
                                    <label>Tâche précédente</label>
                                    <span><form:errors path="previousTask" cssClass="materialize-red-text"/></span>
                                </div>
                            </div>

                            <div class="center">
                                <input class="btn btn-default" type="button" onclick="controlFormTask()"
                                       value="Ajouter"/>
                            </div>

                            <input type="hidden" name="requirementId" value="${requirement.id}" class="form-control"/>

                        </form:form>
                    </div>

                </div>
            </div>
        </div>
    </div>

</my:template>
