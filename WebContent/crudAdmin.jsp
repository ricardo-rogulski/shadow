<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html>
<html>

<style>

.positivo{
	font-weight: bold;
	color: green;
}

.negativo{
	font-weight: bold;
	color: red;
}


</style>


<head>
<link rel="stylesheet" type="text/css" href="css/admin.css"/>
</head> 
<body>   
<f:view>
	<jsp:include page="top.jsp"></jsp:include>
	<rich:panel header="Admin">
		<h:form id="update">
			<h:panelGrid columns="2"> 
				<h:outputText value="Intervalo rob� cota��o A��o:"/>
				<h:inputText value="#{crudAdminHandler.admin.intervaloCotacaoAcao}" size="10"/>
	
				<h:outputText value="Intervalo rob� cota��o Op��o:"/>
				<h:inputText value="#{crudAdminHandler.admin.intervaloCotacaoOpcao}" size="10"/>

				<h:outputText value="Intervalo rob� Neg�cios:"/>
				<h:inputText value="#{crudAdminHandler.admin.intervaloNegocios}" size="10"/>
	
				<h:outputText value="Hor�rio in�cio cota��es:"/>
				<h:inputText value="#{crudAdminHandler.admin.horarioInicioCotacoes}" size="10"/>

				<h:outputText value="Hor�rio fim cota��es:"/>
				<h:inputText value="#{crudAdminHandler.admin.horarioFinalCotacoes}" size="10"/>

				<h:outputText value="A��o:"/>
				<h:inputText value="#{crudAdminHandler.admin.acao}" size="10"/>

				<h:outputText value="Stop Gain:"/>
				<h:inputText value="#{crudAdminHandler.admin.stopGain}" size="10"/>

				<h:outputText value="Stop M�vel:"/>
				<h:inputText value="#{crudAdminHandler.admin.stopMovel}" size="10"/>
				
				<h:outputText value="M�dia m�vel menor"/>
				<h:inputText value="#{crudAdminHandler.admin.mediaMovelMenor}" size="10"/>

				<h:outputText value="M�dia m�vel maior"/>
				<h:inputText value="#{crudAdminHandler.admin.mediaMovelMaior}" size="10"/>

				<h:outputText value="Rob� cota��o A��o ativo"/>
				<h:selectBooleanCheckbox value="#{crudAdminHandler.admin.roboCotacaoAcaoAtivo}"/>

				<h:outputText value="Rob� cota��o Op��o ativo"/>
				<h:selectBooleanCheckbox value="#{crudAdminHandler.admin.roboCotacaoOpcaoAtivo}"/>

				<h:outputText value="Rob� neg�cios ativo"/>
				<h:selectBooleanCheckbox value="#{crudAdminHandler.admin.roboNegociosAtivo}"/>

				<h:outputText value="Usar proxy"/>
				<h:selectBooleanCheckbox value="#{crudAdminHandler.admin.usarProxy}"/>


				<br><br>
				
				<rich:spacer height="10"/>
				<h:commandButton value="Save" action="#{crudAdminHandler.salva}" styleClass="botao"/>
			</h:panelGrid>
		</h:form>
	</rich:panel>
</f:view>
</body>
</html>