<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%--
 *Filename:Login.jsp
 *
 *Description:
 *	このクラスは、ログイン機能を提供するためのものです。
 *	入力フォーム
 *	
 *
 *Author:櫻井
 *Creation Date:2023-09-26
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
    <title>ログイン | pepeohana</title>
    
	<script>
		//フォームバリデーション
		function validateForm() {
			var inputID = document.getElementById("inputID").value;
			var inputPass = document.getElementById("inputPass").value;

			if(inputID === "" && inputPass === ""){
				alert("ユーザーIDとパスワードを入力してください");
				return false; // フォームの送信を中止
			}else if (inputID === "") {
				alert("ユーザーIDを入力してください");
				return false; // フォームの送信を中止
			}else if(inputPass === ""){
				alert("パスワードを入力してください。");
				return false;
			}
			return true; // フォームの送信を続行
		}
	</script>
	
</head>
<%String error = (String) request.getAttribute("message"); %>

<body style="background-color:beige; color:#523F24;">
    <!-- ナビゲーションボタンのカラー -->
    <style>
        .custom-btn {
            border-color: #523F24;
            color: #523F24; /* テキストカラー */
        }
        .custom-btn:focus, .custom-btn:active {
            background-color: #523F24;
            color: #ffffff; /* テキストカラー */
            border-color: #523F24; /* ボーダーカラー */
        }
    </style>
    
    <!-- 未ログインヘッダー -->
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container-fluid">
          <a class="navbar-brand" href="index.jsp">
            <img src="images/pepe_logo.png" alt="ページロゴ" width="auto" height="60">
          </a>
          <div class="btn-group">
            <button type="button" class="btn custom-btn dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
              未ログイン
            </button>
            <ul class="dropdown-menu dropdown-menu-end">
              <li><a class="dropdown-item" href="Login.jsp">ログイン</a></li>
              <li><a class="dropdown-item" href="registUser.jsp">新規ユーザー登録</a></li>
            </ul>
          </div>
        </div>
    </nav>
    <!-- 未ログインヘッダーここまで -->
    
    <div class="p-5">
        <form action="ExeLogin" method="post" onsubmit="return validateForm()" class="container bg-white p-4 rounded" style="max-width:500px;">
            <div class="h2 pb-2 mb-4 text-center">
                ログイン
            </div>
            <%if(error != null){ %>
            	<p class="text-danger"><%= error %></p>
            <% } %>
            <div class="mb-3">
                <label for="" class="form-label">ユーザーID　<span class="badge text-bg-danger">必須</span></label>
                <input type="text" class="form-control" name="USERID" id="inputID">
            </div>
            <div class="mb-3">
                <label for="" class="form-label">パスワード　<span class="badge text-bg-danger">必須</span></label>
                <input type="password" class="form-control" name="PASS" id="inputPass">
            </div>
            <div style="text-align: center;">
                <button type="submit" class="btn btn-lg mt-3" style="background-color:#E87B4C; color:#ffffff;">ログイン</button>
			</div>
            <div class="text-end mt-4">
       			<a class="icon-link icon-link-hover" href="registUser.jsp">
            		新規登録はこちら
        		</a>
			</div>
        </form>
    </div>
    
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