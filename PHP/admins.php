<?php 

	header('Content-Type:text/html; charset=utf-8');

	$id = $_POST['id'];
	$pw = $_POST['pw'];

	// echo "$id\n";
	// echo "$pw\n";

	$conn = mysqli_connect("localhost", "dlso92", "ca9156ti", "dlso92");

	mysqli_query($conn, "set names utf8");

 	$sql="SELECT * FROM Admins";

	if (!$sql){  
    echo "sql2 구문 에러 : ";
    echo mysqli_connect_error();
    exit();  
	}  

	$result = mysqli_query($conn, $sql);

	$row = mysqli_fetch_array($result, MYSQLI_ASSOC);

	$check_id = $row[Admin_ID];
	$check_pw = $row[Admin_PW];

	// echo "$check_id\n $check_pw";

	if ($id==$check_id && $pw==$check_pw) {
		echo("<script>
        setTimeout(function(){
            location.replace('note.php');
        }, 1000);
        setTimeout(function(){
            alert('환영합니다.');
        }, 1000);
      </script>
    ");
	}else{
		echo("<script>
		alert('회원 정보를 확인하세요');
		</script>");
	}
	mysqli_close($conn);
 ?>