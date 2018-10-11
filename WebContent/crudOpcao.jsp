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
			<rich:dataTable border="1" value="#{crudOpcaoHandler.opcoes}" var="l">
				<h:column>
					<f:facet name="header">
						<h:outputText value="Id"></h:outputText>
					</f:facet>
					<h:outputText value="#{l.id}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Ação"></h:outputText>
					</f:facet>
					<h:outputText value="#{l.acao.name}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Série"></h:outputText>
					</f:facet>
					<h:outputText value="#{l.serie.name}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Code"></h:outputText>
					</f:facet>
					<h:outputText value="#{l.code}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Vl. Exerc."></h:outputText>
					</f:facet>
					<h:outputText value="#{l.vlExercicio}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Valor Opção"></h:outputText>
					</f:facet>
					<h:outputText value="#{l.ultimaCotacao.valor}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Oscilação"></h:outputText>
					</f:facet>
					<h:outputText value=" +#{l.ultimaCotacao.oscilacao}" styleClass="positivo" rendered="#{l.ultimaCotacao.oscilacao>=0}"/>
					<h:outputText value=" -#{l.ultimaCotacao.oscilacao}" styleClass="negativo" rendered="#{l.ultimaCotacao.oscilacao<0}"/>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Valor Ext."></h:outputText>
					</f:facet>
					<h:outputText value="#{l.valorExtrinsico}" style="font-weight: bold"/>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Data"></h:outputText>
					</f:facet>
					<h:outputText value="#{l.ultimaCotacao.dataStr}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Ativo"></h:outputText>
					</f:facet>
					<h:outputText value="#{l.ativo}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Update"></h:outputText>
					</f:facet>
					<h:commandLink actionListener="#{crudOpcaoHandler.escolheOpcao}">
						<h:outputText value="update"></h:outputText>
						<f:param id="editId" value="#{l.id}"></f:param>
					</h:commandLink>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Delete"></h:outputText>
					</f:facet>
					<h:commandLink actionListener="#{crudOpcaoHandler.exclui}">
						<h:outputText value="delete"></h:outputText>
						<f:param id="excluiId" value="#{l.id}"></f:param>
					</h:commandLink>
				</h:column>
			</rich:dataTable>
		</h:form>
		<br>
		<rich:separator height="1"/>
		<br>
		<h:form id="ins_acao">
			<h:panelGrid columns="2"> 
				<h:outputText value="Ação:"/>
				<h:selectOneMenu binding="#{crudOpcaoHandler.selectedAcao}" id="acao">
					<f:selectItem itemValue="-1" itemLabel="Selecione"/>
					<f:selectItems value="#{crudOpcaoHandler.acoesToComboBox}"/>
				</h:selectOneMenu>
				
				<h:outputText value="Série:"/>
				<h:selectOneMenu binding="#{crudOpcaoHandler.selectedSerie}" id="serie">
					<f:selectItem itemValue="-1" itemLabel="Selecione"/>
					<f:selectItems value="#{crudOpcaoHandler.seriesToComboBox}"/>
				</h:selectOneMenu>

				<h:outputText value="Codigo:"/>
				<h:inputText value="#{crudOpcaoHandler.opcao.code}" id="code" size="8"/>
	
				<h:outputText value="Vl Exercicio:"/>
				<h:inputText value="#{crudOpcaoHandler.opcao.vlExercicio}" id="exerc" size="5"/>
				
				<h:outputText value="Ativo:"/>
				<h:selectBooleanCheckbox value="#{crudOpcaoHandler.opcao.ativo}"/>
				
				
				<br><rich:spacer height="10"/>
	
				<h:commandButton value="Save" action="#{crudOpcaoHandler.salva}" onclick="return validaForm()" styleClass="botao"/>
				<h:commandButton value="Cancel" action="#{crudOpcaoHandler.cancel}" styleClass="botao"/>
			</h:panelGrid>
		</h:form>
	</rich:panel>
</f:view>
</body>
</html>