package model;

import java.sql.Date;
import java.sql.Timestamp;

public class CatsInfoDto {
	//----------------------------------------------------------------
			//フィールド
			//----------------------------------------------------------------
			private int		catId;         //ネコID
			private int	userId;       //飼い主のID
			private String	catName;       //ネコの名前
			private String	kind;		//ネコの種類
			private Date	birth;		//ネコの誕生日
			private String 	age;       //ネコの年齢
			private int		gender;		//ネコの性別
			private Float		weight;		//ネコの体重
			private byte[]	image;	//ネコの写真
			private String 	comment;	//飼い主のコメント
			private Timestamp 	update;	//投稿日
			private String	userName;	//飼い主の名前


			//----------------------------------------------------------------
			//getter/setter
			//----------------------------------------------------------------

			//getter/setter（対象フィールド：catId）
			public int getCatId() { return catId; }
			public void setCatId(int catId) { this.catId = catId; }
			
			//getter/setter（対象フィールド：userId）
			public int getUserId() { return userId; }
			public void setUserId(int userId) { this.userId = userId; }

			//getter/setter（対象フィールド：catName）
			public String getCatName() { return catName; }
			public void setCatName(String catName) { this.catName = catName; }
		
			
			//getter/setter（対象フィールド：kind）
			public String getKind() { return kind; }
			public void setKind(String kind) { this.kind = kind; }
			
			//getter/setter (対象フィールド:birth)
			public Date getBirth() { return birth; }
			public void setBirth(Date birth) { this.birth = birth; }

			//getter/setter（対象フィールド：age）
			public String getAge() { return age; }
			public void setAge(String age) { this.age = age; }
			
			//getter/setter（対象フィールド：gender）
			public int getGender() { return gender; }
			public void setGender(int gender) { this.gender = gender; }
			
			//getter/setter（対象フィールド：weight）
			public Float getWeight() { return weight; }
			public void setWeight(Float weight) { this.weight = weight; }
			
			//getter/setter（対象フィールド：image）
			public byte[] getImage() { return image; }
			public void setImage(byte[] image) { this.image = image; }
			
			//getter/setter（対象フィールド：comment）
			public String getComment() { return comment; }
			public void setComment(String comment) { this.comment = comment; }
			
			public Timestamp getUpDate() { return update; }
			public void setUpDate(Timestamp update) { this.update = update; }
			
			//getter/setter（対象フィールド：userName）
			public String getUserName() { return userName; }
			public void setUserName(String userName) { this.userName = userName; }
			
		

}
