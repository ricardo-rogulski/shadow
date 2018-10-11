<%@ taglib uri="http://java.sun.com/jsf/core" prefix="f"%>
<%@ taglib uri="http://java.sun.com/jsf/html" prefix="h"%>  
<%@ taglib uri="http://richfaces.org/rich" prefix="rich"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="css/admin.css"/>
<title>Shadow Administration</title>

<style>

.headerStyle{
	text-align: left;   
}

.panelLogin{
	width: 250px;
	height: auto;
	text-align: left;	
}

.inputText{
	border: 2px;
	border-color: #4682B4;
	border-style: solid;
}


</style>

<script type="text/javascript">

function validaForm(){

	var login = document.getElementById("login:login").value;
	var senha = document.getElementById("login:senha").value;
	
	if (login==null || senha==null){
		alert('Phgf');
		return false;
	}
	if (login.length<1 || senha.length<1){
		alert('Please, insert your User and Password');
		return false;
	}
	return true;
}

</script>

</head> 
<body>   
<f:view>
	<center>
	<br><br><br><br>
	<rich:panel header="Login" headerClass="headerStyle" styleClass="panelLogin">
		<h:form id="login">
			<h:panelGrid columns="2"> 
				<h:outputText value="User"/>
				<h:inputText value="#{loginHandler.user.login}" id="login" autocomplete="off" size="20"/>

				<h:outputText value="Password"/>
				<h:inputSecret value="#{loginHandler.user.pass}" id="senha" size="20"></h:inputSecret>

				<br><rich:spacer height="10"/>
				<br>
				<h:commandButton value="Login" action="#{loginHandler.logar}" onclick="return validaForm()" styleClass="botao"></h:commandButton>
				<br>
			</h:panelGrid>
		</h:form>
	</rich:panel>
	<br>
		
	</center>	
</f:view>
</body>
</html>