package com.uxuan.protocl.buffer;

import java.nio.ByteBuffer;

/**
 * @author liuzhen
 * Email liuxing521a@163.com
 * CreatedTime 2016-01-05 22:40:00
 */
public class Student extends Message {

    private int userId;
    private String name;
    private String address;

    private Student() {
    }

    public int getUserId() {
        return userId;
    }

    public Student setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public String getName() {
        return name;
    }

    public Student setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Student setAddress(String address) {
        this.address = address;
        return this;
    }

    public byte[] toArray() {
        final IoBuf builder = IoBuf.allocate(32)
            .writeInt(userId)
            .writeString(name)
            .writeString(address)
            .finishWirte();

        return builder.toArray();
    }

    public ByteBuffer toBuffer() {
        final IoBuf builder = IoBuf.allocate(32)
            .writeInt(userId)
            .writeString(name)
            .writeString(address)
            .finishWirte();

        return builder.toBuffer();
    }

    public static Student newBuilder() {
        return new Student();
    }

    public static Student parseFrom(byte[] bs) {
        return parseBuffer(IoBuf.wrap(bs));
    }

    public static Student parseFrom(ByteBuffer buffer) {
        return parseBuffer(IoBuf.wrap(buffer));
    }

    private static Student parseBuffer(IoBuf buder) {
        Student student = Student.newBuilder();

        student.userId = buder.readInt();
        student.name = buder.readString();
        student.address = buder.readString();

        return student;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Student{");
        builder.append("\n\tuserId:").append(userId);
        builder.append("\n\taddress:").append(address);
        builder.append("\n}");

        return builder.toString();
    }

//	@Override
//	public Student readMsg(IoBuf buf) {
//		this.userId = buf.readInt();
//		this.name = buf.readString();
//		this.address = buf.readString();
//		return this;
//	}
//
//	@Override
//	public void writeMsg(IoBuf buf) {
//		buf.writeInt(userId);
//		buf.writeString(name);
//		buf.writeString(address);
//	}

	@Override
	public Message clone() {
		return new Student();
	}
}
