<?php
    
include_once'dbconnect.php';
     
     
			
$result = array();
			
			
$select= "SELECT *from view";
			
$responce = mysqli_query($con,$select);
	
            
			
while($row = mysqli_fetch_array($responce))
			
{
		
		    
$index['id']      = $row['0'];


		   $index['username']      = $row['1'];
$index['pictures']   = $row['2'];

$index['date']   = $row['3'];
		  $index['price']   = $row['6'];
		
$index['description']   = $row['4'];
$index['contact']      = $row['7'];
		   
 


		



	


array_push($result, $index);
			
				
			
}
			
	    
echo json_encode($result);
			
mysqli_close($con);
	


            
	

?>