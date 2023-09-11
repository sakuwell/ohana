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
    <title>マイページ | pepeohana</title>
</head>

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

    <!-- ログイン済ヘッダー -->
    <nav class="navbar navbar-expand-lg navbar-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="index.jsp">
                <img src="images/pepe_logo.png" alt="ページロゴ" width="auto" height="60">
            </a>
            <div class="btn-group">
                <button type="button" class="btn custom-btn dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                    userName
                </button>
                <ul class="dropdown-menu dropdown-menu-end">
                    <li><a class="dropdown-item" href="<%=request.getContextPath()%>/Mypage">マイページ</a></li>
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
                <th style="width:20%;"><small>ユーザー名</small></th>
                <td style="width:80%;">pepe</td>
            </tr>
            <tr>
                <th><small>ユーザーID</small></th>
                <td>pepe@pepe.com</td>
            </tr>
        </table>
        <div style="text-align: center;">
            <button type="submit" class="btn btn-sm" style=" width:120px; background-color:#E87B4C; color:#ffffff;"
                onclick="">編集する
            </button>
        </div>
    </div>

    <!-- ねこ情報テーブル -->
    <div class="container">
        <div class="h4 pb-2 mt-4 mb-4" style="border-bottom:solid 0.5px; border-color: #523F24;">
            公開中ねこ情報
        </div>

        <!-- カードのコンテンツ -->
        <div class="row mt-4">
            <div class="col-sm-4 col-md-3 mb-2">
                <img src="images/cat_1.jpg" alt="" class="rounded" style="height:170px; width: 100%; object-fit: cover;">
            </div>
            <div class="col-sm-8 col-md-9">
                <table class="table">
                    <tr>
                        <th><small>名前</small></th>
                        <td colspan="2">pepe</td>
                        <td style="text-align: right;">
                            <button type="submit" class="btn btn-sm" style=" width:120px; background-color:#E87B4C; color:#ffffff;"
                                onclick="">編集/削除する
                            </button>
                        </td>
                    </tr>
                    <tr>
                        <th style="width:18%;"><small>性別</small></th>
                        <td style="width:24%;">男の子</td>
                        <th style="width:18%;"><small>描種</small></th>
                        <td style="width:40%;">アメリカンショートヘアー</td>
                    </tr>
                    <tr>
                        <th><small>誕生日</small></th>
                        <td>2020/09/09<br>
                        	(3歳2ヵ月)</td>
                        <th><small>体重</small></th>
                        <td>1.5kg</td>
                    </tr>
                    <tr>
                        <th><small>コメント</small></th>
                        <td colspan="3">ここにコメントが入ります。ここにコメントが入ります。ここにコメントが入ります。</td>
                    </tr>
                </table>
            </div>
        </div><!-- ネコテーブルループここまで -->
        
    </div><!-- ネコ情報テーブルここまで -->
    
    <!-- メッセージテーブル -->
    <div class="container">

        <!-- まずDTOからメッセージ情報を全て取得する
            受信と送信を区分しそれぞれ配列に代入
            recieveMessages[]
            sendMesseges[]
            -->

        <div class="h4 pb-2 mt-4 mb-4" style="border-bottom:solid 0.5px; border-color: #523F24;">
            メッセージ一覧
        </div>

        <p class="mt-4 mb-2">受信メッセージ</p>
        <div class="accordion accordion-flush" id="reciever">

            <!-- 受信メッセージ1件分　ここをループで回す -->
            <div class="accordion-item">
                <!-- メッセージヘッダー -->
                <h2 class="accordion-header">
                    <button class="p-2 accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#reciever1" aria-expanded="false" aria-controls="reciever1">
                        <p style="line-height: 150%; margin:0;">
                            <small>2023/09/11</small><br>
                            対象ねこ&nbsp;<span class="badge rounded-pill text-bg-secondary">pepe</span>&ensp;
                            送信ユーザー&nbsp;<span class="badge rounded-pill text-bg-secondary">kato</span>
                        </p>
                    </button>
                </h2>
                <!-- メッセージ内容コンテナ -->
                <div id="reciever1" class="accordion-collapse collapse" data-bs-parent="#reciever">
                    <div class="accordion-body p-3">
                        メッセージの内容がここに表示されます。
                        <!-- 返信ボタン -->
                        <div class="text-end mt-2">
                            <button type="submit" class="btn btn-sm" style="background-color:#E87B4C; color:#ffffff;"
                                onclick="">返信する
                            </button>
                        </div>
                    </div>
                </div>
            </div><!-- 受信メッセージループここまで -->

            <!-- 受信メッセージ1件分　ここをループで回す -->
            <div class="accordion-item">
                <!-- メッセージヘッダー -->
                <h2 class="accordion-header">
                    <button class="p-2 accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#reciever2" aria-expanded="false" aria-controls="reciever2">
                        <p style="line-height: 150%; margin:0;">
                            <small>2023/09/11</small><br>
                            対象ねこ&nbsp;<span class="badge rounded-pill text-bg-secondary">pepe</span>&ensp;
                            送信ユーザー&nbsp;<span class="badge rounded-pill text-bg-secondary">kato</span>
                        </p>
                    </button>
                </h2>
                <!-- メッセージ内容コンテナ -->
                <div id="reciever2" class="accordion-collapse collapse" data-bs-parent="#reciever">
                    <div class="accordion-body p-3">
                        メッセージの内容がここに表示されます。
                        <!-- 返信ボタン -->
                        <div class="text-end mt-2">
                            <button type="submit" class="btn btn-sm" style="background-color:#E87B4C; color:#ffffff;"
                                onclick="">返信する
                            </button>
                        </div>
                    </div>
                </div>
            </div><!-- 受信メッセージループここまで -->

        </div><!-- 受信テーブルここまで -->
        
        <p class="mt-4 mb-2">送信メッセージ</p>
        <div class="accordion accordion-flush" id="sender">

            <!-- 送信メッセージ1件分　ここをループで回す -->
            <div class="accordion-item">
                <!-- メッセージヘッダー -->
                <h2 class="accordion-header">
                    <button class="p-2 accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sender1" aria-expanded="false" aria-controls="sender1">
                        <p style="line-height: 150%; margin:0;">
                            <small>2023/09/11</small><br>
                            対象ねこ&nbsp;<span class="badge rounded-pill text-bg-secondary">pepe</span>&ensp;
                            送信先ユーザー&nbsp;<span class="badge rounded-pill text-bg-secondary">kato</span>
                        </p>
                    </button>
                </h2>
                <!-- メッセージ内容コンテナ -->
                <div id="sender1" class="accordion-collapse collapse" data-bs-parent="#sender">
                    <div class="accordion-body p-3">
                        メッセージの内容がここに表示されます。
                    </div>
                </div>
            </div><!-- 送信メッセージループここまで -->

            <!-- 送信メッセージ1件分　ここをループで回す -->
            <div class="accordion-item">
                <!-- メッセージヘッダー -->
                <h2 class="accordion-header">
                    <button class="p-2 accordion-button collapsed" type="button" data-bs-toggle="collapse" data-bs-target="#sender2" aria-expanded="false" aria-controls="sender2">
                        <p style="line-height: 150%; margin:0;">
                            <small>2023/09/11</small><br>
                            対象ねこ&nbsp;<span class="badge rounded-pill text-bg-secondary">pepe</span>&ensp;
                            送信先ユーザー&nbsp;<span class="badge rounded-pill text-bg-secondary">kato</span>
                        </p>
                    </button>
                </h2>
                <!-- メッセージ内容コンテナ -->
                <div id="sender2" class="accordion-collapse collapse" data-bs-parent="#sender">
                    <div class="accordion-body p-3">
                        メッセージの内容がここに表示されます。
                    </div>
                </div>
            </div><!-- 送信メッセージループここまで -->

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