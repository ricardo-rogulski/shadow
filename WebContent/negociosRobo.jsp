<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<%@ taglib uri="http://richfaces.org/a4j" prefix="a4j"%>
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

.painel{
	width: 800px;
}



</style>


<head>

<script language="javascript" type="text/javascript" src="js/excanvas.min.js"></script>
<script language="javascript" type="text/javascript" src="js/jquery.js"></script>
<script language="javascript" type="text/javascript" src="js/jquery.flot.js"></script>


<script>



  

$(function () {
    //var d = [[1196463600000, 0], [1196550000000, 0], [1196636400000, 0], [1196722800000, 77], [1196809200000, 3636], [1196895600000, 3575], [1196982000000, 2736], [1197068400000, 1086], [1197154800000, 676], [1197241200000, 1205], [1197327600000, 906], [1197414000000, 710], [1197500400000, 639], [1197586800000, 540], [1197673200000, 435], [1197759600000, 301], [1197846000000, 575], [1197932400000, 481], [1198018800000, 591], [1198105200000, 608], [1198191600000, 459], [1198278000000, 234], [1198364400000, 1352], [1198450800000, 686], [1198537200000, 279], [1198623600000, 449], [1198710000000, 468], [1198796400000, 392], [1198882800000, 282], [1198969200000, 208], [1199055600000, 229], [1199142000000, 177], [1199228400000, 374], [1199314800000, 436], [1199401200000, 404], [1199487600000, 253], [1199574000000, 218], [1199660400000, 476], [1199746800000, 462], [1199833200000, 448], [1199919600000, 442], [1200006000000, 403], [1200092400000, 204], [1200178800000, 194], [1200265200000, 327], [1200351600000, 374], [1200438000000, 507], [1200524400000, 546], [1200610800000, 482], [1200697200000, 283], [1200783600000, 221], [1200870000000, 483], [1200956400000, 523], [1201042800000, 528], [1201129200000, 483], [1201215600000, 452], [1201302000000, 270], [1201388400000, 222], [1201474800000, 439], [1201561200000, 559], [1201647600000, 521], [1201734000000, 477], [1201820400000, 442], [1201906800000, 252], [1201993200000, 236], [1202079600000, 525], [1202166000000, 477], [1202252400000, 386], [1202338800000, 409], [1202425200000, 408], [1202511600000, 237], [1202598000000, 193], [1202684400000, 357], [1202770800000, 414], [1202857200000, 393], [1202943600000, 353], [1203030000000, 364], [1203116400000, 215], [1203202800000, 214], [1203289200000, 356], [1203375600000, 399], [1203462000000, 334], [1203548400000, 348], [1203634800000, 243], [1203721200000, 126], [1203807600000, 157], [1203894000000, 288]];
    //var d = [[1196463600000, 2], [1196467200000, 4], [1196471400000, 6], [1196488400000, 10]];
    
   var valores = [];
   var fechamento = [];
   var mmmenor = [];
   var mmmaior = [];
   var ptos = [];

   	var cots = document.getElementById("flot:cotacoes").value;
   	var pontos = document.getElementById("flot:pontos").value;
   	var acao = document.getElementById("flot:acao").value;
    var coots = cots.split('#');
    var points = pontos.split('#');
   	
   	for (i=0; i<coots.length; i++){
   		var line = coots[i];
   		var params = line.split(',');
   		valores.push([params[0], params[1]]);
   		fechamento.push([params[0], params[2]]);
   		mmmenor.push([params[0], params[3]]);
   		mmmaior.push([params[0], params[4]]);
   	}
   	
   	for (i_=0; i_<points.length; i_++){
   		var line_ = points[i_];
   		var params_ = line_.split(',');
   		ptos.push([params_[0], params_[1]]);
   	}
   	
    
    var options = {
        xaxis: { mode: "time", tickLength: 5 },
        selection: { mode: "x" }
    };
    var plot = $.plot($("#placeholder"), [
                                          { label: acao, data: valores},
                                          { label: "Fechamento", data: fechamento},
                                          { label: "Trava", data: ptos, points: { show: true }, lines: { show: true }}, 
                                          { label: "Média menor", data: mmmenor},
                                          { label: "Média maior", data: mmmaior}]
    , options);
    
});
</script>
  

<link rel="stylesheet" type="text/css" href="css/admin.css"/>
</head> 
<body>   
<f:view>
	<jsp:include page="top.jsp"></jsp:include>

		<rich:panel header="Negocios" styleClass="painel">
			<h:form id="list_negocios">
				<h:outputLink value="analise.jsf">
					<h:outputText value="Voltar" styleClass="bold"/>
				</h:outputLink>
				<br><br>
				<h:outputText value="#{negociosRoboHandler.user.login}" styleClass="bold"/>
				<br><br>
				<h:outputText value="Negócios finalizados (Trava de alta)" styleClass="bold"/>
				<rich:dataTable border="1" value="#{negociosRoboHandler.negociosTravaDeAlta}" var="ta">
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
					<h:column>
						<f:facet name="header">
							<h:outputText value="Ver grafico"></h:outputText>
						</f:facet>
						<h:commandLink action="#{negociosRoboHandler.verGrafico}">
							<h:outputText value="ver"></h:outputText>
							<f:setPropertyActionListener value="#{ta}" target="#{negociosRoboHandler.travaAlta}"/>
						</h:commandLink>
					</h:column>
	
				</rich:dataTable>
	
				<br><br>
				<h:outputText value="Negócios finalizados (Trava de baixa)" styleClass="bold"/>
				<rich:dataTable border="1" value="#{negociosRoboHandler.negociosTravaDeBaixa}" var="ta">
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
				<br><br>
				<h:inputTextarea cols="60" rows="6" value="#{negociosRoboHandler.user.analise}"/>
				<br>
				<h:commandButton value="Save" action="#{negociosRoboHandler.salvaAnalise}"/>
	
			</h:form>
			<br>
		</rich:panel>
		<br><br>
		<rich:panel header="Gráfico" styleClass="painel" rendered="#{negociosRoboHandler.seeGrafico}">
			<h:form id="flot">
						
				<h:inputHidden id="acao" value="#{negociosRoboHandler.travaAlta.acao}"/>
				<h:inputHidden id="pontos" value="#{negociosRoboHandler.pontosTrava}"/>
				<h:inputHidden id="cotacoes" value="#{negociosRoboHandler.cotacoes}">
					<a4j:support event="onclick" reRender="placeholder"/>
				</h:inputHidden>
			
				<div id="placeholder" style="width:750px;height:400px;"></div>
			</h:form>
		</rich:panel>
</f:view>
</body>
</html>