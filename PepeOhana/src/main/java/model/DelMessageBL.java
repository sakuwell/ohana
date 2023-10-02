package model;

/**----------------------------------------------------------------------*
 *■executeDeleteMessageメソッド
 *概要　：対象のメッセージデータを削除する
 *引数　：対象のメッセージデータ（MessagesDto型）
 *戻り値：DB操作成功フラグ（true:成功/false:失敗）
 *----------------------------------------------------------------------**/
public class DelMessageBL {
	public boolean executeDeleteMessage(MessagesDto dto) {
		boolean successDelete=false;
		MessagesDao dao = new MessagesDao();
		successDelete=dao.doDeleteMessage(dto.getMessageId());
		
		return successDelete;
	}
}
