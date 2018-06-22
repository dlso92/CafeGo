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
$sql= "DELETE FROM CafeGoMyCafe WHERE no='$number'";

if (!$sql)
{  
    echo "sql 구문 에러 : ";
    echo mysqli_connect_error();
    exit();  
}  

$sql2="SELECT mycafe_imgPath FROM CafeGoMyCafe WHERE no=$number";

if (!$sql2)
{  
    echo "sql2 구문 에러 : ";
    echo mysqli_connect_error();
    exit();  
}  

$result2=mysqli_query($conn, $sql2);

$row = mysqli_fetch_array($result2, MYSQLI_ASSOC);

$file= $row[mycafe_imgPath];

if (file_exists($file)) {
    unlink($row[mycafe_imgPath]);
    echo "파일 삭제 완료";
}

$result=mysqli_query($conn, $sql);

mysqli_close($conn);
?>


