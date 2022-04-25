<?php
define('HOST','localhost');
define('USER','root');
define('PASS','');
define('DB','api');

 
$con = mysqli_connect(HOST,USER,PASS,DB);
$user_name  = $_GET['user_name'];
 $user1=mysqli_query($con,"select * from tuf where user_name = '$user_name'");
 $result1=mysqli_num_rows($user1);
 $user2=mysqli_query($con,"select * from tui where user_name = '$user_name'");
 $result2=mysqli_num_rows($user2);
 if($result1>$result2){
    $query7 = "DELETE from tuf where user_name='$user_name'";
  $query_run7 = mysqli_query($con,$query7);
$sql = "select * from tufreceipt where user_name = '$user_name'";
 
$res = mysqli_query($con,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,array('user_name'=>$row[2],
'v_model'=>$row[4],
'v_no'=>$row[5],
'mobile'=>$row[6],
'time_slot'=>$row[7],
'Amount'=>$row[8]

));

}
 
echo json_encode(array("result"=>$result));

mysqli_close($con);
 }
 if($result1<$result2){
     $query8 = "DELETE from tui where user_name='$user_name'";
  $query_run8 = mysqli_query($con,$query8);
$sql = "select * from tuireceipt where user_name = '$user_name'";
 
$res = mysqli_query($con,$sql);
 
$result = array();
 
while($row = mysqli_fetch_array($res)){
array_push($result,array('user_name'=>$row[2],
'v_model'=>$row[4],
'v_no'=>$row[5],
'mobile'=>$row[6],
'time_slot'=>$row[7],
'Amount'=>$row[8]

));

}
 
echo json_encode(array("result"=>$result));

mysqli_close($con);
 }
?>