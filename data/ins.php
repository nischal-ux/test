<?php
include_once 'dbconnect.php';
$name=$_POST['name'];
$email=$_POST['email'];
$password=$_POST['password'];	
	
echo 123;
$target_dir = "Images/";
	
$image = $_POST['image'];
	
$imageStore = rand()."_".time().".jpeg";
	
$target_dir = $target_dir."/".$imageStore;
	
file_put_contents($target_dir, base64_decode($image));

	
$select= "INSERT into user(name,email,password,image) VALUES ('$name','$email','$password','$imageStore')";
	
$responce = mysqli_query($con,$select);
	
	
if($responce)
			
{
							
			    
echo "Image Uploaded";
				
				
			
}
	
else{
	        
echo "Failed";
			
}

mysqli_close($con);
	

