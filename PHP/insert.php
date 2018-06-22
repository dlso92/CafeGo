
<?php 

    header('Content-Type:text/html; charset=utf-8');
    $conn = mysqli_connect("localhost","dlso92","ca9156ti","dlso92");
    mysqli_query($conn, "set names utf8");
    $manager = 987654321;
    $title = $_POST[title];
    $content = $_POST[content];
    $now = date("Y-m-d H:i:s");

    $file = $_FILES['img'];
    $srcName = $file['name'];
    $tmpName = $file['tmp_name'];

    $arr = explode(".", $srcName);
    $len = count($arr);
    $fileName = ".".$arr[$len-1];

    $dstName = "Manager/".date('Ymd_His').$fileName;
    $uploadName = date('Ymd_His').$fileName;
    move_uploaded_file($tmpName, $dstName);
    // $imgPath = $_POST[imgPath];

    $query = "INSERT INTO AdminCommunity(id,title,content,date,imgPath) VALUES('$manager','$title','$content','$now','$uploadName')" ;

    $result = mysqli_query($conn, $query) or die(mysql_error());

    mysqli_close($conn);

     echo("<script>
        setTimeout(function(){
            location.replace('note.php');
        }, 1000);
      </script>
    ");

 ?>

 <center>
<font size=2>정상적으로 저장되었습니다.</font>

