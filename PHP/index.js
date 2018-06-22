function adder(){
	location.href="write.php";
}

function rewrite(){
	alert("rewrite");
}
 
function deleted(){
	alert("delete");
}

function focus(){
	location.href='focus.php?no=<%=no%>+msg=<%=msg%>+name=<%=name%>+data=<%=data%>+cnt=<%=cnt%>';
}