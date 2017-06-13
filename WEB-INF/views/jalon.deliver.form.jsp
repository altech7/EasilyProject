<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<my:template action="index">
    <my:title title="Ajout d'une date de livraison réelle"></my:title>

    <div class="row">
        <div class="col s12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title">Jalon : ${jalon.label}</span><br>

                    <div class="row">
                        <form id="deliverForm" class="col s12" action="/EasilyProject/jalon/deliver/update" method="POST">

                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="dateLivraisonReal" required="true" name="dateLivraisonReal"
                                           class="datepicker" type="date"/>
                                    <label for="dateLivraisonReal" data-error="Champs requis" data-success="">Saisissez
                                        une date de livraison réelle</label>
                                </div>
                            </div>

                            <div class="center">
                                <input class="btn btn-default" type="button" onclick="controlFromDeliver()" value="Ajouter"/>
                            </div>

                            <input type="hidden" name="id" value="${jalon.id}" class="form-control"/>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" class="form-control"/>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>

    <input id='showErrorMessage' type="hidden" value=${showErrorMessage}/>
</my:template>