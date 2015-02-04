package mypack;

import java.nio.ByteBuffer;

public class Test {

	static final byte PREFIX = 0x02;

	private Test() {
	} 

public static class Alertresponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x47;


	private short number;

	private Alertresponse() {
	}

	public short getNumber() {
		return this.number;
	}

	public Alertresponse setNumber(short data) {
		this.number = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt16(builder, number);
	}

	@Override
	protected Alertresponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.number = Short.fromBuffer(buf);

		return this;
	}

	@Override
	protected Alertresponse  readMessage(java.nio.ByteBuffer buf) {
		return newBuilder().fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static Alertresponse newBuilder() {
		return new Alertresponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("Alertresponse{");
		strBuf.append("\n\tnumber:").append(number);
		strBuf.append("\n}");

		return strBuf.toString();
	}

	@Override
	public byte SUFFIX() {
		// TODO Auto-generated method stub
		return 0;
	}
}

public static class SendTokenToServen extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x48;


	private String token;

	private SendTokenToServen() {
	}

	public String getToken() {
		return this.token;
	}

	public SendTokenToServen setToken(String data) {
		this.token = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeString(builder, , token);
	}

	@Override
	protected SendTokenToServen fromByteBuffer(java.nio.ByteBuffer buf) {
		this.token = String.fromBuffer(buf);

		return this;
	}

	@Override
	protected SendTokenToServen  readMessage(java.nio.ByteBuffer buf) {
		return SendTokenToServen.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static SendTokenToServen newBuilder() {
		return new SendTokenToServen();
	}

	public static SendTokenToServen fromBuffer(java.nio.ByteBuffer buf) {
		SendTokenToServen data = new SendTokenToServen();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("SendTokenToServen{");
		strBuf.append("\n\ttoken:").append(token);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class Goods extends org.itas.buffer.AbstractMessage{


	private String rid;
	private int coin;
	private int money;

	private Goods() {
	}

	public String getRid() {
		return this.rid;
	}

	public Goods setRid(String data) {
		this.rid = data;
		return this;
	}

	public int getCoin() {
		return this.coin;
	}

	public Goods setCoin(int data) {
		this.coin = data;
		return this;
	}

	public int getMoney() {
		return this.money;
	}

	public Goods setMoney(int data) {
		this.money = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeString(builder, , rid);
		writeInt(builder, , coin);
		writeInt(builder, , money);
	}

	@Override
	protected Goods fromByteBuffer(java.nio.ByteBuffer buf) {
		this.rid = String.fromBuffer(buf);
		this.coin = Int.fromBuffer(buf);
		this.money = Int.fromBuffer(buf);

		return this;
	}

	@Override
	protected Goods  readMessage(java.nio.ByteBuffer buf) {
		return Goods.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static Goods newBuilder() {
		return new Goods();
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("Goods{");
		strBuf.append("\n\trid:").append(rid);
		strBuf.append("\n\tcoin:").append(coin);
		strBuf.append("\n\tmoney:").append(money);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class MyCard extends org.itas.buffer.AbstractMessage{


	private String cardID;
	private byte type;
	private short count;
	private byte isNew;

	private MyCard() {
	}

	public String getCardID() {
		return this.cardID;
	}

	public MyCard setCardID(String data) {
		this.cardID = data;
		return this;
	}

	public byte getType() {
		return this.type;
	}

	public MyCard setType(byte data) {
		this.type = data;
		return this;
	}

	public short getCount() {
		return this.count;
	}

	public MyCard setCount(short data) {
		this.count = data;
		return this;
	}

	public byte getIsNew() {
		return this.isNew;
	}

	public MyCard setIsNew(byte data) {
		this.isNew = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeString(builder, , cardID);
		writeInt8(builder, , type);
		writeInt16(builder, , count);
		writeInt8(builder, , isNew);
	}

	@Override
	protected MyCard fromByteBuffer(java.nio.ByteBuffer buf) {
		this.cardID = String.fromBuffer(buf);
		this.type = Byte.fromBuffer(buf);
		this.count = Short.fromBuffer(buf);
		this.isNew = Byte.fromBuffer(buf);

		return this;
	}

	@Override
	protected MyCard  readMessage(java.nio.ByteBuffer buf) {
		return MyCard.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static MyCard newBuilder() {
		return new MyCard();
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("MyCard{");
		strBuf.append("\n\tcardID:").append(cardID);
		strBuf.append("\n\ttype:").append(type);
		strBuf.append("\n\tcount:").append(count);
		strBuf.append("\n\tisNew:").append(isNew);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class CardGroup extends org.itas.buffer.AbstractMessage{


	private long groupID;
	private String groupName;
	private byte usable;

	private CardGroup() {
	}

	public long getGroupID() {
		return this.groupID;
	}

	public CardGroup setGroupID(long data) {
		this.groupID = data;
		return this;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public CardGroup setGroupName(String data) {
		this.groupName = data;
		return this;
	}

	public byte getUsable() {
		return this.usable;
	}

	public CardGroup setUsable(byte data) {
		this.usable = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt64(builder, , groupID);
		writeString(builder, , groupName);
		writeInt8(builder, , usable);
	}

	@Override
	protected CardGroup fromByteBuffer(java.nio.ByteBuffer buf) {
		this.groupID = Long.fromBuffer(buf);
		this.groupName = String.fromBuffer(buf);
		this.usable = Byte.fromBuffer(buf);

		return this;
	}

	@Override
	protected CardGroup  readMessage(java.nio.ByteBuffer buf) {
		return CardGroup.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static CardGroup newBuilder() {
		return new CardGroup();
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("CardGroup{");
		strBuf.append("\n\tgroupID:").append(groupID);
		strBuf.append("\n\tgroupName:").append(groupName);
		strBuf.append("\n\tusable:").append(usable);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class PlayerProperty extends org.itas.buffer.AbstractMessage{


	private byte type;
	private int value;

	private PlayerProperty() {
	}

	public byte getType() {
		return this.type;
	}

	public PlayerProperty setType(byte data) {
		this.type = data;
		return this;
	}

	public int getValue() {
		return this.value;
	}

	public PlayerProperty setValue(int data) {
		this.value = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , type);
		writeInt(builder, , value);
	}

	@Override
	protected PlayerProperty fromByteBuffer(java.nio.ByteBuffer buf) {
		this.type = Byte.fromBuffer(buf);
		this.value = Int.fromBuffer(buf);

		return this;
	}

	@Override
	protected PlayerProperty  readMessage(java.nio.ByteBuffer buf) {
		return PlayerProperty.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static PlayerProperty newBuilder() {
		return new PlayerProperty();
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("PlayerProperty{");
		strBuf.append("\n\ttype:").append(type);
		strBuf.append("\n\tvalue:").append(value);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class PlayerInfoResult extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x49;


	private int playId;
	private String name;
	private java.util.List<PlayerProperty> propertys;

	private PlayerInfoResult() {
	}

	public int getPlayId() {
		return this.playId;
	}

	public PlayerInfoResult setPlayId(int data) {
		this.playId = data;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public PlayerInfoResult setName(String data) {
		this.name = data;
		return this;
	}

	public java.util.List<PlayerProperty> getPropertys() {
		return this.propertys;
	}

	public PlayerInfoResult setPropertys(java.util.List<PlayerProperty> data) {
		this.propertys = data;
		return this;
	}

	public PlayerInfoResult addPropertys(PlayerProperty data) {
		if (propertys == null) {
			this.propertys = new java.util.LinkedList<>();
		}
		this.propertys.add(data);
		return this;
	}

	public PlayerInfoResult addAllPropertys(java.util.List<PlayerProperty> datas) {
		if (propertys == null) {
			this.propertys = new java.util.LinkedList<>();
		}
		this.propertys.addAll(datas);
		return this;
	}

	public PlayerInfoResult setPropertys(int index, java.util.List<PlayerProperty> data) {
		this.propertys.set(index, data);
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt(builder, , playId);
		writeString(builder, , name);
		writeArray(builder, propertys);
	}

	@Override
	protected PlayerInfoResult fromByteBuffer(java.nio.ByteBuffer buf) {
		this.playId = Int.fromBuffer(buf);
		this.name = String.fromBuffer(buf);
		this.propertys = readArray(buf, PlayerProperty.class

		return this;
	}

	@Override
	protected PlayerInfoResult  readMessage(java.nio.ByteBuffer buf) {
		return PlayerInfoResult.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static PlayerInfoResult newBuilder() {
		return new PlayerInfoResult();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("PlayerInfoResult{");
		strBuf.append("\n\tplayId:").append(playId);
		strBuf.append("\n\tname:").append(name);
		if (propertys  != null) {
			strBuf.append("\npropertys:");
			for (PlayerProperty data : propertys) {
				strBuf.append(data).append(",\n");
			}
		}
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class RewardItem extends org.itas.buffer.AbstractMessage{


	private String itemRid;
	private byte type;
	private byte count;

	private RewardItem() {
	}

	public String getItemRid() {
		return this.itemRid;
	}

	public RewardItem setItemRid(String data) {
		this.itemRid = data;
		return this;
	}

	public byte getType() {
		return this.type;
	}

	public RewardItem setType(byte data) {
		this.type = data;
		return this;
	}

	public byte getCount() {
		return this.count;
	}

	public RewardItem setCount(byte data) {
		this.count = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeString(builder, , itemRid);
		writeInt8(builder, , type);
		writeInt8(builder, , count);
	}

	@Override
	protected RewardItem fromByteBuffer(java.nio.ByteBuffer buf) {
		this.itemRid = String.fromBuffer(buf);
		this.type = Byte.fromBuffer(buf);
		this.count = Byte.fromBuffer(buf);

		return this;
	}

	@Override
	protected RewardItem  readMessage(java.nio.ByteBuffer buf) {
		return RewardItem.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static RewardItem newBuilder() {
		return new RewardItem();
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("RewardItem{");
		strBuf.append("\n\titemRid:").append(itemRid);
		strBuf.append("\n\ttype:").append(type);
		strBuf.append("\n\tcount:").append(count);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class HeroAndCardGroupRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x4a;



	private HeroAndCardGroupRequest() {
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
	}

	@Override
	protected HeroAndCardGroupRequest fromByteBuffer(java.nio.ByteBuffer buf) {

		return this;
	}

	@Override
	protected HeroAndCardGroupRequest  readMessage(java.nio.ByteBuffer buf) {
		return HeroAndCardGroupRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static HeroAndCardGroupRequest newBuilder() {
		return new HeroAndCardGroupRequest();
	}

	public static HeroAndCardGroupRequest fromBuffer(java.nio.ByteBuffer buf) {
		HeroAndCardGroupRequest data = new HeroAndCardGroupRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("HeroAndCardGroupRequest{");
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class HeroAndCardGroupResult extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x4b;


	private String heroId;
	private long groupId;
	private java.util.List<String> heroIDList;
	private java.util.List<CardGroup> groupList;

	private HeroAndCardGroupResult() {
	}

	public String getHeroId() {
		return this.heroId;
	}

	public HeroAndCardGroupResult setHeroId(String data) {
		this.heroId = data;
		return this;
	}

	public long getGroupId() {
		return this.groupId;
	}

	public HeroAndCardGroupResult setGroupId(long data) {
		this.groupId = data;
		return this;
	}

	public java.util.List<String> getHeroIDList() {
		return this.heroIDList;
	}

	public HeroAndCardGroupResult setHeroIDList(java.util.List<String> data) {
		this.heroIDList = data;
		return this;
	}

	public HeroAndCardGroupResult addHeroIDList(String data) {
		if (heroIDList == null) {
			this.heroIDList = new java.util.LinkedList<>();
		}
		this.heroIDList.add(data);
		return this;
	}

	public HeroAndCardGroupResult addAllHeroIDList(java.util.List<String> datas) {
		if (heroIDList == null) {
			this.heroIDList = new java.util.LinkedList<>();
		}
		this.heroIDList.addAll(datas);
		return this;
	}

	public HeroAndCardGroupResult setHeroIDList(int index, java.util.List<String> data) {
		this.heroIDList.set(index, data);
		return this;
	}

	public java.util.List<CardGroup> getGroupList() {
		return this.groupList;
	}

	public HeroAndCardGroupResult setGroupList(java.util.List<CardGroup> data) {
		this.groupList = data;
		return this;
	}

	public HeroAndCardGroupResult addGroupList(CardGroup data) {
		if (groupList == null) {
			this.groupList = new java.util.LinkedList<>();
		}
		this.groupList.add(data);
		return this;
	}

	public HeroAndCardGroupResult addAllGroupList(java.util.List<CardGroup> datas) {
		if (groupList == null) {
			this.groupList = new java.util.LinkedList<>();
		}
		this.groupList.addAll(datas);
		return this;
	}

	public HeroAndCardGroupResult setGroupList(int index, java.util.List<CardGroup> data) {
		this.groupList.set(index, data);
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeString(builder, , heroId);
		writeInt64(builder, , groupId);
		writeArray(builder, heroIDList);
		writeArray(builder, groupList);
	}

	@Override
	protected HeroAndCardGroupResult fromByteBuffer(java.nio.ByteBuffer buf) {
		this.heroId = String.fromBuffer(buf);
		this.groupId = Long.fromBuffer(buf);
		this.heroIDList = readArray(buf, String.class
		this.groupList = readArray(buf, CardGroup.class

		return this;
	}

	@Override
	protected HeroAndCardGroupResult  readMessage(java.nio.ByteBuffer buf) {
		return HeroAndCardGroupResult.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static HeroAndCardGroupResult newBuilder() {
		return new HeroAndCardGroupResult();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("HeroAndCardGroupResult{");
		strBuf.append("\n\theroId:").append(heroId);
		strBuf.append("\n\tgroupId:").append(groupId);
		if (heroIDList  != null) {
			strBuf.append("\nheroIDList:");
			for (String data : heroIDList) {
				strBuf.append(data).append(",\n");
			}
		}
		if (groupList  != null) {
			strBuf.append("\ngroupList:");
			for (CardGroup data : groupList) {
				strBuf.append(data).append(",\n");
			}
		}
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class StartBattleRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x4c;


	private long groupID;
	private String heroID;
	private String historyRid;
	private byte matchType;

	private StartBattleRequest() {
	}

	public long getGroupID() {
		return this.groupID;
	}

	public StartBattleRequest setGroupID(long data) {
		this.groupID = data;
		return this;
	}

	public String getHeroID() {
		return this.heroID;
	}

	public StartBattleRequest setHeroID(String data) {
		this.heroID = data;
		return this;
	}

	public String getHistoryRid() {
		return this.historyRid;
	}

	public StartBattleRequest setHistoryRid(String data) {
		this.historyRid = data;
		return this;
	}

	public byte getMatchType() {
		return this.matchType;
	}

	public StartBattleRequest setMatchType(byte data) {
		this.matchType = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt64(builder, , groupID);
		writeString(builder, , heroID);
		writeString(builder, , historyRid);
		writeInt8(builder, , matchType);
	}

	@Override
	protected StartBattleRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.groupID = Long.fromBuffer(buf);
		this.heroID = String.fromBuffer(buf);
		this.historyRid = String.fromBuffer(buf);
		this.matchType = Byte.fromBuffer(buf);

		return this;
	}

	@Override
	protected StartBattleRequest  readMessage(java.nio.ByteBuffer buf) {
		return StartBattleRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static StartBattleRequest newBuilder() {
		return new StartBattleRequest();
	}

	public static StartBattleRequest fromBuffer(java.nio.ByteBuffer buf) {
		StartBattleRequest data = new StartBattleRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("StartBattleRequest{");
		strBuf.append("\n\tgroupID:").append(groupID);
		strBuf.append("\n\theroID:").append(heroID);
		strBuf.append("\n\thistoryRid:").append(historyRid);
		strBuf.append("\n\tmatchType:").append(matchType);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class SendBattleServenToken extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x4d;


	private int port;
	private String ip;

	private SendBattleServenToken() {
	}

	public int getPort() {
		return this.port;
	}

	public SendBattleServenToken setPort(int data) {
		this.port = data;
		return this;
	}

	public String getIp() {
		return this.ip;
	}

	public SendBattleServenToken setIp(String data) {
		this.ip = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt(builder, , port);
		writeString(builder, , ip);
	}

	@Override
	protected SendBattleServenToken fromByteBuffer(java.nio.ByteBuffer buf) {
		this.port = Int.fromBuffer(buf);
		this.ip = String.fromBuffer(buf);

		return this;
	}

	@Override
	protected SendBattleServenToken  readMessage(java.nio.ByteBuffer buf) {
		return SendBattleServenToken.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static SendBattleServenToken newBuilder() {
		return new SendBattleServenToken();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("SendBattleServenToken{");
		strBuf.append("\n\tport:").append(port);
		strBuf.append("\n\tip:").append(ip);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class CancelBattleRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x4e;



	private CancelBattleRequest() {
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
	}

	@Override
	protected CancelBattleRequest fromByteBuffer(java.nio.ByteBuffer buf) {

		return this;
	}

	@Override
	protected CancelBattleRequest  readMessage(java.nio.ByteBuffer buf) {
		return CancelBattleRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static CancelBattleRequest newBuilder() {
		return new CancelBattleRequest();
	}

	public static CancelBattleRequest fromBuffer(java.nio.ByteBuffer buf) {
		CancelBattleRequest data = new CancelBattleRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("CancelBattleRequest{");
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class CancelBattleResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x4f;


	private byte result;

	private CancelBattleResponse() {
	}

	public byte getResult() {
		return this.result;
	}

	public CancelBattleResponse setResult(byte data) {
		this.result = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , result);
	}

	@Override
	protected CancelBattleResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.result = Byte.fromBuffer(buf);

		return this;
	}

	@Override
	protected CancelBattleResponse  readMessage(java.nio.ByteBuffer buf) {
		return CancelBattleResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static CancelBattleResponse newBuilder() {
		return new CancelBattleResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("CancelBattleResponse{");
		strBuf.append("\n\tresult:").append(result);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class MyCardListRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x50;



	private MyCardListRequest() {
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
	}

	@Override
	protected MyCardListRequest fromByteBuffer(java.nio.ByteBuffer buf) {

		return this;
	}

	@Override
	protected MyCardListRequest  readMessage(java.nio.ByteBuffer buf) {
		return MyCardListRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static MyCardListRequest newBuilder() {
		return new MyCardListRequest();
	}

	public static MyCardListRequest fromBuffer(java.nio.ByteBuffer buf) {
		MyCardListRequest data = new MyCardListRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("MyCardListRequest{");
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class MyCardListResult extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x51;


	private java.util.List<MyCard> cardList;
	private java.util.List<CardGroup> groupList;

	private MyCardListResult() {
	}

	public java.util.List<MyCard> getCardList() {
		return this.cardList;
	}

	public MyCardListResult setCardList(java.util.List<MyCard> data) {
		this.cardList = data;
		return this;
	}

	public MyCardListResult addCardList(MyCard data) {
		if (cardList == null) {
			this.cardList = new java.util.LinkedList<>();
		}
		this.cardList.add(data);
		return this;
	}

	public MyCardListResult addAllCardList(java.util.List<MyCard> datas) {
		if (cardList == null) {
			this.cardList = new java.util.LinkedList<>();
		}
		this.cardList.addAll(datas);
		return this;
	}

	public MyCardListResult setCardList(int index, java.util.List<MyCard> data) {
		this.cardList.set(index, data);
		return this;
	}

	public java.util.List<CardGroup> getGroupList() {
		return this.groupList;
	}

	public MyCardListResult setGroupList(java.util.List<CardGroup> data) {
		this.groupList = data;
		return this;
	}

	public MyCardListResult addGroupList(CardGroup data) {
		if (groupList == null) {
			this.groupList = new java.util.LinkedList<>();
		}
		this.groupList.add(data);
		return this;
	}

	public MyCardListResult addAllGroupList(java.util.List<CardGroup> datas) {
		if (groupList == null) {
			this.groupList = new java.util.LinkedList<>();
		}
		this.groupList.addAll(datas);
		return this;
	}

	public MyCardListResult setGroupList(int index, java.util.List<CardGroup> data) {
		this.groupList.set(index, data);
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeArray(builder, cardList);
		writeArray(builder, groupList);
	}

	@Override
	protected MyCardListResult fromByteBuffer(java.nio.ByteBuffer buf) {
		this.cardList = readArray(buf, MyCard.class
		this.groupList = readArray(buf, CardGroup.class

		return this;
	}

	@Override
	protected MyCardListResult  readMessage(java.nio.ByteBuffer buf) {
		return MyCardListResult.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static MyCardListResult newBuilder() {
		return new MyCardListResult();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("MyCardListResult{");
		if (cardList  != null) {
			strBuf.append("\ncardList:");
			for (MyCard data : cardList) {
				strBuf.append(data).append(",\n");
			}
		}
		if (groupList  != null) {
			strBuf.append("\ngroupList:");
			for (CardGroup data : groupList) {
				strBuf.append(data).append(",\n");
			}
		}
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class CardGroupInfoRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x52;


	private long groupID;

	private CardGroupInfoRequest() {
	}

	public long getGroupID() {
		return this.groupID;
	}

	public CardGroupInfoRequest setGroupID(long data) {
		this.groupID = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt64(builder, , groupID);
	}

	@Override
	protected CardGroupInfoRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.groupID = Long.fromBuffer(buf);

		return this;
	}

	@Override
	protected CardGroupInfoRequest  readMessage(java.nio.ByteBuffer buf) {
		return CardGroupInfoRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static CardGroupInfoRequest newBuilder() {
		return new CardGroupInfoRequest();
	}

	public static CardGroupInfoRequest fromBuffer(java.nio.ByteBuffer buf) {
		CardGroupInfoRequest data = new CardGroupInfoRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("CardGroupInfoRequest{");
		strBuf.append("\n\tgroupID:").append(groupID);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class CardGroupInfoResult extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x53;


	private long groupID;
	private String groupName;
	private java.util.List<MyCard> cardList;

	private CardGroupInfoResult() {
	}

	public long getGroupID() {
		return this.groupID;
	}

	public CardGroupInfoResult setGroupID(long data) {
		this.groupID = data;
		return this;
	}

	public String getGroupName() {
		return this.groupName;
	}

	public CardGroupInfoResult setGroupName(String data) {
		this.groupName = data;
		return this;
	}

	public java.util.List<MyCard> getCardList() {
		return this.cardList;
	}

	public CardGroupInfoResult setCardList(java.util.List<MyCard> data) {
		this.cardList = data;
		return this;
	}

	public CardGroupInfoResult addCardList(MyCard data) {
		if (cardList == null) {
			this.cardList = new java.util.LinkedList<>();
		}
		this.cardList.add(data);
		return this;
	}

	public CardGroupInfoResult addAllCardList(java.util.List<MyCard> datas) {
		if (cardList == null) {
			this.cardList = new java.util.LinkedList<>();
		}
		this.cardList.addAll(datas);
		return this;
	}

	public CardGroupInfoResult setCardList(int index, java.util.List<MyCard> data) {
		this.cardList.set(index, data);
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt64(builder, , groupID);
		writeString(builder, , groupName);
		writeArray(builder, cardList);
	}

	@Override
	protected CardGroupInfoResult fromByteBuffer(java.nio.ByteBuffer buf) {
		this.groupID = Long.fromBuffer(buf);
		this.groupName = String.fromBuffer(buf);
		this.cardList = readArray(buf, MyCard.class

		return this;
	}

	@Override
	protected CardGroupInfoResult  readMessage(java.nio.ByteBuffer buf) {
		return CardGroupInfoResult.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static CardGroupInfoResult newBuilder() {
		return new CardGroupInfoResult();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("CardGroupInfoResult{");
		strBuf.append("\n\tgroupID:").append(groupID);
		strBuf.append("\n\tgroupName:").append(groupName);
		if (cardList  != null) {
			strBuf.append("\ncardList:");
			for (MyCard data : cardList) {
				strBuf.append(data).append(",\n");
			}
		}
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class CreateGroupRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x54;


	private String name;

	private CreateGroupRequest() {
	}

	public String getName() {
		return this.name;
	}

	public CreateGroupRequest setName(String data) {
		this.name = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeString(builder, , name);
	}

	@Override
	protected CreateGroupRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.name = String.fromBuffer(buf);

		return this;
	}

	@Override
	protected CreateGroupRequest  readMessage(java.nio.ByteBuffer buf) {
		return CreateGroupRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static CreateGroupRequest newBuilder() {
		return new CreateGroupRequest();
	}

	public static CreateGroupRequest fromBuffer(java.nio.ByteBuffer buf) {
		CreateGroupRequest data = new CreateGroupRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("CreateGroupRequest{");
		strBuf.append("\n\tname:").append(name);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class CreateGroupResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x55;


	private long groupID;
	private String name;

	private CreateGroupResponse() {
	}

	public long getGroupID() {
		return this.groupID;
	}

	public CreateGroupResponse setGroupID(long data) {
		this.groupID = data;
		return this;
	}

	public String getName() {
		return this.name;
	}

	public CreateGroupResponse setName(String data) {
		this.name = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt64(builder, , groupID);
		writeString(builder, , name);
	}

	@Override
	protected CreateGroupResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.groupID = Long.fromBuffer(buf);
		this.name = String.fromBuffer(buf);

		return this;
	}

	@Override
	protected CreateGroupResponse  readMessage(java.nio.ByteBuffer buf) {
		return CreateGroupResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static CreateGroupResponse newBuilder() {
		return new CreateGroupResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("CreateGroupResponse{");
		strBuf.append("\n\tgroupID:").append(groupID);
		strBuf.append("\n\tname:").append(name);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class ChooseGroupRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x56;


	private long groupId;

	private ChooseGroupRequest() {
	}

	public long getGroupId() {
		return this.groupId;
	}

	public ChooseGroupRequest setGroupId(long data) {
		this.groupId = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt64(builder, groupId);
	}

	@Override
	protected ChooseGroupRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.groupId = Long.fromBuffer(buf);

		return this;
	}

	@Override
	protected ChooseGroupRequest  readMessage(java.nio.ByteBuffer buf) {
		return ChooseGroupRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static ChooseGroupRequest newBuilder() {
		return new ChooseGroupRequest();
	}

	public static ChooseGroupRequest fromBuffer(java.nio.ByteBuffer buf) {
		ChooseGroupRequest data = new ChooseGroupRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("ChooseGroupRequest{");
		strBuf.append("\n\tgroupId:").append(groupId);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class DelGroupRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x57;


	private long groupId;

	private DelGroupRequest() {
	}

	public long getGroupId() {
		return this.groupId;
	}

	public DelGroupRequest setGroupId(long data) {
		this.groupId = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt64(builder, , groupId);
	}

	@Override
	protected DelGroupRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.groupId = Long.fromBuffer(buf);

		return this;
	}

	@Override
	protected DelGroupRequest  readMessage(java.nio.ByteBuffer buf) {
		return DelGroupRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static DelGroupRequest newBuilder() {
		return new DelGroupRequest();
	}

	public static DelGroupRequest fromBuffer(java.nio.ByteBuffer buf) {
		DelGroupRequest data = new DelGroupRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("DelGroupRequest{");
		strBuf.append("\n\tgroupId:").append(groupId);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class DelGroupResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x58;


	private byte result;

	private DelGroupResponse() {
	}

	public byte getResult() {
		return this.result;
	}

	public DelGroupResponse setResult(byte data) {
		this.result = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , result);
	}

	@Override
	protected DelGroupResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.result = Byte.fromBuffer(buf);

		return this;
	}

	@Override
	protected DelGroupResponse  readMessage(java.nio.ByteBuffer buf) {
		return DelGroupResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static DelGroupResponse newBuilder() {
		return new DelGroupResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("DelGroupResponse{");
		strBuf.append("\n\tresult:").append(result);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class UpGroupNameRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x59;


	private String groupName;

	private UpGroupNameRequest() {
	}

	public String getGroupName() {
		return this.groupName;
	}

	public UpGroupNameRequest setGroupName(String data) {
		this.groupName = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeString(builder, , groupName);
	}

	@Override
	protected UpGroupNameRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.groupName = String.fromBuffer(buf);

		return this;
	}

	@Override
	protected UpGroupNameRequest  readMessage(java.nio.ByteBuffer buf) {
		return UpGroupNameRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static UpGroupNameRequest newBuilder() {
		return new UpGroupNameRequest();
	}

	public static UpGroupNameRequest fromBuffer(java.nio.ByteBuffer buf) {
		UpGroupNameRequest data = new UpGroupNameRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("UpGroupNameRequest{");
		strBuf.append("\n\tgroupName:").append(groupName);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class UpGroupNameResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x5a;


	private byte result;

	private UpGroupNameResponse() {
	}

	public byte getResult() {
		return this.result;
	}

	public UpGroupNameResponse setResult(byte data) {
		this.result = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , result);
	}

	@Override
	protected UpGroupNameResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.result = Byte.fromBuffer(buf);

		return this;
	}

	@Override
	protected UpGroupNameResponse  readMessage(java.nio.ByteBuffer buf) {
		return UpGroupNameResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static UpGroupNameResponse newBuilder() {
		return new UpGroupNameResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("UpGroupNameResponse{");
		strBuf.append("\n\tresult:").append(result);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class CardChooseRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x5b;


	private MyCard choose;

	private CardChooseRequest() {
	}

	public MyCard getChoose() {
		return this.choose;
	}

	public CardChooseRequest setChoose(MyCard data) {
		this.choose = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeMessage(builder, choose);
	}

	@Override
	protected CardChooseRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.choose = readChoose(buf);

		return this;
	}

	@Override
	protected CardChooseRequest  readMessage(java.nio.ByteBuffer buf) {
		return CardChooseRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static CardChooseRequest newBuilder() {
		return new CardChooseRequest();
	}

	public static CardChooseRequest fromBuffer(java.nio.ByteBuffer buf) {
		CardChooseRequest data = new CardChooseRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("CardChooseRequest{");
		strBuf.append("\n\tchoose:").append(choose);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class CardChooseResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x5c;


	private byte result;

	private CardChooseResponse() {
	}

	public byte getResult() {
		return this.result;
	}

	public CardChooseResponse setResult(byte data) {
		this.result = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , result);
	}

	@Override
	protected CardChooseResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.result = Byte.fromBuffer(buf);

		return this;
	}

	@Override
	protected CardChooseResponse  readMessage(java.nio.ByteBuffer buf) {
		return CardChooseResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static CardChooseResponse newBuilder() {
		return new CardChooseResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("CardChooseResponse{");
		strBuf.append("\n\tresult:").append(result);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class DelCardRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x5d;


	private MyCard choose;

	private DelCardRequest() {
	}

	public MyCard getChoose() {
		return this.choose;
	}

	public DelCardRequest setChoose(MyCard data) {
		this.choose = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeMessage(builder, choose);
	}

	@Override
	protected DelCardRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.choose = readChoose(buf);

		return this;
	}

	@Override
	protected DelCardRequest  readMessage(java.nio.ByteBuffer buf) {
		return DelCardRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static DelCardRequest newBuilder() {
		return new DelCardRequest();
	}

	public static DelCardRequest fromBuffer(java.nio.ByteBuffer buf) {
		DelCardRequest data = new DelCardRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("DelCardRequest{");
		strBuf.append("\n\tchoose:").append(choose);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class DelCardResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x5e;


	private byte result;

	private DelCardResponse() {
	}

	public byte getResult() {
		return this.result;
	}

	public DelCardResponse setResult(byte data) {
		this.result = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , result);
	}

	@Override
	protected DelCardResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.result = Byte.fromBuffer(buf);

		return this;
	}

	@Override
	protected DelCardResponse  readMessage(java.nio.ByteBuffer buf) {
		return DelCardResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static DelCardResponse newBuilder() {
		return new DelCardResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("DelCardResponse{");
		strBuf.append("\n\tresult:").append(result);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class MakeCardViewRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x5f;



	private MakeCardViewRequest() {
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
	}

	@Override
	protected MakeCardViewRequest fromByteBuffer(java.nio.ByteBuffer buf) {

		return this;
	}

	@Override
	protected MakeCardViewRequest  readMessage(java.nio.ByteBuffer buf) {
		return MakeCardViewRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static MakeCardViewRequest newBuilder() {
		return new MakeCardViewRequest();
	}

	public static MakeCardViewRequest fromBuffer(java.nio.ByteBuffer buf) {
		MakeCardViewRequest data = new MakeCardViewRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("MakeCardViewRequest{");
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class MakeCardViewResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x60;


	private int essence;

	private MakeCardViewResponse() {
	}

	public int getEssence() {
		return this.essence;
	}

	public MakeCardViewResponse setEssence(int data) {
		this.essence = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt(builder, , essence);
	}

	@Override
	protected MakeCardViewResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.essence = Int.fromBuffer(buf);

		return this;
	}

	@Override
	protected MakeCardViewResponse  readMessage(java.nio.ByteBuffer buf) {
		return MakeCardViewResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static MakeCardViewResponse newBuilder() {
		return new MakeCardViewResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("MakeCardViewResponse{");
		strBuf.append("\n\tessence:").append(essence);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class MakeCardRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x61;


	private MyCard choose;

	private MakeCardRequest() {
	}

	public MyCard getChoose() {
		return this.choose;
	}

	public MakeCardRequest setChoose(MyCard data) {
		this.choose = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeMessage(builder, choose);
	}

	@Override
	protected MakeCardRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.choose = readChoose(buf);

		return this;
	}

	@Override
	protected MakeCardRequest  readMessage(java.nio.ByteBuffer buf) {
		return MakeCardRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static MakeCardRequest newBuilder() {
		return new MakeCardRequest();
	}

	public static MakeCardRequest fromBuffer(java.nio.ByteBuffer buf) {
		MakeCardRequest data = new MakeCardRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("MakeCardRequest{");
		strBuf.append("\n\tchoose:").append(choose);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class MakeCardResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x62;


	private byte result;

	private MakeCardResponse() {
	}

	public byte getResult() {
		return this.result;
	}

	public MakeCardResponse setResult(byte data) {
		this.result = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , result);
	}

	@Override
	protected MakeCardResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.result = Byte.fromBuffer(buf);

		return this;
	}

	@Override
	protected MakeCardResponse  readMessage(java.nio.ByteBuffer buf) {
		return MakeCardResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static MakeCardResponse newBuilder() {
		return new MakeCardResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("MakeCardResponse{");
		strBuf.append("\n\tresult:").append(result);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class ResolveCardRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x63;


	private MyCard choose;

	private ResolveCardRequest() {
	}

	public MyCard getChoose() {
		return this.choose;
	}

	public ResolveCardRequest setChoose(MyCard data) {
		this.choose = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeMessage(builder, choose);
	}

	@Override
	protected ResolveCardRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.choose = readChoose(buf);

		return this;
	}

	@Override
	protected ResolveCardRequest  readMessage(java.nio.ByteBuffer buf) {
		return ResolveCardRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static ResolveCardRequest newBuilder() {
		return new ResolveCardRequest();
	}

	public static ResolveCardRequest fromBuffer(java.nio.ByteBuffer buf) {
		ResolveCardRequest data = new ResolveCardRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("ResolveCardRequest{");
		strBuf.append("\n\tchoose:").append(choose);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class ResolveCardResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x64;


	private byte result;

	private ResolveCardResponse() {
	}

	public byte getResult() {
		return this.result;
	}

	public ResolveCardResponse setResult(byte data) {
		this.result = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , result);
	}

	@Override
	protected ResolveCardResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.result = Byte.fromBuffer(buf);

		return this;
	}

	@Override
	protected ResolveCardResponse  readMessage(java.nio.ByteBuffer buf) {
		return ResolveCardResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static ResolveCardResponse newBuilder() {
		return new ResolveCardResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("ResolveCardResponse{");
		strBuf.append("\n\tresult:").append(result);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class ChooseArenaCardRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x65;


	private String rid;

	private ChooseArenaCardRequest() {
	}

	public String getRid() {
		return this.rid;
	}

	public ChooseArenaCardRequest setRid(String data) {
		this.rid = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeString(builder, , rid);
	}

	@Override
	protected ChooseArenaCardRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.rid = String.fromBuffer(buf);

		return this;
	}

	@Override
	protected ChooseArenaCardRequest  readMessage(java.nio.ByteBuffer buf) {
		return ChooseArenaCardRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static ChooseArenaCardRequest newBuilder() {
		return new ChooseArenaCardRequest();
	}

	public static ChooseArenaCardRequest fromBuffer(java.nio.ByteBuffer buf) {
		ChooseArenaCardRequest data = new ChooseArenaCardRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("ChooseArenaCardRequest{");
		strBuf.append("\n\trid:").append(rid);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class ChooseArenaCardResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x66;


	private byte result;
	private java.util.List<MyCard> cards;

	private ChooseArenaCardResponse() {
	}

	public byte getResult() {
		return this.result;
	}

	public ChooseArenaCardResponse setResult(byte data) {
		this.result = data;
		return this;
	}

	public java.util.List<MyCard> getCards() {
		return this.cards;
	}

	public ChooseArenaCardResponse setCards(java.util.List<MyCard> data) {
		this.cards = data;
		return this;
	}

	public ChooseArenaCardResponse addCards(MyCard data) {
		if (cards == null) {
			this.cards = new java.util.LinkedList<>();
		}
		this.cards.add(data);
		return this;
	}

	public ChooseArenaCardResponse addAllCards(java.util.List<MyCard> datas) {
		if (cards == null) {
			this.cards = new java.util.LinkedList<>();
		}
		this.cards.addAll(datas);
		return this;
	}

	public ChooseArenaCardResponse setCards(int index, java.util.List<MyCard> data) {
		this.cards.set(index, data);
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , result);
		writeArray(builder, cards);
	}

	@Override
	protected ChooseArenaCardResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.result = Byte.fromBuffer(buf);
		this.cards = readArray(buf, MyCard.class

		return this;
	}

	@Override
	protected ChooseArenaCardResponse  readMessage(java.nio.ByteBuffer buf) {
		return ChooseArenaCardResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static ChooseArenaCardResponse newBuilder() {
		return new ChooseArenaCardResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("ChooseArenaCardResponse{");
		strBuf.append("\n\tresult:").append(result);
		if (cards  != null) {
			strBuf.append("\ncards:");
			for (MyCard data : cards) {
				strBuf.append(data).append(",\n");
			}
		}
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class ChooseHeroRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x67;


	private String rid;

	private ChooseHeroRequest() {
	}

	public String getRid() {
		return this.rid;
	}

	public ChooseHeroRequest setRid(String data) {
		this.rid = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeString(builder, , rid);
	}

	@Override
	protected ChooseHeroRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.rid = String.fromBuffer(buf);

		return this;
	}

	@Override
	protected ChooseHeroRequest  readMessage(java.nio.ByteBuffer buf) {
		return ChooseHeroRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static ChooseHeroRequest newBuilder() {
		return new ChooseHeroRequest();
	}

	public static ChooseHeroRequest fromBuffer(java.nio.ByteBuffer buf) {
		ChooseHeroRequest data = new ChooseHeroRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("ChooseHeroRequest{");
		strBuf.append("\n\trid:").append(rid);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class ChooseHeroResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x68;


	private byte result;
	private java.util.List<String> rid;

	private ChooseHeroResponse() {
	}

	public byte getResult() {
		return this.result;
	}

	public ChooseHeroResponse setResult(byte data) {
		this.result = data;
		return this;
	}

	public java.util.List<String> getRid() {
		return this.rid;
	}

	public ChooseHeroResponse setRid(java.util.List<String> data) {
		this.rid = data;
		return this;
	}

	public ChooseHeroResponse addRid(String data) {
		if (rid == null) {
			this.rid = new java.util.LinkedList<>();
		}
		this.rid.add(data);
		return this;
	}

	public ChooseHeroResponse addAllRid(java.util.List<String> datas) {
		if (rid == null) {
			this.rid = new java.util.LinkedList<>();
		}
		this.rid.addAll(datas);
		return this;
	}

	public ChooseHeroResponse setRid(int index, java.util.List<String> data) {
		this.rid.set(index, data);
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , result);
		writeArray(builder, rid);
	}

	@Override
	protected ChooseHeroResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.result = Byte.fromBuffer(buf);
		this.rid = readArray(buf, String.class

		return this;
	}

	@Override
	protected ChooseHeroResponse  readMessage(java.nio.ByteBuffer buf) {
		return ChooseHeroResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static ChooseHeroResponse newBuilder() {
		return new ChooseHeroResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("ChooseHeroResponse{");
		strBuf.append("\n\tresult:").append(result);
		if (rid  != null) {
			strBuf.append("\nrid:");
			for (String data : rid) {
				strBuf.append(data).append(",\n");
			}
		}
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class ArenaInfoRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x69;



	private ArenaInfoRequest() {
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
	}

	@Override
	protected ArenaInfoRequest fromByteBuffer(java.nio.ByteBuffer buf) {

		return this;
	}

	@Override
	protected ArenaInfoRequest  readMessage(java.nio.ByteBuffer buf) {
		return ArenaInfoRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static ArenaInfoRequest newBuilder() {
		return new ArenaInfoRequest();
	}

	public static ArenaInfoRequest fromBuffer(java.nio.ByteBuffer buf) {
		ArenaInfoRequest data = new ArenaInfoRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("ArenaInfoRequest{");
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class ArenaInfoResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x6a;


	private String heroRid;
	private java.util.List<MyCard> cards;

	private ArenaInfoResponse() {
	}

	public String getHeroRid() {
		return this.heroRid;
	}

	public ArenaInfoResponse setHeroRid(String data) {
		this.heroRid = data;
		return this;
	}

	public java.util.List<MyCard> getCards() {
		return this.cards;
	}

	public ArenaInfoResponse setCards(java.util.List<MyCard> data) {
		this.cards = data;
		return this;
	}

	public ArenaInfoResponse addCards(MyCard data) {
		if (cards == null) {
			this.cards = new java.util.LinkedList<>();
		}
		this.cards.add(data);
		return this;
	}

	public ArenaInfoResponse addAllCards(java.util.List<MyCard> datas) {
		if (cards == null) {
			this.cards = new java.util.LinkedList<>();
		}
		this.cards.addAll(datas);
		return this;
	}

	public ArenaInfoResponse setCards(int index, java.util.List<MyCard> data) {
		this.cards.set(index, data);
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeString(builder, , heroRid);
		writeArray(builder, cards);
	}

	@Override
	protected ArenaInfoResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.heroRid = String.fromBuffer(buf);
		this.cards = readArray(buf, MyCard.class

		return this;
	}

	@Override
	protected ArenaInfoResponse  readMessage(java.nio.ByteBuffer buf) {
		return ArenaInfoResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static ArenaInfoResponse newBuilder() {
		return new ArenaInfoResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("ArenaInfoResponse{");
		strBuf.append("\n\theroRid:").append(heroRid);
		if (cards  != null) {
			strBuf.append("\ncards:");
			for (MyCard data : cards) {
				strBuf.append(data).append(",\n");
			}
		}
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class RewardResult extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x6b;


	private byte type;
	private java.util.List<PlayerProperty> propertys;
	private java.util.List<RewardItem> items;

	private RewardResult() {
	}

	public byte getType() {
		return this.type;
	}

	public RewardResult setType(byte data) {
		this.type = data;
		return this;
	}

	public java.util.List<PlayerProperty> getPropertys() {
		return this.propertys;
	}

	public RewardResult setPropertys(java.util.List<PlayerProperty> data) {
		this.propertys = data;
		return this;
	}

	public RewardResult addPropertys(PlayerProperty data) {
		if (propertys == null) {
			this.propertys = new java.util.LinkedList<>();
		}
		this.propertys.add(data);
		return this;
	}

	public RewardResult addAllPropertys(java.util.List<PlayerProperty> datas) {
		if (propertys == null) {
			this.propertys = new java.util.LinkedList<>();
		}
		this.propertys.addAll(datas);
		return this;
	}

	public RewardResult setPropertys(int index, java.util.List<PlayerProperty> data) {
		this.propertys.set(index, data);
		return this;
	}

	public java.util.List<RewardItem> getItems() {
		return this.items;
	}

	public RewardResult setItems(java.util.List<RewardItem> data) {
		this.items = data;
		return this;
	}

	public RewardResult addItems(RewardItem data) {
		if (items == null) {
			this.items = new java.util.LinkedList<>();
		}
		this.items.add(data);
		return this;
	}

	public RewardResult addAllItems(java.util.List<RewardItem> datas) {
		if (items == null) {
			this.items = new java.util.LinkedList<>();
		}
		this.items.addAll(datas);
		return this;
	}

	public RewardResult setItems(int index, java.util.List<RewardItem> data) {
		this.items.set(index, data);
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , type);
		writeArray(builder, propertys);
		writeArray(builder, items);
	}

	@Override
	protected RewardResult fromByteBuffer(java.nio.ByteBuffer buf) {
		this.type = Byte.fromBuffer(buf);
		this.propertys = readArray(buf, PlayerProperty.class
		this.items = readArray(buf, RewardItem.class

		return this;
	}

	@Override
	protected RewardResult  readMessage(java.nio.ByteBuffer buf) {
		return RewardResult.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static RewardResult newBuilder() {
		return new RewardResult();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("RewardResult{");
		strBuf.append("\n\ttype:").append(type);
		if (propertys  != null) {
			strBuf.append("\npropertys:");
			for (PlayerProperty data : propertys) {
				strBuf.append(data).append(",\n");
			}
		}
		if (items  != null) {
			strBuf.append("\nitems:");
			for (RewardItem data : items) {
				strBuf.append(data).append(",\n");
			}
		}
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class CardBagInfo extends org.itas.buffer.AbstractMessage{


	private String rid;
	private int coin;
	private short money;

	private CardBagInfo() {
	}

	public String getRid() {
		return this.rid;
	}

	public CardBagInfo setRid(String data) {
		this.rid = data;
		return this;
	}

	public int getCoin() {
		return this.coin;
	}

	public CardBagInfo setCoin(int data) {
		this.coin = data;
		return this;
	}

	public short getMoney() {
		return this.money;
	}

	public CardBagInfo setMoney(short data) {
		this.money = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeString(builder, , rid);
		writeInt(builder, , coin);
		writeInt16(builder, , money);
	}

	@Override
	protected CardBagInfo fromByteBuffer(java.nio.ByteBuffer buf) {
		this.rid = String.fromBuffer(buf);
		this.coin = Int.fromBuffer(buf);
		this.money = Short.fromBuffer(buf);

		return this;
	}

	@Override
	protected CardBagInfo  readMessage(java.nio.ByteBuffer buf) {
		return CardBagInfo.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static CardBagInfo newBuilder() {
		return new CardBagInfo();
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("CardBagInfo{");
		strBuf.append("\n\trid:").append(rid);
		strBuf.append("\n\tcoin:").append(coin);
		strBuf.append("\n\tmoney:").append(money);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class OpenCardsBoxCountRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x6c;



	private OpenCardsBoxCountRequest() {
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
	}

	@Override
	protected OpenCardsBoxCountRequest fromByteBuffer(java.nio.ByteBuffer buf) {

		return this;
	}

	@Override
	protected OpenCardsBoxCountRequest  readMessage(java.nio.ByteBuffer buf) {
		return OpenCardsBoxCountRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static OpenCardsBoxCountRequest newBuilder() {
		return new OpenCardsBoxCountRequest();
	}

	public static OpenCardsBoxCountRequest fromBuffer(java.nio.ByteBuffer buf) {
		OpenCardsBoxCountRequest data = new OpenCardsBoxCountRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("OpenCardsBoxCountRequest{");
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class OpenCardsBoxCountResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x6d;


	private int count;
	private java.util.List<CardBagInfo> boxsPrice;

	private OpenCardsBoxCountResponse() {
	}

	public int getCount() {
		return this.count;
	}

	public OpenCardsBoxCountResponse setCount(int data) {
		this.count = data;
		return this;
	}

	public java.util.List<CardBagInfo> getBoxsPrice() {
		return this.boxsPrice;
	}

	public OpenCardsBoxCountResponse setBoxsPrice(java.util.List<CardBagInfo> data) {
		this.boxsPrice = data;
		return this;
	}

	public OpenCardsBoxCountResponse addBoxsPrice(CardBagInfo data) {
		if (boxsPrice == null) {
			this.boxsPrice = new java.util.LinkedList<>();
		}
		this.boxsPrice.add(data);
		return this;
	}

	public OpenCardsBoxCountResponse addAllBoxsPrice(java.util.List<CardBagInfo> datas) {
		if (boxsPrice == null) {
			this.boxsPrice = new java.util.LinkedList<>();
		}
		this.boxsPrice.addAll(datas);
		return this;
	}

	public OpenCardsBoxCountResponse setBoxsPrice(int index, java.util.List<CardBagInfo> data) {
		this.boxsPrice.set(index, data);
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt(builder, , count);
		writeArray(builder, boxsPrice);
	}

	@Override
	protected OpenCardsBoxCountResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.count = Int.fromBuffer(buf);
		this.boxsPrice = readArray(buf, CardBagInfo.class

		return this;
	}

	@Override
	protected OpenCardsBoxCountResponse  readMessage(java.nio.ByteBuffer buf) {
		return OpenCardsBoxCountResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static OpenCardsBoxCountResponse newBuilder() {
		return new OpenCardsBoxCountResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("OpenCardsBoxCountResponse{");
		strBuf.append("\n\tcount:").append(count);
		if (boxsPrice  != null) {
			strBuf.append("\nboxsPrice:");
			for (CardBagInfo data : boxsPrice) {
				strBuf.append(data).append(",\n");
			}
		}
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class OpenCardsBoxRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x6e;


	private byte type;

	private OpenCardsBoxRequest() {
	}

	public byte getType() {
		return this.type;
	}

	public OpenCardsBoxRequest setType(byte data) {
		this.type = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , type);
	}

	@Override
	protected OpenCardsBoxRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.type = Byte.fromBuffer(buf);

		return this;
	}

	@Override
	protected OpenCardsBoxRequest  readMessage(java.nio.ByteBuffer buf) {
		return OpenCardsBoxRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static OpenCardsBoxRequest newBuilder() {
		return new OpenCardsBoxRequest();
	}

	public static OpenCardsBoxRequest fromBuffer(java.nio.ByteBuffer buf) {
		OpenCardsBoxRequest data = new OpenCardsBoxRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("OpenCardsBoxRequest{");
		strBuf.append("\n\ttype:").append(type);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class OpenCardsBoxResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x6f;


	private byte type;
	private java.util.List<MyCard> cardIDs;

	private OpenCardsBoxResponse() {
	}

	public byte getType() {
		return this.type;
	}

	public OpenCardsBoxResponse setType(byte data) {
		this.type = data;
		return this;
	}

	public java.util.List<MyCard> getCardIDs() {
		return this.cardIDs;
	}

	public OpenCardsBoxResponse setCardIDs(java.util.List<MyCard> data) {
		this.cardIDs = data;
		return this;
	}

	public OpenCardsBoxResponse addCardIDs(MyCard data) {
		if (cardIDs == null) {
			this.cardIDs = new java.util.LinkedList<>();
		}
		this.cardIDs.add(data);
		return this;
	}

	public OpenCardsBoxResponse addAllCardIDs(java.util.List<MyCard> datas) {
		if (cardIDs == null) {
			this.cardIDs = new java.util.LinkedList<>();
		}
		this.cardIDs.addAll(datas);
		return this;
	}

	public OpenCardsBoxResponse setCardIDs(int index, java.util.List<MyCard> data) {
		this.cardIDs.set(index, data);
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , type);
		writeArray(builder, cardIDs);
	}

	@Override
	protected OpenCardsBoxResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.type = Byte.fromBuffer(buf);
		this.cardIDs = readArray(buf, MyCard.class

		return this;
	}

	@Override
	protected OpenCardsBoxResponse  readMessage(java.nio.ByteBuffer buf) {
		return OpenCardsBoxResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static OpenCardsBoxResponse newBuilder() {
		return new OpenCardsBoxResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("OpenCardsBoxResponse{");
		strBuf.append("\n\ttype:").append(type);
		if (cardIDs  != null) {
			strBuf.append("\ncardIDs:");
			for (MyCard data : cardIDs) {
				strBuf.append(data).append(",\n");
			}
		}
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class MyTaskObjective extends org.itas.buffer.AbstractMessage{


	private byte type;
	private String rid;
	private byte nowCount;
	private byte needCount;

	private MyTaskObjective() {
	}

	public byte getType() {
		return this.type;
	}

	public MyTaskObjective setType(byte data) {
		this.type = data;
		return this;
	}

	public String getRid() {
		return this.rid;
	}

	public MyTaskObjective setRid(String data) {
		this.rid = data;
		return this;
	}

	public byte getNowCount() {
		return this.nowCount;
	}

	public MyTaskObjective setNowCount(byte data) {
		this.nowCount = data;
		return this;
	}

	public byte getNeedCount() {
		return this.needCount;
	}

	public MyTaskObjective setNeedCount(byte data) {
		this.needCount = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , type);
		writeString(builder, , rid);
		writeInt8(builder, , nowCount);
		writeInt8(builder, , needCount);
	}

	@Override
	protected MyTaskObjective fromByteBuffer(java.nio.ByteBuffer buf) {
		this.type = Byte.fromBuffer(buf);
		this.rid = String.fromBuffer(buf);
		this.nowCount = Byte.fromBuffer(buf);
		this.needCount = Byte.fromBuffer(buf);

		return this;
	}

	@Override
	protected MyTaskObjective  readMessage(java.nio.ByteBuffer buf) {
		return MyTaskObjective.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static MyTaskObjective newBuilder() {
		return new MyTaskObjective();
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("MyTaskObjective{");
		strBuf.append("\n\ttype:").append(type);
		strBuf.append("\n\trid:").append(rid);
		strBuf.append("\n\tnowCount:").append(nowCount);
		strBuf.append("\n\tneedCount:").append(needCount);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class MyTask extends org.itas.buffer.AbstractMessage{


	private String taskId;
	private byte status;
	private String taskRid;
	private java.util.List<MyTaskObjective> objective;
	private java.util.List<PlayerProperty> properties;
	private java.util.List<RewardItem> items;

	private MyTask() {
	}

	public String getTaskId() {
		return this.taskId;
	}

	public MyTask setTaskId(String data) {
		this.taskId = data;
		return this;
	}

	public byte getStatus() {
		return this.status;
	}

	public MyTask setStatus(byte data) {
		this.status = data;
		return this;
	}

	public String getTaskRid() {
		return this.taskRid;
	}

	public MyTask setTaskRid(String data) {
		this.taskRid = data;
		return this;
	}

	public java.util.List<MyTaskObjective> getObjective() {
		return this.objective;
	}

	public MyTask setObjective(java.util.List<MyTaskObjective> data) {
		this.objective = data;
		return this;
	}

	public MyTask addObjective(MyTaskObjective data) {
		if (objective == null) {
			this.objective = new java.util.LinkedList<>();
		}
		this.objective.add(data);
		return this;
	}

	public MyTask addAllObjective(java.util.List<MyTaskObjective> datas) {
		if (objective == null) {
			this.objective = new java.util.LinkedList<>();
		}
		this.objective.addAll(datas);
		return this;
	}

	public MyTask setObjective(int index, java.util.List<MyTaskObjective> data) {
		this.objective.set(index, data);
		return this;
	}

	public java.util.List<PlayerProperty> getProperties() {
		return this.properties;
	}

	public MyTask setProperties(java.util.List<PlayerProperty> data) {
		this.properties = data;
		return this;
	}

	public MyTask addProperties(PlayerProperty data) {
		if (properties == null) {
			this.properties = new java.util.LinkedList<>();
		}
		this.properties.add(data);
		return this;
	}

	public MyTask addAllProperties(java.util.List<PlayerProperty> datas) {
		if (properties == null) {
			this.properties = new java.util.LinkedList<>();
		}
		this.properties.addAll(datas);
		return this;
	}

	public MyTask setProperties(int index, java.util.List<PlayerProperty> data) {
		this.properties.set(index, data);
		return this;
	}

	public java.util.List<RewardItem> getItems() {
		return this.items;
	}

	public MyTask setItems(java.util.List<RewardItem> data) {
		this.items = data;
		return this;
	}

	public MyTask addItems(RewardItem data) {
		if (items == null) {
			this.items = new java.util.LinkedList<>();
		}
		this.items.add(data);
		return this;
	}

	public MyTask addAllItems(java.util.List<RewardItem> datas) {
		if (items == null) {
			this.items = new java.util.LinkedList<>();
		}
		this.items.addAll(datas);
		return this;
	}

	public MyTask setItems(int index, java.util.List<RewardItem> data) {
		this.items.set(index, data);
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeString(builder, , taskId);
		writeInt8(builder, , status);
		writeString(builder, , taskRid);
		writeArray(builder, objective);
		writeArray(builder, properties);
		writeArray(builder, items);
	}

	@Override
	protected MyTask fromByteBuffer(java.nio.ByteBuffer buf) {
		this.taskId = String.fromBuffer(buf);
		this.status = Byte.fromBuffer(buf);
		this.taskRid = String.fromBuffer(buf);
		this.objective = readArray(buf, MyTaskObjective.class
		this.properties = readArray(buf, PlayerProperty.class
		this.items = readArray(buf, RewardItem.class

		return this;
	}

	@Override
	protected MyTask  readMessage(java.nio.ByteBuffer buf) {
		return MyTask.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static MyTask newBuilder() {
		return new MyTask();
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("MyTask{");
		strBuf.append("\n\ttaskId:").append(taskId);
		strBuf.append("\n\tstatus:").append(status);
		strBuf.append("\n\ttaskRid:").append(taskRid);
		if (objective  != null) {
			strBuf.append("\nobjective:");
			for (MyTaskObjective data : objective) {
				strBuf.append(data).append(",\n");
			}
		}
		if (properties  != null) {
			strBuf.append("\nproperties:");
			for (PlayerProperty data : properties) {
				strBuf.append(data).append(",\n");
			}
		}
		if (items  != null) {
			strBuf.append("\nitems:");
			for (RewardItem data : items) {
				strBuf.append(data).append(",\n");
			}
		}
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class TaskRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x70;



	private TaskRequest() {
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
	}

	@Override
	protected TaskRequest fromByteBuffer(java.nio.ByteBuffer buf) {

		return this;
	}

	@Override
	protected TaskRequest  readMessage(java.nio.ByteBuffer buf) {
		return TaskRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static TaskRequest newBuilder() {
		return new TaskRequest();
	}

	public static TaskRequest fromBuffer(java.nio.ByteBuffer buf) {
		TaskRequest data = new TaskRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("TaskRequest{");
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class TaskResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x71;


	private byte isReflushAble;
	private java.util.List<MyTask> tasks;

	private TaskResponse() {
	}

	public byte getIsReflushAble() {
		return this.isReflushAble;
	}

	public TaskResponse setIsReflushAble(byte data) {
		this.isReflushAble = data;
		return this;
	}

	public java.util.List<MyTask> getTasks() {
		return this.tasks;
	}

	public TaskResponse setTasks(java.util.List<MyTask> data) {
		this.tasks = data;
		return this;
	}

	public TaskResponse addTasks(MyTask data) {
		if (tasks == null) {
			this.tasks = new java.util.LinkedList<>();
		}
		this.tasks.add(data);
		return this;
	}

	public TaskResponse addAllTasks(java.util.List<MyTask> datas) {
		if (tasks == null) {
			this.tasks = new java.util.LinkedList<>();
		}
		this.tasks.addAll(datas);
		return this;
	}

	public TaskResponse setTasks(int index, java.util.List<MyTask> data) {
		this.tasks.set(index, data);
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , isReflushAble);
		writeArray(builder, tasks);
	}

	@Override
	protected TaskResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.isReflushAble = Byte.fromBuffer(buf);
		this.tasks = readArray(buf, MyTask.class

		return this;
	}

	@Override
	protected TaskResponse  readMessage(java.nio.ByteBuffer buf) {
		return TaskResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static TaskResponse newBuilder() {
		return new TaskResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("TaskResponse{");
		strBuf.append("\n\tisReflushAble:").append(isReflushAble);
		if (tasks  != null) {
			strBuf.append("\ntasks:");
			for (MyTask data : tasks) {
				strBuf.append(data).append(",\n");
			}
		}
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class TaskReflushRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x72;


	private String taskId;

	private TaskReflushRequest() {
	}

	public String getTaskId() {
		return this.taskId;
	}

	public TaskReflushRequest setTaskId(String data) {
		this.taskId = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeString(builder, , taskId);
	}

	@Override
	protected TaskReflushRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.taskId = String.fromBuffer(buf);

		return this;
	}

	@Override
	protected TaskReflushRequest  readMessage(java.nio.ByteBuffer buf) {
		return TaskReflushRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static TaskReflushRequest newBuilder() {
		return new TaskReflushRequest();
	}

	public static TaskReflushRequest fromBuffer(java.nio.ByteBuffer buf) {
		TaskReflushRequest data = new TaskReflushRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("TaskReflushRequest{");
		strBuf.append("\n\ttaskId:").append(taskId);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class TaskSubmitRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x73;


	private String taskId;

	private TaskSubmitRequest() {
	}

	public String getTaskId() {
		return this.taskId;
	}

	public TaskSubmitRequest setTaskId(String data) {
		this.taskId = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeString(builder, , taskId);
	}

	@Override
	protected TaskSubmitRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.taskId = String.fromBuffer(buf);

		return this;
	}

	@Override
	protected TaskSubmitRequest  readMessage(java.nio.ByteBuffer buf) {
		return TaskSubmitRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static TaskSubmitRequest newBuilder() {
		return new TaskSubmitRequest();
	}

	public static TaskSubmitRequest fromBuffer(java.nio.ByteBuffer buf) {
		TaskSubmitRequest data = new TaskSubmitRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("TaskSubmitRequest{");
		strBuf.append("\n\ttaskId:").append(taskId);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class TaskStatusChanged extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x74;


	private java.util.List<MyTask> tasks;

	private TaskStatusChanged() {
	}

	public java.util.List<MyTask> getTasks() {
		return this.tasks;
	}

	public TaskStatusChanged setTasks(java.util.List<MyTask> data) {
		this.tasks = data;
		return this;
	}

	public TaskStatusChanged addTasks(MyTask data) {
		if (tasks == null) {
			this.tasks = new java.util.LinkedList<>();
		}
		this.tasks.add(data);
		return this;
	}

	public TaskStatusChanged addAllTasks(java.util.List<MyTask> datas) {
		if (tasks == null) {
			this.tasks = new java.util.LinkedList<>();
		}
		this.tasks.addAll(datas);
		return this;
	}

	public TaskStatusChanged setTasks(int index, java.util.List<MyTask> data) {
		this.tasks.set(index, data);
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeArray(builder, tasks);
	}

	@Override
	protected TaskStatusChanged fromByteBuffer(java.nio.ByteBuffer buf) {
		this.tasks = readArray(buf, MyTask.class

		return this;
	}

	@Override
	protected TaskStatusChanged  readMessage(java.nio.ByteBuffer buf) {
		return TaskStatusChanged.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static TaskStatusChanged newBuilder() {
		return new TaskStatusChanged();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("TaskStatusChanged{");
		if (tasks  != null) {
			strBuf.append("\ntasks:");
			for (MyTask data : tasks) {
				strBuf.append(data).append(",\n");
			}
		}
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class GoodsListRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x75;


	private byte shopType;
	private short pageNum;

	private GoodsListRequest() {
	}

	public byte getShopType() {
		return this.shopType;
	}

	public GoodsListRequest setShopType(byte data) {
		this.shopType = data;
		return this;
	}

	public short getPageNum() {
		return this.pageNum;
	}

	public GoodsListRequest setPageNum(short data) {
		this.pageNum = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , shopType);
		writeInt16(builder, , pageNum);
	}

	@Override
	protected GoodsListRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.shopType = Byte.fromBuffer(buf);
		this.pageNum = Short.fromBuffer(buf);

		return this;
	}

	@Override
	protected GoodsListRequest  readMessage(java.nio.ByteBuffer buf) {
		return GoodsListRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static GoodsListRequest newBuilder() {
		return new GoodsListRequest();
	}

	public static GoodsListRequest fromBuffer(java.nio.ByteBuffer buf) {
		GoodsListRequest data = new GoodsListRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("GoodsListRequest{");
		strBuf.append("\n\tshopType:").append(shopType);
		strBuf.append("\n\tpageNum:").append(pageNum);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class GoodsListResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x76;


	private byte shopType;
	private short maxPage;
	private java.util.List<Goods> goods;

	private GoodsListResponse() {
	}

	public byte getShopType() {
		return this.shopType;
	}

	public GoodsListResponse setShopType(byte data) {
		this.shopType = data;
		return this;
	}

	public short getMaxPage() {
		return this.maxPage;
	}

	public GoodsListResponse setMaxPage(short data) {
		this.maxPage = data;
		return this;
	}

	public java.util.List<Goods> getGoods() {
		return this.goods;
	}

	public GoodsListResponse setGoods(java.util.List<Goods> data) {
		this.goods = data;
		return this;
	}

	public GoodsListResponse addGoods(Goods data) {
		if (goods == null) {
			this.goods = new java.util.LinkedList<>();
		}
		this.goods.add(data);
		return this;
	}

	public GoodsListResponse addAllGoods(java.util.List<Goods> datas) {
		if (goods == null) {
			this.goods = new java.util.LinkedList<>();
		}
		this.goods.addAll(datas);
		return this;
	}

	public GoodsListResponse setGoods(int index, java.util.List<Goods> data) {
		this.goods.set(index, data);
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , shopType);
		writeInt16(builder, , maxPage);
		writeArray(builder, goods);
	}

	@Override
	protected GoodsListResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.shopType = Byte.fromBuffer(buf);
		this.maxPage = Short.fromBuffer(buf);
		this.goods = readArray(buf, Goods.class

		return this;
	}

	@Override
	protected GoodsListResponse  readMessage(java.nio.ByteBuffer buf) {
		return GoodsListResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static GoodsListResponse newBuilder() {
		return new GoodsListResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("GoodsListResponse{");
		strBuf.append("\n\tshopType:").append(shopType);
		strBuf.append("\n\tmaxPage:").append(maxPage);
		if (goods  != null) {
			strBuf.append("\ngoods:");
			for (Goods data : goods) {
				strBuf.append(data).append(",\n");
			}
		}
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class GoodsBuyRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x77;


	private String rid;
	private byte type;

	private GoodsBuyRequest() {
	}

	public String getRid() {
		return this.rid;
	}

	public GoodsBuyRequest setRid(String data) {
		this.rid = data;
		return this;
	}

	public byte getType() {
		return this.type;
	}

	public GoodsBuyRequest setType(byte data) {
		this.type = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeString(builder, , rid);
		writeInt8(builder, , type);
	}

	@Override
	protected GoodsBuyRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.rid = String.fromBuffer(buf);
		this.type = Byte.fromBuffer(buf);

		return this;
	}

	@Override
	protected GoodsBuyRequest  readMessage(java.nio.ByteBuffer buf) {
		return GoodsBuyRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static GoodsBuyRequest newBuilder() {
		return new GoodsBuyRequest();
	}

	public static GoodsBuyRequest fromBuffer(java.nio.ByteBuffer buf) {
		GoodsBuyRequest data = new GoodsBuyRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("GoodsBuyRequest{");
		strBuf.append("\n\trid:").append(rid);
		strBuf.append("\n\ttype:").append(type);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class GoodsBuyResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x78;


	private String rid;
	private byte result;

	private GoodsBuyResponse() {
	}

	public String getRid() {
		return this.rid;
	}

	public GoodsBuyResponse setRid(String data) {
		this.rid = data;
		return this;
	}

	public byte getResult() {
		return this.result;
	}

	public GoodsBuyResponse setResult(byte data) {
		this.result = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeString(builder, , rid);
		writeInt8(builder, , result);
	}

	@Override
	protected GoodsBuyResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.rid = String.fromBuffer(buf);
		this.result = Byte.fromBuffer(buf);

		return this;
	}

	@Override
	protected GoodsBuyResponse  readMessage(java.nio.ByteBuffer buf) {
		return GoodsBuyResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static GoodsBuyResponse newBuilder() {
		return new GoodsBuyResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("GoodsBuyResponse{");
		strBuf.append("\n\trid:").append(rid);
		strBuf.append("\n\tresult:").append(result);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class HeroViewRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x79;


	private String heroId;

	private HeroViewRequest() {
	}

	public String getHeroId() {
		return this.heroId;
	}

	public HeroViewRequest setHeroId(String data) {
		this.heroId = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeString(builder, , heroId);
	}

	@Override
	protected HeroViewRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.heroId = String.fromBuffer(buf);

		return this;
	}

	@Override
	protected HeroViewRequest  readMessage(java.nio.ByteBuffer buf) {
		return HeroViewRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static HeroViewRequest newBuilder() {
		return new HeroViewRequest();
	}

	public static HeroViewRequest fromBuffer(java.nio.ByteBuffer buf) {
		HeroViewRequest data = new HeroViewRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("HeroViewRequest{");
		strBuf.append("\n\theroId:").append(heroId);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class HeroViewResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x7a;


	private String heroId;
	private java.util.List<String> ownHero;
	private java.util.List<String> initskins;

	private HeroViewResponse() {
	}

	public String getHeroId() {
		return this.heroId;
	}

	public HeroViewResponse setHeroId(String data) {
		this.heroId = data;
		return this;
	}

	public java.util.List<String> getOwnHero() {
		return this.ownHero;
	}

	public HeroViewResponse setOwnHero(java.util.List<String> data) {
		this.ownHero = data;
		return this;
	}

	public HeroViewResponse addOwnHero(String data) {
		if (ownHero == null) {
			this.ownHero = new java.util.LinkedList<>();
		}
		this.ownHero.add(data);
		return this;
	}

	public HeroViewResponse addAllOwnHero(java.util.List<String> datas) {
		if (ownHero == null) {
			this.ownHero = new java.util.LinkedList<>();
		}
		this.ownHero.addAll(datas);
		return this;
	}

	public HeroViewResponse setOwnHero(int index, java.util.List<String> data) {
		this.ownHero.set(index, data);
		return this;
	}

	public java.util.List<String> getInitskins() {
		return this.initskins;
	}

	public HeroViewResponse setInitskins(java.util.List<String> data) {
		this.initskins = data;
		return this;
	}

	public HeroViewResponse addInitskins(String data) {
		if (initskins == null) {
			this.initskins = new java.util.LinkedList<>();
		}
		this.initskins.add(data);
		return this;
	}

	public HeroViewResponse addAllInitskins(java.util.List<String> datas) {
		if (initskins == null) {
			this.initskins = new java.util.LinkedList<>();
		}
		this.initskins.addAll(datas);
		return this;
	}

	public HeroViewResponse setInitskins(int index, java.util.List<String> data) {
		this.initskins.set(index, data);
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeString(builder, , heroId);
		writeArray(builder, ownHero);
		writeArray(builder, initskins);
	}

	@Override
	protected HeroViewResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.heroId = String.fromBuffer(buf);
		this.ownHero = readArray(buf, String.class
		this.initskins = readArray(buf, String.class

		return this;
	}

	@Override
	protected HeroViewResponse  readMessage(java.nio.ByteBuffer buf) {
		return HeroViewResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static HeroViewResponse newBuilder() {
		return new HeroViewResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("HeroViewResponse{");
		strBuf.append("\n\theroId:").append(heroId);
		if (ownHero  != null) {
			strBuf.append("\nownHero:");
			for (String data : ownHero) {
				strBuf.append(data).append(",\n");
			}
		}
		if (initskins  != null) {
			strBuf.append("\ninitskins:");
			for (String data : initskins) {
				strBuf.append(data).append(",\n");
			}
		}
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class HeroSkinsRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x7b;


	private String skinId;

	private HeroSkinsRequest() {
	}

	public String getSkinId() {
		return this.skinId;
	}

	public HeroSkinsRequest setSkinId(String data) {
		this.skinId = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeString(builder, , skinId);
	}

	@Override
	protected HeroSkinsRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.skinId = String.fromBuffer(buf);

		return this;
	}

	@Override
	protected HeroSkinsRequest  readMessage(java.nio.ByteBuffer buf) {
		return HeroSkinsRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static HeroSkinsRequest newBuilder() {
		return new HeroSkinsRequest();
	}

	public static HeroSkinsRequest fromBuffer(java.nio.ByteBuffer buf) {
		HeroSkinsRequest data = new HeroSkinsRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("HeroSkinsRequest{");
		strBuf.append("\n\tskinId:").append(skinId);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class HeroSkinsResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x7c;


	private java.util.List<String> skins;

	private HeroSkinsResponse() {
	}

	public java.util.List<String> getSkins() {
		return this.skins;
	}

	public HeroSkinsResponse setSkins(java.util.List<String> data) {
		this.skins = data;
		return this;
	}

	public HeroSkinsResponse addSkins(String data) {
		if (skins == null) {
			this.skins = new java.util.LinkedList<>();
		}
		this.skins.add(data);
		return this;
	}

	public HeroSkinsResponse addAllSkins(java.util.List<String> datas) {
		if (skins == null) {
			this.skins = new java.util.LinkedList<>();
		}
		this.skins.addAll(datas);
		return this;
	}

	public HeroSkinsResponse setSkins(int index, java.util.List<String> data) {
		this.skins.set(index, data);
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeArray(builder, skins);
	}

	@Override
	protected HeroSkinsResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.skins = readArray(buf, String.class

		return this;
	}

	@Override
	protected HeroSkinsResponse  readMessage(java.nio.ByteBuffer buf) {
		return HeroSkinsResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static HeroSkinsResponse newBuilder() {
		return new HeroSkinsResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("HeroSkinsResponse{");
		if (skins  != null) {
			strBuf.append("\nskins:");
			for (String data : skins) {
				strBuf.append(data).append(",\n");
			}
		}
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class SceneViewRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x7d;


	private byte type;

	private SceneViewRequest() {
	}

	public byte getType() {
		return this.type;
	}

	public SceneViewRequest setType(byte data) {
		this.type = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , type);
	}

	@Override
	protected SceneViewRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.type = Byte.fromBuffer(buf);

		return this;
	}

	@Override
	protected SceneViewRequest  readMessage(java.nio.ByteBuffer buf) {
		return SceneViewRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static SceneViewRequest newBuilder() {
		return new SceneViewRequest();
	}

	public static SceneViewRequest fromBuffer(java.nio.ByteBuffer buf) {
		SceneViewRequest data = new SceneViewRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("SceneViewRequest{");
		strBuf.append("\n\ttype:").append(type);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class SceneViewResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x7e;


	private byte type;
	private java.util.List<String> Skins;
	private java.util.List<String> initSkins;

	private SceneViewResponse() {
	}

	public byte getType() {
		return this.type;
	}

	public SceneViewResponse setType(byte data) {
		this.type = data;
		return this;
	}

	public java.util.List<String> getSkins() {
		return this.Skins;
	}

	public SceneViewResponse setSkins(java.util.List<String> data) {
		this.Skins = data;
		return this;
	}

	public SceneViewResponse addSkins(String data) {
		if (Skins == null) {
			this.Skins = new java.util.LinkedList<>();
		}
		this.Skins.add(data);
		return this;
	}

	public SceneViewResponse addAllSkins(java.util.List<String> datas) {
		if (Skins == null) {
			this.Skins = new java.util.LinkedList<>();
		}
		this.Skins.addAll(datas);
		return this;
	}

	public SceneViewResponse setSkins(int index, java.util.List<String> data) {
		this.Skins.set(index, data);
		return this;
	}

	public java.util.List<String> getInitSkins() {
		return this.initSkins;
	}

	public SceneViewResponse setInitSkins(java.util.List<String> data) {
		this.initSkins = data;
		return this;
	}

	public SceneViewResponse addInitSkins(String data) {
		if (initSkins == null) {
			this.initSkins = new java.util.LinkedList<>();
		}
		this.initSkins.add(data);
		return this;
	}

	public SceneViewResponse addAllInitSkins(java.util.List<String> datas) {
		if (initSkins == null) {
			this.initSkins = new java.util.LinkedList<>();
		}
		this.initSkins.addAll(datas);
		return this;
	}

	public SceneViewResponse setInitSkins(int index, java.util.List<String> data) {
		this.initSkins.set(index, data);
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , type);
		writeArray(builder, Skins);
		writeArray(builder, initSkins);
	}

	@Override
	protected SceneViewResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.type = Byte.fromBuffer(buf);
		this.Skins = readArray(buf, String.class
		this.initSkins = readArray(buf, String.class

		return this;
	}

	@Override
	protected SceneViewResponse  readMessage(java.nio.ByteBuffer buf) {
		return SceneViewResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static SceneViewResponse newBuilder() {
		return new SceneViewResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("SceneViewResponse{");
		strBuf.append("\n\ttype:").append(type);
		if (Skins  != null) {
			strBuf.append("\nSkins:");
			for (String data : Skins) {
				strBuf.append(data).append(",\n");
			}
		}
		if (initSkins  != null) {
			strBuf.append("\ninitSkins:");
			for (String data : initSkins) {
				strBuf.append(data).append(",\n");
			}
		}
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class SkinChangeRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x7f;


	private String skinId;
	private byte type;

	private SkinChangeRequest() {
	}

	public String getSkinId() {
		return this.skinId;
	}

	public SkinChangeRequest setSkinId(String data) {
		this.skinId = data;
		return this;
	}

	public byte getType() {
		return this.type;
	}

	public SkinChangeRequest setType(byte data) {
		this.type = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeString(builder, , skinId);
		writeInt8(builder, , type);
	}

	@Override
	protected SkinChangeRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.skinId = String.fromBuffer(buf);
		this.type = Byte.fromBuffer(buf);

		return this;
	}

	@Override
	protected SkinChangeRequest  readMessage(java.nio.ByteBuffer buf) {
		return SkinChangeRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static SkinChangeRequest newBuilder() {
		return new SkinChangeRequest();
	}

	public static SkinChangeRequest fromBuffer(java.nio.ByteBuffer buf) {
		SkinChangeRequest data = new SkinChangeRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("SkinChangeRequest{");
		strBuf.append("\n\tskinId:").append(skinId);
		strBuf.append("\n\ttype:").append(type);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class SkinChangeResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x0;


	private byte type;

	private SkinChangeResponse() {
	}

	public byte getType() {
		return this.type;
	}

	public SkinChangeResponse setType(byte data) {
		this.type = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , type);
	}

	@Override
	protected SkinChangeResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.type = Byte.fromBuffer(buf);

		return this;
	}

	@Override
	protected SkinChangeResponse  readMessage(java.nio.ByteBuffer buf) {
		return SkinChangeResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static SkinChangeResponse newBuilder() {
		return new SkinChangeResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("SkinChangeResponse{");
		strBuf.append("\n\ttype:").append(type);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class EssenceChanged extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x0;


	private int value;

	private EssenceChanged() {
	}

	public int getValue() {
		return this.value;
	}

	public EssenceChanged setValue(int data) {
		this.value = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt(builder, , value);
	}

	@Override
	protected EssenceChanged fromByteBuffer(java.nio.ByteBuffer buf) {
		this.value = Int.fromBuffer(buf);

		return this;
	}

	@Override
	protected EssenceChanged  readMessage(java.nio.ByteBuffer buf) {
		return EssenceChanged.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static EssenceChanged newBuilder() {
		return new EssenceChanged();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("EssenceChanged{");
		strBuf.append("\n\tvalue:").append(value);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class StoryViewRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x0;



	private StoryViewRequest() {
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
	}

	@Override
	protected StoryViewRequest fromByteBuffer(java.nio.ByteBuffer buf) {

		return this;
	}

	@Override
	protected StoryViewRequest  readMessage(java.nio.ByteBuffer buf) {
		return StoryViewRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static StoryViewRequest newBuilder() {
		return new StoryViewRequest();
	}

	public static StoryViewRequest fromBuffer(java.nio.ByteBuffer buf) {
		StoryViewRequest data = new StoryViewRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("StoryViewRequest{");
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class StoryViewResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x0;


	private String storyId;

	private StoryViewResponse() {
	}

	public String getStoryId() {
		return this.storyId;
	}

	public StoryViewResponse setStoryId(String data) {
		this.storyId = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeString(builder, , storyId);
	}

	@Override
	protected StoryViewResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.storyId = String.fromBuffer(buf);

		return this;
	}

	@Override
	protected StoryViewResponse  readMessage(java.nio.ByteBuffer buf) {
		return StoryViewResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static StoryViewResponse newBuilder() {
		return new StoryViewResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("StoryViewResponse{");
		strBuf.append("\n\tstoryId:").append(storyId);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class SetNewPlayerGuideStepToServer extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x0;


	private int step;

	private SetNewPlayerGuideStepToServer() {
	}

	public int getStep() {
		return this.step;
	}

	public SetNewPlayerGuideStepToServer setStep(int data) {
		this.step = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt(builder, , step);
	}

	@Override
	protected SetNewPlayerGuideStepToServer fromByteBuffer(java.nio.ByteBuffer buf) {
		this.step = Int.fromBuffer(buf);

		return this;
	}

	@Override
	protected SetNewPlayerGuideStepToServer  readMessage(java.nio.ByteBuffer buf) {
		return SetNewPlayerGuideStepToServer.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static SetNewPlayerGuideStepToServer newBuilder() {
		return new SetNewPlayerGuideStepToServer();
	}

	public static SetNewPlayerGuideStepToServer fromBuffer(java.nio.ByteBuffer buf) {
		SetNewPlayerGuideStepToServer data = new SetNewPlayerGuideStepToServer();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("SetNewPlayerGuideStepToServer{");
		strBuf.append("\n\tstep:").append(step);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class GetNewPlayerGuideStepToServer extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x0;


	private int step;

	private GetNewPlayerGuideStepToServer() {
	}

	public int getStep() {
		return this.step;
	}

	public GetNewPlayerGuideStepToServer setStep(int data) {
		this.step = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt(builder, , step);
	}

	@Override
	protected GetNewPlayerGuideStepToServer fromByteBuffer(java.nio.ByteBuffer buf) {
		this.step = Int.fromBuffer(buf);

		return this;
	}

	@Override
	protected GetNewPlayerGuideStepToServer  readMessage(java.nio.ByteBuffer buf) {
		return GetNewPlayerGuideStepToServer.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static GetNewPlayerGuideStepToServer newBuilder() {
		return new GetNewPlayerGuideStepToServer();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("GetNewPlayerGuideStepToServer{");
		strBuf.append("\n\tstep:").append(step);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class GetMyAllSkinRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x0;


	private byte type;

	private GetMyAllSkinRequest() {
	}

	public byte getType() {
		return this.type;
	}

	public GetMyAllSkinRequest setType(byte data) {
		this.type = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , type);
	}

	@Override
	protected GetMyAllSkinRequest fromByteBuffer(java.nio.ByteBuffer buf) {
		this.type = Byte.fromBuffer(buf);

		return this;
	}

	@Override
	protected GetMyAllSkinRequest  readMessage(java.nio.ByteBuffer buf) {
		return GetMyAllSkinRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static GetMyAllSkinRequest newBuilder() {
		return new GetMyAllSkinRequest();
	}

	public static GetMyAllSkinRequest fromBuffer(java.nio.ByteBuffer buf) {
		GetMyAllSkinRequest data = new GetMyAllSkinRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("GetMyAllSkinRequest{");
		strBuf.append("\n\ttype:").append(type);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class GetMyAllSkinResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x0;


	private byte type;
	private java.util.List<String> skinIdList;

	private GetMyAllSkinResponse() {
	}

	public byte getType() {
		return this.type;
	}

	public GetMyAllSkinResponse setType(byte data) {
		this.type = data;
		return this;
	}

	public java.util.List<String> getSkinIdList() {
		return this.skinIdList;
	}

	public GetMyAllSkinResponse setSkinIdList(java.util.List<String> data) {
		this.skinIdList = data;
		return this;
	}

	public GetMyAllSkinResponse addSkinIdList(String data) {
		if (skinIdList == null) {
			this.skinIdList = new java.util.LinkedList<>();
		}
		this.skinIdList.add(data);
		return this;
	}

	public GetMyAllSkinResponse addAllSkinIdList(java.util.List<String> datas) {
		if (skinIdList == null) {
			this.skinIdList = new java.util.LinkedList<>();
		}
		this.skinIdList.addAll(datas);
		return this;
	}

	public GetMyAllSkinResponse setSkinIdList(int index, java.util.List<String> data) {
		this.skinIdList.set(index, data);
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , type);
		writeArray(builder, skinIdList);
	}

	@Override
	protected GetMyAllSkinResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.type = Byte.fromBuffer(buf);
		this.skinIdList = readArray(buf, String.class

		return this;
	}

	@Override
	protected GetMyAllSkinResponse  readMessage(java.nio.ByteBuffer buf) {
		return GetMyAllSkinResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static GetMyAllSkinResponse newBuilder() {
		return new GetMyAllSkinResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("GetMyAllSkinResponse{");
		strBuf.append("\n\ttype:").append(type);
		if (skinIdList  != null) {
			strBuf.append("\nskinIdList:");
			for (String data : skinIdList) {
				strBuf.append(data).append(",\n");
			}
		}
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class ArenaTimesRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x0;



	private ArenaTimesRequest() {
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
	}

	@Override
	protected ArenaTimesRequest fromByteBuffer(java.nio.ByteBuffer buf) {

		return this;
	}

	@Override
	protected ArenaTimesRequest  readMessage(java.nio.ByteBuffer buf) {
		return ArenaTimesRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static ArenaTimesRequest newBuilder() {
		return new ArenaTimesRequest();
	}

	public static ArenaTimesRequest fromBuffer(java.nio.ByteBuffer buf) {
		ArenaTimesRequest data = new ArenaTimesRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("ArenaTimesRequest{");
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class ArenaTimesResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x0;


	private byte isOpen;
	private byte winTimes;
	private byte maxWinTimes;
	private byte loseTimes;
	private byte maxLoseTimes;

	private ArenaTimesResponse() {
	}

	public byte getIsOpen() {
		return this.isOpen;
	}

	public ArenaTimesResponse setIsOpen(byte data) {
		this.isOpen = data;
		return this;
	}

	public byte getWinTimes() {
		return this.winTimes;
	}

	public ArenaTimesResponse setWinTimes(byte data) {
		this.winTimes = data;
		return this;
	}

	public byte getMaxWinTimes() {
		return this.maxWinTimes;
	}

	public ArenaTimesResponse setMaxWinTimes(byte data) {
		this.maxWinTimes = data;
		return this;
	}

	public byte getLoseTimes() {
		return this.loseTimes;
	}

	public ArenaTimesResponse setLoseTimes(byte data) {
		this.loseTimes = data;
		return this;
	}

	public byte getMaxLoseTimes() {
		return this.maxLoseTimes;
	}

	public ArenaTimesResponse setMaxLoseTimes(byte data) {
		this.maxLoseTimes = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , isOpen);
		writeInt8(builder, , winTimes);
		writeInt8(builder, , maxWinTimes);
		writeInt8(builder, , loseTimes);
		writeInt8(builder, , maxLoseTimes);
	}

	@Override
	protected ArenaTimesResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.isOpen = Byte.fromBuffer(buf);
		this.winTimes = Byte.fromBuffer(buf);
		this.maxWinTimes = Byte.fromBuffer(buf);
		this.loseTimes = Byte.fromBuffer(buf);
		this.maxLoseTimes = Byte.fromBuffer(buf);

		return this;
	}

	@Override
	protected ArenaTimesResponse  readMessage(java.nio.ByteBuffer buf) {
		return ArenaTimesResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static ArenaTimesResponse newBuilder() {
		return new ArenaTimesResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("ArenaTimesResponse{");
		strBuf.append("\n\tisOpen:").append(isOpen);
		strBuf.append("\n\twinTimes:").append(winTimes);
		strBuf.append("\n\tmaxWinTimes:").append(maxWinTimes);
		strBuf.append("\n\tloseTimes:").append(loseTimes);
		strBuf.append("\n\tmaxLoseTimes:").append(maxLoseTimes);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class ArenaRewardRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x0;



	private ArenaRewardRequest() {
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
	}

	@Override
	protected ArenaRewardRequest fromByteBuffer(java.nio.ByteBuffer buf) {

		return this;
	}

	@Override
	protected ArenaRewardRequest  readMessage(java.nio.ByteBuffer buf) {
		return ArenaRewardRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static ArenaRewardRequest newBuilder() {
		return new ArenaRewardRequest();
	}

	public static ArenaRewardRequest fromBuffer(java.nio.ByteBuffer buf) {
		ArenaRewardRequest data = new ArenaRewardRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("ArenaRewardRequest{");
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class TitleDataRequest extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.RecivedAble{

	static final byte SUFFIX = 0x0;



	private TitleDataRequest() {
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
	}

	@Override
	protected TitleDataRequest fromByteBuffer(java.nio.ByteBuffer buf) {

		return this;
	}

	@Override
	protected TitleDataRequest  readMessage(java.nio.ByteBuffer buf) {
		return TitleDataRequest.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static TitleDataRequest newBuilder() {
		return new TitleDataRequest();
	}

	public static TitleDataRequest fromBuffer(java.nio.ByteBuffer buf) {
		TitleDataRequest data = new TitleDataRequest();
		data.readMessage(buf);
		return data;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("TitleDataRequest{");
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class TitleDataResponse extends org.itas.buffer.AbstractMessage implements   org.itas.buffer.SendAble{

	static final byte SUFFIX = 0x0;


	private byte rank;
	private byte score;

	private TitleDataResponse() {
	}

	public byte getRank() {
		return this.rank;
	}

	public TitleDataResponse setRank(byte data) {
		this.rank = data;
		return this;
	}

	public byte getScore() {
		return this.score;
	}

	public TitleDataResponse setScore(byte data) {
		this.score = data;
		return this;
	}

	@Override
	public void toByteBuffer(org.itas.buffer.BubferBuilder builder) {
		writeInt8(builder, , rank);
		writeInt8(builder, , score);
	}

	@Override
	protected TitleDataResponse fromByteBuffer(java.nio.ByteBuffer buf) {
		this.rank = Byte.fromBuffer(buf);
		this.score = Byte.fromBuffer(buf);

		return this;
	}

	@Override
	protected TitleDataResponse  readMessage(java.nio.ByteBuffer buf) {
		return TitleDataResponse.fromBuffer(buf);
	}

	@Override
	protected void  writeMessage(org.itas.buffer.BubferBuilder builder) {
		toByteBuffer(builder);
	}

	public static TitleDataResponse newBuilder() {
		return new TitleDataResponse();
	}

	@Override
	public java.nio.ByteBuffer toBuffer() {
		org.itas.buffer.BubferBuilder builder = new org.itas.buffer.BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		toByteBuffer(builder);

		java.nio.ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();
		strBuf.append("TitleDataResponse{");
		strBuf.append("\n\trank:").append(rank);
		strBuf.append("\n\tscore:").append(score);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

}