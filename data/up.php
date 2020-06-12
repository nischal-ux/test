

<?php
if($_SERVER['REQUEST_METHOD']=='POST'){

include_once 'dbconnect.php';
 $id  = $_POST['id'];

$Sql_Query = "DELETE FROM view WHERE id = '$id'";

 if(mysqli_query($con,$Sql_Query))
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