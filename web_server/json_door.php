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
	
	$sql = "SELECT * FROM door ORDER BY sr DESC";
	$result = $conn->query($sql);
	
	$response = array();
	$this_row = array("sr" => "Sr", "date" => "Date", "time" => "Time", "userid" => "User ID", "code" => "Code", "action" => "Action");
	$response[] = $this_row;
	if ($result->num_rows > 0)
	{
		while($row = $result->fetch_assoc())
		{
			//Formatting of date
			$db_dt = date("j F, Y", strtotime($row["ts"]));
			$db_tm = date("g:i:s a", strtotime($row["ts"]));
			//Device status
			if($row["ac"] == '1')
			{
				$db_ac = "Open";
			}
			elseif($row["ac"] == '0')
			{
				$db_ac = "Close";
			}
			$this_row = array("sr" => $row["sr"], "date" => $db_dt, "time" => $db_tm, "userid" => $row["id"], "code" => $row["cd"], "action" => $db_ac);
			$response[] = $this_row;
		}
	}
	else
	{
		echo "0 results";
	}

	echo json_encode(array("door"=>$response));
	
	mysqli_close($con);
?>