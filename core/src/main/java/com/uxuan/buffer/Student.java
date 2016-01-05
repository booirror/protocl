package com.uxuan.buffer;

import java.nio.ByteBuffer;

/**
 * @author liuzhen
 *         <p/>
 *         Email liuxing521a@163.com
 *         CreatedTime 2016-01-05 22:40:00
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
        final UXBuilder builder = UXBuilder.allocate(32)
            .writeInt(userId)
            .writeString(name)
            .writeString(address)
            .finishWirte();

        return builder.toArray();
    }

    public ByteBuffer toBuffer() {
        final UXBuilder builder = UXBuilder.allocate(32)
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
        return parseBuffer(UXBuilder.wrap(bs));
    }

    public static Student parseFrom(ByteBuffer buffer) {
        return parseBuffer(UXBuilder.wrap(buffer));
    }

    private static Student parseBuffer(UXBuilder buder) {
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
}
