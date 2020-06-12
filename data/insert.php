<?php
include_once'dbconnect.php';


$username=$_POST['username'];

$password=$_POST['password'];
$price=$_POST['price'];
$description=$_POST['description'];
$contact=$_POST['contact'];
$date = date('Y/m/d H:i:s');
//$artistid=$_POST['artistid'];
$target_dir = "Images/";
	
$pictures = $_POST['pictures'];
	
$imageStore = rand()."_".time().".jpeg";
	
$target_dir = $target_dir."/".$imageStore;
	
file_put_contents($target_dir, base64_decode($pictures));

 $Sql_Query = "select * from loginuser where username = '$username' and password = '$password' ";
 $check =mysqli_query($con,$Sql_Query);
 if($check->num_rows > 0){

$sql="Insert into view(price,description,password,username,pictures,date,contact) values ('$price','$description','$password','$username','$imageStore','$date','$contact')";
	
$r=mysqli_query($con,$sql);	

echo "Data Inserted";

 }
 else{
 echo "UserName Not Inserted";
 
 }	



mysqli_close($con);
	









