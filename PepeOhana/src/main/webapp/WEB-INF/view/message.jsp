<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UsersInfoDto" %>

<%--
 *Filename:message.jsp
 *
 *Description:
 *	このクラスは、メッセージ機能を提供するためのものです。
 *	前ページで抽出した情報を入力フォームに表示する
 *	入力フォームに送信したいメッセージを入力し「送信する」をクリックすると
 *	入力した内容をデータベースに登録し、マイページ画面へ遷移する
 *	メッセージの登録に失敗した場合は、画面を遷移せずエラーメッセージを表示する
 *	メッセージが入力されていない状態で「送信する」をクリックするとアラートを表示する
 *	マイページ画面の「返信する」からの遷移の場合
 *	受信したメッセージを表示させる
 *	
 *
 *Author:櫻井
 *Creation Date:2023-09-03
 *
 *Copyright © 2023 KEG Sakura All rights reserved. --%>
 
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <title>メッセージ送信 | pepeohana</title>
    
   	<script>
		function validateForm() {
			var inputComment = document.getElementById("inputComment").value;

			if(inputComment === ""){
				alert("メッセージが入力されていません");
				return false; // フォームの送信を中止
			}
			document.getElementById("btn").disabled = true;
			return true; // フォームの送信を続行
		}
	</script>
</head>

<%
    // セッションを取得
	UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");  
	int id = userInfoOnSession.getID();
	String userId = userInfoOnSession.getUserId();
	String userName = userInfoOnSession.getUserName();
%>

<% String error = (String) request.getAttribute("message"); %>

<%!  String replaceEscapeChar(String inputText) {
		String charAfterEscape = inputText;
		charAfterEscape = charAfterEscape.replace("&", "&amp;");
		charAfterEscape = charAfterEscape.replace("<", "&lt;");
		charAfterEscape = charAfterEscape.replace(">", "&gt;");
		charAfterEscape = charAfterEscape.replace("\"", " &quot; ");
		charAfterEscape = charAfterEscape.replace(" ' ", "&#039;");
		return charAfterEscape;}%>
		
<!-- ナビゲーションボタンのカラー -->
<style>
    .custom-btn {
        border-color: #523F24;
        color: #523F24;
    }
    .custom-btn:focus, .custom-btn:active {
        background-color: #523F24;
        color: #ffffff;
        border-color: #523F24;
    }
</style>

<body style="background-color:beige; color:#523F24;">
    
    <!-- ログイン済ヘッダー -->
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.jsp">
                <img src="images/pepe_logo.png" alt="ページロゴ" width="auto" height="60">
            </a>
            <div class="btn-group">
                <button type="button" class="btn custom-btn dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                    <%= replaceEscapeChar(userName) %>
                </button>
                <ul class="dropdown-menu dropdown-menu-end">
                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/ExeMyPage">マイページ</a></li>
                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/ExeLogout"onClick="return confirm('ログアウトしますか?');">ログアウト</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- ログイン済ヘッダーここまで -->
    
    
    <!-- ここから下　ページごとの内容 -->
	<div class="p-5">
        <form action="ExeSendMessage" method="post" onsubmit="return validateForm()" class="container bg-white p-4 rounded" style="max-width:500px;">
            <div class="h2 pb-2 mb-4 text-center">
                メッセージ送信
            </div>
            <%if(error != null){ %>
            	<p class="text-danger"><%= error %></p>
            <% } %>
           	<div class="mb-3 row">
		    	<label class="col-sm-4 col-form-label">対象ねこ</label>
		    	<div class="col-sm-8">
		      		<input type="text" readonly class="form-control-plaintext" name="CATNAME" id="catName" value="<%=request.getAttribute("CATNAME")%>">
		      		<input type="hidden" name="CATID" id="catId" value="<%=request.getAttribute("CATID")%>">
		    	</div>
		  	</div>
           	<div class="mb-3 row">
	    		<label class="col-sm-4 col-form-label">送信先ユーザー</label>
		    	<div class="col-sm-8">
		      		<input type="text" readonly class="form-control-plaintext" name="RECIEVERNAME" id="ownerUser" value="<%=request.getAttribute("RECIEVERNAME")%>">
		      		<input type="hidden" name="RECIEVERID" id="recieverId" value="<%=request.getAttribute("RECIEVERID")%>">
		    	</div>
		  	</div>
		  	<% if(request.getAttribute("MESSAGE") != null){ %>
		  	<div class="mb-3">
	    		<label class="form-label">受信メッセージ</label>
                <p><small><%=request.getAttribute("MESSAGE")%></small></p>
		  	</div>
		  	<%} %>
            <div class="mb-3">
                <label class="form-label">メッセージ　<span class="badge text-bg-danger">必須</span></label>
                <textarea class="form-control" name="COMMENT" id="inputComment" cols="50" rows="8" maxlength="500"></textarea>
            </div>
            <div style="text-align: center;">
                <button type="submit" id="btn" class="btn btn-lg mt-3" style="background-color:#E87B4C; color:#ffffff;">送信する</button>
            </div>
        </form>
    </div>

    
	<!-- ここまで　ページごとの内容 -->
	
	
    <!-- フッター -->
	<footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
    	<div class="col-md-4 d-flex align-items-center ms-3">
          	<span class="mb-3 mb-md-0 text-body-secondary">© 2023 pepeohana, Inc</span>
        </div>
        <ul class="nav col-md-4 justify-content-end list-unstyled d-flex me-3">
            <li><a href="#"><img src=images/twitter_logo.png width="auto" height="25"></a></li>
            <li class="ms-4"><a href="#"><img src=images/insta_logo.png width="auto" height="25"></a></li>
            <li class="ms-4"><a href="#"><img src=images/facebook_logo.png width="auto" height="25"></a></li>
        </ul>
	</footer>
    <!-- フッター　ここまで -->

</body>
</html>