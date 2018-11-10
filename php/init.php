<?php
$con=mysqli_connect("localhost","id7554085_root","goel1234567890","id7554085_automaticcarparking");
if(!$con)
{
	echo "Error ...".mysqli_connect_error();
}
else
{
echo "Sccess";
}
?>