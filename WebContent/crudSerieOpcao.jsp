<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/admin.css"/>
</head> 
<body>   
<f:view>
	<jsp:include page="top.jsp"></jsp:include>
	<rich:panel header="Séries" styleClass="language-details">
		<h:form id="list_serie">
			<rich:dataTable border="1" value="#{crudSerieOpcaoHandler.series}" var="l">
				<h:column>
					<f:facet name="header">
						<h:outputText value="Id"></h:outputText>
					</f:facet>
					<h:outputText value="#{l.id}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Name"></h:outputText>
					</f:facet>
					<h:outputText value="#{l.name}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Data Exerc."></h:outputText>
					</f:facet>
					<h:outputText value="#{l.dataExercicioStr}"></h:outputText>
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="Update"></h:outputText>
					</f:facet>
					<h:commandLink actionListener="#{crudSerieOpcaoHandler.escolheSerieOpcao}">
						<h:outputText value="update"></h:outputText>
						<f:param id="editId" value="#{l.id}"></f:param>
					</h:commandLink>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Delete"></h:outputText>
					</f:facet>
					<h:commandLink actionListener="#{crudSerieOpcaoHandler.exclui}">
						<h:outputText value="delete"></h:outputText>
						<f:param id="excluiId" value="#{l.id}"></f:param>
					</h:commandLink>
				</h:column>
			</rich:dataTable>
			<br>
			<rich:separator height="1"/>
			<br>
			<h:panelGrid columns="2"> 
				<h:outputText value="Nome:"/>
				<h:inputText value="#{crudSerieOpcaoHandler.serie.name}" id="name" size="20"/>
	
				<h:outputText value="Data:"/>
				<rich:calendar direction="top-right" value="#{crudSerieOpcaoHandler.serie.dataExercicio.time}" ></rich:calendar>
	
				<br><rich:spacer height="10"/>
				
				<h:commandButton value="Save" action="#{crudSerieOpcaoHandler.salva}" styleClass="botao"/>
				<h:commandButton value="Cancel" action="#{crudSerieOpcaoHandler.cancel}" styleClass="botao"/>
				
			</h:panelGrid>
		</h:form>
	</rich:panel>
</f:view>
</body>
</html>