<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UsersInfoDto" %>
<%@ page import="model.CatsInfoDto" %>
<% CatsInfoDto cat =(CatsInfoDto)request.getAttribute("cat"); %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <title>ネコ情報編集ページ | pepeohana</title>
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
	    const photo = document.getElementById("inputImage");
	    photo.addEventListener("change", function (e) {
	        const file = e.target.files[0];
	        const reader = new FileReader();
	        const image = document.getElementById("setImage");

	        setImage.style.height = "100px";
	        setImage.style.width = "auto";

	        reader.addEventListener("load", function () {
	            image.src = reader.result;
	        }, false);

	        if (file) {
	            reader.readAsDataURL(file);
	        }
	    });
	</script>
</head>

<%
    // セッションを取得
	UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");
	String userId = userInfoOnSession.getUserId();
	String userName = userInfoOnSession.getUserName();
%>


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
                    <%= userName %>
                </button>
                <ul class="dropdown-menu dropdown-menu-end">
                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/ExeMyPage">マイページ</a></li>
                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/ExeLogout">ログアウト</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- ログイン済ヘッダーここまで -->
    
    
    <!-- ここから下　ページごとの内容 -->
	<div class="p-5">
        <form  action="ExeEditCat" method="post" onsubmit="return validateForm()"  class="container bg-white p-4 rounded" style="max-width:500px;">
            <div class="h2 pb-2 mb-4 text-center">
                ねこ情報編集
            </div>
            <p class="text-danger">エラーメッセージ</p>
            <div class="mb-3">
                <label for="" class="form-label">名前　<span class="badge text-bg-danger">必須</span></label>
                <input type="text" class="form-control" name="CATNAME" id="inputName" value="<%=cat.getCatName() %>">
            </div>
            <div class="mb-3">            
            	<label for="inputKind" class="form-label">描種<span class="badge text-danger">必須</span></label>
				<select class="form-select" name="KIND" id="inputKind">
    				<option value="">選択してください</option>
    				<option value=1 <% if (Integer.parseInt (cat.getKind() == 1)) { %>selected<% } %>>スコティッシュ・フォールド</option>
    				<option value=2 <% if (Integer.parseInt(cat.getKind() == 2)) { %>selected<% } %>>マンチカン</option>
    				<option value=3 <% if (Integer.parseInt(cat.getKind() == 3)) { %>selected<% } %>>アメリカンショートヘアー</option>
    				<option value=4 <% if (Integer.parseInt(cat.getKind() == 4)) { %>selected<% } %>>ノルウェージャン・フォレスト・キャット</option>
    				<option value=5 <% if (Integer.parseInt(cat.getKind() == 5)) { %>selected<% } %>>ブリティッシュ・ショートヘア</option>
    				<option value=6 <% if (Integer.parseInt(cat.getKind() == 6)) { %>selected<% } %>>混血種</option>
    				<option value=7 <% if (Integer.parseInt(cat.getKind() == 7)) { %>selected<% } %>>その他</option>    				
     				<option value=8 <% if (Integer.parseInt(cat.getKind() == 8)) { %>selected<% } %>>不明</option>
				</select>
			</div>
            <div class="mb-3">
                <label for="" class="form-label">誕生日　※不明の場合は、コメント欄におおよそを記入してください</label>
                <input type="date" class="form-control" name="BIRTH" placeholder="yyyy/mm/dd" value="<%=cat.getBirth() %>">
            </div>
            <div class="mb-3">
                <label for="" class="form-label">体重　<span class="badge text-bg-danger">必須</span>　※0.1kg単位で、単位は入力不要です(例：2.5)</label>
                <input type="text" class="form-control" name="WEIGHT" id="inputWeight" value="<%=cat.getWeight() %>">
            </div>
   <!--        <div class="mb-3">
				<label for="" class="form-label">性別　<span class="badge text-bg-danger">必須</span></label><br>
				<input type="radio" class="btn-check" name="GENDER" id="inputMale" checked>
				<label class="btn btn-outline-secondary" for="inputMale">男の子</label>
				<input type="radio" class="btn-check" name="GENDER" id="inputFemale">
				<label class="btn btn-outline-secondary ms-3" for="inputFemale">女の子</label>
       		 </div>
       -->          
            <div class="mb-3">
    			<label for="" class="form-label">性別 <span class="badge text-bg-danger">必須</span></label><br>
    			<input type="radio" class="btn-check" name="GENDER" id="inputMale" <% if (cat.getGender() == 1) { %>checked<% } %>>
    			<label class="btn btn-outline-secondary" for="inputMale">男の子</label>
    			<input type="radio" class="btn-check" name="GENDER" id="inputFemale" <% if (cat.getGender() == 2) { %>checked<% } %>>
    			<label class="btn btn-outline-secondary ms-3" for="inputFemale">女の子</label>
			</div>
            
            
            <div class="mb-3">
                <label for="inputImage" class="form-label">画像　<span class="badge text-bg-danger">必須</span></label>
                <input type="file" class="form-control mb-2" name="IMAGE" id="inputImage" accept="image/png, image/jpeg" value="<%=cat.getImage() %>"><br>
                <img id="setImage">
            </div>
            <div class="mb-3">
                <label for="" class="form-label">コメント　<span class="badge text-bg-danger">必須</span></label>
                <textarea class="form-control" name="COMMENT" id="inputComment" cols="50" rows="4" maxlength="200"><%=cat.getComment() %></textarea>
            </div>
            <div class="form-check">
				<input class="form-check-input" type="checkbox" name="DEL" value="1" id="flexCheckDefault">
				<label class="form-check-label" for="flexCheckDefault">
    				<span class="text-danger">「<%=cat.getCatName() %>」の情報を削除する　※削除後の復元不可</span>
  				</label>
			</div>
            <div style="text-align: center;">
                <button type="submit" id="btn" class="btn btn-lg mt-3" style="background-color:#E87B4C; color:#ffffff;">更新する</button>
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