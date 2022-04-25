<?php
$conn=mysqli_connect("localhost","root","");
mysqli_select_db($conn,"api");

$owner_id=trim($_POST['owner_id']);
$user_name=trim($_POST['user_name']);
$name=trim($_POST['name']);
$v_model=trim($_POST['v_model']);
$v_no=trim($_POST['v_no']);
$mobile=trim($_POST['mobile']);
$time_slot=trim($_POST['time_slot']);

$location1='tuf';
$location2='tui';
$slot = '150';
$slot2 = '100';
if($location1==$owner_id)

{
	$user=mysqli_query($conn,"SELECT * FROM tuf");
  	$result=mysqli_num_rows($user);
	if($slot>$result)
	{
		$cal=$time_slot*10;
		echo $cal;
		$qry1="INSERT INTO tuf (owner_id, user_name, name, v_model, v_no, mobile, time_slot, Amount) 
                       VALUES ('$owner_id', '$user_name', '$name', '$v_model', '$v_no', '$mobile', '$time_slot', '$cal')";
		$res=mysqli_query($conn,$qry1);

		$qry3="INSERT INTO tufreceipt (owner_id, user_name, name, v_model, v_no, mobile, time_slot, Amount) 
                       VALUES ('$owner_id', '$user_name', '$name', '$v_model', '$v_no', '$mobile', '$time_slot', '$cal')";
		$res=mysqli_query($conn,$qry3);
  
	}
	else
	{
		echo 'Slots full';
	}
}
if($location2==$owner_id)
{
	$user=mysqli_query($conn,"SELECT * FROM tui");
  	$result1=mysqli_num_rows($user);
	if($slot2>$result)
	{
		$cal=$time_slot*10;
		$qry2="INSERT INTO tui (owner_id, user_name, name, v_model, v_no, mobile, time_slot, Amount) 
                       VALUES ('$owner_id', '$user_name', '$name', '$v_model', '$v_no', '$mobile', '$time_slot', '$cal')";
		$res2=mysqli_query($conn,$qry2);
		$qry2="INSERT INTO tuireceipt (owner_id, user_name, name, v_model, v_no, mobile, time_slot, Amount) 
                       VALUES ('$owner_id', '$user_name', '$name', '$v_model', '$v_no', '$mobile', '$time_slot', '$cal')";
		$res=mysqli_query($conn,$qry2);
	}
	else
	{
		echo 'Slots Full';
	}
}


?>