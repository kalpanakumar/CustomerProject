<!DOCTYPE html>
<html>
<head>
<title>Page Title</title>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> 
<link rel="stylesheet" type="text/css" href="CustomerPageStyle.css">
</head>
<body>
<div id  = " header" class="well"> Customer's Todo List </div>
<div class="col-md-4" id ="Customer_Name_List">
	<button id="btn1" onclick="ShowFunction()">Add Customer</button>
	<ul id="CustomerName"> </ul>
</div>
<div class="col-md-4">
<div style="display:none;" id="Details"> 

<table id="Details" style="width:100%" >
  <tr>
    <td>First Name</td>
     <td> <input  id="Name" class ="inputBox" type="text" placeholder = "First Name"  ></td> 
   
    <!-- <td><input class ="inputBox" placeholder = "First name" id="Name" type="text" required></td>   -->
  </tr>
  <tr>
    <td>Mobile Number</td> 
    <td><input class ="inputBox" id="num" type="number" required></td>  
  </tr>
  <tr>
    <td>Email</td> 
    <td><input class ="inputBox" id="email" type="email" required></td>  
  </tr>
  <tr>
    <td>Address</td>
   <td><input class ="inputBox" id="Address" type="text" required></td>   
  </tr>
    
</table>
<button  id ="Create" onclick="HideFunction()">Create Customer</button>
<button id ="Update" onclick="UpdateData()">Update</button>
<!-- <button class="btn btn-primary " id ="Delete" onclick="DeleteData()">Delete</button> -->
</div> 
</div>	
<div style="display:none;" id ="DispTodo" class="col-md-4">

<input  id="ITEM" style="width: 290px; " type="input" placeholder="Add Item...." onblur="add()">
<!-- <button class="btn btn-primary " id = "TodoListButton " onclick="addListItem()">Add</button> -->
<div class ="todoDiv">
<ul id="todoList">
	
</ul>
</div>
</div>
<script type="text/javascript" src="CustomerPage.js">
</script>
</body>

</html>