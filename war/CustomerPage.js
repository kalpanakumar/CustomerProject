var varDel;
var ListId = 0;
var list = document.getElementById("CustomerName");
function ShowFunction() {
	var showDetails = document.getElementById('Details');
	showDetails.style.display = 'block';
	var hideTodoDetails = document.getElementById('DispTodo');
	hideTodoDetails.style.display = 'none';
	var CreateBtn = document.getElementById("Create");
	CreateBtn.style.display = "block";
	var UpdateBtn = document.getElementById("Update");
	UpdateBtn.style.display = "none";

	document.getElementById("todoList").innerHTML = " ";
	document.getElementById("Name").value = " ";
	document.getElementById("num").value = " ";
	document.getElementById("email").value = " ";
	document.getElementById("Address").value = " ";
}
function HideFunction() {
	ListId++;

	var TodoListValues = [];

	var arr = document.getElementById("todoList").getElementsByTagName("span");
	for (i = 0; i < arr.length; i++) {
		TodoListValues[i] = arr[i].innerText;

	}

	console.log("this is arr " + arr[0]);
	console.log("this is TodoListValues " + TodoListValues);

	var deatailsArray = [];
	document.getElementById("Create").style.display = 'block';
	var InputCustomerAddress = document.getElementById("Address").value;
	var InputCustomer = document.getElementById("Name").value;
	var InputEmail = document.getElementById("email").value;
	var ListItem = document.createElement("li");
	var ListText = document.createElement("span");

	

	ListText.id = ListId;
	deleteText = document.createElement("lable");
	deleteText.innerHTML = "&nbsp;&#10007;&nbsp;";
	deleteText.style.cssFloat = "right";
	deleteText.id = ListId;
	
	ListText.innerText = InputCustomer;
	ListItem.append(ListText);
	ListItem.append(deleteText);
	var StoreValue = new Object();
	StoreValue.CustomerName = InputCustomer;
	
	StoreValue.CustomerEmail = InputEmail;
	StoreValue.CustomerAddress = InputCustomerAddress;
	var json = JSON.stringify(StoreValue);

	if (InputCustomer == " " ) {
		alert("Customer name and the email address is required fields please fill that");
		var showDetails = document.getElementById('Details');
		showDetails.style.display = 'block';
	} else {
		$.ajax({
			url: 'SaveCustomerData',
			type:'get',
			data: 'data=' + json,
				success : function(data) {	
					if(data =='True'){
						alert("Customer is successfully created");
						list.appendChild(ListItem);
						var HideDetails = document.getElementById('Details');
						HideDetails.style.display = 'none';
						var hideTodoDetails = document.getElementById('DispTodo');
						hideTodoDetails.style.display = 'none';
					}else {
						alert("email exist");
					}
				}	
			});
		

	}

	deleteText.onclick = DeleteData;

	var InputCustomerNumber = document.getElementById("num").value;
	
	deatailsArray.push(InputCustomer);
	deatailsArray.push(InputCustomerNumber);
	deatailsArray.push(InputCustomerAddress);
	deatailsArray.push(ListId);
	deatailsArray.push(TodoListValues);

	if (InputCustomer == " ") {
		console.log("Enter the customer");
	} else {
		localStorage.setItem(ListId, JSON.stringify(deatailsArray));
	}
	ListItemArray = [];
	ListText.onclick = getStoreData;
		
}

