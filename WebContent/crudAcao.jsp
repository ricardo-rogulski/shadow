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
	<rich:panel header="Ações">
		<h:form>
			<rich:dataTable border="1" value="#{crudAcaoHandler.acoes}" var="l">
				<h:column>
					<f:facet name="header">
						<h:outputText value="Id"></h:outputText>
					</f:facet>
					<h:outputText value="#{l.id}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Code"></h:outputText>
					</f:facet>
					<h:outputText value="#{l.code}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Name"></h:outputText>
					</f:facet>
					<h:outputText value="#{l.name}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Valor"></h:outputText>
					</f:facet>
					<h:outputText value="#{l.ultimaCotacao.valor}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Data"></h:outputText>
					</f:facet>
					<h:outputText value="#{l.ultimaCotacao.dataStr}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Update"></h:outputText>
					</f:facet>
					<h:commandLink actionListener="#{crudAcaoHandler.escolheAcao}">
						<h:outputText value="update"></h:outputText>
						<f:param id="editId" value="#{l.id}"></f:param>
					</h:commandLink>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Delete"></h:outputText>
					</f:facet>
					<h:commandLink actionListener="#{crudAcaoHandler.exclui}">
						<h:outputText value="delete"></h:outputText>
						<f:param id="excluiId" value="#{l.id}"></f:param>
					</h:commandLink>
				</h:column>
			</rich:dataTable>
		</h:form>
		<br>
		<rich:separator height="1"/>
		<br><br><br>
		<h:form id="ins_acao">
			<h:panelGrid columns="2"> 
				<h:outputText value="Nome:"/>
				<h:inputText value="#{crudAcaoHandler.acao.name}" id="name" size="20"/>
	
				<h:outputText value="Código:"/>
				<h:inputText value="#{crudAcaoHandler.acao.code}" id="code" size="5"/>
				
				<br><rich:spacer height="10"/>
	
				<h:commandButton value="Save" action="#{crudAcaoHandler.salva}" onclick="return validaForm()" styleClass="botao"/>
				<h:commandButton value="Cancel" action="#{crudAcaoHandler.cancel}" styleClass="botao"/>
			</h:panelGrid>
		</h:form>
	</rich:panel>
</f:view>
</body>
</html>