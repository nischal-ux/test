<?php



include 'dbconnect.php';
if($_SERVER['REQUEST_METHOD']=='GET'){
 

 $id = $_GET['id'];


$Sql = "DELETE FROM userdata Where id = '$id' " ;

 if(mysqli_query($con,$Sql))
{

 echo 'Record Deleted Successfully';
}
else
{
 echo 'Something went wrong';
 }
}
 
 mysqli_close($con);
?>