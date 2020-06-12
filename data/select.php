<?php 

	
	include_once 'dbconnect.php';
		
	$sql= "SELECT view.*,loginuser.email,loginuser.name
from view join loginuser on view.username=loginuser.username
";
	$r = mysqli_query($con,$sql);
	$arr=array();
while($row=mysqli_fetch_assoc($r)){
$arr[]=$row;
	
}

header('Content-Type:application/json');
echo json_encode($arr);	
			
