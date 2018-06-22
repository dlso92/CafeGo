<?php  

    header('Content-Type:text/html; charset=utf-8');

$conn=mysqli_connect("localhost","dlso92","ca9156ti", "dlso92" );  
if (!$conn)  
{  
    echo "MySQL 접속 에러 : ";
    echo mysqli_connect_error();
    exit();  
}  

mysqli_query($conn," set names utf8"); 


$sql="select * from Community ORDER BY no DESC";

$result=mysqli_query($conn, $sql);

$row_num = mysqli_num_rows($result);

echo "[";

for($i=0; $i<$row_num; $i++){

 $row = mysqli_fetch_array($result, MYSQLI_ASSOC);

 echo "{\"list_no\":\"$row[no]\", \"no\":\"$row[Cafe_no]\", \"date\":\"$row[date]\", \"id\":\"$row[id]\", \"title\":\"$row[title]\", \"content\":\"$row[content]\", \"imgPath\":\"$row[imgPath]\"}";

 if($i!=$row_num-1){
     echo ",";
 }
}
echo "]";

mysqli_close($conn);  
   
?>