<?php
 if($_SERVER['REQUEST_METHOD']=='POST'){
 include 'dbconnect.php';
 $email = $_POST['email'];
 $password = $_POST['password'];
 $Sql_Query = "select * from loginuser where email = '$email' and password = '$password' ";
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