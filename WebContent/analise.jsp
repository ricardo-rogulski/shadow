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
	<rich:panel header="Robôs">
		<h:form id="list_analises">
			<h:outputText value="Análise robôs. Negócios finalizados." styleClass="bold"/>
			<rich:dataTable border="1" value="#{analiseHandler.analises}" var="a">
				<rich:column sortBy="#{a.robo.login}">
					<f:facet name="header">
						<h:outputText value="Usuário"></h:outputText>
					</f:facet>
					<h:outputText value="#{a.robo.login}"></h:outputText>
				</rich:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Negócios"></h:outputText>
					</f:facet>
					<h:outputText value="#{a.qtdNegocios}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Neg positivos"></h:outputText>
					</f:facet>
					<h:outputText value="#{a.qtdNegociosPositivos}"></h:outputText>
				</h:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Neg negativos"></h:outputText>
					</f:facet>
					<h:outputText value="#{a.qtdNegociosNegativos}"></h:outputText>
				</h:column>
				<rich:column sortBy="#{a.robo.lucro}">
					<f:facet name="header">
						<h:outputText value="Lucro"></h:outputText>
					</f:facet>
					<h:outputText value="#{a.lucroStr}" styleClass="bold"></h:outputText>
				</rich:column>
				<h:column>
					<f:facet name="header">
						<h:outputText value="Corretagem"></h:outputText>
					</f:facet>
					<h:outputText value="#{a.corretagem}"></h:outputText>
				</h:column>

				<h:column>
					<f:facet name="header">
						<h:outputText value="Ver negócios"></h:outputText>
					</f:facet>
					<h:commandLink action="#{analiseHandler.verNegocios}">
						<h:outputText value="ver"></h:outputText>
						<f:setPropertyActionListener value="#{a}" target="#{analiseHandler.analise}"/>
					</h:commandLink>
				</h:column>
			</rich:dataTable>
			<br><br>
			
			<h:outputText value="Negócios pendentes (Travas de Alta)" styleClass="bold"/>
			<rich:dataTable border="1" value="#{analiseHandler.travasDeAltaPendentes}" var="ta">
				<h:column>
					<f:facet name="header">
						<h:outputText value="Usuário"></h:outputText>
					</f:facet>
					<h:outputText value="#{ta.user.login}"></h:outputText>
				</h:column>
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
			</rich:dataTable>
			<br><br>
			
			<h:outputText value="Negócios pendentes (Travas de Baixa)" styleClass="bold"/>
			<rich:dataTable border="1" value="#{analiseHandler.travasDeBaixaPendentes}" var="ta">
				<h:column>
					<f:facet name="header">
						<h:outputText value="Usuário"></h:outputText>
					</f:facet>
					<h:outputText value="#{ta.user.login}"></h:outputText>
				</h:column>
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
			</rich:dataTable>
		
		
		</h:form>
		
		
		
	</rich:panel>
</f:view>
</body>
</html>