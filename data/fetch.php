<?php
    
include_once'dbconnect.php';
     
     
			
$result = array();
			
			
$select= "SELECT *from loginuser";
			
$responce = mysqli_query($con,$select);
	
            
			
while($row = mysqli_fetch_array($responce))
			
{
		
		    
$index['id']      = $row['0'];


		   $index['username']      = $row['2'];


$index['name']   = $row['1'];
		 
		
$index['email']   = $row['3'];

$index['profile']   = $row['5'];		   
 


		



	


array_push($result, $index);
			
				
			
}
			
	    
echo json_encode($result);
			
mysqli_close($con);
	


            
	

?>