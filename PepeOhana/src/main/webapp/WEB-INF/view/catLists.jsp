<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UsersInfoDto" %>
<%@ page import="model.CatsInfoDto" %>
<%@ page import="java.io.FileOutputStream" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <title>ねこまっち検索結果 | pepeohana</title>
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
	<% if (isLoggedIn) { %>
    <!-- ログイン済ヘッダー -->
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.jsp">
                <img src="images/pepe_logo.png" alt="ページロゴ" width="auto" height="60">
            </a>
            <div class="btn-group">
                <button type="button" class="btn custom-btn dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                    <%=replaceEscapeChar(userName)%>
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
    <img class="img-fluid mb-4" src="images/hero_match.png"  alt="index画像" style="width:100%; object-fit: cover;">


    <!-- ここから下　ページごとの内容 -->
    <div class="container">
        <div class="h3 pb-2 mt-3 mb-4 text-center">
            ねこまっち検索結果
        </div>
        <div class="row">
           <!-- カードのコンテンツ -->
           <% List<CatsInfoDto> list = (List<CatsInfoDto>)request.getAttribute("list");
			  for (int i = 0; i < list.size(); i++) {
			CatsInfoDto dto = list.get(i); %>
           <div class="col-6 col-md-4 col-lg-3">
                <div class="card mb-3">
                <%String webContentPath = getServletContext().getRealPath("/images");
		             		  String imageFileName = webContentPath + "/img_" + dto.getCatId() + ".jpg";
		             		  FileOutputStream outputStream = new FileOutputStream(imageFileName);
		             		  outputStream.write(dto.getImage());
		             		  outputStream.close();%>
                	<img src="<%=request.getContextPath()%>/images/img_<%=dto.getCatId()%>.jpg" style="height:180px; width: 100%; object-fit: cover;" class="card-img-top" alt="猫画像">
                    <div class="card-body">
                        <h5 class="card-title text-center border-bottom pb-2"><%=replaceEscapeChar(dto.getCatName()) %><small> ちゃん</small></h5>
                        <% if(Integer.parseInt(dto.getKind()) == 1){ %><p class="card-text text-right">描種 : スコティッシュ・フォールド</p><% } %>
                        <% if(Integer.parseInt(dto.getKind()) == 2){ %><p class="card-text text-right">描種 : マンチカン</p><% } %>
                        <% if(Integer.parseInt(dto.getKind()) == 3){ %><p class="card-text text-right">描種 : アメリカンショートヘアー</p><% } %>
                        <% if(Integer.parseInt(dto.getKind()) == 4){ %><p class="card-text text-right">描種 : ノルウェージャン・フォレスト・キャット</p><% } %>
                        <% if(Integer.parseInt(dto.getKind()) == 5){ %><p class="card-text text-right">描種 : ブリティッシュ・ショートヘア</p><% } %>
                        <% if(Integer.parseInt(dto.getKind()) == 6){ %><p class="card-text text-right">描種 : 混血種</p><% } %>
                        <% if(Integer.parseInt(dto.getKind()) == 7){ %><p class="card-text text-right">描種 : その他</p><% } %>
                        <% if(Integer.parseInt(dto.getKind()) == 8){ %><p class="card-text text-right">描種 : 不明</p><% } %>
                        <p class="card-text text-right">年齢 : <%=dto.getAge() %></p>
                        <%int g = dto.getGender();
                        if (g == 1){   %>
                        <p class="card-text text-right">性別 : 男の子</p>
                        <%}else{ %>
                        <p class="card-text text-right">性別 : 女の子</p>
                        <%}; %>
                        
                        <div class="d-flex justify-content-center">
                            <a href="<%=request.getContextPath()%>/ExeShowCatInfo?CATID=<%= dto.getCatId()%>" class="stretched-link"></a>
                        </div>
                    </div>
                </div>
            </div>
            <%}; %>
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