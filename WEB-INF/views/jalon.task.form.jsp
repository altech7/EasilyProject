<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib prefix="my" tagdir="/WEB-INF/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<my:template action="index">
    <my:title title="Ajout de tâches au jalon"></my:title>

    <div class="row">
        <div class="col s12">
            <div class="card">
                <div class="card-content">
                    <span class="card-title">Projet : ${project.name} / Jalon : ${jalon.label}</span><br>

                    <div class="row">
                        <form class="col s12" action="/EasilyProject/jalon/task/create" method="POST">

                            <div class="input-field col s12">
                                <select multiple id="tasks" name="tasks" required>
                                    <option value="" disabled selected>choisissez les tâches</option>
                                    <c:forEach items="${tasks}" var="task">
                                        <option value="${task.id}">#${task.id} - ${task.label}</option>
                                    </c:forEach>
                                </select>
                                <label>Tâches du projet</label>
                            </div>

                            <div class="center">
                                <input class="btn btn-default" type="submit" value="Ajouter"/>
                            </div>

                            <input type="hidden" name="jalonId" value="${jalon.id}" class="form-control"/>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"
                                   class="form-control"/>
                        </form>
                    </div>

                </div>
            </div>
        </div>
    </div>

</my:template>

<script>
    $(document).ready(function () {
        $('select').material_select();
    });
</script>
