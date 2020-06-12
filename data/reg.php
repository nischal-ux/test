<?php
include_once'dbconnect.php';


$username=$_POST['username'];

$password=$_POST['password'];
$name=$_POST['name'];
$email=$_POST['email'];

$target_dir = "Profile/";
	
$profile = $_POST['profile'];
	
$imageStore = rand()."_".time().".jpeg";
	
$target_dir = $target_dir."/".$imageStore;
	
file_put_contents($target_dir, base64_decode($profile));

$Sql_Query = "select * from loginuser where email = '$email' and password = '$password' ";
 $check =mysqli_query($con,$Sql_Query);
 if($check->num_rows > 0){
 echo "Email Alerady";
 }
 else{
$sql="Insert into loginuser(name,email,password,username,profile) values ('$name','$email','$password','$username','$imageStore')";
$r=mysqli_query($con,$sql);	
$array=array();
$r="userinserted";
$array[]=$r;
header('Content-Type:application/json');
echo json_encode($array);
 }
 }





mysqli_close($con);
	









