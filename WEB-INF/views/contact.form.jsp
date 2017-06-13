<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<my:template action="index">
    <my:title title="Formulaire de contact"></my:title>

    <div class="row">
        <div class="col s12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title"></span><br>

                    <div class="row">
                        <form:form class="col s12" action="/EasilyProject/contact/send">
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="to" required="true" name="to" class=validate" type="email"/>
                                    <label for="to" data-error="Ce champ est requis" data-success="">To</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="from" required="true" name="from" class=validate" type="email"/>
                                    <label for="from" data-error="Ce champ est requis" data-success="">From</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <input id="title" required="true" name="title" class=validate" type="text"/>
                                    <label for="title" data-error="Ce champ est requis" data-success="">Title</label>
                                </div>
                            </div>
                            <div class="row">
                                <div class="input-field col s12">
                                    <textarea id="message" class="materialize-textarea validate" required="true" name="message" ></textarea>
                                    <label for="message">Message</label>
                                </div>
                            </div>
                            <div class="center">
                                <input class="btn btn-default" type="submit" value="Envoyer"/>
                            </div>
                        </form:form>
                    </div>


                    <div class="card invoices-card">
                        <div class="card-content">
                            <span class="card-title"></span>
                            <c:if test="${!messages.isEmpty()}">
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
                                    <c:forEach items="${messages}" var="message">
                                        <tr>
                                            <td>${message.to}</td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</my:template>