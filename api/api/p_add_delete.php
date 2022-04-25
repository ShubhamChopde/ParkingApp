<?php
$conn=mysqli_connect("localhost","root","");
mysqli_select_db($conn,"api");

$email=trim($_POST['email']);
$slots=trim($_POST['slots']);

$qry="SELECT * from p_register where email='$email'";
$raw=mysqli_query($conn,$qry);
$count=mysqli_num_rows($raw);


if($count>0)
{
	$query = "UPDATE p_register SET slots='$slots' where email='$email'";
      $query_run = mysqli_query($conn,$query);
$response = "1";
echo $response;
}	
?>