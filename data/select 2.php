<?php
include_once 'dbconnect.php';
if ($con->connect_error) {
 die("Connection failed: " . $con->connect_error);
} 
$sql ="SELECT * from data";



$result = $con->query($sql);

if ($result->num_rows >=0) {
 
 
 while($row[] = $result->fetch_assoc()) {
 
 $tem = $row;
 
 $json = json_encode($tem);
 
 
 }
 
} else {
 echo "No Results Found.";
}
echo $json;
$con->close();
?>