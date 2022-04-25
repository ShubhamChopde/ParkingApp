<?php
$conn=mysqli_connect("localhost","root","");
mysqli_select_db($conn,"api");

$name=trim($_POST['name']);
$mobile_no=trim($_POST['mobile']);
$email_id=trim($_POST['email']);
$password=trim($_POST['username']);
$parking_address=trim($_POST['password']);
$no_of_slots=trim($_POST['password']);
$id=trim($_POST['password']);

$qry="select * from parking_owner_register where name='$name'";
$raw=mysqli_query($conn,$qry);
$count=mysqli_num_rows($raw);

if($count>0)
{
$response="exist";
}
else
{
$qry1="INSERT INTO register (name,mobile_no,email_id,password,parking_address,no_of_slots,id) 
       VALUES ('$name','$mobile_no','$email_id','$password','$parking_address','$no_of_slots','$id')";
$res=mysqli_query($conn,$qry1);
if($res==true)
$response="Inserted";
else
$response="Failed";
}

echo $response;

?>