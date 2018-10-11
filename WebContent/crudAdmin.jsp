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
				<h:outputText value="Intervalo robô cotação Ação:"/>
				<h:inputText value="#{crudAdminHandler.admin.intervaloCotacaoAcao}" size="10"/>
	
				<h:outputText value="Intervalo robô cotação Opção:"/>
				<h:inputText value="#{crudAdminHandler.admin.intervaloCotacaoOpcao}" size="10"/>

				<h:outputText value="Intervalo robô Negócios:"/>
				<h:inputText value="#{crudAdminHandler.admin.intervaloNegocios}" size="10"/>
	
				<h:outputText value="Horário início cotações:"/>
				<h:inputText value="#{crudAdminHandler.admin.horarioInicioCotacoes}" size="10"/>

				<h:outputText value="Horário fim cotações:"/>
				<h:inputText value="#{crudAdminHandler.admin.horarioFinalCotacoes}" size="10"/>

				<h:outputText value="Ação:"/>
				<h:inputText value="#{crudAdminHandler.admin.acao}" size="10"/>

				<h:outputText value="Stop Gain:"/>
				<h:inputText value="#{crudAdminHandler.admin.stopGain}" size="10"/>

				<h:outputText value="Stop Móvel:"/>
				<h:inputText value="#{crudAdminHandler.admin.stopMovel}" size="10"/>
				
				<h:outputText value="Média móvel menor"/>
				<h:inputText value="#{crudAdminHandler.admin.mediaMovelMenor}" size="10"/>

				<h:outputText value="Média móvel maior"/>
				<h:inputText value="#{crudAdminHandler.admin.mediaMovelMaior}" size="10"/>

				<h:outputText value="Robô cotação Ação ativo"/>
				<h:selectBooleanCheckbox value="#{crudAdminHandler.admin.roboCotacaoAcaoAtivo}"/>

				<h:outputText value="Robô cotação Opção ativo"/>
				<h:selectBooleanCheckbox value="#{crudAdminHandler.admin.roboCotacaoOpcaoAtivo}"/>

				<h:outputText value="Robô negócios ativo"/>
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