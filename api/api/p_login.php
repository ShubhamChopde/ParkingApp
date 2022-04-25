<?php
  session_start();
  $msg="";
  $_SESSION['email']="email";
  $email=$_POST['email'];
  $password=$_POST['password'];
  $connect=mysqli_connect("localhost","root","","api");
  $user=mysqli_query($connect,"SELECT * FROM p_register where email='$email'");
  $result=mysqli_num_rows($user);
  $user=mysqli_query($connect,"SELECT * FROM p_register where password='$password'");
  $result1=mysqli_num_rows($user);
  if($result>0 && $result1>0){
     echo '1';

}
   
?>

