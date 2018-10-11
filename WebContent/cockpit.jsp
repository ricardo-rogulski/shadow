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

.panelA{
	height: auto;
	width: 800px;
}


.panelSTA{
	height: auto;
	width: 800px;
}

.panelSTB{
	height: auto;
	width: 800px;
}

.panelGRA{
	position: absolute;
	height: 610px;
	width: 600px;
	top: 75px;
	left: 720px;
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
	<h:form id="cockpit">
		<h:panelGrid columns="1">
			<rich:panel header="Ações" styleClass="panelA">
				<h:panelGroup>
					<h:outputText value="Saldo: "/>
					<h:outputText value="#{loginHandler.user.saldoStr}" styleClass="bold"/>
				</h:panelGroup>
				<rich:spacer width="30"/>

				<h:panelGrid columns="5">
					<rich:dataGrid value="#{cockpitHandler.acoes}" var="a" columns="2">
						<rich:panel>
							<h:outputText value="#{a.code}" styleClass="bold"/>
							<h:outputText value=" (+#{a.ultimaCotacao.oscilacao})" styleClass="positivo" rendered="#{a.ultimaCotacao.oscilacao>=0}"/>
							<h:outputText value=" (-#{a.ultimaCotacao.oscilacao})" styleClass="negativo" rendered="#{a.ultimaCotacao.oscilacao<0}"/>
							<h:outputText value=" (#{a.ultimaCotacao.valor})"/>
						</rich:panel>
					</rich:dataGrid>
					<rich:spacer width="50"/>
					<h:outputLink value="graficoAcao.jsf">
						<h:outputText value="Grafico histórico"/>
					</h:outputLink>
					<rich:spacer width="50"/>
					<h:outputLink value="graficoDiarioAcao.jsf">
						<h:outputText value="Grafico diário"/>
					</h:outputLink>

				</h:panelGrid>
			</rich:panel>
		
			<rich:panel header="Travas de alta" styleClass="panelSTA">
				<rich:dataTable value="#{cockpitHandler.travasDeAltaPossiveis}" var="ta">
					<h:column>
						<f:facet name="header">
							<h:outputText value="Compra"/>
						</f:facet>
						<h:outputText value="#{ta.opcaoCompra}" styleClass="bold"/>
						<h:outputText value=" (+#{ta.valorOscilacaoOpcaoCompraAtual})" styleClass="positivo" rendered="#{ta.valorOscilacaoOpcaoCompraAtual>=0}"/>
						<h:outputText value=" (-#{ta.valorOscilacaoOpcaoCompraAtual})" styleClass="negativo" rendered="#{ta.valorOscilacaoOpcaoCompraAtual<0}"/>
						<h:outputText value=" (#{ta.valorOpcaoCompraAtual})"/>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Venda"/>
						</f:facet>
						<h:outputText value="#{ta.opcaoVenda}" styleClass="bold"/>
						<h:outputText value=" (+#{ta.valorOscilacaoOpcaoVendaAtual})" styleClass="positivo" rendered="#{ta.valorOscilacaoOpcaoVendaAtual>=0}"/>
						<h:outputText value=" (-#{ta.valorOscilacaoOpcaoVendaAtual})" styleClass="negativo" rendered="#{ta.valorOscilacaoOpcaoVendaAtual<0}"/>
						<h:outputText value=" (#{ta.valorOpcaoVendaAtual})"/>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Spreed"/>
						</f:facet>
						<h:outputText value="#{ta.spreedStr}"/>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Quantidade"/>
						</f:facet>
						<h:outputText value="#{ta.quantidadeStr}"/>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Margem"/>
						</f:facet>
						<h:outputText value="#{ta.margemSegurancaStr}"/>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Lucro pot. %"/>
						</f:facet>
						<h:outputText value="#{ta.lucroPotencialPCTStr} %"/>
					</h:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Lucro pot. R$"/>
						</f:facet>
						<h:outputText value="#{ta.lucroPotencialRSStr}"/>
					</h:column>
					<rich:column sortBy="#{ta.pontos}">
						<f:facet name="header">
							<h:outputText value="Pontuação"/>
						</f:facet>
							<center>
								<h:outputText value="#{ta.pontuacao}"/>
							</center>
					</rich:column>
					<h:column>
						<f:facet name="header">
							<h:outputText value="Detalhes"></h:outputText>
						</f:facet>
						<h:commandLink actionListener="#{cockpitHandler.detalhes}">
							<h:outputText value="detalhes"></h:outputText>
							<f:param id="detalhe" value="#{ta.opcaoCompra};#{ta.opcaoVenda}"></f:param>
						</h:commandLink>
					</h:column>
				</rich:dataTable>
			</rich:panel>
		</h:panelGrid>
		<rich:panel header="Gráficos" styleClass="panelGRA" rendered="false">
			<rich:dataGrid value="#{cockpitHandler.acoes}" var="a">
				<h:graphicImage value="#{a.grafico}"/>
			</rich:dataGrid>
		</rich:panel>

	</h:form>
</f:view>
</body>
</html>