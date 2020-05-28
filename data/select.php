<?php 

	
	include_once 'dbconnect.php';
		
	$sql= "SELECT *from user";
	$r = mysqli_query($con,$sql);
	$arr=array();
while($row=mysqli_fetch_assoc($r)){
$arr[]=$row;
	
}

header('Content-Type:application/json');
echo json_encode($arr);	
			
