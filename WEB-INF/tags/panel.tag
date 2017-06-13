<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@attribute name="title" required="true"%>
<%@attribute name="total" required="false"%>
<%@attribute name="nbArticles" required="false"%>

<div class="panel panel-default">
	<div class="panel-heading">${title}</div>
	<div class="panel-body">
		<jsp:doBody />
	</div>

	<c:if test="${total > 0 }">
		<div class="panel-footer">
			<span>Total : ${total} euros | Nombre(s) d'article(s) : ${nbArticles}</span>
		</div>
	</c:if>
</div>