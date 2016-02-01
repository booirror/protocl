package org.itas;

import com.uxuan.protocl.buffer.IoBuf;
import org.itas.Comm;
import java.nio.ByteBuffer;

public class Family {

public static class Famils extends com.uxuan.protocl.buffer.Message {

	private Comm.Man wife;
	private Comm.Man husBand;
	private java.util.List<Comm.Man> child;

	private Famils() {
	}

	public Comm.Man getWife() {
		return this.wife;
	}

	public Famils setWife(Comm.Man value) {
		this.wife = value;
		return this;
	}

	public Comm.Man getHusBand() {
		return this.husBand;
	}

	public Famils setHusBand(Comm.Man value) {
		this.husBand = value;
		return this;
	}

	public java.util.List<Comm.Man> getChild() {
		return this.child;
	}

	public Famils setChild(java.util.List<Comm.Man> value) {
		this.child = value;
		return this;
	}

	public Famils addChild(Comm.Man value) {
		if (this.child == null) {
			this.child = new java.util.ArrayList<>();
		}
		this.child.add(value);
		return this;
	}

	public Famils addAllChild(java.util.List<Comm.Man> value) {
		if (this.child == null) {
			this.child = new java.util.ArrayList<>();
		}
		this.child.addAll(value);
		return this;
	}

	public Famils setChild(int index, Comm.Man value) {
		this.child.set(index, value);
		return this;
	}

	@Override
	public IoBuf toIoBuf() {
		IoBuf buf = IoBuf.allocate(64);
		if (wife != null) {
			buf.writeInt8(2);
			buf.writeByte(this.wife.toArray());
		} else {
			buf.writeInt8(0);
		}
		if (husBand != null) {
			buf.writeInt8(2);
			buf.writeByte(this.husBand.toArray());
		} else {
			buf.writeInt8(0);
		}
		buf.writeArray(this.child);
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
	 public Famils parse(IoBuf buf) {
		int types1 = buf.readInt8();
		if (types1 == 1) {
			this.wife = buf.readEnum(Comm.Man.class, buf.readInt32());
		} else if (types1 == 2) {
			this.wife = com.uxuan.protocl.buffer.Message.getMessage(Comm.Man).parse(buf)
		}
		int types2 = buf.readInt8();
		if (types2 == 1) {
			this.husBand = buf.readEnum(Comm.Man.class, buf.readInt32());
		} else if (types2 == 2) {
			this.husBand = com.uxuan.protocl.buffer.Message.getMessage(Comm.Man).parse(buf)
		}
		this.child = buf.readArray();
	}

	@Override
	 public Famils parse(ByteBuffer buf) {
		return parse(IoBuf.wrap(buf));
	}

	@Override
	 public Famils parse(byte[] bytes) {
		return parse(IoBuf.wrap(bytes));
	}

	public static Famils newBuilder() {
		return new Famils();
	}

	public Famils clone() {
		return newBuilder();
	}

	public static Famils parseFrom(IoBuf buf) {
		return newBuilder().parse(buf);
	}

	public static Famils parseFrom(ByteBuffer buf) {
		return newBuilder().parse(buf);
	}

	public static Famils parseFrom(byte[] bs) {
		return newBuilder().parse(bs);
	}
}

public static class Country extends com.uxuan.protocl.buffer.Message {

	private java.util.Map<String, Comm.Man> persons;
	private ArrayTest arrays;
	private SetTest sets;
	private MapTest maps;

	private Country() {
	}

	public java.util.Map<String, Comm.Man> getPersons() {
		return this.persons;
	}

	public Country setPersons(java.util.Map<String, Comm.Man> value) {
		this.persons = value;
		return this;
	}

	public Country putPersons(String key, Comm.Man value) {
		if (this.persons == null) {
			this.persons = new java.util.HashMap<>();
		}
		this.persons.put(key, value);
		return this;
	}

	public Country addAllPersons(java.util.Map<String, Comm.Man> value) {
		if (this.persons == null) {
			this.persons = new java.util.HashMap<>();
		}
		this.persons.putAll(value);
		return this;
	}

	public ArrayTest getArrays() {
		return this.arrays;
	}

	public Country setArrays(ArrayTest value) {
		this.arrays = value;
		return this;
	}

	public SetTest getSets() {
		return this.sets;
	}

	public Country setSets(SetTest value) {
		this.sets = value;
		return this;
	}

