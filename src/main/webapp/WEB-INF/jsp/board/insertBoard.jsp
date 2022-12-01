<html xmlns:th="http://www.thymeleaf.org" 
xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity5">

<head>
   <title>새 글 등록</title>
   <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<body th:align="center">
<h1>게시글 등록</h1>
<form th:action="@{/board/insertBoardProc}" method="post">
<table th:align="center" border="1" th:cellpadding="0" th:cellspacing="0">            
   <tr>
      <td th:text="제목" width="80"></td>
      <td><input name="title" type="text" size="50"></td>
   </tr>
   <tr>
      <td th:text="작성자"></td>
<!--       <td><span sec:authentication="principal.member.name"></span></td> -->
   </tr>
   <tr>
      <td th:text="내용">
      <td><textarea name="content" cols="50" rows="10"></textarea></td>
   </tr>
   <tr>
      <td colspan="2" align="center">
         <input type="submit" value="게시글 등록">
      </td>
   </tr>
</table>
</form>   
<a th:href="@{/board/boardList}">글목록</a>
</body>
</html>