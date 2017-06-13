<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<my:template action="index">
    <script src="${pageContext.request.contextPath}/assets/plugins/vertical-timeline/js/modernizr.js"></script>
    <script src="http://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="http://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>


    <div class="parallax-container">
        <div>
            <div class="parallax">
                <img src="${pageContext.request.contextPath}/assets/images/background-signin.jpg">
            </div>
            <div>
                <h2 class="header center"><i class="large material-icons" style="color: #fafafa; opacity: 0.87;">schedule</i></h2>
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
            <div class="row">
                <div class="col s12">
                    <div class="page-title"></div>
                </div>
                <div class="col s12">
                    <section id="cd-timeline" class="cd-container">
                        <c:forEach items="${jalons}" var="jalon">
                            <div class="cd-timeline-block">
                                <div class="cd-timeline-img cd-picture pink">
                                    <img src="${pageContext.request.contextPath}/assets/plugins/vertical-timeline/img/cd-icon-picture.svg"
                                         alt="Picture">
                                </div>

                                <div class="cd-timeline-content">
                                    <h2>Jalon : ${jalon.label}</h2>
                                    <div class="row">
                                        <div class="col s12">
                                            <div class="row">
                                                <div class="input-field col s8">
                                                    <label><span style="color: #26a69a">Etat : </span><span>${jalon.etat.getState()}</span></label>
                                                </div>
                                                <div class="input-field col s3">
                                                    <label><span style="color: #26a69a">Manager</span> : ${jalon.manager.trigramme}</label>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col s12">
                                            <div class="row">

                                                <div class="center" style="font-size: 15px; padding-top: 50px; color: #9e9e9e;"> Avancement du jalon
                                                    <div class="progress m-t-md">
                                                        <div class="determinate" style="width: ${jalon.avancement}%"></div>
                                                    </div>
                                                    <div>${jalon.avancement}%</div>
                                                </div>

                                            </div>
                                        </div>
                                    </div>
                                    <span class="cd-date"><fmt:formatDate value="${jalon.dateLivraisonReal}" type="date"/></span>
                                </div> <!-- cd-timeline-content -->
                            </div>
                            <!-- cd-timeline-block -->
                        </c:forEach>
                    </section>
                </div>
            </div>
        </div>
    </div>

</my:template>

<script src="${pageContext.request.contextPath}/assets/plugins/vertical-timeline/js/main.js"></script>