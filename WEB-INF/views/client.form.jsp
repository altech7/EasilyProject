<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<my:template action="index">
    <my:title title="Création d'une nouvelle ressource"></my:title>

    <div class="row">
        <div class="col s12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title"></span><br>

                    <div class="row">
                        <form:form class="col s12" action="/EasilyProject/client/create" commandName="client">

                            <div class="row">
                                <div class="input-field col s6">
                                    <form:input id="lastName" required="true" class="validate" type="text"
                                                path="lastName"/>
                                    <label for="lastName" data-error="Ce champ est requis" data-success="">Nom</label>
                                </div>
                                <span><form:errors path="lastName" cssClass="materialize-red-text"/></span>
                                <div class="input-field col s6">
                                    <form:input id="firstName" required="true" class="validate" type="text"
                                                path="firstName"/>
                                    <label for="firstName" data-error="Ce champ est requis"
                                           data-success="">Prénom</label>
                                </div>
                                <span><form:errors path="firstName" cssClass="materialize-red-text"/></span>
                            </div>

                            <div class="row">
                                <div class="input-field col s12">
                                    <form:input id="trigramme" required="true" class="validate" type="text" path="trigramme" maxlength="3" length="3" pattern=".{3,}"/>
                                    <label for="trigramme" data-error="Ce champ est requis" data-success="">Trigramme</label>
                                </div>
                                <span><form:errors path="trigramme" cssClass="materialize-red-text"/></span>
                            </div>

                            <div class="row">
                                <div class="input-field col s12">
                                    <form:input id="email" required="true" class="validate" type="email"
                                                path="email"/>
                                    <label for="email" data-error="Ce champ est requis" data-success="">Email</label>
                                </div>
                                <span><form:errors path="email" cssClass="materialize-red-text"/></span>
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