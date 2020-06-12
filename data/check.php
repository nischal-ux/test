<?php
 if($_SERVER['REQUEST_METHOD']=='POST'){
 include 'dbconnect.php';

 $password = $_POST['password'];
 $Sql_Query = "select password from loginuser where  password = '$password' ";
 $check =mysqli_query($con,$Sql_Query);
 if($check->num_rows > 0){
 echo "Data Matched";
 }
 else{
 echo "Invalid Username or Password Please Try Again";
 }
 }else{
 echo "Check Again";
 }


?>