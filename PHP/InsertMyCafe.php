<?php 

	header('Content-Type:text/html; charset=utf-8');

	$title = $_POST['title'];
	$content = $_POST['content'];
	$id = $_POST['id'];

	$srcFileName = $_FILES['upload']['name'];
	$tmpFileName = $_FILES['upload']['tmp_name'];

	//소스파일에서 확장자 알아내기
	$arr = explode(".",$srcFileName);
	$len = count($arr); //배열길이 얻어오기
	$fileName = ".".$arr[$len-1];

	//저장할 이미지 경로와 파일명.확장자
	$dstName = "upload/".date(Ymd_His).$fileName;
	$uploadName = date(Ymd_His).$fileName;

	if(move_uploaded_file($tmpFileName, $dstName)){
		echo "upload success\n";
	}else{
		echo "upload fail\n";
	}
	
	echo "$title\n";
	echo "$id\n;";
	echo "$content\n";
	echo "$dstName\n";

	$now = date("Y-m-d H:i:s");

	//DB에 저장!!
	//talk라는 이름의 테이블에 저장
	$conn= mysqli_connect("localhost", "dlso92", "ca9156ti", "dlso92");

	mysqli_query($conn, "set names utf8");

	//원하는 쿼리문 작성
	$sql= "insert into CafeGoMyCafe(title, id, content, mycafe_imgPath, date) values('$title','$id','$content','$uploadName','$now')";
	$result= mysqli_query($conn, $sql);


	if($result){
		echo "insert success";
	}else{
		echo "insert fail";
	}

	mysqli_close($conn);
 ?>