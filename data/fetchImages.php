<?php
    
include_once'dbconnect.php';
     
     
			
$result = array();
			
			
$select= "SELECT *from userdata,artist";
			
$responce = mysqli_query($con,$select);
	
            
			
while($row = mysqli_fetch_array($responce))
			
{
		
		    
$index['id']      = $row['0'];


		   $index['username']      = $row['2'];

		  
$index['contact']      = $row['4'];
		   $index['password']      = $row['3'];
 

$index['profileurl']   = $row['5'];

	$index['artistid']   = $row['6'];

		
$index['email']   = $row['7'];

$index['price']   = $row['8'];
		
$index['description']   = $row['9'];
	
$index['pictures']   = $row['10'];

$index['date']   = $row['11'];

array_push($result, $index);
			
				
			
}
			
	    
echo json_encode($result);
			
mysqli_close($con);
	


            
	

?>