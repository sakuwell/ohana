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
    <title>Top | pepeohana</title>
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

<body>
	<% if (isLoggedIn) { %>
    <!-- ログイン済ヘッダー -->
    <nav class="navbar navbar-expand-lg navbar-light bg-white">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.jsp">
                <img src="images/pepe_ohana_logo.png" alt="ページロゴ" width="auto" height="70">
            </a>
            <div class="btn-group">
                <button type="button" class="btn btn-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                    userName
                </button>
                <ul class="dropdown-menu dropdown-menu-end">
                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/ExeMyPage">マイページ</a></li>
                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/ExeLogout">ログアウト</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <!-- ログイン済ヘッダーここまで -->
    <% } else { %>
    <!-- 未ログインヘッダー -->
    <nav class="navbar navbar-expand-lg navbar-light bg-white">
    	<div class="container-fluid">
        	<a class="navbar-brand" href="index.jsp">
          		<img src="images/pepe_ohana_logo.png" alt="ページロゴ" width="auto" height="70">
          	</a>
         	<div class="btn-group">
            	<button type="button" class="btn btn-outline-secondary dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
             		 未ログイン
            	</button>
            	<ul class="dropdown-menu dropdown-menu-end">
              		<li><a class="dropdown-item" href="jsp/Login.jsp">ログイン</a></li>
              		<li><a class="dropdown-item" href="jsp/registUser.jsp">新規ユーザー登録</a></li>
            	</ul>
          	</div>
        </div>
    </nav>
    <!-- 未ログインヘッダーここまで -->
    <% } %>


    <!-- ヒーロー画像 -->
    <img class="img-fluid mb-4" src="images/hero_index.jpg"  alt="index画像" style="max-height:600px; width:100%; object-fit: cover;">

    <!-- ここから下　ページごとの内容 -->
    <div>
        <!-- 検索フォームゾーン -->
        <div class="container">
            <div class="h4 pb-2 mb-4 text-black border-bottom border-black">
                検索結果
            </div>
                <p>まっち数：</p>
            <!-- 猫カード -->
            <div class="row">
               <!-- カードのコンテンツ1 -->
               <div class="col-6 col-md-4 col-lg-3">
                    <div class="card mb-3">
                    	<img src="images/cat_2.jpg" style="height:180px; width: 100%; object-fit: cover;" class="card-img-top" alt="猫画像">
                        <div class="card-body">
                            <h5 class="card-title text-center border-bottom pb-2">pepe<small> ちゃん</small></h5>
                            <p class="card-text text-right">描種 : アメリカンショートヘアー</p>
                            <p class="card-text text-right">年齢 : 1歳2ヵ月</p>
                            <p class="card-text text-right">性別 : 男の子</p>
                            <div class="d-flex justify-content-center">
                                <a href="#" class="stretched-link"></a>
                            </div>
                        </div>
                    </div>
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
	<footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
    	<div class="col-md-4 d-flex align-items-center ms-3">
        	<a href="index.jsp" class="mb-3 me-2 mb-md-0"><img alt="ロゴ画像" src="images/nikukyu_logo.png" width="auto" height="40"></a>
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