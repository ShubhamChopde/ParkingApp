<?php
  session_start();
  $msg="";
  if($_SERVER["REQUEST_METHOD"]=="POST"){
  $_SESSION['name']="satyam";
  $username=$_POST['username'];
  $password=$_POST['password'];
  $connect=mysqli_connect("localhost","root","","api");
  $user=mysqli_query($connect,"SELECT * FROM register where username='$username'");
  $result=mysqli_num_rows($user);
  $user=mysqli_query($connect,"SELECT * FROM register where password='$password'");
  $result1=mysqli_num_rows($user);
  if($result>0 && $result1>0){
     echo '1';

  }
}
   
?>

