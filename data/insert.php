<?php
include_once'dbconnect.php';

$username=$_POST['username'];
$email=$_POST['email'];
$password=$_POST['password'];
$contact=$_POST['contact'];
//$artistid=$_POST['artistid'];

$target_dir = "Images/";
	
$profileurl = $_POST['profileurl'];
	
$imageStore = rand()."_".time().".jpeg";
	
$target_dir = $target_dir."/".$imageStore;
	
file_put_contents($target_dir, base64_decode($profileurl));

	
$sql="Insert into artist(email) values ('$email')";
	
$r=mysqli_query($con,$sql);	
if($r)
			
{

	$sql3 = mysqli_query($con,"SELECT MAX(ID) FROM artist");
$row = mysqli_fetch_row($sql3);
print_r($row[0]);

$sql1="Insert into userdata(username,artistid,contact,password,profileurl) values ('{$username}','{$row[0]}','{$contact}','{$password}','{$imageStore}')";

$r1=mysqli_query($con,$sql1);	
				
			
}
else{}
	


mysqli_close($con);
	









