package model;

import java.sql.Timestamp;

/**----------------------------------------------------------------------*
 *Filename:MessagesDto.java
 *
 *Description:
 *	このクラスは、データベースの「messages」テーブルに対応した
 *	データ受け渡し用のクラスです。
 *	
 *	
 *Author:加藤
 *Creation Date:2023-09-13
 *
 *Copyright © 2023 KEG Sakura All rights reserved.
 *----------------------------------------------------------------------**/

public class MessagesDto {
	//----------------------------------------------------------------
	//フィールド
	//----------------------------------------------------------------
	private int senderId;         //送り主ID
	private int catId;       //ネコID
	private int recieverId;       //受け取り主ID
	private String message;       //メッセージ文
	private Timestamp send_Date;       //メッセージ文
	private int messageId;			//メッセージID

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
	public Timestamp getSend_Date() { return send_Date; }
	public void setSend_Date(Timestamp send_Date) { this.send_Date = send_Date; }

	//getter/setter（対象フィールド：senderId）
	public int getMessageId() { return messageId; }
	public void setMessageId(int messageId) { this.messageId = messageId; }
}
