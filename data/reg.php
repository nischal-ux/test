<?php
include_once'dbconnect.php';

$name=$_POST['name'];
$email=$_POST['email'];
$password=$_POST['password'];
//$artistid=$_POST['artistid'];

	
$sql="Insert into user(name,email,password) values ('$name','$email','$password')";
	
$r=mysqli_query($con,$sql);	

$array=array();
$r="userinserted";
$array[]=$r;
header('Content-Type:application/json');
echo json_encode($array);



mysqli_close($con);
	









