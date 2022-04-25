<?php
  $connection = mysqli_connect("localhost","root","");
  $db = mysqli_select_db($connection,'api');



  $user_name =trim( $_POST['user_name']);
  $user=mysqli_query($connection,"SELECT * FROM tuf where user_name='$user_name'");
  $result=mysqli_num_rows($user);
  echo $result;
  $user1=mysqli_query($connection,"SELECT * FROM tui where user_name='$user_name'");
  $result1=mysqli_num_rows($user1);
  echo $result1;
if($result>$result1)
{
  $query = "DELETE from tuf where user_name='$user_name'";
  $query_run = mysqli_query($connection,$query);
  $query5 = "DELETE from tufreceipt where user_name='$user_name'";
  $query_run5 = mysqli_query($connection,$query5);
  if($result>0 ){
     echo '1';

  }
}
if ($result<$result1) {
  $query1 = "DELETE from tui where user_name='$user_name'";
  $query_run = mysqli_query($connection,$query1);
  $query6 = "DELETE from tuireceipt where user_name='$user_name'";
  $query_run6 = mysqli_query($connection,$query6);
  if($result1>0){
     echo '1';

  }
}
  
  ?>