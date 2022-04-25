<?php
$conn=mysqli_connect("localhost","root","");
mysqli_select_db($conn,"api");

$name=trim($_POST['name']);
$mobile=trim($_POST['mobile']);
$email=trim($_POST['email']);
$username=trim($_POST['username']);
$password=trim($_POST['password']);

$qry="SELECT * from register where username='$username'";
$raw=mysqli_query($conn,$qry);
$count=mysqli_num_rows($raw);

if($count>0)
{
$response="exist";
}
else
{
$qry1="INSERT INTO register (id, name, mobile, email, username, password) 
       VALUES (NULL, '$name', '$mobile', '$email', '$username', '$password')";
$res=mysqli_query($conn,$qry1);
if($res==true)
$response="Inserted";
else
$response="Failed";
}

echo $response;

?>