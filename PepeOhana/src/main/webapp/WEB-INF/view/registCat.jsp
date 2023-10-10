<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UsersInfoDto" %>

<%--
 *Filename:registCat.jsp
 *
 *Description:
 *	このクラスは、ネコ登録機能を提供するためのものです。
 *	入力フォームに名前、猫種、誕生日、体重、性別、画像、コメントを選択または入力し、
 *	ネコ情報登録処理を行う
 *	全ての入力フォームに入力されていないとアラームを表示する
 *	ネコ情報登録に失敗した場合は、画面は戻りエラーメッセージを表示させる
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
    <title>ネコ新規登録ページ | pepeohana</title>
    <script>
	   	function validateForm() {
	        var inputName = document.getElementById("inputName").value;
	        var inputKind = document.getElementById("inputKind").value;
	        var inputWeight = document.getElementById("inputWeight").value;
	        var inputMale = document.getElementById("inputMale").checked; // ラジオボタンのチェック状態を取得
	        var inputFemale = document.getElementById("inputFemale").checked; // ラジオボタンのチェック状態を取得
	        var inputImage = document.getElementById("inputImage").value;
	        var inputComment = document.getElementById("inputComment").value;
				
	        if (inputName === "" || inputKind === "" || inputWeight === "" || inputImage === "" || inputComment === "" || (!inputMale && !inputFemale)) {
	            alert("入力できていない項目があります");
	            return false;
	        }
	        	
	        	document.getElementById("btn").disabled = true;
	            return true;
	        
	    }
	    
	    
	   //画像選択後のサムネイル表示
	   
	    function Image(obj){
		    var fileReader = new FileReader();
		    fileReader.onload =(function(){
			    document.getElementById('setImage').src = fileReader.result;
			    });
		    fileReader.readAsDataURL(obj.files[0]);
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

<%String error = (String) request.getAttribute("message"); %>

<%!  String replaceEscapeChar(String inputText) {
		String charAfterEscape = inputText;
		charAfterEscape = charAfterEscape.replace("&", "&amp;");
		charAfterEscape = charAfterEscape.replace("<", "&lt;");
		charAfterEscape = charAfterEscape.replace(">", "&gt;");
		charAfterEscape = charAfterEscape.replace("\"", " &quot; ");
		charAfterEscape = charAfterEscape.replace(" ' ", "&#039;");
		return charAfterEscape;}%>
		
		
<body style="background-color:beige; color:#523F24;">
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
        <form  action="ExeRegistCat" method="post" onsubmit="return validateForm()" enctype="multipart/form-data"  class="container bg-white p-4 rounded" style="max-width:500px;">
            <div class="h2 pb-2 mb-4 text-center">
                ねこ新規登録
            </div>
            <%if(error != null){ %>
            	<p class="text-danger"><%= error %></p>
            <% } %>
            <div class="mb-3">
                <label for="" class="form-label">名前　<span class="badge text-bg-danger">必須</span></label>
                <input type="text" class="form-control" name="CATNAME" id="inputName" value="">
            </div>
            <div class="mb-3">
                <label for="" class="form-label">描種　<span class="badge text-bg-danger">必須</span></label>
	            <select class="form-select" name="KIND" id="inputKind">
				 	<option value="">選択してください</option>
				 	<option value="1">スコティッシュ・フォールド</option>
					<option value="2">マンチカン</option>
					<option value="3">アメリカンショートヘアー</option>
					<option value="4">ノルウェージャン・フォレスト・キャット</option>
					<option value="5">ブリティッシュ・ショートヘア</option>
					<option value="6">混血種</option>
					<option value="7">その他</option>
					<option value="8">不明</option>
				</select>
			</div>
            <div class="mb-3">
                <label class="form-label">誕生日　※不明の場合は、コメント欄におおよそを記入してください</label>
                <input type="date" class="form-control" name="BIRTH" placeholder="yyyy-MM-dd">
            </div>
            <div class="mb-3">
                <label class="form-label">体重　<span class="badge text-bg-danger">必須</span>　※0.1kg単位で、単位は入力不要です(例：2.5)</label>
                <input type="number" step="0.1" class="form-control" name="WEIGHT" id="inputWeight">
            </div>
            <div class="mb-3">
				<label class="form-label">性別　<span class="badge text-bg-danger">必須</span></label><br>
				<input type="radio" class="btn-check" name="GENDER" value = 1 id="inputMale" autocomplete="off">
				<label class="btn btn-outline-secondary" for="inputMale">男の子</label>
				<input type="radio" class="btn-check" name="GENDER" value = 2 id="inputFemale" autocomplete="off">
				<label class="btn btn-outline-secondary ms-3" for="inputFemale">女の子</label>
            </div>
            <div class="mb-3">
                <label for="inputImage" class="form-label">画像　<span class="badge text-bg-danger">必須</span></label>
                <input type="file" class="form-control mb-2" name="IMAGE" id="inputImage" accept="image/png, image/jpeg" onchange="Image(this)"><br>
                <img id="setImage" src="data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==" style="max-width:100px;">
            </div>
            <div class="mb-3">
                <label class="form-label">コメント　<span class="badge text-bg-danger">必須</span></label>
                <textarea class="form-control" name="COMMENT" id="inputComment" cols="50" rows="4" maxlength="200" ></textarea>
            </div>
            <div style="text-align: center;">
                <button type="submit" id="btn" class="btn btn-lg mt-3" style="background-color:#E87B4C; color:#ffffff;">登録する</button>
            </div>
        </form>
    </div>

	<!-- ここまで　ページごとの内容 -->
	
	
    <!-- フッター -->
    <div class="text-center mt-4">
        <a class="icon-link icon-link-hover" href="#">
            ページトップへ
        </a>
	</div>
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