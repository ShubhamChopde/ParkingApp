<?php 
$connection=mysqli_connect("localhost","root","");
mysqli_select_db($connection,"api");
	
	$result = array();
	$result['data'] = array();
	$select= "SELECT * from tuf";
	$response = mysqli_query($connection,$select);
	
	while($row = mysqli_fetch_array($response))
		{
                        $index['id']           = $row['0'];
                        $index['user_name']    = $row['2'];
			$index['name']         = $row['3'];
			$index['v_model']      = $row['4'];
			$index['v_no']         = $row['5'];
			$index['mobile']       = $row['6'];
			$index['time_slot']    = $row['7'];
			$index['Amount']       = $row['8'];
			
			array_push($result['data'], $index);
		}
			
			$result["success"]="1";
			echo json_encode($result);
			mysqli_close($connection);

 ?>