	public MapTest getMaps() {
		return this.maps;
	}

	public Country setMaps(MapTest value) {
		this.maps = value;
		return this;
	}

	@Override
	public IoBuf toIoBuf() {
		IoBuf buf = IoBuf.allocate(64);
		buf.writeMap(this.persons);
		if (arrays != null) {
			buf.writeInt8(2);
			buf.writeByte(this.arrays.toArray());
		} else {
			buf.writeInt8(0);
		}
		if (sets != null) {
			buf.writeInt8(2);
			buf.writeByte(this.sets.toArray());
		} else {
			buf.writeInt8(0);
		}
		if (maps != null) {
			buf.writeInt8(2);
			buf.writeByte(this.maps.toArray());
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
	 public Country parse(IoBuf buf) {
		this.persons = buf.readMap();
		int types2 = buf.readInt8();
		if (types2 == 1) {
			this.arrays = buf.readEnum(ArrayTest.class, buf.readInt32());
		} else if (types2 == 2) {
			this.arrays = com.uxuan.protocl.buffer.Message.getMessage(ArrayTest).parse(buf)
		}
		int types3 = buf.readInt8();
		if (types3 == 1) {
			this.sets = buf.readEnum(SetTest.class, buf.readInt32());
		} else if (types3 == 2) {
			this.sets = com.uxuan.protocl.buffer.Message.getMessage(SetTest).parse(buf)
		}
		int types4 = buf.readInt8();
		if (types4 == 1) {
			this.maps = buf.readEnum(MapTest.class, buf.readInt32());
		} else if (types4 == 2) {
			this.maps = com.uxuan.protocl.buffer.Message.getMessage(MapTest).parse(buf)
		}
	}

	@Override
	 public Country parse(ByteBuffer buf) {
		return parse(IoBuf.wrap(buf));
	}

	@Override
	 public Country parse(byte[] bytes) {
		return parse(IoBuf.wrap(bytes));
	}

	public static Country newBuilder() {
		return new Country();
	}

	public Country clone() {
		return newBuilder();
	}

	public static Country parseFrom(IoBuf buf) {
		return newBuilder().parse(buf);
	}

	public static Country parseFrom(ByteBuffer buf) {
		return newBuilder().parse(buf);
	}

	public static Country parseFrom(byte[] bs) {
		return newBuilder().parse(bs);
	}
}

public static class ArrayTest extends com.uxuan.protocl.buffer.Message {

	private java.util.List<String> name;
	private java.util.List<Boolean> isMerry;
	private java.util.List<Byte> children;
	private java.util.List<Short> age;
	private java.util.List<Integer> money;
	private java.util.List<Long> mobile;
	private java.util.List<String> address;
	private java.util.List<Float> value1;
	private java.util.List<Double> value2;
	private java.util.List<Comm.Sex> sex;
	private java.util.List<Comm.Man> sex1;

	private ArrayTest() {
	}

	public java.util.List<String> getName() {
		return this.name;
	}

	public ArrayTest setName(java.util.List<String> value) {
		this.name = value;
		return this;
	}

	public ArrayTest addName(String value) {
		if (this.name == null) {
			this.name = new java.util.ArrayList<>();
		}
		this.name.add(value);
		return this;
	}

	public ArrayTest addAllName(java.util.List<String> value) {
		if (this.name == null) {
			this.name = new java.util.ArrayList<>();
		}
		this.name.addAll(value);
		return this;
	}

	public ArrayTest setName(int index, String value) {
		this.name.set(index, value);
		return this;
	}

	public java.util.List<Boolean> getIsMerry() {
		return this.isMerry;
	}

	public ArrayTest setIsMerry(java.util.List<Boolean> value) {
		this.isMerry = value;
		return this;
	}

	public ArrayTest addIsMerry(Boolean value) {
		if (this.isMerry == null) {
			this.isMerry = new java.util.ArrayList<>();
		}
		this.isMerry.add(value);
		return this;
	}

	public ArrayTest addAllIsMerry(java.util.List<Boolean> value) {
		if (this.isMerry == null) {
			this.isMerry = new java.util.ArrayList<>();
		}
		this.isMerry.addAll(value);
		return this;
	}

	public ArrayTest setIsMerry(int index, Boolean value) {
		this.isMerry.set(index, value);
		return this;
	}

	public java.util.List<Byte> getChildren() {
		return this.children;
	}

	public ArrayTest setChildren(java.util.List<Byte> value) {
		this.children = value;
		return this;
	}

	public ArrayTest addChildren(Byte value) {
		if (this.children == null) {
			this.children = new java.util.ArrayList<>();
		}
		this.children.add(value);
		return this;
	}

	public ArrayTest addAllChildren(java.util.List<Byte> value) {
		if (this.children == null) {
			this.children = new java.util.ArrayList<>();
		}
		this.children.addAll(value);
		return this;
	}

	public ArrayTest setChildren(int index, Byte value) {
		this.children.set(index, value);
		return this;
	}

	public java.util.List<Short> getAge() {
		return this.age;
	}

	public ArrayTest setAge(java.util.List<Short> value) {
		this.age = value;
		return this;
	}

	public ArrayTest addAge(Short value) {
		if (this.age == null) {
			this.age = new java.util.ArrayList<>();
		}
		this.age.add(value);
		return this;
	}

	public ArrayTest addAllAge(java.util.List<Short> value) {
		if (this.age == null) {
			this.age = new java.util.ArrayList<>();
		}
		this.age.addAll(value);
		return this;
	}

	public ArrayTest setAge(int index, Short value) {
		this.age.set(index, value);
		return this;
	}

	public java.util.List<Integer> getMoney() {
		return this.money;
	}

	public ArrayTest setMoney(java.util.List<Integer> value) {
		this.money = value;
		return this;
	}

	public ArrayTest addMoney(Integer value) {
		if (this.money == null) {
			this.money = new java.util.ArrayList<>();
		}
		this.money.add(value);
		return this;
	}

	public ArrayTest addAllMoney(java.util.List<Integer> value) {
		if (this.money == null) {
			this.money = new java.util.ArrayList<>();
		}
		this.money.addAll(value);
		return this;
	}

	public ArrayTest setMoney(int index, Integer value) {
		this.money.set(index, value);
		return this;
	}

	public java.util.List<Long> getMobile() {
		return this.mobile;
	}

	public ArrayTest setMobile(java.util.List<Long> value) {
		this.mobile = value;
		return this;
	}

	public ArrayTest addMobile(Long value) {
		if (this.mobile == null) {
			this.mobile = new java.util.ArrayList<>();
		}
		this.mobile.add(value);
		return this;
	}

	public ArrayTest addAllMobile(java.util.List<Long> value) {
		if (this.mobile == null) {
			this.mobile = new java.util.ArrayList<>();
		}
		this.mobile.addAll(value);
		return this;
	}

	public ArrayTest setMobile(int index, Long value) {
		this.mobile.set(index, value);
		return this;
	}

	public java.util.List<String> getAddress() {
		return this.address;
	}

	public ArrayTest setAddress(java.util.List<String> value) {
		this.address = value;
		return this;
	}

	public ArrayTest addAddress(String value) {
		if (this.address == null) {
			this.address = new java.util.ArrayList<>();
		}
		this.address.add(value);
		return this;
	}

	public ArrayTest addAllAddress(java.util.List<String> value) {
		if (this.address == null) {
			this.address = new java.util.ArrayList<>();
		}
		this.address.addAll(value);
		return this;
	}

	public ArrayTest setAddress(int index, String value) {
		this.address.set(index, value);
		return this;
	}

	public java.util.List<Float> getValue1() {
		return this.value1;
	}

	public ArrayTest setValue1(java.util.List<Float> value) {
		this.value1 = value;
		return this;
	}

	public ArrayTest addValue1(Float value) {
		if (this.value1 == null) {
			this.value1 = new java.util.ArrayList<>();
		}
		this.value1.add(value);
		return this;
	}

	public ArrayTest addAllValue1(java.util.List<Float> value) {
		if (this.value1 == null) {
			this.value1 = new java.util.ArrayList<>();
		}
		this.value1.addAll(value);
		return this;
	}

	public ArrayTest setValue1(int index, Float value) {
		this.value1.set(index, value);
		return this;
	}

	public java.util.List<Double> getValue2() {
		return this.value2;
	}

	public ArrayTest setValue2(java.util.List<Double> value) {
		this.value2 = value;
		return this;
	}

	public ArrayTest addValue2(Double value) {
		if (this.value2 == null) {
			this.value2 = new java.util.ArrayList<>();
		}
		this.value2.add(value);
		return this;
	}

	public ArrayTest addAllValue2(java.util.List<Double> value) {
		if (this.value2 == null) {
			this.value2 = new java.util.ArrayList<>();
		}
		this.value2.addAll(value);
		return this;
	}

	public ArrayTest setValue2(int index, Double value) {
		this.value2.set(index, value);
		return this;
	}

	public java.util.List<Comm.Sex> getSex() {
		return this.sex;
	}

	public ArrayTest setSex(java.util.List<Comm.Sex> value) {
		this.sex = value;
		return this;
	}

	public ArrayTest addSex(Comm.Sex value) {
		if (this.sex == null) {
			this.sex = new java.util.ArrayList<>();
		}
		this.sex.add(value);
		return this;
	}

	public ArrayTest addAllSex(java.util.List<Comm.Sex> value) {
		if (this.sex == null) {
			this.sex = new java.util.ArrayList<>();
		}
		this.sex.addAll(value);
		return this;
	}

	public ArrayTest setSex(int index, Comm.Sex value) {
		this.sex.set(index, value);
		return this;
	}

	public java.util.List<Comm.Man> getSex1() {
		return this.sex1;
	}

	public ArrayTest setSex1(java.util.List<Comm.Man> value) {
		this.sex1 = value;
		return this;
	}

	public ArrayTest addSex1(Comm.Man value) {
		if (this.sex1 == null) {
			this.sex1 = new java.util.ArrayList<>();
		}
		this.sex1.add(value);
		return this;
	}

	public ArrayTest addAllSex1(java.util.List<Comm.Man> value) {
		if (this.sex1 == null) {
			this.sex1 = new java.util.ArrayList<>();
		}
		this.sex1.addAll(value);
		return this;
	}

	public ArrayTest setSex1(int index, Comm.Man value) {
		this.sex1.set(index, value);
		return this;
	}

	@Override
	public IoBuf toIoBuf() {
		IoBuf buf = IoBuf.allocate(64);
		buf.writeArray(this.name);
		buf.writeArray(this.isMerry);
		buf.writeArray(this.children);
		buf.writeArray(this.age);
		buf.writeArray(this.money);
		buf.writeArray(this.mobile);
		buf.writeArray(this.address);
		buf.writeArray(this.value1);
		buf.writeArray(this.value2);
		buf.writeArray(this.sex);
		buf.writeArray(this.sex1);
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
	 public ArrayTest parse(IoBuf buf) {
		this.name = buf.readArray();
		this.isMerry = buf.readArray();
		this.children = buf.readArray();
		this.age = buf.readArray();
		this.money = buf.readArray();
		this.mobile = buf.readArray();
		this.address = buf.readArray();
		this.value1 = buf.readArray();
		this.value2 = buf.readArray();
		this.sex = buf.readArray();
		this.sex1 = buf.readArray();
	}

	@Override
	 public ArrayTest parse(ByteBuffer buf) {
		return parse(IoBuf.wrap(buf));
	}

	@Override
	 public ArrayTest parse(byte[] bytes) {
		return parse(IoBuf.wrap(bytes));
	}

	public static ArrayTest newBuilder() {
		return new ArrayTest();
	}

	public ArrayTest clone() {
		return newBuilder();
	}

	public static ArrayTest parseFrom(IoBuf buf) {
		return newBuilder().parse(buf);
	}

	public static ArrayTest parseFrom(ByteBuffer buf) {
		return newBuilder().parse(buf);
	}

	public static ArrayTest parseFrom(byte[] bs) {
		return newBuilder().parse(bs);
	}
}

public static class SetTest extends com.uxuan.protocl.buffer.Message {

	private java.util.Set<String> name;
	private java.util.Set<Boolean> isMerry;
	private java.util.Set<Byte> children;
	private java.util.Set<Short> age;
	private java.util.Set<Integer> money;
	private java.util.Set<Long> mobile;
	private java.util.Set<String> address;
	private java.util.Set<Float> value1;
	private java.util.Set<Double> value2;
	private java.util.Set<Comm.Sex> sex;
	private java.util.Set<Comm.Man> sex1;

	private SetTest() {
	}

	public java.util.Set<String> getName() {
		return this.name;
	}

	public SetTest setName(java.util.Set<String> value) {
		this.name = value;
		return this;
	}

	public SetTest addName(String value) {
		if (this.name == null) {
			this.name = new java.util.HashSet<>();
		}
		this.name.add(value);
		return this;
	}

	public SetTest addAllName(java.util.Set<String> value) {
		if (this.name == null) {
			this.name = new java.util.HashSet<>();
		}
		this.name.addAll(value);
		return this;
	}

	public java.util.Set<Boolean> getIsMerry() {
		return this.isMerry;
	}

	public SetTest setIsMerry(java.util.Set<Boolean> value) {
		this.isMerry = value;
		return this;
	}

	public SetTest addIsMerry(Boolean value) {
		if (this.isMerry == null) {
			this.isMerry = new java.util.HashSet<>();
		}
		this.isMerry.add(value);
		return this;
	}

	public SetTest addAllIsMerry(java.util.Set<Boolean> value) {
		if (this.isMerry == null) {
			this.isMerry = new java.util.HashSet<>();
		}
		this.isMerry.addAll(value);
		return this;
	}

	public java.util.Set<Byte> getChildren() {
		return this.children;
	}

	public SetTest setChildren(java.util.Set<Byte> value) {
		this.children = value;
		return this;
	}

	public SetTest addChildren(Byte value) {
		if (this.children == null) {
			this.children = new java.util.HashSet<>();
		}
		this.children.add(value);
		return this;
	}

	public SetTest addAllChildren(java.util.Set<Byte> value) {
		if (this.children == null) {
			this.children = new java.util.HashSet<>();
		}
		this.children.addAll(value);
		return this;
	}

	public java.util.Set<Short> getAge() {
		return this.age;
	}

	public SetTest setAge(java.util.Set<Short> value) {
		this.age = value;
		return this;
	}

	public SetTest addAge(Short value) {
		if (this.age == null) {
			this.age = new java.util.HashSet<>();
		}
		this.age.add(value);
		return this;
	}

	public SetTest addAllAge(java.util.Set<Short> value) {
		if (this.age == null) {
			this.age = new java.util.HashSet<>();
		}
		this.age.addAll(value);
		return this;
	}

	public java.util.Set<Integer> getMoney() {
		return this.money;
	}

	public SetTest setMoney(java.util.Set<Integer> value) {
		this.money = value;
		return this;
	}

	public SetTest addMoney(Integer value) {
		if (this.money == null) {
			this.money = new java.util.HashSet<>();
		}
		this.money.add(value);
		return this;
	}

	public SetTest addAllMoney(java.util.Set<Integer> value) {
		if (this.money == null) {
			this.money = new java.util.HashSet<>();
		}
		this.money.addAll(value);
		return this;
	}

	public java.util.Set<Long> getMobile() {
		return this.mobile;
	}

	public SetTest setMobile(java.util.Set<Long> value) {
		this.mobile = value;
		return this;
	}

	public SetTest addMobile(Long value) {
		if (this.mobile == null) {
			this.mobile = new java.util.HashSet<>();
		}
		this.mobile.add(value);
		return this;
	}

	public SetTest addAllMobile(java.util.Set<Long> value) {
		if (this.mobile == null) {
			this.mobile = new java.util.HashSet<>();
		}
		this.mobile.addAll(value);
		return this;
	}

	public java.util.Set<String> getAddress() {
		return this.address;
	}

	public SetTest setAddress(java.util.Set<String> value) {
		this.address = value;
		return this;
	}

	public SetTest addAddress(String value) {
		if (this.address == null) {
			this.address = new java.util.HashSet<>();
		}
		this.address.add(value);
		return this;
	}

	public SetTest addAllAddress(java.util.Set<String> value) {
		if (this.address == null) {
			this.address = new java.util.HashSet<>();
		}
		this.address.addAll(value);
		return this;
	}

	public java.util.Set<Float> getValue1() {
		return this.value1;
	}

	public SetTest setValue1(java.util.Set<Float> value) {
		this.value1 = value;
		return this;
	}

	public SetTest addValue1(Float value) {
		if (this.value1 == null) {
			this.value1 = new java.util.HashSet<>();
		}
		this.value1.add(value);
		return this;
	}

	public SetTest addAllValue1(java.util.Set<Float> value) {
		if (this.value1 == null) {
			this.value1 = new java.util.HashSet<>();
		}
		this.value1.addAll(value);
		return this;
	}

	public java.util.Set<Double> getValue2() {
		return this.value2;
	}

	public SetTest setValue2(java.util.Set<Double> value) {
		this.value2 = value;
		return this;
	}

	public SetTest addValue2(Double value) {
		if (this.value2 == null) {
			this.value2 = new java.util.HashSet<>();
		}
		this.value2.add(value);
		return this;
	}

	public SetTest addAllValue2(java.util.Set<Double> value) {
		if (this.value2 == null) {
			this.value2 = new java.util.HashSet<>();
		}
		this.value2.addAll(value);
		return this;
	}

	public java.util.Set<Comm.Sex> getSex() {
		return this.sex;
	}

	public SetTest setSex(java.util.Set<Comm.Sex> value) {
		this.sex = value;
		return this;
	}

	public SetTest addSex(Comm.Sex value) {
		if (this.sex == null) {
			this.sex = new java.util.HashSet<>();
		}
		this.sex.add(value);
		return this;
	}

	public SetTest addAllSex(java.util.Set<Comm.Sex> value) {
		if (this.sex == null) {
			this.sex = new java.util.HashSet<>();
		}
		this.sex.addAll(value);
		return this;
	}

	public java.util.Set<Comm.Man> getSex1() {
		return this.sex1;
	}

	public SetTest setSex1(java.util.Set<Comm.Man> value) {
		this.sex1 = value;
		return this;
	}

	public SetTest addSex1(Comm.Man value) {
		if (this.sex1 == null) {
			this.sex1 = new java.util.HashSet<>();
		}
		this.sex1.add(value);
		return this;
	}

	public SetTest addAllSex1(java.util.Set<Comm.Man> value) {
		if (this.sex1 == null) {
			this.sex1 = new java.util.HashSet<>();
		}
		this.sex1.addAll(value);
		return this;
	}

	@Override
	public IoBuf toIoBuf() {
		IoBuf buf = IoBuf.allocate(64);
		buf.writeSet(this.name);
		buf.writeSet(this.isMerry);
		buf.writeSet(this.children);
		buf.writeSet(this.age);
		buf.writeSet(this.money);
		buf.writeSet(this.mobile);
		buf.writeSet(this.address);
		buf.writeSet(this.value1);
		buf.writeSet(this.value2);
		buf.writeSet(this.sex);
		buf.writeSet(this.sex1);
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
	 public SetTest parse(IoBuf buf) {
		this.name = buf.readSet();
		this.isMerry = buf.readSet();
		this.children = buf.readSet();
		this.age = buf.readSet();
		this.money = buf.readSet();
		this.mobile = buf.readSet();
		this.address = buf.readSet();
		this.value1 = buf.readSet();
		this.value2 = buf.readSet();
		this.sex = buf.readSet();
		this.sex1 = buf.readSet();
	}

	@Override
	 public SetTest parse(ByteBuffer buf) {
		return parse(IoBuf.wrap(buf));
	}

	@Override
	 public SetTest parse(byte[] bytes) {
		return parse(IoBuf.wrap(bytes));
	}

	public static SetTest newBuilder() {
		return new SetTest();
	}

	public SetTest clone() {
		return newBuilder();
	}

	public static SetTest parseFrom(IoBuf buf) {
		return newBuilder().parse(buf);
	}

	public static SetTest parseFrom(ByteBuffer buf) {
		return newBuilder().parse(buf);
	}

	public static SetTest parseFrom(byte[] bs) {
		return newBuilder().parse(bs);
	}
}

public static class MapTest extends com.uxuan.protocl.buffer.Message {

	private java.util.Map<String, Comm.Man> name;
	private java.util.Map<Boolean, Comm.Man> isMerry;
	private java.util.Map<Byte, Comm.Man> children;
	private java.util.Map<Short, Comm.Man> age;
	private java.util.Map<Integer, Comm.Man> money;
	private java.util.Map<Long, Comm.Man> mobile;
	private java.util.Map<String, Comm.Man> address;
	private java.util.Map<Float, Comm.Man> value1;
	private java.util.Map<Double, Comm.Man> value2;
	private java.util.Map<Comm.Sex, Comm.Man> sex;
	private java.util.Map<Comm.Man, Comm.Sex> sex1;

	private MapTest() {
	}

	public java.util.Map<String, Comm.Man> getName() {
		return this.name;
	}

	public MapTest setName(java.util.Map<String, Comm.Man> value) {
		this.name = value;
		return this;
	}

	public MapTest putName(String key, Comm.Man value) {
		if (this.name == null) {
			this.name = new java.util.HashMap<>();
		}
		this.name.put(key, value);
		return this;
	}

	public MapTest addAllName(java.util.Map<String, Comm.Man> value) {
		if (this.name == null) {
			this.name = new java.util.HashMap<>();
		}
		this.name.putAll(value);
		return this;
	}

	public java.util.Map<Boolean, Comm.Man> getIsMerry() {
		return this.isMerry;
	}

	public MapTest setIsMerry(java.util.Map<Boolean, Comm.Man> value) {
		this.isMerry = value;
		return this;
	}

	public MapTest putIsMerry(Boolean key, Comm.Man value) {
		if (this.isMerry == null) {
			this.isMerry = new java.util.HashMap<>();
		}
		this.isMerry.put(key, value);
		return this;
	}

	public MapTest addAllIsMerry(java.util.Map<Boolean, Comm.Man> value) {
		if (this.isMerry == null) {
			this.isMerry = new java.util.HashMap<>();
		}
		this.isMerry.putAll(value);
		return this;
	}

	public java.util.Map<Byte, Comm.Man> getChildren() {
		return this.children;
	}

	public MapTest setChildren(java.util.Map<Byte, Comm.Man> value) {
		this.children = value;
		return this;
	}

	public MapTest putChildren(Byte key, Comm.Man value) {
		if (this.children == null) {
			this.children = new java.util.HashMap<>();
		}
		this.children.put(key, value);
		return this;
	}

	public MapTest addAllChildren(java.util.Map<Byte, Comm.Man> value) {
		if (this.children == null) {
			this.children = new java.util.HashMap<>();
		}
		this.children.putAll(value);
		return this;
	}

	public java.util.Map<Short, Comm.Man> getAge() {
		return this.age;
	}

	public MapTest setAge(java.util.Map<Short, Comm.Man> value) {
		this.age = value;
		return this;
	}

	public MapTest putAge(Short key, Comm.Man value) {
		if (this.age == null) {
			this.age = new java.util.HashMap<>();
		}
		this.age.put(key, value);
		return this;
	}

	public MapTest addAllAge(java.util.Map<Short, Comm.Man> value) {
		if (this.age == null) {
			this.age = new java.util.HashMap<>();
		}
		this.age.putAll(value);
		return this;
	}

	public java.util.Map<Integer, Comm.Man> getMoney() {
		return this.money;
	}

	public MapTest setMoney(java.util.Map<Integer, Comm.Man> value) {
		this.money = value;
		return this;
	}

	public MapTest putMoney(Integer key, Comm.Man value) {
		if (this.money == null) {
			this.money = new java.util.HashMap<>();
		}
		this.money.put(key, value);
		return this;
	}

	public MapTest addAllMoney(java.util.Map<Integer, Comm.Man> value) {
		if (this.money == null) {
			this.money = new java.util.HashMap<>();
		}
		this.money.putAll(value);
		return this;
	}

	public java.util.Map<Long, Comm.Man> getMobile() {
		return this.mobile;
	}

	public MapTest setMobile(java.util.Map<Long, Comm.Man> value) {
		this.mobile = value;
		return this;
	}

	public MapTest putMobile(Long key, Comm.Man value) {
		if (this.mobile == null) {
			this.mobile = new java.util.HashMap<>();
		}
		this.mobile.put(key, value);
		return this;
	}

	public MapTest addAllMobile(java.util.Map<Long, Comm.Man> value) {
		if (this.mobile == null) {
			this.mobile = new java.util.HashMap<>();
		}
		this.mobile.putAll(value);
		return this;
	}

	public java.util.Map<String, Comm.Man> getAddress() {
		return this.address;
	}

	public MapTest setAddress(java.util.Map<String, Comm.Man> value) {
		this.address = value;
		return this;
	}

	public MapTest putAddress(String key, Comm.Man value) {
		if (this.address == null) {
			this.address = new java.util.HashMap<>();
		}
		this.address.put(key, value);
		return this;
	}

	public MapTest addAllAddress(java.util.Map<String, Comm.Man> value) {
		if (this.address == null) {
			this.address = new java.util.HashMap<>();
		}
		this.address.putAll(value);
		return this;
	}

	public java.util.Map<Float, Comm.Man> getValue1() {
		return this.value1;
	}

	public MapTest setValue1(java.util.Map<Float, Comm.Man> value) {
		this.value1 = value;
		return this;
	}

	public MapTest putValue1(Float key, Comm.Man value) {
		if (this.value1 == null) {
			this.value1 = new java.util.HashMap<>();
		}
		this.value1.put(key, value);
		return this;
	}

	public MapTest addAllValue1(java.util.Map<Float, Comm.Man> value) {
		if (this.value1 == null) {
			this.value1 = new java.util.HashMap<>();
		}
		this.value1.putAll(value);
		return this;
	}

	public java.util.Map<Double, Comm.Man> getValue2() {
		return this.value2;
	}

	public MapTest setValue2(java.util.Map<Double, Comm.Man> value) {
		this.value2 = value;
		return this;
	}

	public MapTest putValue2(Double key, Comm.Man value) {
		if (this.value2 == null) {
			this.value2 = new java.util.HashMap<>();
		}
		this.value2.put(key, value);
		return this;
	}

	public MapTest addAllValue2(java.util.Map<Double, Comm.Man> value) {
		if (this.value2 == null) {
			this.value2 = new java.util.HashMap<>();
		}
		this.value2.putAll(value);
		return this;
	}

	public java.util.Map<Comm.Sex, Comm.Man> getSex() {
		return this.sex;
	}

	public MapTest setSex(java.util.Map<Comm.Sex, Comm.Man> value) {
		this.sex = value;
		return this;
	}

	public MapTest putSex(Comm.Sex key, Comm.Man value) {
		if (this.sex == null) {
			this.sex = new java.util.HashMap<>();
		}
		this.sex.put(key, value);
		return this;
	}

	public MapTest addAllSex(java.util.Map<Comm.Sex, Comm.Man> value) {
		if (this.sex == null) {
			this.sex = new java.util.HashMap<>();
		}
		this.sex.putAll(value);
		return this;
	}

	public java.util.Map<Comm.Man, Comm.Sex> getSex1() {
		return this.sex1;
	}

	public MapTest setSex1(java.util.Map<Comm.Man, Comm.Sex> value) {
		this.sex1 = value;
		return this;
	}

	public MapTest putSex1(Comm.Man key, Comm.Sex value) {
		if (this.sex1 == null) {
			this.sex1 = new java.util.HashMap<>();
		}
		this.sex1.put(key, value);
		return this;
	}

	public MapTest addAllSex1(java.util.Map<Comm.Man, Comm.Sex> value) {
		if (this.sex1 == null) {
			this.sex1 = new java.util.HashMap<>();
		}
		this.sex1.putAll(value);
		return this;
	}

	@Override
	public IoBuf toIoBuf() {
		IoBuf buf = IoBuf.allocate(64);
		buf.writeMap(this.name);
		buf.writeMap(this.isMerry);
		buf.writeMap(this.children);
		buf.writeMap(this.age);
		buf.writeMap(this.money);
		buf.writeMap(this.mobile);
		buf.writeMap(this.address);
		buf.writeMap(this.value1);
		buf.writeMap(this.value2);
		buf.writeMap(this.sex);
		buf.writeMap(this.sex1);
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
	 public MapTest parse(IoBuf buf) {
		this.name = buf.readMap();
		this.isMerry = buf.readMap();
		this.children = buf.readMap();
		this.age = buf.readMap();
		this.money = buf.readMap();
		this.mobile = buf.readMap();
		this.address = buf.readMap();
		this.value1 = buf.readMap();
		this.value2 = buf.readMap();
		this.sex = buf.readMap();
		this.sex1 = buf.readMap();
	}

	@Override
	 public MapTest parse(ByteBuffer buf) {
		return parse(IoBuf.wrap(buf));
	}

	@Override
	 public MapTest parse(byte[] bytes) {
		return parse(IoBuf.wrap(bytes));
	}

	public static MapTest newBuilder() {
		return new MapTest();
	}

	public MapTest clone() {
		return newBuilder();
	}

	public static MapTest parseFrom(IoBuf buf) {
		return newBuilder().parse(buf);
	}

	public static MapTest parseFrom(ByteBuffer buf) {
		return newBuilder().parse(buf);
	}

	public static MapTest parseFrom(byte[] bs) {
		return newBuilder().parse(bs);
	}
}

private Family() {
}

}