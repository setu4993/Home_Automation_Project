<?php

	$host = "server_host";
	$db = "server_db";
	$username = "server_db_username";
	$password = "server_dp_pwd";
	$conn = mysqli_connect($host, $username, $password, $db);

	$device = $_POST["device"];
	$action = $_POST["action"];
	
	$sql_query = "INSERT INTO request (dt, ac) VALUES('$device', '$action');";
	
	if(mysqli_query($conn, $sql_query)){
		echo "Success!";
	}
	else{
		echo "Failed!";
	}

?>