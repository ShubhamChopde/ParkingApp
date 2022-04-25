<?php
$conn=mysqli_connect("localhost","root","");
mysqli_select_db($conn,"api");

$Mailid=trim($_POST['Mailid']);
$password=trim($_POST['password']);

$qry="SELECT * from register where email='$Mailid'";
$raw=mysqli_query($conn,$qry);
$count=mysqli_num_rows($raw);


if($count>0)
{
	$query = "UPDATE register SET password='$password'where email='$Mailid'";
      $query_run = mysqli_query($conn,$query);
$response = "1";
echo $response;
}	
?>