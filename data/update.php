<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
include_once 'dbconnect.php';
   $id = $_GET["id"];
    // $price = $_POST["price"];
     $email = $_POST["email"];
     //$description = $_POST["description"];
 //$date = date('Y/m/d H:i:s');
   // $pictures = $_POST["pictures"];
     //$target_dir = "Images/";
	
//$pictures = $_POST['pictures'];
	
//$imageStore = rand()."_".time().".jpeg";
	
//$target_dir = $target_dir."/".$imageStore;
	
//file_put_contents($target_dir, base64_decode($pictures));


    $sql = "UPDATE artist SET  email = '$email' WHERE id = '$id' ";
     
     $r = mysqli_query($con,$sql);
     
     if($r){
print_r($name);
         echo "Data Updated";
        
     }
     else{
         echo "Failed";
     }

     mysqli_close($con);
}
     ?>


     
     
   
     
     
     
     

