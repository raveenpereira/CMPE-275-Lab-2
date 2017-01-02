<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Delius+Unicase"
	rel="stylesheet">
</head>
<style>
body {
	font-size: 24px;
	font-family: 'Delius Unicase', cursive;
	background-color: #FFB480;
	margin-left: 380px;
	margin-top: 100px;
}

input[type="submit"] {
	width: 90px;
	height: 40px;
	border-style: solid;
	margin-bottom: 10px;
	font-size: 15px;
	margin-left: 50px;
	border-radius: 10px;
	margin-top: 50px;
}

input[type="submit"]:hover {
	box-shadow: 10px 10px 5px #888888;
	opacity: 0.7;
}

input[type="text"] {
	font-size: 24px;
	font-family: 'Delius Unicase', cursive;
	border-radius: 10px;
	border-style: solid;
}
</style>
<body>
    <p>Team 10 Ishan Vadwala , Raveen Pereira</p>

	<h3>Welcome, Enter User Details</h3>
	<div id="formdata">
		<form method="POST" action="" onsubmit="createUser();" id="createUserForm">
			<table>
				<tr>
					<td>Id</td>
					<td><input type="text"  name="id" value=" " readonly="true" /></td>
				</tr>
				<tr>
					<td>firstName</td>
					<td><input type="text"  name="firstname" /></td>
				</tr>
				<tr>
					<td>lastname</td>
					<td><input type="text"  name="lastname" /></td>
				</tr>
				<tr>
					<td>title</td>
					<td><input type="text"  name="title" /></td>
				</tr>
				<tr>
					<td>city</td>
					<td><input type="text"  name="city" /></td>
				</tr>
				<tr>
					<td>state</td>
					<td><input type="text"  name="state" /></td>
				</tr>
				<tr>
					<td>street</td>
					<td><input type="text"  name="street" /></td>
				</tr>
				<tr>
					<td>Zip</td>
					<td><input type="text"  name="zip" /></td>
				</tr>
				<td><input type="button" onClick="createUser();" value="Submit" /></td>
				</tr>
			</table>
		</form>
		<form id="dummyForm" action="" method = "POST"/>
	</div>
	<script>
	function createUser(){
		var formData = document.getElementById("createUserForm");
		var params="?"
		for(i=1;i<formData.length-1;i++){
			params+=formData[i].name;
			params+="=";
			params+=formData[i].value;
			params+="&";
		}
		params =params.substring(0,params.length-1);
		var xhttp = new XMLHttpRequest();
		var url = "/user"+(params);
		console.log(url);
	//formData.action=url;
	//	var dummyForm = document.getElementById("dummyForm");
	//	dummyForm.action=url;
	//	dummyForm.submit();
		
		
		var http = new XMLHttpRequest();
		http.open("POST", url, true);
		
		//Send the proper header information along with the request
		http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

		http.onreadystatechange = function() {//Call a function when the state changes.
		    if(http.readyState == 4 && http.status == 201) {
		       // alert(http.responseText);
		        window.location.replace("/user/"+http.responseText);
		    }
		}
		http.send();
	}
	
	</script>
</body>
</html>