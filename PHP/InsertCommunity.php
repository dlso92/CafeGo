<?php

	header('Content-Type:text/html; charset=utf-8');
	$number = $_POST['no'];
	$conn = mysqli_connect("localhost","dlso92","ca9156ti","dlso92");

if (!$conn)  
{  
    echo "MySQL 접속 에러 : ";
    echo mysqli_connect_error();
    exit();  
} 

mysqli_query($conn, "set names utf8");

$now = date("Y-m-d H:i:s");

$sql="SELECT id,title,content,mycafe_imgPath FROM CafeGoMyCafe WHERE no=$number";	//마이 카페에 있는 정보 가져오기
$result = mysqli_query($conn, $sql);
$row = mysqli_fetch_array($result, MYSQLI_ASSOC);

$sql="INSERT INTO Community(Cafe_no, id, date, title, content, imgPath) values('$number','$row[id]','$now','$row[title]','$row[content]','$row[mycafe_imgPath]')"; //마이카페에서 가져온 정보를 통해 집어넣기
$result = mysqli_query($conn, $sql);

$sql="SELECT co.no, co.Cafe_no, co.date, ca.id, ca.title, ca.content, ca.mycafe_imgPath FROM Community AS co LEFT JOIN CafeGoMyCafe AS ca ON co.Cafe_no = ca.no ORDER BY co.no DESC";
$result = mysqli_query($conn, $sql);

$row_num = mysqli_num_rows($result);

// echo "[";

// for($i=0; $i<$row_num; $i++){

// 	$row = mysqli_fetch_array($result, MYSQLI_ASSOC);

// 	echo "{\"list_no\":\"$row[no]\", \"no\":\"$row[Cafe_no]\", \"date\":\"$row[date]\", \"id\":\"$row[id]\", \"title\":\"$row[title]\", \"content\":\"$row[content]\", \"imgPath\":\"$row[mycafe_imgPath]\"}";

// 	if($i!=$row_num-1){
// 		echo ",";
// 	}
// }
// echo "]";


$row = mysqli_fetch_array($result, MYSQLI_ASSOC);

	echo "[{\"list_no\":\"$row[no]\", \"no\":\"$row[Cafe_no]\", \"date\":\"$row[date]\", \"id\":\"$row[id]\", \"title\":\"$row[title]\", \"content\":\"$row[content]\", \"imgPath\":\"$row[mycafe_imgPath]\"}]";

mysqli_close($conn);

?>