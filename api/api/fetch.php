<?php
session_start();
$conn = mysqli_connect("localhost","root","");
mysqli_select_db($conn,"api");
$username = $_SESSION['name'];
$sql = "select * from register where username = '$username' ";
$raw = mysqli_query($conn,$sql);
 
while($res = mysqli_fetch_array($raw))
{
    $data[]=$res;
}
 
print(json_encode($data));
 
mysqli_close($conn);
 
?>