<?php

    $connection=mysqli_connect("localhost","root","");
    mysqli_select_db($connection,"api");
    
     $user_name=trim($_POST['user_name']);
     $name=trim($_POST['name']);
     $v_model=trim($_POST['v_model']);
     $v_no=trim($_POST['v_no']);
     $mobile=trim($_POST['mobile']);
     
     $sql = "UPDATE tuf SET  user_name = '$user_name', name = '$name', v_model = '$v_model', v_no = '$v_no', mobile = '$mobile' WHERE name = '$name' ";
     
     $result = mysqli_query($connection,$sql);
     
     if($result){
         echo "Data Updated";
        
     }
     else{
         echo "Failed";
     }
     mysqli_close($connection);
     
        
?>