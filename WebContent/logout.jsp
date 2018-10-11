<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html>
<html>
<head>
<title>Sun</title>
<link rel="stylesheet" type="text/css" href="css/admin.css"/>

<style>
.botao{
	color: #FFFFFF;
	background-color: #4682B4;
	border-color: #4682B4;
	border-style: solid;
	font-weight: bold; 
}

</style>


</head>
<f:view>       
 	<jsp:include page="top.jsp"></jsp:include>
	<h:form id="login">
	<h:form>
		<br><br>
		<center>
		<h:panelGrid>
			<rich:panel>
				<center>
				<h:outputText value="Você realmente deseja sair?" style="font-weight:bold"/>
				<br><br>
				<h:commandButton value=" Yes " action="#{loginHandler.sair}" styleClass="botao"></h:commandButton>
				</center>
			</rich:panel>
		</h:panelGrid>
		</center>
	</h:form>

	</h:form> 	
</f:view>
</html>