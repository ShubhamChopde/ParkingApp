<?php
$conn=mysqli_connect("localhost","root","");
mysqli_select_db($conn,"api");

$name=trim($_POST['name']);
$mobile=trim($_POST['mobile']);
$email=trim($_POST['email']);
$password=trim($_POST['password']);
$address=trim($_POST['address']);
$slots=trim($_POST['slots']);

$qry="select * from p_register where email='$email'";
$raw=mysqli_query($conn,$qry);
$count=mysqli_num_rows($raw);

if($count>0)
{
$response="exist";
}
else
{
$qry1="INSERT INTO p_register (id, name, mobile, email, password, address, slots) 
       VALUES (NULL, '$name', '$mobile', '$email', '$password', '$address', '$slots')";
$res=mysqli_query($conn,$qry1);
if($res==true)
$response="Register Successful";
else
$response="Register Failed";
}

echo $response;

?>