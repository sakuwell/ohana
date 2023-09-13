package model;

import java.sql.Date;
import java.sql.Timestamp;

public class MyPageDto {
	//----------------------------------------------------------------
			//フィールド
			//----------------------------------------------------------------
			private int			ownerId;    	//オーナーユーザーID
			private int			catId;      	//ネコID
			private String		catName;    	//ネコの名前
			private String		kind;			//ネコの種類
			private Date		birth;			//ネコの誕生日
			private String 		age;       		//ネコの年齢
			private int			gender;			//ネコの性別
			private float		weight;			//ネコの体重
			private byte[]		image;			//ネコの写真
			private String 		comment;		//飼い主のコメント
			private Timestamp 	regDate;		//登録日
			private String 		message;		//メッセージ
			private Timestamp 	sentDate;		//送信日
			private String 		messageType;	//送信・受信タイプ
			private int 		targetUserId;	//対象ユーザーID
			private String 		targetUserName;	//対象ユーザー名
			private int 		targetCatId;	//対象ネコID
			private String 		targetCatName;	//対象ネコ名
			


			//----------------------------------------------------------------
			//getter/setter
			//----------------------------------------------------------------

			public int getOwnerId() { return ownerId; }
			public void setOwnerId(int ownerId) { this.ownerId = ownerId; }
			
			public int getCatId() { return catId; }
			public void setCatId(int catId) { this.catId = catId; }

			public String getCatName() { return catName; }
			public void setCatName(String catName) { this.catName = catName; }
			
			public String getKind() { return kind; }
			public void setKind(String kind) { this.kind = kind; }
			
			public Date getBirth() { return birth; }
			public void setBirth(Date birth) { this.birth = birth; }

			public String getAge() { return age; }
			public void setAge(String age) { this.age = age; }
			
			public int getGender() { return gender; }
			public void setGender(int gender) { this.gender = gender; }
			
			public float getWeight() { return weight; }
			public void setWeight(float weight) { this.weight = weight; }
			
			public byte[] getImage() { return image; }
			public void setImage(byte[] image) { this.image = image; }
			
			public String getComment() { return comment; }
			public void setComment(String comment) { this.comment = comment; }
			
			public Timestamp getRegDate() { return regDate; }
			public void setRegDate(Timestamp regDate) { this.regDate = regDate; }
		
			public String getMessage() { return message; }
			public void setMessage(String message) { this.message = message; }

			public Timestamp getSentDate() { return sentDate; }
			public void setSentDate(Timestamp sentDate) { this.sentDate = sentDate; }
			
			public String getMessageType() { return messageType; }
			public void setMessageType(String messageType) { this.messageType = messageType; }
			
			public int getTargetUserId() { return targetUserId; }
			public void setTargetUserId(int targetUserId ) { this.targetUserId = targetUserId; }

			public String getTargetUserName() { return targetUserName; }
			public void setTargetUserName(String targetUserName ) { this.targetUserName = targetUserName; }
			
			public int getTargetCatId() { return targetCatId; }
			public void setTargetCatId(int targetCatId ) { this.targetCatId = targetCatId; }
			
			public String getTargetCatName() { return targetCatName; }
			public void setTargetCatName(String targetCatName ) { this.targetCatName = targetCatName; }

}