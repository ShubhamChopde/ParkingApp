<?php
$conn=mysqli_connect("localhost","root","");
mysqli_select_db($conn,"api");



  if($_SERVER["REQUEST_METHOD"]=="POST"){
  $username=$_POST['Mailid'];
  $password=$_POST['password'];
  echo "string";
  
  $user=mysqli_query($conn,"SELECT * FROM register where email='satyam.s.ko@gmail.com'");
  $result=mysqli_num_rows($user);
  if($result>0){
    $query = "UPDATE register SET password='98'where email='satyam.s.ko@gmail.com'";
      $query_run = mysqli_query($conn,$query);
     echo '1';

  }
}
   
?>

