<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UsersInfoDto" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <title>トップページ | pepeohana</title>
    <script>
    	function validateGenderForm() {
    		var Gender1 = document.getElementById("gender_1").checked; // ラジオボタンのチェック状態を取得
    		var Gender2 = document.getElementById("gender_2").checked; // ラジオボタンのチェック状態を取得

    		if  (!Gender1 && !Gender2) {
	            alert("男の子か女の子を選んでください");
	            return false;
	        }
	            return true;
	    }
    	function validateIdForm() {
    		var catId = document.getElementById("inputCatId").value;

    		if  (catId == "") {
	            alert("ねこIDを入力してください");
	            return false;
	        }
	            return true;
	    }
    </script>
</head>

<%
    // セッションを取得
	UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO"); 
	boolean isLoggedIn = false; 
	String userName = "";
	//HttpSession mysession = request.getSession(true);

    // セッションが存在し、username属性もセットされている場合はログイン済み
    if (userInfoOnSession != null) {
		userName = userInfoOnSession.getUserName();
        isLoggedIn = true;
    }
%>

<%!  String replaceEscapeChar(String inputText) {
		String charAfterEscape = inputText;
		charAfterEscape = charAfterEscape.replace("&", "&amp;");
		charAfterEscape = charAfterEscape.replace("<", "&lt;");
		charAfterEscape = charAfterEscape.replace(">", "&gt;");
		charAfterEscape = charAfterEscape.replace("\"", " &quot; ");
		charAfterEscape = charAfterEscape.replace(" ' ", "&#039;");
		return charAfterEscape;}
