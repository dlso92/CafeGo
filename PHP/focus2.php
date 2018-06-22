<html> 
<head>
	<meta charset='UTF-8'>
	<title>admin page</title>
	<style>
	td  { 
			font-size : 9pt; 
		}
    	A:link  { 
    		font : 9pt;    
    		color : black;     
    		text-decoration : none;    
    		font-family : 굴림;    
    		font-size : 9pt;  
    	}
    	A:visited  {   
    		text-decoration : none; 
    		color : black;    
    		font-size : 9pt;  
    	}
    	A:hover  {     
    		text-decoration : underline; 
    		color : black; 
    		font-size : 9pt;  
    	}

    @media(max-width:400px){
    	table{
    		    width:90%; 
    		    border:0; 
    		    cellpadding:2; 
    		    cellspacing:1; 
				position:relative;
				top:10%;
				left:1%;
				border-collapse:collapse;
			}
	
		td  { 
			font-size : 9pt;   
			width: 10%;
		}
    	A:link  { 
    		font : 9pt;      
    		color : black;     
    		text-decoration : none;    
    		font-family : 굴림;    
    		font-size : 9pt;  
    	}
    	A:visited  {   
    		text-decoration : none; 
    		color : black;    
    		font-size : 9pt;  
    	}
    	A:hover  {     
    		text-decoration : 
    		underline; color : 
    		black; 
    		font-size : 9pt;  }
		
		}
    </style>
</head> 
<body topmargin=0 leftmargin=0 text=#464646>
	<center>
		<BR>
<?

$number = $_GET['no'];
$title = $_GET['title'];
$name = $_GET['name'];
$date = $_GET['date']; 
$cnt = $_GET['cnt'];
$msg = $_GET['msg'];

 ?>

 			<table width=580 border=0 cellpadding=2 cellspacing=1 bgcolor="#777777">
 				<tr>
 					<td height=20 colspan="4" align="center" bgcolor="$999999">
 						<font color="white"><B><?=$title?></B></font>
 					</td>
 				</tr>
 				<tr>
 					<td width=50 height="20" align="center" bgcolor="#EEEEEE">작성자</td><td width="240" bgcolor="white"><?=$name?></td>
 				</tr>
 				<tr>
 					<td width="50" height="20" align="center" bgcolor="#EEEEEE">날&nbsp;&nbsp;&nbsp;짜</td><td width="240" bgcolor="white"> <?=$date?></td>
 					<td width="50" height="20" align="center" bgcolor="#EEEEEE">조회수</td><td width="240" bgcolor="white"> <?=$cnt?></td>
 				</tr>
 				<tr>
 					<td bgcolor="white" colspan="4">
 						<font color="black">
 							<pre> <?=$msg?></pre>
 						</font>
 					</td>
 				</tr>

 				<tr>
 					<td colspan="1" bgcolor="#999999">
 						<table width="100%">
 							<tr>
 								<td tidth="200" align="right" height="20">
 									<a href='edit2.php?no=<?=$number?>&title=<?=$title?>&name=<?=$name?>&date=<?=$date?>&cnt=<?=$cnt?>&msg=<?=$msg?>'><input type='submit' name='' value='수정'></a>
 								<td width="200" align="right" height="20">
 									<input type=button value='확인' onclick='history.back(-1)'>
 								</td>
 							</tr>
 						</table>
 					</td>
 				</tr>
 			</table>
 			


 		</BR>
 	</center>
 </body>
 </html>