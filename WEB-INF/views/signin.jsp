<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>

    <!-- Title -->
    <title>Easily Project</title>

    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta charset="UTF-8">
    <meta name="description" content="Responsive Admin Dashboard Template"/>
    <meta name="keywords" content="admin,dashboard"/>
    <meta name="author" content="Steelcoders"/>

    <!-- Styles -->
    <link type="text/css" rel="stylesheet"
          href="${pageContext.request.contextPath}/assets/plugins/materialize/css/materialize.min.css"/>
    <link href="http://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
    <link
            href="${pageContext.request.contextPath}/assets/plugins/material-preloader/css/materialPreloader.min.css"
            rel="stylesheet">


    <!-- Theme Styles -->
    <link href="${pageContext.request.contextPath}/assets/css/alpha.min.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/assets/css/custom.css" rel="stylesheet" type="text/css"/>

</head>
<body class="signin-page">
<div class="loader-bg"></div>
<div class="loader">
    <div class="preloader-wrapper big active">
        <div class="spinner-layer spinner-blue">
            <div class="circle-clipper left">
                <div class="circle"></div>
            </div>
            <div class="gap-patch">
                <div class="circle"></div>
            </div>
            <div class="circle-clipper right">
                <div class="circle"></div>
            </div>
        </div>
        <div class="spinner-layer spinner-red">
            <div class="circle-clipper left">
                <div class="circle"></div>
            </div>
            <div class="gap-patch">
                <div class="circle"></div>
            </div>
            <div class="circle-clipper right">
                <div class="circle"></div>
            </div>
        </div>
        <div class="spinner-layer spinner-yellow">
            <div class="circle-clipper left">
                <div class="circle"></div>
            </div>
            <div class="gap-patch">
                <div class="circle"></div>
            </div>
            <div class="circle-clipper right">
                <div class="circle"></div>
            </div>
        </div>
        <div class="spinner-layer spinner-green">
            <div class="circle-clipper left">
                <div class="circle"></div>
            </div>
            <div class="gap-patch">
                <div class="circle"></div>
            </div>
            <div class="circle-clipper right">
                <div class="circle"></div>
            </div>
        </div>
    </div>
</div>
<div class="mn-content valign-wrapper">
    <main class="mn-inner container">
        <div class="center" style="padding-bottom: 60px">
            <h1>
                <span style="color: white; font-style: italic; opacity: 0.82;">Easily Project</span>
            </h1>
        </div>
        <div class="valign">
            <div class="row">
                <div class="col s12 m6 l4 offset-l4 offset-m3">
                    <div class="card white darken-1">
                        <div class="card-content ">
                            <span class="card-title">Connexion</span>
                            <div class="row">

                                <form:form class="col s12" method="POST" action="signin">
                                    <div class="input-field col s12">
                                        <label class="sr-only">Utilisateur</label>
                                        <input type="text" name="email" class="validate" required autofocus/>
                                    </div>

                                    <div class="input-field col s12">
                                        <label class="sr-only">Mot de passe</label>
                                        <input type="password" name="password" class="validate" required/>
                                    </div>

                                    <c:if test="${param.error != null}">
                                        <div class="center" style="color: #ee6e73;">Ce client n'existe pas.</div>
                                    </c:if>

                                    <div class="col s12 right-align m-t-sm">
                                        <a href="" class="waves-effect waves-grey btn-flat">S'inscrire</a>
                                        <button class="waves-effect waves-light btn teal" type="submit">Se connecter
                                        </button>
                                    </div>
                                </form:form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>

<!-- Javascripts -->
<script src="${pageContext.request.contextPath}/assets/plugins/jquery/jquery-2.2.0.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/materialize/js/materialize.min.js"></script>
<script
        src="${pageContext.request.contextPath}/assets/plugins/material-preloader/js/materialPreloader.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/plugins/jquery-blockui/jquery.blockui.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/alpha.min.js"></script>

</body>
</html>