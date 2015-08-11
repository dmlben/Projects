<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="./jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="./jquery/jquery.blockUI.js"></script>
<title>POOL TESTER</title>

<script type="text/javascript">

$(document).ready(function () {
	
	 $(document).ajaxStart(function () {
	        $("#loading").show();
	    }).ajaxStop(function () {
	        $("#loading").hide();
	    });
});


function executeNqueries(){
	
	var nbRequetes=$("#nbProcess").val();
	if(isNaN(nbRequetes))nbRequetes=2;
	
	//clear content
	$("#resultid").html("");
	
	//ajax calls
	//document.getElementById("load").style.display = "block";
	$.blockUI({ 
		message: '<img src="./jquery/indicator.gif" /> Veuillez patienter...',
		css: {
		    border: 'none', 
            padding: '15px', 
            backgroundColor: '#000', 
            '-webkit-border-radius': '10px', 
            '-moz-border-radius': '10px', 
            opacity: .5, 
            color: '#fff' 
        } }); 
	
	var urlcontext='<%=request.getContextPath()%>';
	 $.ajax({
	        url :urlcontext+"/test?nbProcess="+nbRequetes,
	        type : 'POST',
	        dataType: 'html',
	        
	        success : function(response){ 
	          //console.log('code html:'+response);
	          $("#resultid").append("<p><font color='green'>Mono user : </font></p>");
	          $("#resultid").append(response);
	          //document.getElementById("load").style.display = "none";
	          $.unblockUI();
	        },
	        
	        error : function(erreur){
	           //console.log('erreur:'+erreur);
	           alert(erreur.responseText);
	           $.unblockUI();
	        }
	        
	    }); 
	
	
	return;
	
}

function executeNRequests(){
	var n = $("#nbUsers").val();
	if(isNaN(n))n=2;
	var nbRequetes=$("#nbProcessExecute").val();
	if(isNaN(nbRequetes))nbRequetes=2;
	
	//clear content
	$("#resultid").html("");
	
	var i=1;
	var urlcontext='<%=request.getContextPath()%>';
	
	$.blockUI({ 
		message: '<img src="./jquery/indicator.gif" /> Veuillez patienter...',
		css: {
		    border: 'none', 
            padding: '15px', 
            backgroundColor: '#000', 
            '-webkit-border-radius': '10px', 
            '-moz-border-radius': '10px', 
            opacity: .5, 
            color: '#fff' 
        } }); 
	
	
	
	for(i=1;i<=n;i++){
	//ajax calls
		document.getElementById("load").style.display = "block";
		 $.ajax({
		        url : urlcontext+"/test?nbProcess="+nbRequetes,
		        type : 'POST',
		        dataType: 'html',
		        
		        success : function(response){ 
		          $("#resultid").append("<p><font color='green'>User lambda : </font></p>");
		          $("#resultid").append(response);
		          $.unblockUI();
		        },
		        
		        error : function(erreur){
		           //console.log('erreur:'+erreur);
		           $("#resultid").append("<p><font color='red'>KO - User lambda : </font></p>");
		           //$("#resultid").append(erreur);
		           alert(erreur.responseText);
		           $.unblockUI();
		        }
		        
		    }); 
	}
	
	return;
	
}

/**
 * environ 10 mn par requete
 */
function executeNRequestsKiller(){
	var n = $("#nbUsersK").val();
	if(isNaN(n))n=2;
	var nbRequetes=$("#nbProcessExecuteK").val();
	if(isNaN(nbRequetes))nbRequetes=2;
	
	//clear content
	$("#resultid").html("");
	
	var i=1;
	var urlcontext='<%=request.getContextPath()%>';
	
	$.blockUI({ 
		message: '<img src="./jquery/indicator.gif" /> Veuillez patienter...',
		css: {
		    border: 'none', 
            padding: '15px', 
            backgroundColor: '#000', 
            '-webkit-border-radius': '10px', 
            '-moz-border-radius': '10px', 
            opacity: .5, 
            color: '#fff' 
        } }); 
	
	for(i=1;i<=n;i++){
	//ajax calls
		 $.ajax({
		        url : urlcontext+"/execKiller?nbProcess="+nbRequetes,
		        type : 'POST',
		        dataType: 'html',
		        
		        success : function(response){ 
		          //console.log('code html:'+response);
		          $("#resultid").append("<p><font color='green'>User lambda : </font></p>");
		          $("#resultid").append(response);
		          $.unblockUI();
		        },
		        
		        error : function(erreur){
		          
		           $("#resultid").append("<p><font color='red'>KO - User lambda : </font></p>");
		           alert(erreur.responseText);
		           $.unblockUI();
		        }
		        
		    }); 
	}
	
	return;
	
}
</script>

</head>
<body>
	
	<h1> Pool tester - Mono utilisateur  </h1>
	<form action="javascript:executeNqueries();" method="post">	
		<table>
			<tr>
				<td> Nombre de Connexions simultanee :</td>
				<td>
					<input type="text" id="nbProcess" name="nbProcess"> (default value = 2)
				</td>
				
			</tr>
	
				<td>
					<input type="submit" value="tester">
				</td>
			</tr>
		</table>
	</form>	
	<h1> Pool tester - Multi utilisateur  </h1>
	<form action="javascript:executeNRequests();" method="post">	
		<table>
			<tr>
				<td>
				 Nombre utilisateurs  :
				<input type="text" id="nbUsers" name="nbUsers"/> (default value = 2)
					
				</td>
			</tr>
			<tr>
				<td>
					nombre requete à executer :
					<input type="text" id="nbProcessExecute" name="nbProcessExecute"/> (default value = 2)
				</td>
			</tr>

				<td>
					<input type="submit" value="tester batch">
				</td>
			</tr>
		</table>
	</form>
	
	<h1> Pool tester - killer  </h1>
	<form action="javascript:executeNRequestsKiller();" method="post">	
		<table>
			<tr>
				<td>
				 Nombre utilisateurs  :
				<input type="text" id="nbUsersK" name="nbUsersK"/> (default value = 2)
					
				</td>
			</tr>
			<tr>
				<td>
					nombre requete à executer :
					<input type="text" id="nbProcessExecuteK" name="nbProcessExecuteK"/> (default value = 2)
				</td>
			</tr>

				<td>
					<input type="submit" value="tester killer">
				</td>
			</tr>
		</table>
	</form>
	<div id="resultid">
	</div>	
	
</body>
</html>