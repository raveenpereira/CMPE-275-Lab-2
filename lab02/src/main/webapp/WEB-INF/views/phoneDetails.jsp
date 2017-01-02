<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<link href="https://fonts.googleapis.com/css?family=Delius+Unicase"
	rel="stylesheet">
<title>Team 10 Ishan Vadwala , Raveen Pereira</title>

</head>
<style>
#updateButton {
	width: 90px;
	height: 40px;
	border-style: solid;
	font-size: 15px;
	margin-left: 50px;
	margin-top: 50px;
	margin-bottom: 10px;
	border-radius: 10px;
}

#updateButton:hover {
	box-shadow: 10px 10px 5px #888888;
	opacity: 0.7;
}

#deleteButton {
	width: 90px;
	height: 40px;
	border-style: solid;
	font-size: 15px;
	margin-left: 50px;
	margin-top: 50px;
	margin-bottom: 10px;
	border-radius: 10px;
}

#deleteButton:hover {
	box-shadow: 10px 10px 5px #888888;
	opacity: 0.7;
}

{
width
:
 
90
px
;

	
height
:
 
40
px
;

	
border-style
:
 
solid
;

	
font-size
:
 
15
px
;

	
margin-left
:
 
50
px
;

	
margin-top
:
 
50
px
;

	
margin-bottom
:
 
10
px
;

	
border-radius
:
 
10
px
;


}
#updateButtonhover {
	box-shadow: 10px 10px 5px #888888;
	opacity: 0.7;
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

body {
	background-color: #FFB480;
	font-family: 'Delius Unicase', cursive;
}

input[type="text"] {
	font-size: 14px;
	font-family: 'Delius Unicase', cursive;
	border-radius: 10px;
	border-style: solid;
}

#div1 {
	float: left;
	margin-left: 30px;
}

#div2 {
	float: right;
	margin-right: 30px;
}

#wrapper {
	margin-top: 50px;
}
</style>
<body>
	<p>Team 10 Ishan Vadwala , Raveen Pereira</p>

	<script>var userId = new Array();</script>
	<div id="wrapper">
		<div id="div1">
			<h1>Phone Details Page</h1>
			<form method="POST" id="updatePhoneForm">
				<table>
					<tr>
						<td>ID</td>
						<td><input type="text" name="phoneId" size="10"
							value="${phone.id}" id="phoneId" readonly="" /></td>
					<tr>
					<tr>
					<tr>
					<tr>
					<tr>
					<tr>
					<tr>
					<tr>
					<tr>
						<td>Phone Number</td>
						<td><input type="text" name="number" size="10"
							value="${phone.number}" /></td>
						<td>Description</td>
						<td><input type="text" name="description" size="10"
							value="${phone.description}" /></td>
					</tr>
					<tr>
					<tr>
					<tr>
						<td>Street</td>
						<td><input type="text" name="street" size="10"
							value="${phone.address.street}" /></td>
						<td>City</td>
						<td><input type="text" name="city" size="10"
							value="${phone.address.city}" /></td>

					</tr>
					<tr>
						<td>State</td>
						<td><input type="text" name="state" size="10"
							value="${phone.address.state}" /></td>
						<td>Zip</td>
						<td><input type="text" name="zip" size="10"
							value="${phone.address.zip}" /></td>
					</tr>
					<tr>
					<tr>
					<tr>
					<tr></tr>
					</tr>
					<tr>
						<div id="buttondiv" style="margin-bottom: 30px;">
							<td><input type="button" onclick="updatePhone();"
								name="Update" id="updateButton" value="Update" /></td>


							<td><input type="button" onclick="deletePhone();"
								id="deleteButton" name="Delete" value="Delete" /></td>
						</div>
				</table>
			</form>
		</div>
		<div id="div2">
			<table>
				<th>Included users</th>
				<tr>
					<th>Id</th>
					<th>Title</th>
					<th>First Name</th>
					<th>Last Name</th>
				</tr>
				<c:forEach items="${included}" var="user">
					<tr>
						<td><c:out value="${user.id}" /></td>
						<script>
						userId.push("${user.id}");
						</script>
						<td><c:out value="${user.title}" /></td>
						<td><c:out value="${user.firstname}" /></td>
						<td><c:out value="${user.lastname}" /></td>
						<td><input type="button" id="${user.id}"
							onclick="deleteUser(this.id);" style="width: 60px;"
							value="Delete" /></td>
					</tr>
				</c:forEach>
			</table>
			<table>
				<th>Not included users</th>
				<tr>
					<th>Id</th>
					<th>Title</th>
					<th>First Name</th>
					<th>Last Name</th>
				</tr>
				<c:forEach items="${notIncluded}" var="user">
					<tr>
						<td><c:out value="${user.id}" /></td>
						<td><c:out value="${user.title}" /></td>
						<td><c:out value="${user.firstname}" /></td>
						<td><c:out value="${user.lastname}" /></td>
						<td><input type="button" id="${user.id}"
							onclick="addUser(this.id);" style="width: 60px;" value="Add" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>

