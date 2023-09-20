<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UsersInfoDto" %>
<%@ page import="model.MyPageDto" %>
<%@ page import="java.io.FileOutputStream" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-geWF76RCwLtnZ8qwWowPQNguL3RmwHVBC9FhGdlKrxdiJJigb/j/68SIy3Te4Bkz" crossorigin="anonymous"></script>
    <title>マイページ | pepeohana</title>
</head>

<!--sessionから取得する内容
        userName,Id
    Dtoから取得する内容
        ownerId,catName,kind,birth,weight,image,comment,c_date,catId,
        messageId,message,m_date,senderId,reciverId
-->
<%
    // セッションを取得
	UsersInfoDto userInfoOnSession = (UsersInfoDto)session.getAttribute("LOGIN_INFO");  
	int id = userInfoOnSession.getID();
	String userId = userInfoOnSession.getUserId();
	String userName = userInfoOnSession.getUserName();
%>
<%  // Dtoを取得
	List<MyPageDto> list = (List<MyPageDto>)request.getAttribute("MYPAGE");
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

    <!-- ヒーロー画像 -->
    <img src="images/hero_mypage.png"  alt="猫画像" style="max-height:600px; width: 100%; object-fit: cover;">

    <!-- ページごとのコンテンツ -->
    

    <!-- ユーザー情報テーブル -->
    <div class="container">
        <div class="h4 pb-2 mt-4 mb-4" style="border-bottom:solid 0.5px; border-color: #523F24;">
            ユーザー情報
        </div>
        <table class="table">
            <tr>
                <th><small>ユーザーID</small></th>
                <td><%= userId %></td>
            </tr>
            <tr>
                <th style="width:20%;"><small>ユーザー名</small></th>
                <td style="width:80%;"><%= userName %></td>
            </tr>
        </table>
        <div style="text-align: center;">
            <a href="<%= request.getContextPath() %>/EditUser">
            	<button class="btn btn-sm" style=" width:120px; background-color:#E87B4C; color:#ffffff;"
                	onclick="">編集する
            	</button>
            </a>
        </div>
    </div>

    <!-- ねこ情報テーブル -->
    
    <div class="container">
        <div class="h4 pb-2 mt-4 mb-4" style="border-bottom:solid 0.5px; border-color: #523F24;">
            公開中ねこ情報
        </div>
        <div class="text-end mb-4">
	        <a href="<%= request.getContextPath() %>/RegistCat">
	        	<button class="btn" style=" width:180px; background-color:#E87B4C; color:#ffffff;"
	            	onclick="">ねこ情報を登録する
	        	</button>
	        </a>
        </div>

        <!-- カードのコンテンツ -->
		<div class="row mt-4">
		
		<!-- ネコ情報のループ -->
		<% int oldCatId = 0; %>	
		<% boolean catsFound = false; %>
		<% if (!list.isEmpty()) { %>
		<% 		for (int i = 0; i < list.size(); i++) {
		   			MyPageDto dto = list.get(i);
		    	if (dto.getCatId() != 0) { // ねこ情報をチェック
		        	int newCatId = dto.getCatId();
		        	if (newCatId != oldCatId) {
		            	catsFound = true;
		%>
		<!-- 画像保存用 -->
        <%
		        	String webContentPath = getServletContext().getRealPath("/images");
			   		String imageFileName = webContentPath + "/img_" + dto.getCatId() + ".jpg";
			   		System.out.println(imageFileName);
			   		FileOutputStream outputStream = new FileOutputStream(imageFileName);
			   		outputStream.write(dto.getImage());
			   		outputStream.close();
   		 %>					
        	<div class="col-sm-4 col-md-3 mb-2">
                <img src="<%=request.getContextPath()%>/images/img_<%=dto.getCatId()%>.jpg" alt="<%=dto.getCatName()%>画像" class="rounded" style="height:180px; width: 100%; object-fit: cover;">
            </div>
            <div class="col-sm-8 col-md-9">
                <table class="table">
                    <tr>
                        <th><small>名前</small></th>
                        <td ><%= dto.getCatName() %></td>
                        <th><small>登録日</small></th>
                        <td><%= new SimpleDateFormat("yyyy年MM月dd日").format(dto.getRegDate()) %></td>
                    </tr>
                    <tr>
                        <th style="width:18%;"><small>性別</small></th>
                        <td style="width:24%;">
                        	<% if(dto.getGender() == 1) {%>
                        	男の子
                        	<% } else { %>
                        	女の子
                        	<% } %>
                        </td>
                        <th><small>体重</small></th>
                        <td><%= dto.getWeight() %>kg</td>
                    </tr>
                    <tr>
                        <th><small>誕生日</small></th>
                        <td><%= new SimpleDateFormat("yyyy年MM月dd日").format(dto.getBirth()) %><br>
                        	(<%= dto.getAge() %>)</td>
                        <th style="width:18%;"><small>描種</small></th>
                        <td style="width:40%;"><%= dto.getKind() %></td>
                    </tr>
                    <tr>
                        <th><small>コメント</small></th>
                        <td colspan="3"><%= dto.getComment() %></td>
                    </tr>
                </table>
            </div>
            <div class="mb-4" style="text-align: center;">
            <a href="<%= request.getContextPath() %>/ExeEditCat?ID=<%= dto.getCatId() %>">
            	<button class="btn btn-sm" style=" width:120px; background-color:#E87B4C; color:#ffffff;"
                	onclick="">編集・削除する
            	</button>
            </a>
       		</div>
		
		<%          	oldCatId = dto.getCatId();
		        	}
		    	} 
			} 
		}%>
		<% if (!catsFound) { %>
		    <p class="text-center">募集中のねこ情報はありません</p>
		<% } %>
		
		<!-- ネコテーブルループここまで -->
       	</div>
        
    </div><!-- ネコ情報テーブルここまで -->
    
    
    <!-- メッセージテーブル -->
    <div class="container">

        <div class="h4 pb-2 mt-4 mb-4" style="border-bottom:solid 0.5px; border-color: #523F24;">
            メッセージ一覧
        </div>

        <p class="h5 mt-4 mb-2">受信メッセージ</p>
        <div class="accordion accordion-flush" id="reciever">
        
	        <!-- 受信メッセージループ -->
	        <% boolean receivedMessagesExist = false; %>
       		<% if (!list.isEmpty()) { %>
			<% 		for (int i = 0; i < list.size(); i++) {
	    				MyPageDto dto = list.get(i);
	    				String messageType = dto.getMessageType();
	    				if ("r".equals(messageType)) { // Check if messageType is "r"
	        				receivedMessagesExist = true;
			%>

            <div class="accordion-item">
                <!-- メッセージヘッダー -->
                <h2 class="accordion-header">
                    <button class="p-2 accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#reciever<%= i %>" aria-expanded="false" aria-controls="reciever1">
                        <p style="line-height: 150%; margin:0;">
                            <small><%= dto.getSentDate() %></small><br>
                            対象ねこ&nbsp;<span class="badge rounded-pill text-bg-secondary"><%= dto.getTargetCatName() %></span>&ensp;
                            送信ユーザー&nbsp;<span class="badge rounded-pill text-bg-secondary"><%= dto.getTargetUserName() %></span>
                        </p>
                    </button>
                </h2>
                <!-- メッセージ内容コンテナ -->
                <div id="reciever<%= i %>" class="accordion-collapse collapse" data-bs-parent="#reciever">
                    <div class="accordion-body p-3">
                        	<%= dto.getMessage() %>
                        <!-- 返信ボタン -->
                        <div class="text-end mt-2">
                            <a href="<%=request.getContextPath()%>/Message?CATID=<%=dto.getTargetCatId() %>&RECIEVERID=<%=dto.getTargetUserId() %>&RECIEVERNAME=<%=dto.getTargetUserName() %>&CATNAME=<%=dto.getTargetCatName() %>">
	                            <button type="submit" class="btn btn-sm" style="background-color:#E87B4C; color:#ffffff;"
	                                onclick="">返信する
	                            </button>
	                        </a>
                        </div>
                    </div>
                </div>
            </div><!-- 受信メッセージループここまで -->
            
			<%		}
			 	}
       		}
			
			 if (!receivedMessagesExist) { %>
			    <p class="text-center">受信メッセージはありません</p>
			    
			<% } %>

        </div><!-- 受信テーブルここまで -->
        
        <p class="h5 mt-5 mb-2">送信メッセージ</p>
        <div class="accordion accordion-flush" id="sender">

          	<!-- 送信メッセージループ -->
	        <% boolean sendMessagesExist = false; %>
       		<% if (!list.isEmpty()) { %>
			<% 		for (int i = 0; i < list.size(); i++) {
	    				MyPageDto dto = list.get(i);
	    				String messageType = dto.getMessageType();
	    				if ("s".equals(messageType)) { // Check if messageType is "r"
	        				sendMessagesExist = true;
			%>
            <div class="accordion-item">
                <!-- メッセージヘッダー -->
                <h2 class="accordion-header">
                    <button class="p-2 accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sender<%= i %>" aria-expanded="false" aria-controls="sender1">
                        <p style="line-height: 150%; margin:0;">
                            <small><%= dto.getSentDate() %></small><br>
                            対象ねこ&nbsp;<span class="badge rounded-pill text-bg-secondary"><%= dto.getTargetCatName() %></span>&ensp;
                            送信ユーザー&nbsp;<span class="badge rounded-pill text-bg-secondary"><%= dto.getTargetUserName() %></span>
                        </p>
                    </button>
                </h2>
                <!-- メッセージ内容コンテナ -->
                <div id="sender<%= i %>" class="accordion-collapse collapse" data-bs-parent="#sender">
                    <div class="accordion-body p-3">
                        <%= dto.getMessage() %>
                    </div>
                </div>
            </div>
            
   			<%		}
				 }
			}
			
			 if (!sendMessagesExist) { %>
			    <p class="text-center">送信メッセージはありません</p>
			    
			<% } %><!-- 送信メッセージループここまで -->

        </div><!-- 送信テーブルここまで -->

    </div><!-- メッセージテーブルここまで -->

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