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
	position: absolute;
	height: 80px;
	width: 800px;
	top: 75px;
}

.panelGRA{
	position: absolute;
	height: 600px;
	width: 800px;
	top: 165px;
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
		<rich:panel header="Ações" styleClass="panelA">
			<h:outputLink value="cockpit.jsf">
				<h:outputText value="Voltar" styleClass="bold"/>
			</h:outputLink>
		</rich:panel>
	
		<rich:panel header="Gráfico" styleClass="panelGRA" rendered="true">
			<center>
				<rich:dataGrid value="#{cockpitHandler.acoes}" var="a">
					<rich:panel>
						<h:outputText value="#{a.code}" styleClass="bold"/>
						<h:outputText value=" (+#{a.ultimaOscilacao})" styleClass="positivo" rendered="#{a.ultimaOscilacao>=0}"/>
						<h:outputText value=" (-#{a.ultimaOscilacao})" styleClass="negativo" rendered="#{a.ultimaOscilacao<0}"/>
						<h:outputText value=" (#{a.ultimoValor})"/>
						<br><br>
						<h:graphicImage value="#{a.graficoDiario}"/>
					</rich:panel>
				</rich:dataGrid>
			</center>
		</rich:panel>

	</h:form>
</f:view>
</body>
</html>