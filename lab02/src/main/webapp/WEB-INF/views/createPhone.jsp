<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
    <head>
        <link href="https://fonts.googleapis.com/css?family=Delius+Unicase" rel="stylesheet">
        <title>Team 10 Ishan Vadwala , Raveen Pereira</title>
    
    </head>
    <style>
     body{
    font-size:24px;
    font-family: 'Delius Unicase', cursive;
    background-color:#FFB480;
    margin-left:380px;
    margin-top:100px;
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
     input[type="text"]
{
    font-size:24px;
     font-family: 'Delius Unicase', cursive;
     border-radius:10px;
     border-style:solid;
}
    </style>
    <body>
    <p>Team 10 Ishan Vadwala , Raveen Pereira</p>
        <h3>Enter Phone Details</h3>
        <form method="POST" action="" id="createPhoneForm">
             <table>
             <tr>
                    <td>id</td>
                    <td><input type="text" name="id" value=" " readonly="true"/></td>
                </tr>
                <tr>
                    <td>Number</td>
                    <td><input type="text" name="number"/></td>
                </tr>
                 <tr>
                    <td>Description></td>
                    <td><input type="text" name="description"/></td>
                </tr>
                <tr>
                    <td>city</td>
                    <td><input type="text" name="city"/></td>
                </tr>
                 <tr>
                    <td>state</td>
                    <td><input type="text" name="state"/></td>
                </tr>
                  <tr>
                    <td>street</td>
                    <td><input type="text" name="street"/></td>
                </tr>
                  <tr>
                    <td>Zip</td>
                    <td><input type="text"  name="zip" maxlength="5"/></td>
                </tr>
                    <td><input type="button" onClick="createPhone();"  value="Create"/></td>
                </tr>
            </table>
        </form>
        <form id="dummyForm", method="POST" action="" />
        <script>
        function createPhone(){
        	var formData = document.getElementById("createPhoneForm");
    		var params="?"
    		for(i=1;i<formData.length-1;i++){
    			params+=formData[i].name;
    			params+="=";
    			params+=formData[i].value;
    			params+="&";
    		}
    		params =params.substring(0,params.length-1);

    		
    		var url = "/phone/${phone.id}"+(params);
    		console.log(url);

    		var http = new XMLHttpRequest();
    		http.open("POST", url, true);
    		
    		//Send the proper header information along with the request
    		http.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    		http.onreadystatechange = function() {//Call a function when the state changes.
    		    if(http.readyState == 4 && http.status == 201) {
    		       // alert(http.responseText);
    		        window.location.replace("/phone/"+http.responseText);
    		    }
    		}
    		http.send();
        }
        </script>
    </body>
</html>