function getStoreData() {
	var getId = this.id;
	varDel = getId;

	var showTodoDetails = document.getElementById('DispTodo');
	showTodoDetails.style.display = 'block';
	var deletebtn1 = document.createElement("button");
	var DelleteBtnText = document.createTextNode("Delete"); // Create a text
															// node
	deletebtn1.appendChild(DelleteBtnText);
	deletebtn1.id = getId;
	var Button1 = document.createElement("Button");
	Button1.innerHtml = deletebtn1;
	var UpdateBtn = document.getElementById("Update");
	UpdateBtn.style.display = "block";

	var CreateBtn = document.getElementById("Create");
	CreateBtn.style.display = "none";
	var deleteBtn = document.getElementById("del_" + getId);

	var storedNames = JSON.parse(localStorage.getItem(getId));

	document.getElementById("Name").value = storedNames[0];

	document.getElementById("num").value = storedNames[1];
	document.getElementById("Address").value = storedNames[2];
	var showDetails = document.getElementById('Details');
	showDetails.style.display = 'block';
	document.getElementById("todoList").innerHTML = " ";

	for (i = 0; i < storedNames[4].length; i++) {
		var list = document.getElementById("todoList");
		var ListItem = document.createElement("li");

		deleteText = document.createElement("lable");
		deleteText.innerHTML = "&nbsp;&#10007;&nbsp;"

		deleteText.style.cssFloat = "right";

		var ListText = document.createElement("span");
		ListText.setAttribute("contenteditable", "true");

		ListText.innerText = storedNames[4][i];

		ListItem.append(ListText);
		ListItem.append(deleteText);
		list.appendChild(ListItem);
		deleteText.onclick = DeleteItem;
	}

}
function UpdateData() {
	var deatailsArray = [];
	var TodoListValues = [];

	var arr = document.getElementById("todoList").getElementsByTagName("span");
	for (i = 0; i < arr.length; i++) {
		TodoListValues[i] = arr[i].innerText;

	}

	var UpdatedName = this.document.getElementById("Name").value;
	var UpdatedNum = this.document.getElementById("num").value;
	var UpdatedAddress = this.document.getElementById("Address").value;
	deatailsArray.push(UpdatedName);
	deatailsArray.push(UpdatedNum);
	deatailsArray.push(UpdatedAddress);

	deatailsArray.push(varDel);
	deatailsArray.push(TodoListValues);

	if (UpdatedName == "") {
		alert("Enter the customer")
	} else {
		localStorage.setItem(varDel, JSON.stringify(deatailsArray));
		var revomeLi = document.getElementById(varDel);
		revomeLi.innerText = UpdatedName;

		alert("Your Customer's details has been sucessfully updated");
	}

}
function DeleteData() {

	var getId = this.id;

	localStorage.removeItem(getId);
	var showDetails = document.getElementById('Details');
	showDetails.style.display = 'none';
	var showTodoDetails = document.getElementById('DispTodo');
	showTodoDetails.style.display = 'none';
	this.parentNode.remove();

}

function DeleteItem() {
	this.parentNode.remove();

}
var totalItems = 0;
function addNewItem(list, itemText) {
	totalItems++;

	var ListItem = document.createElement("li");
	ListItem.style.width = "95%";
	ListItem.style.height = "35px";

	deleteText = document.createElement("lable");
	deleteText.innerHTML = "&nbsp;&#10007;&nbsp;"

	var ListText = document.createElement("span");

	ListItem.style.paddingTop = "4.5px";
	ListText.id = "sp_" + totalItems;
	ListText.innerText = itemText;
	ListText.setAttribute("contenteditable", "true");
	deleteText.setAttribute("id", "cross");
	deleteText.style.cssFloat = "right";

	ListItem.append(ListText);

	ListItem.append(deleteText);

	list.appendChild(ListItem);

	deleteText.onclick = DeleteItem;
	var checkboxSave = ListText.innerText;

}

var Item1 = document.getElementById("ITEM");

var GetItem;

Item1.onkeyup = function(event) {
	if (event.which == 13) {
		GetItem = Item1.value
		if (!GetItem) {

			return false;
		}
		var SetList = document.getElementById("todoList");
		addNewItem(SetList, GetItem);
		Item1.value = "";
		Item1.focus();
		var StoreList = new Object();
		StoreList.TodoItem = GetItem;
		var json = JSON.stringify(StoreList);
		$.ajax({
			url: 'CustomerTodoList',
			type:'get',
			data: 'data=' + json,
				success : function(data) {	
					if(data =='True'){
						
						
					}else {
						alert("email exist");
					}
				}	
			});
		

	}

};

function add()

{
	GetItem = Item1.value
	if (!GetItem) {

		return false;
	} else {
		var SetList = document.getElementById("todoList");
		addNewItem(SetList, GetItem);
		Item1.value = "";
	}
	Item1.focus();
}
// ajax part



