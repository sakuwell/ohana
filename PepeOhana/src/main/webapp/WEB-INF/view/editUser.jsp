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
    <title>ユーザー編集ページ | pepeohana</title>
</head>


<body  style="background-color:beige;">
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

    <!-- ヒーロー画像 -->
    <img class="img-fluid mb-4" src="images/hero_index.jpg"  alt="index画像" style="max-height:500px; width:100%; object-fit: cover;">

    <!-- ここから下　ページごとの内容 -->
    <div class="container">
        <div class="h4 pb-2 mb-4 text-black border-bottom border-black">
            ねこまっちんぐ
        </div>
            <p>家族をさがしているネコがたくさんいます！</p>
        <!-- 猫カードエリア -->
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
           <!-- カードのコンテンツ2 -->
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
           <!-- カードのコンテンツ3 -->
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
           <!-- カードのコンテンツ4 -->
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
             
        <!-- 検索フォームエリア -->
        <div class="card mt-3 p-3">
         	<form action="SearchCat">
         		<h3 class="border-bottom pb-2" style="text-align:center;">まっちんぐ</h3>
         		<div class="form-check mt-3">
					<label for="" class="form-label">性別を選んでください(複数選択可)<span class="text-danger">　※必須</span></label><br>
	         		<input type="checkbox" class="btn-check" id="gender_1" value="1" autocomplete="off">
					<label class="btn btn-outline-secondary" for="gender_1">男の子</label>
	         		<input type="checkbox" class="btn-check" id="gender_2" value="2" autocomplete="off">
					<label class="btn btn-outline-secondary ms-3" for="gender_2">女の子</label>
				</div>
         		<div class="form-check mt-3">
					<label for="" class="form-label">年齢を選んでください(複数選択可)<span class="text-danger">　※必須</span></label><br>
	         		<input type="checkbox" class="btn-check" id="age_1" value="1" autocomplete="off">
					<label class="btn btn-outline-secondary" for="age_1">～1歳未満</label>
	         		<input type="checkbox" class="btn-check" id="age_2" value="2" autocomplete="off">
					<label class="btn btn-outline-secondary ms-3" for="age_2">1歳～3歳未満</label>
	         		<input type="checkbox" class="btn-check" id="age_3" value="3" autocomplete="off">
					<label class="btn btn-outline-secondary ms-3" for="age_3">3歳～</label>
				</div>
				<p style="text-align:center; margin-bottom:0;">
					<button type="submit" class="btn btn-success btn-lg mt-4">まっちんぐ</button>
				</p>
			</form>
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