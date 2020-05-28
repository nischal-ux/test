<?php
include_once 'dbconnect.php';
		
echo 123;
$target_dir = "Images/";
	
	$image = $_POST['image'];

$imageStore = rand()."_".time().".jpeg";
	
$target_dir = $target_dir."/".$imageStore;
	
file_put_contents($target_dir, base64_decode($image));

	
$select= "INSERT into image(image) VALUES ('$imageStore')";
	
$responce = mysqli_query($con,$select);
	
	
if($responce)
			
{
							
			    
echo "Image Uploaded";
				
	mysqli_close($con);
			
			
}
	
else{
	        
echo "Failed";
			
}

	


