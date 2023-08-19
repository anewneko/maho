package bot.discord.maho.bookkeeping.discord.Entity;

import java.sql.Timestamp;

import bot.discord.maho.bookkeeping.core.pojo.CorePojo;

public class Bookkeeping extends CorePojo{
	
	
	private static final long serialVersionUID = -9077990332142388724L;
	private Integer BookkeepingId;
	private Timestamp CreateTime;
	private String  Item;
	private Integer  Price;
	
	
	public Integer getBookkeepingId() {
		return BookkeepingId;
	}
	public void setBookkeepingId(Integer bookkeepingId) {
		BookkeepingId = bookkeepingId;
	}
	public Timestamp getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(Timestamp createTime) {
		CreateTime = createTime;
	}
	public String getItem() {
		return Item;
	}
	public void setItem(String item) {
		Item = item;
	}
	public Integer getPrice() {
		return Price;
	}
	public void setPrice(Integer price) {
		Price = price;
	}
}
