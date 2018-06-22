<? 
header('Content-Type:text/html; charset=utf-8');

$conn= mysqli_connect("localhost", "dlso92", "ca9156ti", "dlso92");

mysqli_query($conn, "set names utf8");
 
    $number = $_GET['no'];
    $title = $_GET['title'];
    $name = $_GET['name'];
    $ymd = date("Y-m-d");
    $cnt = $_GET['cnt'];
    $msg = $_GET['msg'];

    $query = "select * from AdminCommunity where no=$number";
    $result=mysqli_query($conn,$query);
    $row=mysqli_fetch_array($result, MYSQLI_ASSOC);

    if ($number==$row[no]) {
        $query = "UPDATE AdminCommunity SET title='$title', name='$name', date='$ymd', cnt='$cnt', msg='$msg' WHERE no=$number";
        $result=mysqli_query($conn,$query);
    }
    else {
        echo ("
        <script>
        alert('Fail');
        history.go(-1);
        </script>
        ");
        exit;
    }

    //데이터베이스와의 연결 종료
    mysqli_close($conn);

    

   $mobilechk = '/(iPod|iPhone|Android|BlackBerry|SymbianOS|SCH-M\d+|Opera Mini|Windows CE|Nokia|SonyEricsson|webOS|PalmOS)/i';
        if(preg_match($mobilechk, $_SERVER['HTTP_USER_AGENT'])) {
             echo("<script>
        setTimeout(function(){
            location.replace('note_app.php');
        }, 1000);
      </script>
    ");
        }else{
             echo("<script>
        setTimeout(function(){
            location.replace('note.php');
        }, 10000);
      </script>
    ");
        }
?>

<center>
    <font size=2>수정되었습니다.</font>