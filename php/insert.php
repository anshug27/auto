<?php
require "init.php";


	$user_id=$_POST["e_id"];
	$name=$_POST["e_name"];
	$email=$_POST["e_mail"];
	$contact=$_POST["e_contact"];
$sql_query = "insert into users values('$user_id','$name','$email','$contact');";
if(mysqli_query($con,$sql_query))
{
echo "Inserted";
}
else
{
echo "Not Inserted";
}
?>