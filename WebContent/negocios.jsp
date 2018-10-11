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

.bold{
	font-weight: bold;
}



</style>


<head>
<link rel="stylesheet" type="text/css" href="css/admin.css"/>
</head> 
<body>   
<f:view>
	<jsp:include page="top.jsp"></jsp:include>
	<rich:panel header="Negocios">
		<h:form id="list_negocios">
			<h:outputText value="Saldo: #{loginHandler.user.saldoStr}" styleClass="bold"/>
			<br><br>
			<h:outputText value="Negócios pendentes" styleClass="bold"/>
			<rich:dataTable border="1" value="#{negociosHandler.negociosPendentes}" var="ta">
				<h:column>
					<f:facet name="header">
						<h:outputText value="Compra"></h:outputText>
					</f:facet>
					<h:outputText value="#{ta.opcaoCompra}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Venda"></h:outputText>
					</f:facet>
					<h:outputText value="#{ta.opcaoVenda}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Quantidade"></h:outputText>
					</f:facet>
					<h:outputText value="#{ta.quantidade}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Data trava"></h:outputText>
					</f:facet>
					<h:outputText value="#{ta.dataCompraStr}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Spreed"></h:outputText>
					</f:facet>
					<h:outputText value="Na trava #{ta.spreedInicialStr}  máximo #{ta.spreedMaximoStr}"/>
					<rich:inputNumberSlider showBoundaryValues="false" width="150" minValue="0" maxValue="#{ta.spreedMaximo}" step="0.05" value="#{ta.spreedInicial}" showInput="false"/>
					<h:outputText value="Atual #{ta.spreedStr}"/>
					<rich:inputNumberSlider showBoundaryValues="false" width="150" minValue="0" maxValue="#{ta.spreedMaximo}" step="0.05" value="#{ta.spreedAtual}" showInput="false"/>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Lucro potencial"></h:outputText>
					</f:facet>
					<h:outputText value="#{ta.lucroPotencialRSStr}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Lucro atual"></h:outputText>
					</f:facet>
					<h:outputText value="R$ #{ta.lucroAtualStr }   (#{ta.lucroAtualPCTStr}%)" styleClass="bold"/>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Desfazer"></h:outputText>
					</f:facet>
					<h:commandLink action="#{negociosHandler.desfazTrava}">
						<h:outputText value="undo it."></h:outputText>
						<f:setPropertyActionListener value="#{ta}" target="#{negociosHandler.trava}"/>
					</h:commandLink>
				</h:column>
			</rich:dataTable>
			
			<br><br>
			<h:outputText value="Negócios finalizados" styleClass="bold"/>
			<rich:dataTable border="1" value="#{negociosHandler.negociosFinalizados}" var="ta">
				<h:column>
					<f:facet name="header">
						<h:outputText value="Compra"></h:outputText>
					</f:facet>
					<h:outputText value="#{ta.opcaoCompra}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Venda"></h:outputText>
					</f:facet>
					<h:outputText value="#{ta.opcaoVenda}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Quantidade"></h:outputText>
					</f:facet>
					<h:outputText value="#{ta.quantidade}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Data trava"></h:outputText>
					</f:facet>
					<h:outputText value="#{ta.dataCompraStr}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Data destrava"></h:outputText>
					</f:facet>
					<h:outputText value="#{ta.dataVendaStr}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Lucro R$"></h:outputText>
					</f:facet>
					<h:outputText value="#{ta.lucroFinalStr}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Lucro %"></h:outputText>
					</f:facet>
					<h:outputText value="#{ta.lucroFinalPCTStr} %"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Motivo venda"></h:outputText>
					</f:facet>
					<h:outputText value="#{ta.motivoVenda}"></h:outputText>
				</h:column>

			</rich:dataTable>
		</h:form>
		<br>
	</rich:panel>
</f:view>
</body>
</html>