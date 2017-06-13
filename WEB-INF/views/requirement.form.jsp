<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<my:template action="index">
    <my:title title="Création d'une exigence"></my:title>

    <div class="row">
        <div class="col s12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title"></span><br>

                    <div class="row">

                        <form class="col s12" action="/EasilyProject/requirement/create" method="POST">

                            <div class="row">
                                <div class="input-field col s12">
                                    <textarea id="description" required class="validate materialize-textarea" type="text" name="description" maxlength="255" length="255"></textarea>
                                    <label for="description" data-error="Champs requis"
                                           data-success="">Exigences</label>
                                </div>
                                <span><form:errors path="description" cssClass="materialize-red-text"/></span>
                            </div>

                            <div class="row">
                                <div class="input-field col s4">
                                    <div class="switch m-b-md">
                                        <label>
                                            Non fonctionnelle
                                            <input type="checkbox" name="isFonctionnal" id="inputFonctionnal" value="false" onclick="checkFade();"/>
                                            <span class="lever"></span>
                                            Fonctionnelle
                                        </label>
                                    </div>

                                </div>
                                <div class="input-field col s8" id="selectTypeRequirement" style="opacity: 0">
                                    <select name="typeRequirement">
                                        <option value=" " selected>Choisissez un type d'exigence</option>
                                        <c:forEach items="${typeRequirements}" var="typeRequirement">
                                            <option value="${typeRequirement.code}">${typeRequirement.libelle}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>

                            <div class="center">
                                <input class="btn btn-default" type="submit" value="Ajouter"/>
                            </div>

                            <input type="hidden" name="projectId" value="${project.id}" class="form-control" />
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" class="form-control" />
                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>

</my:template>