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
    <title>ネコ編集情報ページ | pepeohana</title>
</head>

<%
    // セッションを取得
	UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");
    if (userInfoOnSession != null) {
		String userId = userInfoOnSession.getUserId();
		String userName = userInfoOnSession.getUserName();
    }
%>


<body style="background-color:beige; color:#523F24;">

    <!-- ナビゲーションボタンのカラー -->
    <style>
        .login-custom-btn{
            background-color: #523F24;
            color: #ffffff; /* テキストカラー */
            border-color: #523F24; /* ボーダーカラー */
        }
        .login-custom-btn:focus, .login-custom-btn:active{
            border-color: #523F24;
            color: #523F24; /* テキストカラー */
        }
    </style>
    <!-- ログイン済ヘッダー -->
    <nav class="navbar navbar-expand-lg navbar-light border-bottom">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.jsp">
                <img src="images/pepe_logo.png" alt="ページロゴ" width="auto" height="70">
            </a>
            <div class="btn-group">
                <button type="button" class="btn login-custom-btn dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
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
    
    
    <!-- ここから下　ページごとの内容 -->
	<div class="p-5">
        <form  action="ExeEditUser" class="container bg-white p-4 rounded" style="max-width:500px;">
            <div class="h2 pb-2 mb-4 text-center">
                ネコ情報編集
            </div>
            <p class="text-danger">エラーメッセージ</p>
            <div class="mb-3">
                <label for="" class="form-label">ユーザーID<span class="text-danger">　※必須</span></label>
                <input type="text" class="form-control" id="" value="">
            </div>
            <div class="mb-3">
                <label for="" class="form-label">ユーザー名<span class="text-danger">　※必須</span></label>
                <input type="text" class="form-control" id="" value="">
            </div>
            <div class="mb-3">
				<label for="" class="form-label">性別<span class="text-danger">　※必須</span></label><br>
				<input type="radio" class="btn-check" name="GENDER" id="success-outlined" autocomplete="off">
				<label class="btn btn-outline-success" for="success-outlined">男の子</label>
				<input type="radio" class="btn-check" name="options-outlined" id="danger-outlined" autocomplete="off">
				<label class="btn btn-outline-danger" for="danger-outlined">女の子</label>
            </div>
       		<div class="form-check mt-3">
         		<input type="checkbox" class="btn-check" id="gender_1" value="1" autocomplete="off">
				<label class="btn btn-outline-secondary" for="gender_1">男の子</label>
         		<input type="checkbox" class="btn-check" id="gender_2" value="2" autocomplete="off">
				<label class="btn btn-outline-secondary ms-3" for="gender_2">女の子</label>
			</div>
            <div style="text-align: center;">
                <button type="submit" class="btn btn-lg mt-3" style="background-color:#E87B4C; color:#ffffff;">更新する</button>
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