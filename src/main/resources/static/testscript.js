function renew(){
	clearFields();
	showList();
	getUsers();
}

function addUser(){
	changeUser(0, "", "", "", "");
}

function cancelForm(){
	clearFields();
	showList();
}

function clean(element) {
	element.style.display = "none";
}

function validateEmail(email){
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(email);
}

function showList(){
	document.getElementById("user-list").style.display = "block";
	document.getElementById("user-form").style.display = "none";

}
function clearFields(){
	document.getElementById("id").value = 0;
	document.getElementById("name").value = "";
	document.getElementById("lastName").value = "";
	document.getElementById("email").value = "";
	document.getElementById("password").value = "";
}
function showForm(){
	document.getElementById("user-list").style.display = "none";
	document.getElementById("user-form").style.display = "block";

	document.getElementById("nameError").style.display = "none";
	document.getElementById("lastNameError").style.display = "none";
	document.getElementById("emailError").style.display = "none";
	document.getElementById("passwordError").style.display = "none";
}

function changeUser(userId, userName, userLastName, userEmail, userPassword){
	showForm();
	document.getElementById("id").value = 0;
	if (userId != 0){
		document.getElementById("id").value = userId;
		document.getElementById("name").value = userName;
		document.getElementById("lastName").value = userLastName;
		document.getElementById("email").value = userEmail;
		document.getElementById("password").value = userPassword;
	}
}

function getData(userMap){

	if (document.getElementById("id").value != 0){
		userMap["id"] = document.getElementById("id").value;
	}
	userMap["name"] = document.getElementById("name").value;;
	userMap["lastName"] = document.getElementById("lastName").value;;
	userMap["email"] = document.getElementById("email").value;;
	userMap["password"] = document.getElementById("password").value;;

	return userMap;
}

function inputValidation() {

	var save = true;
	if (document.getElementById("name").value == "") {
		save = false;
		document.getElementById("nameError").style.display = "inline-block";
	}

	if (document.getElementById("lastName").value == "") {
		save = false;
		document.getElementById("lastNameError").style.display = "inline-block";
	}

	if (document.getElementById("email").value == "") {
		save = false;
		document.getElementById("emailError").style.display = "inline-block";
	} else {

		if (!validateEmail(document.getElementById("email").value)) {
			save = false;
			document.getElementById("emailError").style.display = "inline-block";
		}
	}

	if (document.getElementById("password").value == "") {
		save = false;
		document.getElementById("passwordError").style.display = "inline-block";
	}
	if (save){
		saveUser();
	}
}