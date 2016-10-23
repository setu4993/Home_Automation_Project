<?php
	$host = "server_host";
	$db = "server_db";
	$username = "server_db_username";
	$password = "server_dp_pwd";
	// Create connection
	$conn = new mysqli($host, $user, $password, $db);
	// Check connection
	if ($conn->connect_error)
	{
		die("Connection failed: " . $conn->connect_error);
	}

	$sql = "SELECT * FROM active";
	$result = $conn->query($sql);

	if ($result->num_rows > 0)
	{
		while($row = $result->fetch_assoc())
		{
			//Formatting of date
			$db_dt = date("j F, Y", strtotime($row["ts"]));
			$db_tm = date("g:i:s a", strtotime($row["ts"]));
			//Device type
			if($row["dt"] == 'd')
			{
				$db_ty = "Door";
			}
			elseif($row["dt"] == 'l')
			{
				$db_ty = "Light";
			}
			elseif($row["dt"] == 'g')
			{
				$db_ty = "Gas";
			}
			else
			{
				$db_ty = "";
			}
			//Device status
			if($row["st"] == '1')
			{
				$db_st = "On";
			}
			elseif($row["st"] == '0')
			{
				$db_st = "Off";
			}
			$this_row = "id: " . $row["sr"]. " - date: " . $db_dt. " - time: " . $db_tm. " - device: " . $db_ty. " - devid: " . $row["di"]. " - status: " . $db_st. "<br>";
			echo $this_row;
		}
	}
	else
	{
		echo "0 results";
	}
	$conn->close();
?>