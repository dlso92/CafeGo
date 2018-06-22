<html> 
<head>
    <meta charset='UTF-8'>
<title>admin page</title>
<style> 
td { font-size : 9pt; }
A:link { font : 9pt; color : black; text-decoration : none;
font-family: 굴림; font-size : 9pt; }
A:visited { text-decoration : none; color : black;
font-size : 9pt; }
A:hover { text-decoration : underline; color : black;
font-size : 9pt;}
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
 
<form action=update2.php?no=<?=$number?>&title=<?=$title?>&name=<?=$name?>&date=<?=$date?>&cnt=<?=$cnt?>&msg=<?=$msg?>method=post>
<table width=580 border=0 cellpadding=2 cellspacing=1 bgcolor=#777777>
    <tr>
        <td height=20 align=center bgcolor=#999999>
            <font color=white><B>수 정 하 기</B></font>
        </td>
    </tr>
<!-- 입력 부분 -->
    <tr>
        <td bgcolor=white>&nbsp;
        <table>
            <tr>
                <td width=60 align=left >작성자</td>
                <td align=left >
                    <INPUT type=text name=name size=20
                    value="<?=$name?>" autocomplete="off">
                </td>
            </tr>
            <tr>
                <td width=60 align=left >제 목</td>
                <td align=left >
                    <INPUT type=text name=title size=60
                    value="<?=$title?>" autocomplete="off">
                </td>
            </tr>
            <tr>
                <td width=60 align=left >내용</td>
                <td align=left >
                    <TEXTAREA name=msg cols=65 rows=15><?=$msg?></TEXTAREA>
                </td>
            </tr>
            <tr>
                <td colspan=10 align=center>
                    <INPUT type=submit value="글 저장하기">
                    &nbsp;&nbsp;
                    <INPUT type=reset value="다시 쓰기">
                    &nbsp;&nbsp;
                    <INPUT type=button value="되돌아가기"
                    onclick="history.back(-1)">
                </td>
            </tr>
            </TABLE>
        </td>
    </tr>
<!-- 입력 부분 끝 -->
</table>
</form>
</center>
</body>
</html>
