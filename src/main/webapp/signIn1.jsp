<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="signIn1.css" />
    <title> 캐릭터 선택 </title>
</head>
<body>
    <div class="element">
        <div class="div">
            <div class="overlap-group">
                <!-- 이미지를 클릭하여 캐릭터 선택 -->
                <a href="signIn2.jsp?character=character1"><img class="img" src="img/character1.png" /></a>
                <a href="signIn2.jsp?character=character2"><img class="img" src="img/character2.png" /></a>
                <a href="signIn2.jsp?character=character3"><img class="img" src="img/character3.png" /></a>
                <!-- 각 이미지를 클릭할 때 선택된 캐릭터 정보를 signIn2.jsp로 전달 -->
                
                <img class="union" src="img/union.png" />
            </div>
        </div>
    </div>
</body>
</html>

