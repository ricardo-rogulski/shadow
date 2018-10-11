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

.panelDTA{
	position: absolute;
	height: 80px;
	width: 1000px;
	top: 75px;
}

.panelValores{
	position: absolute;
	height: 350px;
	width: 300px;
	top: 75px;
	left: 10px;
}


.panelGrafico{
	position: absolute;
	height: 350px;
	width: 550px;
	top: 75px;
	left: 320px;
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
		<h:panelGrid columns="2">
			<rich:panel header="Detalhes da Trava de Alta" styleClass="panelValores">
			
				<h:panelGrid columns="2">
				
					<h:outputLink value="cockpit.jsf">
						<h:outputText value="Voltar" styleClass="bold"/>
					</h:outputLink>
					<h:outputText/>
					
					<rich:spacer height="15"/>
					<rich:spacer height="15"/>
				
					<h:outputText value="Ação: #{dtaHandler.acao.code}" styleClass="bold"/>
					<h:panelGroup>
						<h:outputText value=" (+#{dtaHandler.acao.ultimaCotacao.oscilacao})" styleClass="positivo" rendered="#{dtaHandler.acao.ultimaCotacao.oscilacao>=0}"/>
						<h:outputText value=" (-#{dtaHandler.acao.ultimaCotacao.oscilacao})" styleClass="negativo" rendered="#{dtaHandler.acao.ultimaCotacao.oscilacao<0}"/>
						<h:outputText value=" (#{dtaHandler.acao.ultimaCotacao.valor})"/>
					</h:panelGroup>
				
					<h:outputText value="Opção compra: #{dtaHandler.trava.opcaoCompra}" styleClass="bold"/>
					<h:panelGroup>
						<h:outputText value=" (+#{dtaHandler.trava.valorOscilacaoOpcaoCompraAtual})" styleClass="positivo" rendered="#{dtaHandler.trava.valorOscilacaoOpcaoCompraAtual>=0}"/>
						<h:outputText value=" (-#{dtaHandler.trava.valorOscilacaoOpcaoCompraAtual})" styleClass="negativo" rendered="#{dtaHandler.trava.valorOscilacaoOpcaoCompraAtual<0}"/>
						<h:outputText value=" (#{dtaHandler.trava.valorOpcaoCompraAtual})"/>
					</h:panelGroup>

					<h:outputText value="Opção venda: #{dtaHandler.trava.opcaoVenda}" styleClass="bold"/>
					<h:panelGroup>
						<h:outputText value=" (+#{dtaHandler.trava.valorOscilacaoOpcaoVendaAtual})" styleClass="positivo" rendered="#{dtaHandler.trava.valorOscilacaoOpcaoVendaAtual>=0}"/>
						<h:outputText value=" (-#{dtaHandler.trava.valorOscilacaoOpcaoVendaAtual})" styleClass="negativo" rendered="#{dtaHandler.trava.valorOscilacaoOpcaoVendaAtual<0}"/>
						<h:outputText value=" (#{dtaHandler.trava.valorOpcaoVendaAtual})"/>
					</h:panelGroup>

					<rich:spacer height="15"/>
					<rich:spacer height="15"/>
					
					<h:outputText value="Saldo usuário:" styleClass="bold"/>
					<h:outputText value="#{dtaHandler.trava.user.saldoStr}" styleClass="bold"/>
					
					<h:outputText value="Valor negócio:" styleClass="bold"/>
					<h:outputText value="#{dtaHandler.trava.valorNegocioStr}" styleClass="bold"/>

					<h:outputText value="Lucro potencial R$: " styleClass="bold"/>
					<h:outputText value="#{dtaHandler.trava.lucroPotencialRSStr}" styleClass="bold"/>

					<h:outputText value="Lucro potencial %: " styleClass="bold"/>
					<h:outputText value="#{dtaHandler.trava.lucroPotencialPCTStr}%" styleClass="bold"/>

					<rich:spacer height="15"/>
					<rich:spacer height="15"/>				
				
					<h:outputText value="Spreed atual: " styleClass="bold"/>
					<h:outputText value="#{dtaHandler.trava.spreedStr}" styleClass="bold"/>

					<h:outputText value="Spreed máximo: " styleClass="bold"/>
					<h:outputText value="#{dtaHandler.trava.spreedMaximoStr}" styleClass="bold"/>
					

					<h:outputText value="Margem: " styleClass="bold"/>
					<h:outputText value="#{dtaHandler.trava.margemSegurancaStr}" styleClass="bold"/>
					
					<rich:spacer height="15"/>
					<rich:spacer height="15"/>
					
					<h:outputText value="Fazer trava: " styleClass="bold"/>
					<h:commandLink actionListener="#{dtaHandler.fazTrava}">
						<h:outputText value="do it." styleClass="bold"></h:outputText>
						<f:param id="travaId" value="#{n.id}"></f:param>
					</h:commandLink>
					
				</h:panelGrid>
			</rich:panel>
			<br>
			<rich:panel styleClass="panelGrafico">
				<h:graphicImage value="#{dtaHandler.grafico}"/>
			</rich:panel>

		</h:panelGrid>
	</h:form>
</f:view>
</body>
</html>