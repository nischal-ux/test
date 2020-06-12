<?php

if($_SERVER['REQUEST_METHOD']=='POST'){
include_once 'dbconnect.php';
   $id = $_POST["id"];
    $price = $_POST["price"];
     $username = $_POST["username"];
$contact= $_POST["contact"];
     $description = $_POST["description"];
$date = date('Y/m/d H:i:s');
   $pictures = $_POST["pictures"];
     $target_dir = "Images/";
	
$pictures = $_POST['pictures'];
	
$imageStore = rand()."_".time().".jpeg";
	
$target_dir = $target_dir."/".$imageStore;
	
file_put_contents($target_dir, base64_decode($pictures));


    $sql = "UPDATE view  SET username = '$username',price = '$price',
description = '$description',contact= '$contact',date = '$date'
WHERE id = '$id' ";
      
     $r = mysqli_query($con,$sql);
     
     if($r){

         echo "Data Updated";
        
     }
     else{
         echo "Failed";
     }

     mysqli_close($con);
}
     ?>


     
     
   
     
     
     
     

