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
		die("Connection failed" . $conn->connect_error);
	}
	
	$sql = "SELECT * FROM light ORDER BY sr DESC";
	$result = $conn->query($sql);
	
	$response = array();
	$this_row = array("sr" => "Sr", "date" => "Date", "time" => "Time", "id" => "ID", "action" => "Action");
	$response[] = $this_row;
	if ($result->num_rows > 0)
	{
		while($row = $result->fetch_assoc())
		{
			//Formatting of date
			$db_dt = date("j F, Y", strtotime($row["ts"]));
			$db_tm = date("g:i:s a", strtotime($row["ts"]));
			//Device status
			if($row["vl"] == '1')
			{
				$db_vl = "On";
			}
			elseif($row["vl"] == '0')
			{
				$db_vl = "Off";
			}
			$this_row = array("sr" => $row["sr"], "date" => $db_dt, "time" => $db_tm, "id" => $row["id"], "action" => $db_vl);
			$response[] = $this_row;
		}
	}
	else
	{
		echo "0 results";
	}

	echo json_encode(array("light"=>$response));
	
	mysqli_close($con);
?>