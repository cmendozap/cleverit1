function getUsers(){

	$.ajax({
		method: "GET",
		url: "http://jsonverserverclever.azurewebsites.net/User",
		success: onSuccess,
		error: onError
	})

}

function saveUser(){
	var userMap = {};

	userMap = getData(userMap);

	if (document.getElementById("id").value != 0){
		$.ajax({
			type: "PUT",
   			url: "http://jsonverserverclever.azurewebsites.net/User/" + userMap["id"],
			data: userMap,
    		success: function() {renew();},
    		error: function() {alert("Error guardando datos.");}
			});
	} else {
		$.ajax({
   	 		type: "POST",
   			url: "http://jsonverserverclever.azurewebsites.net/User",
    		data: userMap,
    		success: function() {renew();},
    		error: function() {alert("Error guardando datos.");}
			});
	}
}
function deleteUser(id){
    jQuery.ajax({
        url: 'http://jsonverserverclever.azurewebsites.net/User/' + id,
        type: 'DELETE',
        success: function() {
            getUsers();
        }
    });
}

function onSuccess(jsonReturn){
	$("#full-table").empty();
   	var fadeInAmt = 200;
	var fullTable = "";

   for(var i=0; i<jsonReturn.length; i++) {

	    var id = jsonReturn[i].id;
    	var name = jsonReturn[i].name;
    	var lastName = jsonReturn[i].lastName;
		var email = jsonReturn[i].email;
		var password = jsonReturn[i].password;

		if (i==0){
			fullTable = "<table><thead><tr><th>Nombre</th><th>Apellido</th><th>Email</th><th>Acciones</th></tr></thead><tbody>";
		}

  	  //load content one peice at a time
		fullTable = fullTable + "<tr><td>" + name + "</td><td>" + lastName + "</td><td>" + email + "</td><td><a title='Cambiar usuario' href='#' class='btn btn-change' onclick='changeUser(" + id + ", \"" + name +  "\"" + ", \"" + lastName +  "\"" + ", \"" + email +  "\"" + ", \"" + password +  "\"" + ");return false';>Cambiar</a>&nbsp;<a title='Borrar usuario' href='#' class='btn btn-delete' onclick='deleteUser(" + id + ");return false';>Borrar</a></td></tr>";

		if (i==jsonReturn.length-1){
			fullTable = fullTable + "</tbody></table>";
			$(fullTable).hide().appendTo($("#full-table")).fadeIn(fadeInAmt);

	}
  }
}

//if JSON fails
function onError(){
    $("#full-table").html("Error cargando datos.");
}
