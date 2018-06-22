<?php  
 
	echo "
		<!DOCTYPE html>
		<html>
		<head>
		<meta charset='UTF-8'>
		<title>Admin Page</title>

		<script src='index.js'></script>
		<script type='text/javascript' src='http://code.jquery.com/jquery-3.2.0.min.js'></script>

		<script>
			$('msg').click(function(){
				var tdArr = new Array();
				var checkBtn = $(this);
				var tr = checkBtn.parent().parent();
				var td = tr.children();

				var no = td.eq(0).text();

				jQuery('#no').val($no);

				location.href='focus.php?no=<%=no%>';
		});	
		</script>


		<link rel='stylesheet' type='text/css' href='index.css'>       

		</head>
		<body>
		<p>
			<a href='notice.php'>공지사항</a>
			<a href='note.php'>게시판</a>
		</p>

		<div id='board'>
		<div id='title'>
			<h3>l 게시판</h3>
		</div>
		<form action='Delete.php' method='post'>
		<div class='list'>
			<dl>
				<dt class='no'>번호</dt>
				<dt class='msg'>제목</dt>
				<dt class='name'>작성자</dt>
				<dt class='date'>작성일</dt>
				<dt class='cnt'>조회수</dt>
			</dl>
		";

	header('Content-Type:text/html; charset=utf-8');

	$conn= mysqli_connect("localhost", "dlso92", "ca9156ti", "dlso92");

	mysqli_query($conn, "set names utf8");

	//board라는 테이블에서 데이터 가져오는 쿼리문
	$sql="select * from Community";
	$result= mysqli_query($conn, $sql);

	//$result는 결과 테이블(표)
	//데이터의 총 개수(라인수: row개수 ) 
	$row_count= mysqli_num_rows($result);


	for($i=0; $i<$row_count; $i++){
		mysqli_data_seek($result, $i);//커서이동

		$row= mysqli_fetch_array($result, MYSQLI_ASSOC);//하나의 row(레코드)을 얻어오기
		// *resultType
		// 1.MYSQLI_ASSOC : 연관배열
		// 2.MYSQLI_NUM   : 인덱스배열
		// 3.MYSQLI_BOTH  : 둘다.
		 //   echo "$row[no] $row[name] $row[msg] $row[imgPath] $row[date]<br/>"; 
		 // <input type='checkbox' name='checkbox' value= '$row[no]'>
		echo ("
		
			<dl>
				<dd class='no'>
				<input type='checkbox' name='checkbox' value='$row[no]'>$row[no]
				</dd>
				<dd class='msg'><a href='focus2.php?no=$row[no]&title=$row[title]&name=$row[name]&date=$row[date]&cnt=$row[cnt]&msg=$row[msg]'>$row[title]</a></dd>
				<dd class='name'>$row[name]</dd>
				<dd class='date'>$row[date]</dd>
				<dd class='cnt'>$row[cnt]</dd>

			</dl>	
		");
	}

	echo"
		</div>
		
		

		<div class='list-btn'>
			<p>			
			<a href='write.php'><button type='button'>글쓰기</button></a>
			<button type='submit'>삭제</button>
			
			</p>		
		</div>
		</form>
		
		<div class='list-bot'>
			<p>
				<a href='#'>1</a>
				
			</p>
		</div>
	</body>
</html>
	";
	mysqli_close($conn);
 ?>