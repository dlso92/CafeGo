<?php 

	header('Content-Type:text/html; charset=utf-8');

	$conn = mysqli_connect("localhost","dlso92","ca9156ti","dlso92");

	mysqli_query($conn, "set names utf8");

	$sql = "select * from CafeGoMembers";
	$result = mysqli_query($conn, $sql);
	//결과의 총 레코드 수
	$row_num = mysqli_num_rows($result);

	for($i = 0; $i < $row_num; $i++){
	$row = mysqli_fetch_array($result, MYSQLI_ASSOC);//한줄씩..
	echo "$row[no]&$row[nickName]&$row[id]&$row[imgPath]";
	}
	mysqli_close($conn);
 ?>