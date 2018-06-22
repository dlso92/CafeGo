<?php  
 
	echo "
		<!DOCTYPE html>
		<html>
		<head>
		<meta charset='UTF-8'>
		<title>Login Page</title>

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
		<style>
		body{
			height: 100%;
			
		}
		label{
			display: inline-block;
			width: 120px;
		}
		input{
			display: inline-block;
			width: 160px;
			margin-left: 4px;
		}

		div#login{
			margin-top: 100px;
		}
		div#login fieldset{
			height: 120px;
			width: 260px;
			border: 2px solid black;
			margin-left: auto;
			margin-right: auto;
		}
		div#login div{
			margin: 10px;

		}
		.list-btn{
			margin-left 20px;
		}
		</style>

		<div id='login'>
			<form method='post' action='admins.php'>
			<fieldset>
			<legend>Login</legend>
			
			<label for='id'>ID </label>
			<input type='text' name='id'><br>
			
			<label for='pw'>PW </label>
			<input type='password' name='pw'><br>
			
			<div class='list-btn'>
			<p>			
			<a href='#'><button type='submit'>확인</button></a>
			<button type='reset'>지우기</button>
			</p>		
			</div>
			</fieldset>
			</form>
			</div>
		";



	
 ?>