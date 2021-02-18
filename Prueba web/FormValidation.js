const maxNameLen=10;
const maxSurnameLen=20;



function validateForm(){

	let name=document.getElementById('nombre');
	let surname=document.getElementById('apellido');
	let telefon=document.getElementById('telefono');
	let email=document.getElementById('email');
	let msg=[];

	if(isEmptyOrNull(name.value) || isEmptyOrNull(surname.value) || isEmptyOrNull(email.value) ){

		msg += "- El campo nombre, apellido y email son obligatorios \n";
	}

	if(isNaN(telefono.value)){
		msg+= "- El numero de telefono no puede contener letras \n";
	}

	if(checkStrLen(name.value,maxNameLen)){
		msg+="- El nombre no puede super a los " + maxNameLen +" caracteres. \n";
	}

	if(checkStrLen(surname.value,maxSurnameLen)){
		msg+="- El apellido no puede super a los " + maxSurnameLen +" caracteres. \n";
	}

	if(!email.value.includes('@')){

		msg+="- El correo electronico debe contener el simbolo @ \n";
	}

	if(msg.length ==0){

		msg="El formulario es correcto";
	}
	alert(msg);
}

function isEmptyOrNull(str){

	return str==null || str.length == 0;
}

function checkStrLen(str ,Len){
	return str.length > Len;
}