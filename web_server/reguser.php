<?php

	$host = "server_host";
	$db = "server_db";
	$username = "server_db_username";
	$password = "server_dp_pwd";
	$conn = mysqli_connect($host, $username, $password, $db);

	$name = $_POST["name"];
	$user = $_POST["user"];
	$pswd = $_POST["pswd"];
	$dkey = $_POST["dkey"];
	
	$sql_query = "INSERT INTO users (name, user, pswd, dkey) VALUES('$name', '$user', '$pswd', '$dkey');";
	
	if(mysqli_query($conn, $sql_query)){
		echo "Success!";
	}
	else{
		echo "Failed!";
	}

?>