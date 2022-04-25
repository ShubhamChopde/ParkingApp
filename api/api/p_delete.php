<?php 
$connection=mysqli_connect("localhost","root","");
mysqli_select_db($connection,"api");
	
	$name = $_POST["name"];


        $sql = "DELETE FROM tuf WHERE name='$name'";
	$result = mysqli_query($connection,$sql);
        if($result){
            echo "Data Deleted";
        }
        else{
            echo "Failed";
        }
        mysqli_close($connection);

 ?>