%>
<% String error = (String) request.getAttribute("message"); %>
		
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
    
	<% if (isLoggedIn) { %>
    <!-- ログイン済ヘッダー -->
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">
                <img src="images/pepe_logo.png" alt="ページロゴ" width="auto" height="60">
            </a>
            <div class="btn-group">
                <button type="button" class="btn custom-btn dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                    <%=replaceEscapeChar(userName)%>
                </button>
                <ul class="dropdown-menu dropdown-menu-end">
                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/ExeMyPage">マイページ</a></li>
                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/ExeLogout" onClick="return confirm('ログアウトしますか?');">ログアウト</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- ログイン済ヘッダーここまで -->
    <% } else { %>
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
    <% } %>


    <!-- ヒーロー画像 -->
    <img class="img-fluid mb-4" src="images/hero_index.png"  alt="index画像" style="max-height:600px; width:100%; object-fit: cover;">

    <!-- ここから下　ページごとの内容 -->
    <div class="container">
        <div class="h4 pb-2 mb-4" style="border-bottom:solid 0.5px; border-color: #523F24;">
            ねこまっちんぐ
        </div>
            <p>家族をさがしているねこがたくさんいます！※まっちねこの一例</p>
        <!-- 猫カードエリア -->
        <div class="row">
           <!-- カードのコンテンツ1 -->
           <div class="col-6 col-md-4 col-lg-3">
                <div class="card mb-3">
                	<img src="images/cat_1.jpg" style="height:180px; width: 100%; object-fit: cover;" class="card-img-top" alt="猫画像">
                    <div class="card-body">
                        <h5 class="card-title text-center border-bottom pb-2">pepe<small> ちゃん</small></h5>
                        <p class="card-text text-right">描種 : アメリカンショートヘアー</p>
                        <p class="card-text text-right">年齢 : 1歳2ヵ月</p>
                        <p class="card-text text-right">性別 : 男の子</p>
                    </div>
                </div>
            </div>
           <!-- カードのコンテンツ2 -->
           <div class="col-6 col-md-4 col-lg-3">
                <div class="card mb-3">
                	<img src="images/cat_2.jpg" style="height:180px; width: 100%; object-fit: cover;" class="card-img-top" alt="猫画像">
                    <div class="card-body">
                        <h5 class="card-title text-center border-bottom pb-2">pepe<small> ちゃん</small></h5>
                        <p class="card-text text-right">描種 : アメリカンショートヘアー</p>
                        <p class="card-text text-right">年齢 : 1歳2ヵ月</p>
                        <p class="card-text text-right">性別 : 男の子</p>
                    </div>
                </div>
            </div>
           <!-- カードのコンテンツ3 -->
           <div class="col-6 col-md-4 col-lg-3">
                <div class="card mb-3">
                	<img src="images/cat_3.jpg" style="height:180px; width: 100%; object-fit: cover;" class="card-img-top" alt="猫画像">
                    <div class="card-body">
                        <h5 class="card-title text-center border-bottom pb-2">pepe<small> ちゃん</small></h5>
                        <p class="card-text text-right">描種 : アメリカンショートヘアー</p>
                        <p class="card-text text-right">年齢 : 1歳2ヵ月</p>
                        <p class="card-text text-right">性別 : 男の子</p>
                    </div>
                </div>
            </div>
           <!-- カードのコンテンツ4 -->
           <div class="col-6 col-md-4 col-lg-3">
                <div class="card mb-3">
                	<img src="images/cat_4.jpg" style="height:180px; width: 100%; object-fit: cover;" class="card-img-top" alt="猫画像">
                     <div class="card-body">
                         <h5 class="card-title text-center border-bottom pb-2">pepe<small> ちゃん</small></h5>
                         <p class="card-text text-right">描種 : アメリカンショートヘアー</p>
                         <p class="card-text text-right">年齢 : 1歳2ヵ月</p>
                         <p class="card-text text-right">性別 : 男の子</p>
                     </div>
                 </div>
            </div>
		</div>
             
        <!-- 検索フォームエリア -->
        <div class="row mt-5" id="search">
           <div class="col-md-6 mt-3">
                <div class="card mb-3 p-3">
		         	<form action="ExeSearchCat" onsubmit="return validateGenderForm()">
		         		<h3 class="border-bottom pb-2" style="text-align:center; color:#523F24;">条件まっち</h3>
		         		<div class="form-check mt-3">
		         			<p class="text-danger">はじめての方はこちらから</p>
							<label class="form-label">性別を選んでください(複数選択可)</label><br>
			         		<input type="checkbox" class="btn-check" name="GENDER1" id="gender_1" value="1" autocomplete="off">
							<label class="btn btn-outline-secondary" for="gender_1">男の子</label>
			         		<input type="checkbox" class="btn-check" name="GENDER2" id="gender_2" value="2" autocomplete="off">
							<label class="btn btn-outline-secondary ms-3" for="gender_2">女の子</label>
						</div>
						<p style="text-align:center; margin-bottom:0;">
							<button type="submit" class="btn btn-lg mt-4" style="background-color:#E87B4C; color:#ffffff;">まっちんぐ</button>
						</p>
					</form>
				</div>
			</div>
           <div class="col-md-6 mt-3">
                <div class="card mb-3 p-3">
		         	<form action="ExeShowCatInfo" onsubmit="return validateIdForm()">
		         		<h3 class="border-bottom pb-2" style="text-align:center; color:#523F24;">IDまっち</h3>
		         		<div class="form-check mt-3">
       			            <%if(error != null){ %>
            					<p class="text-danger"><%= error %></p>
            				<% }else{ %>
            					<p class="text-danger">以前検索したネコをもう一度検索したい方はこちら</p>
            				<% } %>
							<label class="form-label">ねこIDを入力してください</label>
							<label class="form-label">※ねこIDは、ねこの詳細画面に記載しています</label>
       		                <input type="text" class="form-control" style="width:100px;" name="CATID" id="inputCatId">
						</div>
						<p style="text-align:center; margin-bottom:0;">
							<button type="submit" class="btn btn-lg mt-4" style="background-color:#E87B4C; color:#ffffff;">まっちんぐ</button>
						</p>
					</form>
				</div>
			</div>

		</div>

		
	</div>
    <!-- ここまで　ページごとの内容 -->

    <!-- フッター -->
    <div class="text-center mt-4">
        <a class="icon-link icon-link-hover" href="#">
            ページトップへ
        </a>
    </div>
    <img src="images/footer_cat.png" alt=""  class="img-fluid" style="width:100%;">
    <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-1">
        <div class="col-md-4 d-flex align-items-center ms-3">
          <span class="mb-3 mb-md-0 text-body-secondary">© 2023 pepeohana, Inc</span>
        </div>
    
        <ul class="nav col-md-4 justify-content-end list-unstyled d-flex me-3">
            <li><a href="#"><img src=images/twitter_logo.png width="auto" height="24"></a></li>
            <li class="ms-4"><a href="#"><img src=images/insta_logo.png width="auto" height="24"></a></li>
            <li class="ms-4"><a href="#"><img src=images/facebook_logo.png width="auto" height="24"></a></li>
        </ul>
      </footer>
    <!-- フッター　ここまで -->

</body>
</html>