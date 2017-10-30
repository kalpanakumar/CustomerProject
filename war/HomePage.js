$(document).ready(function() { 
	
		$('#SignUp_button').on('click', check);
        $('#LogIn_button').on('click' , logIn);
		$("#ShowPassword").click(function(){
		    var ShowHideButton = document.getElementById("ShowPassword");

			if( ShowHideButton.innerHTML == 'Show') { 
    	var pwds = document.getElementById("pswd");
    	pwds.setAttribute("type","text");
    	ShowHideButton.innerHTML = 'Hide';
    	} else {
    		var pwds = document.getElementById("pswd");
    	pwds.setAttribute("type","Password");
    	ShowHideButton.innerHTML = 'Show';
    	}
			
    });

});
function check(event)
{ var NameEntered;
var EmailEntered;
var ConfirmPasswordEntered;
  
	var Name = $ ('#name').val();
  if ( Name.match(/\d/) || Name.length <= 2 || /^[a-zA-Z ]*$/.test(Name) == false ) 
    {  $("#Check_Name").text("Enter valid name");
    NameEntered = 0;}
     else
      { $("#Check_Name").text("");
       NameEntered = 1;}

   var emailVal = $ ('#email').val();
var email = new RegExp('^([A-z0-9]{3,9})+@([A-z0-9]{3,8})+.([A-z]{2,10})$');
if (email.test(emailVal)){
	$("#Check_Email").text("");
	EmailEntered =1;
}else {
$("#Check_Email").text("Please enter a valid email");
EmailEntered =0;
}

var password  = $ ('#pswd').val();
if(password.length < 8){
	$("#length").addClass('invalid').removeClass('valid');
} else{
	$("#length").addClass('valid').removeClass('invalid');
}

if ( password.match(/[A-z]/)){
	$('#letter').removeClass('invalid').addClass('valid');
}else{
	$('#letter').addClass('invalid').removeClass('valid');
}
	
	if ( password.match(/[A-Z]/) ) {
    $('#capital').removeClass('invalid').addClass('valid');
} else {
    $('#capital').removeClass('valid').addClass('invalid');
}

if ( password.match(/\d/) ) {
    $('#number').removeClass('invalid').addClass('valid');
} else {
    $('#number').removeClass('valid').addClass('invalid');
}
$('#error').show();

  if ( $("#length").hasClass( "valid" ) &&  $("#letter").hasClass( "valid" )  && $("#capital").hasClass( "valid") && $("#number").hasClass( "valid") )  
 { var conP = $ ('#conPswd').val();
$('#error').hide();
if ( conP !== password ){
   $("#ConPword").text("Confirm Password should be same as Password");
   ConfirmPasswordEntered =0;
} else { 
	ConfirmPasswordEntered =1;
	$("#ConPword").text("");
	
}
 }
  if( NameEntered == 1 && EmailEntered ==1 && ConfirmPasswordEntered ==1 ) 
	{  // $("#AccountCreationMessage").text("Your account has been Successfully created");

			var obj = new Object();
			var Password = $("#conPswd").val();
			var Email = $("#email").val();
			obj.EnterEmail = Email;

			obj.EnterPassword = Password;
			
			var json = JSON.stringify(obj);

	$.ajax({
		url: 'SignUp',
	type:'get',
	data: 'data=' + json,
		success : function(data) {	
			if(data =='True'){
				
		        $(location).attr('href','Todo.jsp');
			}else {
				alert("email exist");
			}
		}	
	});
		
	} else {
		$("#AccountCreationMessage").text("Please enter the details properly ");
	}
}

function logIn(event)
{var obj = new Object();
var Email = $("#LogIn_Email").val();
var Password = $("#LogIn_password").val();
obj.EnterLogInEmail = Email;

obj.EnterLogInPassword = Password;

var json = JSON.stringify(obj);

$.ajax({
url: 'LogIn',
type:'get',
data: 'data=' + json,
success : function(data) {	
	
	if(data =='True'){
        $(location).attr('href','Todo.jsp');
	//var successUrl = "TodoListJavaScript.jsp"; // might be a good idea to return this URL in the successful AJAX call
  // window.location.href = successUrl;
}else{
    alert('Fail....');
}
}
});
}



	