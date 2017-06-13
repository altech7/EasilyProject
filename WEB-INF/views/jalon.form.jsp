<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<my:template action="index">
    <my:title title="Création d'un nouveau jalon"></my:title>

    <div class="row">
        <div class="col s12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title"></span><br>

                    <div class="row">
                        <form:form class="col s12" action="/EasilyProject/jalon/create"
                                   commandName="jalon" method="POST">

                            <div class="row">
                                <div class="input-field col s12">
                                    <form:input id="label" required="true" class="validate" type="text" path="label"/>
                                    <label for="label" data-error="Champ invalide" data-success="">Nom du
                                        livrable</label>
                                </div>
                                <span><form:errors path="label" cssClass="materialize-red-text"/></span>
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

                            <div class="center">
                                <input class="btn btn-default" type="submit" value="Ajouter"/>
                            </div>

                            <input type="hidden" name="projectId" value="${project.id}" class="form-control"/>

                        </form:form>
                    </div>

                </div>
            </div>
        </div>
    </div>

</my:template>