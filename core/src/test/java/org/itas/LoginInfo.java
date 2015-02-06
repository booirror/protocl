package org.itas;

import org.itas.TComm.*;
import java.nio.ByteBuffer;
import org.itas.buffer.NetRecivedAble;
import org.itas.buffer.BubferBuilder;
import java.util.ArrayList;
import org.itas.buffer.AbstractMessage;
import java.util.List;
import org.itas.buffer.NetSendAble;

public class LoginInfo {

	static final byte PREFIX = 0x01;

	private LoginInfo() {
	} 

public static class Wife extends AbstractMessage{

	private String nickname;
	private Chirld chirld;
	private List<Chirld> chirlds;

	private Wife() {
	}

	public String getNickname() {
		return this.nickname;
	}

	public Wife setNickname(String data) {
		this.nickname = data;
		return this;
	}

	public Chirld getChirld() {
		return this.chirld;
	}

	public Wife setChirld(Chirld data) {
		this.chirld = data;
		return this;
	}

	public List<Chirld> getChirlds() {
		return this.chirlds;
	}

	public Wife setChirlds(List<Chirld> data) {
		this.chirlds = data;
		return this;
	}

	public Wife addChirlds(Chirld data) {
		if (this.chirlds == null) {
			this.chirlds = new ArrayList<>();
		}
		this.chirlds.add(data);
		return this;
	}

	public Wife addAllChirlds(List<Chirld> datas) {
		if (this.chirlds == null) {
			this.chirlds = new ArrayList<>();
		}
		this.chirlds.addAll(datas);
		return this;
	}

	public Wife setChirlds(int index, Chirld data) {
		this.chirlds.set(index, data);
		return this;
	}

	@Override
	public Wife readMsg(ByteBuffer buf) {
		this.nickname = readString(buf);
		this.chirld = Chirld.newBuilder().readMsg(buf);
		this.chirlds = readArray(Chirld.class, buf);
		return this;
	}

	@Override
	public void writeMsg(BubferBuilder builder) {
		writeString(builder, this.nickname);
		this.chirld.writeMsg(builder);
		writeArray(builder, this.chirlds);
	}

