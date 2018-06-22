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


$sql="select * from CafeGoMembers";

$result=mysqli_query($conn, $sql);

$row_num = mysqli_num_rows($result);

    echo "[";

for($i=0; $i<$row_num; $i++){
    $row = mysqli_fetch_array($result, MYSQLI_ASSOC);
    echo "{\"no\":\"$row[no]\", \"name\":\"$row[nickName]\", \"id\":\"$row[id]\", \"imgPath\":\"$row[imgPath]\"}";
    if($i!=$row_num-1){
        echo ",\n";
    }
}

    echo "]";

// if($result){  
    
//     while($row=mysqli_fetch_array($result)){
//         array_push($data, 
//             array('no'=>$row[0],
//             'title'=>$row[1],
//             'content'=>$row[2],
//             'mycafe_imgPath'=>$row[3]

//         ));
//     }

//     echo "<pre>"; print_r($data); echo '</pre>';

// }  

mysqli_close($conn);  
   
?>