<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/admin.css"/>
<meta http-equiv="Content-Type" content="text/html; UTF-8">
<title>Shadow Administration</title>

</head> 
<body>   
<f:subview id="top"> 
	<h:form id="top_">
		<rich:panel header="Shadow - administration tool">
			<h:panelGrid columns="25">
				<h:outputLink value="crudAdmin.jsf">
					<h:outputText value="Administração"/>
				</h:outputLink>
				<rich:spacer width="10"/>
				<h:outputLink value="crudAcao.jsf">
					<h:outputText value="Ação"/>
				</h:outputLink>
				<rich:spacer width="10"/>
				<h:outputLink value="crudSerieOpcao.jsf">
					<h:outputText value="Série Opção"/>
				</h:outputLink>
				<rich:spacer width="10"/>
				<h:outputLink value="crudOpcao.jsf">
					<h:outputText value="Opção"/>
				</h:outputLink>
				<rich:spacer width="10"/>
				<h:outputLink value="cockpit.jsf">
					<h:outputText value="Cockpit"/>
				</h:outputLink>
				<rich:spacer width="10"/>
				<h:outputLink value="negocios.jsf">
					<h:outputText value="Negócios Realizados"/>
				</h:outputLink>
				<rich:spacer width="10"/>
				<h:outputLink value="analise.jsf">
					<h:outputText value="Análise Robôs"/>
				</h:outputLink>
				<rich:spacer width="10"/>
				<h:outputLink value="graficoDiarioFlot.jsf">
					<h:outputText value="Gráfico diário"/>
				</h:outputLink>
				<rich:spacer width="10"/>
				<h:outputLink value="logout.jsf">
					<h:outputText value="Sair"/>
				</h:outputLink>
				<rich:spacer width="10"/>
				<h:outputText value="Usuário: #{loginHandler.user.login}"/>

			</h:panelGrid>
		</rich:panel>
	</h:form>

</f:subview>
</body>
</html>