	public static Wife newBuilder() {
		return new Wife();
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();

		strBuf.append("Wife{");
		strBuf.append("\n\tnickname:").append(nickname);
		strBuf.append("\n\tchirld:").append(chirld);
		if (chirlds  != null) {
			strBuf.append("\nchirlds:");
			for (Chirld data : chirlds) {
				strBuf.append(data).append(",\n");
			}
		}
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class Player extends AbstractMessage{

	private boolean merry;
	private byte chirld;
	private short age;
	private int whifes;
	private long phoneNum;
	private float credit;
	private double money;
	private String nickname;
	private List<Boolean> chirldMerry;
	private List<Byte> chirldAges;
	private List<String> chirldNames;
	private List<Wife> wifs;
	private Wife wife;

	private Player() {
	}

	public boolean getMerry() {
		return this.merry;
	}

	public Player setMerry(boolean data) {
		this.merry = data;
		return this;
	}

	public byte getChirld() {
		return this.chirld;
	}

	public Player setChirld(byte data) {
		this.chirld = data;
		return this;
	}

	public short getAge() {
		return this.age;
	}

	public Player setAge(short data) {
		this.age = data;
		return this;
	}

	public int getWhifes() {
		return this.whifes;
	}

	public Player setWhifes(int data) {
		this.whifes = data;
		return this;
	}

	public long getPhoneNum() {
		return this.phoneNum;
	}

	public Player setPhoneNum(long data) {
		this.phoneNum = data;
		return this;
	}

	public float getCredit() {
		return this.credit;
	}

	public Player setCredit(float data) {
		this.credit = data;
		return this;
	}

	public double getMoney() {
		return this.money;
	}

	public Player setMoney(double data) {
		this.money = data;
		return this;
	}

	public String getNickname() {
		return this.nickname;
	}

	public Player setNickname(String data) {
		this.nickname = data;
		return this;
	}

	public List<Boolean> getChirldMerry() {
		return this.chirldMerry;
	}

	public Player setChirldMerry(List<Boolean> data) {
		this.chirldMerry = data;
		return this;
	}

	public Player addChirldMerry(Boolean data) {
		if (this.chirldMerry == null) {
			this.chirldMerry = new ArrayList<>();
		}
		this.chirldMerry.add(data);
		return this;
	}

	public Player addAllChirldMerry(List<Boolean> datas) {
		if (this.chirldMerry == null) {
			this.chirldMerry = new ArrayList<>();
		}
		this.chirldMerry.addAll(datas);
		return this;
	}

	public Player setChirldMerry(int index, Boolean data) {
		this.chirldMerry.set(index, data);
		return this;
	}

	public List<Byte> getChirldAges() {
		return this.chirldAges;
	}

	public Player setChirldAges(List<Byte> data) {
		this.chirldAges = data;
		return this;
	}

	public Player addChirldAges(Byte data) {
		if (this.chirldAges == null) {
			this.chirldAges = new ArrayList<>();
		}
		this.chirldAges.add(data);
		return this;
	}

	public Player addAllChirldAges(List<Byte> datas) {
		if (this.chirldAges == null) {
			this.chirldAges = new ArrayList<>();
		}
		this.chirldAges.addAll(datas);
		return this;
	}

	public Player setChirldAges(int index, Byte data) {
		this.chirldAges.set(index, data);
		return this;
	}

	public List<String> getChirldNames() {
		return this.chirldNames;
	}

	public Player setChirldNames(List<String> data) {
		this.chirldNames = data;
		return this;
	}

	public Player addChirldNames(String data) {
		if (this.chirldNames == null) {
			this.chirldNames = new ArrayList<>();
		}
		this.chirldNames.add(data);
		return this;
	}

	public Player addAllChirldNames(List<String> datas) {
		if (this.chirldNames == null) {
			this.chirldNames = new ArrayList<>();
		}
		this.chirldNames.addAll(datas);
		return this;
	}

	public Player setChirldNames(int index, String data) {
		this.chirldNames.set(index, data);
		return this;
	}

	public List<Wife> getWifs() {
		return this.wifs;
	}

	public Player setWifs(List<Wife> data) {
		this.wifs = data;
		return this;
	}

	public Player addWifs(Wife data) {
		if (this.wifs == null) {
			this.wifs = new ArrayList<>();
		}
		this.wifs.add(data);
		return this;
	}

	public Player addAllWifs(List<Wife> datas) {
		if (this.wifs == null) {
			this.wifs = new ArrayList<>();
		}
		this.wifs.addAll(datas);
		return this;
	}

	public Player setWifs(int index, Wife data) {
		this.wifs.set(index, data);
		return this;
	}

	public Wife getWife() {
		return this.wife;
	}

	public Player setWife(Wife data) {
		this.wife = data;
		return this;
	}

	@Override
	public Player readMsg(ByteBuffer buf) {
		this.merry = readBool(buf);
		this.chirld = readInt8(buf);
		this.age = readInt16(buf);
		this.whifes = readInt(buf);
		this.phoneNum = readInt64(buf);
		this.credit = readFloat(buf);
		this.money = readDouble(buf);
		this.nickname = readString(buf);
		this.chirldMerry = readArray(Boolean.class, buf);
		this.chirldAges = readArray(Byte.class, buf);
		this.chirldNames = readArray(String.class, buf);
		this.wifs = readArray(Wife.class, buf);
		this.wife = Wife.newBuilder().readMsg(buf);
		return this;
	}

	@Override
	public void writeMsg(BubferBuilder builder) {
		writeBool(builder, this.merry);
		writeInt8(builder, this.chirld);
		writeInt16(builder, this.age);
		writeInt(builder, this.whifes);
		writeInt64(builder, this.phoneNum);
		writeFloat(builder, this.credit);
		writeDouble(builder, this.money);
		writeString(builder, this.nickname);
		writeArray(builder, this.chirldMerry);
		writeArray(builder, this.chirldAges);
		writeArray(builder, this.chirldNames);
		writeArray(builder, this.wifs);
		this.wife.writeMsg(builder);
	}

	public static Player newBuilder() {
		return new Player();
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();

		strBuf.append("Player{");
		strBuf.append("\n\tmerry:").append(merry);
		strBuf.append("\n\tchirld:").append(chirld);
		strBuf.append("\n\tage:").append(age);
		strBuf.append("\n\twhifes:").append(whifes);
		strBuf.append("\n\tphoneNum:").append(phoneNum);
		strBuf.append("\n\tcredit:").append(credit);
		strBuf.append("\n\tmoney:").append(money);
		strBuf.append("\n\tnickname:").append(nickname);
		if (chirldMerry  != null) {
			strBuf.append("\nchirldMerry:");
			for (Boolean data : chirldMerry) {
				strBuf.append(data).append(",\n");
			}
		}
		if (chirldAges  != null) {
			strBuf.append("\nchirldAges:");
			for (Byte data : chirldAges) {
				strBuf.append(data).append(",\n");
			}
		}
		if (chirldNames  != null) {
			strBuf.append("\nchirldNames:");
			for (String data : chirldNames) {
				strBuf.append(data).append(",\n");
			}
		}
		if (wifs  != null) {
			strBuf.append("\nwifs:");
			for (Wife data : wifs) {
				strBuf.append(data).append(",\n");
			}
		}
		strBuf.append("\n\twife:").append(wife);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class PlayerRequest extends AbstractMessage implements NetRecivedAble{

	static final byte SUFFIX = 0x01;

	private Player player;

	private PlayerRequest() {
	}

	@Override
	public final byte SUFFIX() {
		return SUFFIX;
	}

	public Player getPlayer() {
		return this.player;
	}

	public PlayerRequest setPlayer(Player data) {
		this.player = data;
		return this;
	}

	@Override
	public PlayerRequest readMsg(ByteBuffer buf) {
		this.player = Player.newBuilder().readMsg(buf);
		return this;
	}

	@Override
	public void writeMsg(BubferBuilder builder) {
		this.player.writeMsg(builder);
	}

	public static PlayerRequest newBuilder() {
		return new PlayerRequest();
	}

	public static PlayerRequest fromBuffer(ByteBuffer buf) {
		return newBuilder().readMsg(buf);
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();

		strBuf.append("PlayerRequest{");
		strBuf.append("\n\tplayer:").append(player);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class PlayerResponse extends AbstractMessage implements NetSendAble{

	static final byte SUFFIX = 0x02;

	private Player player;

	private PlayerResponse() {
	}

	@Override
	public final byte SUFFIX() {
		return SUFFIX;
	}

	public Player getPlayer() {
		return this.player;
	}

	public PlayerResponse setPlayer(Player data) {
		this.player = data;
		return this;
	}

	@Override
	public PlayerResponse readMsg(ByteBuffer buf) {
		this.player = Player.newBuilder().readMsg(buf);
		return this;
	}

	@Override
	public void writeMsg(BubferBuilder builder) {
		this.player.writeMsg(builder);
	}

	public static PlayerResponse newBuilder() {
		return new PlayerResponse();
	}

	@Override
	public ByteBuffer toBuffer() {
		BubferBuilder builder = new BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		writeMsg(builder);

		ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();

		return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();

		strBuf.append("PlayerResponse{");
		strBuf.append("\n\tplayer:").append(player);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

public static class PlayerInfo extends AbstractMessage implements NetRecivedAble, NetSendAble{

	static final byte SUFFIX = 0x03;

	private Player player;

	private PlayerInfo() {
	}

	@Override
	public final byte SUFFIX() {
		return SUFFIX;
	}

	public Player getPlayer() {
		return this.player;
	}

	public PlayerInfo setPlayer(Player data) {
		this.player = data;
		return this;
	}

	@Override
	public PlayerInfo readMsg(ByteBuffer buf) {
		this.player = Player.newBuilder().readMsg(buf);
		return this;
	}

	@Override
	public void writeMsg(BubferBuilder builder) {
		this.player.writeMsg(builder);
	}

	public static PlayerInfo newBuilder() {
		return new PlayerInfo();
	}

	public static PlayerInfo fromBuffer(ByteBuffer buf) {
		return newBuilder().readMsg(buf);
	}

	@Override
	public ByteBuffer toBuffer() {
		BubferBuilder builder = new BubferBuilder(64);

		builder.addShort((short)0);
		builder.addShort((short)((PREFIX << 8) | SUFFIX));
		writeMsg(builder);

		ByteBuffer buf = builder.toBuffer();
		builder.replaceShort(0, (short)buf.position());
		buf.flip();

		return buf;
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();

		strBuf.append("PlayerInfo{");
		strBuf.append("\n\tplayer:").append(player);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

}