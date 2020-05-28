<?php
include_once'dbconnect.php';

$email=$_POST['email'];
$price=$_POST['price'];
$desciption=$_POST['description'];
$date = date('Y/m/d H:i:s');
//$artistid=$_POST['artistid'];

$target_dir = "Images/";
	
$pictures = $_POST['pictures'];
	
$imageStore = rand()."_".time().".jpeg";
	
$target_dir = $target_dir."/".$imageStore;
	
file_put_contents($target_dir, base64_decode($pictures));

	
$sql="Insert into artisit(email,price,description,pictures,date) values ('$email','$price','$description','$imagestore','$date')";
	
$r=mysqli_query($con,$sql);	
if($r){
echo"uploades";
}else{}



mysqli_close($con);
	