<script>

	function updatePhone() {
	var thisForm = document.getElementById("updatePhoneForm");
	var formData = document.getElementById("updatePhoneForm");
	var params="?"
	for(i=1;i<formData.length-2;i++){
		params+=formData[i].name;
		params+="=";
		params+=formData[i].value;
		params+="&";
	}
//	params =params.substring(0,params.length-1);

	for(i =0;i<userId.length;i++){
		params+="users[]=";
		params+=userId[i]+"&";
	}
	
	if(userId.length == 0){
		params+="users[]=  ";
	}
	params =params.substring(0,params.length-1);
	var url = "/phone/${phone.id}"+(params);
	
	console.log(userId);
	console.log(url);
	
	var http = new XMLHttpRequest();
	http.open("POST", url, true);
	
	//Send the proper header information along with the request
	http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

	http.onreadystatechange = function() {//Call a function when the state changes.
	    if(http.readyState == 4 && http.status == 200) {
	       // alert(http.responseText);
	        window.location.replace("/phone/"+http.responseText);
	    }
	}
	http.send();
	}

	function updateUser() {
		console.log("hi");
		var idvalue = phone.id
		console.log(idvalue);
		var xhttp = new XMLHttpRequest();
		var url = "/updatephone";
		console.log(url);

		xhttp.open("GET", url);
		xhttp.send();
	}
	function addUser(userId) {
		console.log("hi");
		var xhttp = new XMLHttpRequest();
		var url = "/adduser";
		console.log(url);
		console.log(xhttp.params);
		xhttp.open("POST", url);
		xhttp
				.setRequestHeader("Content-Type",
						"application/json;charset=UTF-8");
		
		xhttp.onreadystatechange = function() {//Call a function when the state changes.
		    if(xhttp.readyState == 4 && xhttp.status == 200) {
		       // alert(http.responseText);
		        window.location.replace("/phone/"+xhttp.responseText);
		    }
		}
		
		xhttp.send(JSON.stringify({
			phoneId : "${phone.id}",
			userId : userId
		}));

	}
	function deleteUser(userId) {
		console.log("hi");
		var xhttp = new XMLHttpRequest();
		var url = "/deleteuser";
		console.log(url);
		console.log(xhttp.params);
		xhttp.open("POST", url);
		xhttp
				.setRequestHeader("Content-Type",
						"application/json;charset=UTF-8");
		xhttp.onreadystatechange = function() {//Call a function when the state changes.
		    if(xhttp.readyState == 4 && xhttp.status == 204) {
		       // alert(http.responseText);
		        window.location.replace("/phone/${phone.id}");
		    }
		}
		xhttp.send(JSON.stringify({
			phoneId : "${phone.id}",
			userId : userId
		}));

	}
	function deletePhone() {
		console.log("hi");
		var xhttp = new XMLHttpRequest();
		var url = "/phone/${phone.id}";
		xhttp.open("DELETE", url);
		xhttp.onreadystatechange = function() {//Call a function when the state changes.
		    if(xhttp.readyState == 4 && xhttp.status == 204) {
		       // alert(http.responseText);
		        window.location.replace("/phone");
		    }
		    if(xhttp.readyState == 4 && xhttp.status == 400) {
			        alert("Bad Request. Phone Number ${phone.id} still has users");
			        window.location.replace("/phone/${phone.id}");
			    }
		    if(xhttp.readyState == 4 && xhttp.status == 404) {
			       // alert(http.responseText);
			        window.location.replace("/error/${phone.id}");
			    }
		}
		xhttp.send();

	}

</script>
</html>
