<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
   <head>
    <link rel="stylesheet" type="text/css" href="http://fonts.googleapis.com/css?family=Tangerine">
    <link href="https://fonts.googleapis.com/css?family=Delius+Unicase" rel="stylesheet">
    <title>Team 10 Ishan Vadwala , Raveen Pereira</title>
  </head>
  <style>
  input[type="text"]
{
    font-size:24px;
    font-family: 'Delius Unicase', cursive;
    border-radius:10px;
    border-style:solid;
}
body{

}
.button{
width:340px;
font-size: 16px;
}
#updateButton{
width:90px;
height:40px;
border-style:solid;
font-size:15px;
margin-left:50px;
margin-top:50px;
margin-bottom:10px;
border-radius:10px;
}
#updateButton:hover{
box-shadow: 10px 10px 5px #888888;
opacity:0.7;
}
input[type="submit"]{
width:90px;
height:40px;
border-style:solid;
margin-bottom:10px;
font-size:15px;
margin-left:50px;
border-radius:10px;
margin-top:50px;
}
input[type="submit"]:hover{
box-shadow: 10px 10px 5px #888888;
opacity:0.7;
}

#wrapper{
overflow: auto;
margin-top:90px;
}
#div1{
float:left;
margin-left:150px;
}
#div2{
float:right;
margin-right:50px;}

  </style>
    <body style="background-color:#FFB480;font-family: 'Delius Unicase', cursive;">
        
    
    <div id="wrapper">
    <div id="div1">
        <h2> User Details</h2>
        <form method="POST" action=""  id="updateUserForm">
             <table>
             <tr>
                    <td>id</td>
                    <td><input type="text" name="id" value="${user.id}"  readonly="true"/></td>
                </tr>
                <tr>
                    <td>firstName</td>
                    <td><input type="text" name="firstname" value="${user.firstname}"/></td>
                </tr>
                 <tr>
                    <td>lastname</td>
                    <td><input type="text" name="lastname" value="${user.lastname}"/></td>
                </tr>
                  <tr>
                    <td>title</td>
                    <td><input type="text" name="title" value="${user.title}" /></td>
                </tr>
                <tr>
                    <td>city</td>
                    <td><input type="text" name="city" value="${user.address.city}"/></td>
                </tr>
                 <tr>
                    <td>state</td>
                    <td><input type="text" name="state" value="${user.address.state}"/></td>
                </tr>
                  <tr>
                    <td>street</td>
                    <td><input type="text" name="street" value="${user.address.street}"/></td>
                </tr>
                  <tr>
                    <td>Zip></td>
                    <td><input type="text" name="zip" value="${user.address.zip}"/></td>
                </tr>
                    <td><input type="button" value="Update" onClick="updateUser();" id="updateButton" /></td>
					<td><input type="button" onclick="deleteUser();"
					name="Delete" value="Delete" id="deleteButton" /></td>
                </tr>
            </table>
            </div>
            <div id="div2">
                    <table>
	<th><h2>Phone's Assigned</h2></th> 
    <tr>
    	
        <th>Id</th>
        
        <th>Number</th>
        
        <th>Description</th>
        
    </tr>
    <c:forEach items="${user.phones}" var="phone">
   
    <tr style="font-family: 'Montserrat', sans-serif;">
 
        <td>
            <c:out value="${phone.id}" />
        </td>
        
        <td>
            <c:out value="${phone.number}" />
        </td>
        <td>
            <c:out value="${phone.description}" />
        </td>
    </tr>
    </c:forEach>
</table>
        </form>
        </div>
</div>
<form id="dummyForm" action ="" method="POST"/>
    </body>
    <script>
function deleteUser(){
	console.log("hi");
	var xhttp=new XMLHttpRequest();
	var url="/user/"+${user.id};
	console.log(url);
	xhttp.open("DELETE",url);
	xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

	xhttp.onreadystatechange = function() {//Call a function when the state changes.
	    if(xhttp.readyState == 4 && xhttp.status == 204) {
	       // alert(http.responseText);
	        window.location.replace("/user/"+xhttp.responseText);
	    }
	    if(xhttp.readyState == 4 && xhttp.status == 404) {
		       // alert(http.responseText);
		        window.location.replace("/error/"+${user.id});
		    }
	}
	xhttp.send();
}
function updateUser(){
	var formData = document.getElementById("updateUserForm");
	var params="?"
	for(i=1;i<formData.length-2;i++){
		params+=formData[i].name;
		params+="=";
		params+=formData[i].value;
		params+="&";
	}
	params =params.substring(0,params.length-1);
	var url = "/user/"+${user.id}+(params);
	console.log(url);
	var http = new XMLHttpRequest();
	
	http.open("POST", url, true);
	
	//Send the proper header information along with the request
	http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

	http.onreadystatechange = function() {//Call a function when the state changes.
	    if(http.readyState == 4 && http.status == 200) {
	       // alert(http.responseText);
	        window.location.replace("/user/"+http.responseText);
	    }
	}
	http.send();
	
	
}

</script>
</html>