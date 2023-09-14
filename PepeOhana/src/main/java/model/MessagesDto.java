package model;

import java.sql.Timestamp;
public class MessagesDto {
	//----------------------------------------------------------------
	//フィールド
	//----------------------------------------------------------------
	private int senderId;         //送り主ID
	private int catId;       //ネコID
	private int recieverId;       //受け取り主ID
	private String message;       //メッセージ文
	private Timestamp sendDate;       //メッセージ文


	//----------------------------------------------------------------
	//getter/setter
	//----------------------------------------------------------------

	//getter/setter（対象フィールド：senderId）
	public int getSenderId() { return senderId; }
	public void setSenderId(int senderId) { this.senderId = senderId; }

	//getter/setter（対象フィールド：catId）
	public int getCatId() { return catId; }
	public void setCatId(int catId) { this.catId = catId; }

	//getter/setter（対象フィールド：recieverId）
	public int getRecieverId() { return recieverId; }
	public void setRecieverId(int recieverId) { this.recieverId = recieverId; }
	
	//getter/setter（対象フィールド：message）
	public String getMessage() { return message; }
	public void setMessage(String message) { this.message = message; }
	
	//getter/setter（対象フィールド：sendDate）
	public Timestamp getSendDate() { return sendDate; }
	public void setSendDate(Timestamp sendDate) { this.sendDate = sendDate; }
}
