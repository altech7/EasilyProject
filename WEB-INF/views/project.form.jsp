<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<my:template action="index">
    <my:title title="Création d'un nouveau projet"></my:title>

    <div class="row">
        <div class="col s12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title"></span><br>

                    <div class="row">
                        <form:form class="col s12" action="/EasilyProject/project/create" commandName="project">

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
                                    <form:input id="name" required="true" class="validate" type="text" path="name"/>
                                    <label for="name" data-error="Un trigramme doit posséder 3 caracterès" data-success="">Nom du projet</label>
                                </div>
                                <span><form:errors path="name" cssClass="materialize-red-text"/></span>
                                <div class="input-field col s6">
                                    <form:input id="trigramme" pattern=".{3,}" required="true" class="validate" type="text" path="trigramme" length="3"  maxlength="3" />
                                    <label for="trigramme" data-error="Un trigramme doit posséder 3 caracterès" data-success="">Trigramme</label>
                                </div>
                                <span><form:errors path="trigramme" cssClass="materialize-red-text"/></span>
                            </div>

                            <div class="center">
                                <input class="btn btn-default" type="submit" value="Ajouter"/>
                            </div>

                        </form:form>
                    </div>

                </div>
            </div>
        </div>
    </div>

</my:template>