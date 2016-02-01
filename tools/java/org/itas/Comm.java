package org.itas;

import com.uxuan.protocl.buffer.IoBuf;
import java.nio.ByteBuffer;

public class Comm {

public static enum Sex implements com.uxuan.protocl.buffer.Eum {
	female {
		public int value() {
			return 0;
		}
	},
	male {
		public int value() {
			return 1;
		}
	},
	;

	private Sex() {
	}

}

public static class Man extends com.uxuan.protocl.buffer.Message {

	private String name;
	private boolean isMerry;
	private byte children;
	private short age;
	private int money;
	private long mobile;
	private String address;
	private float value1;
	private double value2;
	private Sex sex;
	private String identity;

	private Man() {
	}

	public String getName() {
		return this.name;
	}

	public Man setName(String value) {
		this.name = value;
		return this;
	}

	public boolean getIsMerry() {
		return this.isMerry;
	}

	public Man setIsMerry(boolean value) {
		this.isMerry = value;
		return this;
	}

	public byte getChildren() {
		return this.children;
	}

	public Man setChildren(byte value) {
		this.children = value;
		return this;
	}

	public short getAge() {
		return this.age;
	}

	public Man setAge(short value) {
		this.age = value;
		return this;
	}

	public int getMoney() {
		return this.money;
	}

	public Man setMoney(int value) {
		this.money = value;
		return this;
	}

	public long getMobile() {
		return this.mobile;
	}

	public Man setMobile(long value) {
		this.mobile = value;
		return this;
	}

	public String getAddress() {
		return this.address;
	}

	public Man setAddress(String value) {
		this.address = value;
		return this;
	}

	public float getValue1() {
		return this.value1;
	}

	public Man setValue1(float value) {
		this.value1 = value;
		return this;
	}

	public double getValue2() {
		return this.value2;
	}

	public Man setValue2(double value) {
		this.value2 = value;
		return this;
	}

	public Sex getSex() {
		return this.sex;
	}

	public Man setSex(Sex value) {
		this.sex = value;
		return this;
	}

	public String getIdentity() {
		return this.identity;
	}

	public Man setIdentity(String value) {
		this.identity = value;
		return this;
	}

	@Override
	public IoBuf toIoBuf() {
		IoBuf buf = IoBuf.allocate(64);
		buf.writeString(this.name);
		buf.writeBool(this.isMerry);
		buf.writeInt8(this.children);
		buf.writeInt16(this.age);
		buf.writeInt32(this.money);
		buf.writeInt64(this.mobile);
		buf.writeString(this.address);
		buf.writeFloat(this.value1);
		buf.writeDouble(this.value2);
		if (sex != null) {
			buf.writeInt8(2);
			buf.writeByte(this.sex.toArray());
		} else {
			buf.writeInt8(0);
		}
		if (identity != null) {
			buf.writeInt8(2);
			buf.writeByte(this.identity.toArray());
		} else {
			buf.writeInt8(0);
		}
		return buf;
	}

	@Override
	public ByteBuffer toBuffer() {
		return toIoBuf().toBuffer();
	}

	@Override
	public byte[] toArray() {
		return toIoBuf().toArray();
	}

	@Override
	 public Man parse(IoBuf buf) {
		this.name = buf.readString();
		this.isMerry = buf.readBool();
		this.children = buf.readInt8();
		this.age = buf.readInt16();
		this.money = buf.readInt32();
		this.mobile = buf.readInt64();
		this.address = buf.readString();
		this.value1 = buf.readFloat();
		this.value2 = buf.readDouble();
		int types10 = buf.readInt8();
		if (types10 == 1) {
			this.sex = buf.readEnum(Sex.class, buf.readInt32());
		} else if (types10 == 2) {
			this.sex = com.uxuan.protocl.buffer.Message.getMessage(Sex).parse(buf)
		}
		int types11 = buf.readInt8();
		if (types11 == 1) {
			this.identity = buf.readEnum(String.class, buf.readInt32());
		} else if (types11 == 2) {
			this.identity = com.uxuan.protocl.buffer.Message.getMessage(String).parse(buf)
		}
	}

	@Override
	 public Man parse(ByteBuffer buf) {
		return parse(IoBuf.wrap(buf));
	}

	@Override
	 public Man parse(byte[] bytes) {
		return parse(IoBuf.wrap(bytes));
	}

	public static Man newBuilder() {
		return new Man();
	}

	public Man clone() {
		return newBuilder();
	}

	public static Man parseFrom(IoBuf buf) {
		return newBuilder().parse(buf);
	}

	public static Man parseFrom(ByteBuffer buf) {
		return newBuilder().parse(buf);
	}

	public static Man parseFrom(byte[] bs) {
		return newBuilder().parse(bs);
	}
}

private Comm() {
}

}