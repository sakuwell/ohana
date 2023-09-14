<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UsersInfoDto" %>
<%@ page import="model.CatsInfoDto" %>
<%@ page import="java.io.FileOutputStream" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <title>ねこまっち詳細ページ | pepeohana</title>
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
<script>
	function validateForm() {
		if(isLoggedIn){
			return true;
		}else{
			alert("ログインしてください");
			return false
		}
</script>

<!--sessionから取得する内容
        userName,Id
    Dtoから取得する内容
        ownerId,catName,kind,birth,weight,image,comment,c_date,catId,
        messageId,message,m_date,senderId,reciverId
-->


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
                      <%=userName%>
                </button>
                <ul class="dropdown-menu dropdown-menu-end">
                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/Mypage">マイページ</a></li>
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
              <li><a class="dropdown-item" href="jsp/Login.jsp">ログイン</a></li>
              <li><a class="dropdown-item" href="jsp/registUser.jsp">新規ユーザー登録</a></li>
            </ul>
          </div>
        </div>
    </nav>
    <!-- 未ログインヘッダーここまで -->
    <% } %>

    <!-- ヒーロー画像 -->
    <img src="images/hero_match.png"  alt="猫画像" style="width: 100%; object-fit: cover;">

    <!-- ページごとのコンテンツ -->


    <!-- ねこ情報テーブル -->
    <% CatsInfoDto ShowCatInfo = (CatsInfoDto)request.getAttribute("showCatInfo"); %>
    
    <div class="container">
        <div class="h3 pb-2 mt-3 mb-4 text-center">
            <%=ShowCatInfo.getCatName() %>
        </div>

        <!-- カードのコンテンツ -->
        <div class="row mt-4">
            <div class="col-sm-4 col-md-3 mb-2">
            <%String webContentPath = getServletContext().getRealPath("/img");
		             		  String imageFileName = webContentPath + "/cat_" + ShowCatInfo.getCatId() + ".jpg";
		             		  FileOutputStream outputStream = new FileOutputStream(imageFileName);
		             		  outputStream.write(ShowCatInfo.getImage());
		             		  outputStream.close();%>
                <img src="<%=request.getContextPath()%>/img/cat_<%=ShowCatInfo.getCatId()%>.jpg" alt="" class="rounded" style="height:170px; width: 100%; object-fit: cover;">
            </div>
            <div class="col-sm-8 col-md-9">
                <table class="table">
                    <tr>
                        <th><small>オーナーユーザー</small></th>
                        <td><%=ShowCatInfo.getUserName() %></td>
                        <th><small>登録日</small></th>
                        <td><%=new SimpleDateFormat("yyyy年MM月dd日").format(ShowCatInfo.getUp_Date()) %></td>

                    </tr>
                    <tr>
                        <th style="width:30%;"><small>性別</small></th>
                        <%int g = ShowCatInfo.getGender();
                        if (g == 1){   %>
                        <td style="width:20%;">男の子</td>
                        <%}else{ %>
                        <td style="width:20%;">女の子</td>
                        <%}; %>
                        <th style="width:20%;"><small>描種</small></th>
                        <td style="width:30%;"><%=ShowCatInfo.getKind() %></td>
                    </tr>
                    <tr>
                        <th><small>誕生日</small></th>
                        <td><%=new SimpleDateFormat("yyyy年MM月dd日").format(ShowCatInfo.getBirth())
                        %><br>
                        	(<%=ShowCatInfo.getAge() %>)</td>
                        <th><small>体重</small></th>
                        <td><%=ShowCatInfo.getWeight() %>kg</td>
                    </tr>
                    <tr>
                        <th><small>コメント</small></th>
                        <td colspan="3"><%=ShowCatInfo.getComment() %></td>
                    </tr>
                </table>
            </div>
            <% if (isLoggedIn) { %>
            <div class="text-center">
	            <a href="<%=request.getContextPath()%>/Message?CATID=<%=ShowCatInfo.getCatId() %>&RECIEVERID=<%=ShowCatInfo.getUserId() %>&RECIEVERNAME=<%=ShowCatInfo.getUserName() %>&CATNAME=<%=ShowCatInfo.getCatName() %>" class="btn" style="width:200px; background-color:#E87B4C; color:#ffffff;">
	            	メッセージを送る
	            </a>
            </div>
            <% } else { %>
            <div class="text-center">
	            <a href="<%=request.getContextPath()%>/Message" class="btn" style="width:200px; background-color:#E87B4C; color:#ffffff;">
	            	メッセージを送る
	            </a>
	        </div>
	        <div class="text-center mt-4">
	            <a class="icon-link icon-link-hover" href="<%=request.getContextPath()%>/Message">
            		メッセージを送る場合はログインしてください
        		</a>
            </div>
            <%} %>
        </div><!-- ネコテーブルループここまで -->
        
    </div><!-- ネコ情報テーブルここまで -->
    

    <!-- フッター -->
    <!--  <div class="text-center mt-4">
    
        <a class="icon-link icon-link-hover" href="#">
            ページトップへ
        </a>
    </div>-->
    <img src="images/footer_cat.png" alt=""  class="img-fluid" style="width:100%;">
    <footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-1">
        <div class="col-md-4 d-flex align-items-center ms-3">
          <span class="mb-3 mb-md-0 text-body-secondary">© 2023 Company, Inc</span>
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