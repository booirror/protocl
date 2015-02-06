package org.itas;

import java.nio.ByteBuffer;
import org.itas.buffer.BubferBuilder;
import org.itas.buffer.AbstractMessage;

public class TComm {

	static final byte PREFIX = 0x02;

	private TComm() {
	} 

public static class Chirld extends AbstractMessage{

	private String nickname;

	private Chirld() {
	}

	public String getNickname() {
		return this.nickname;
	}

	public Chirld setNickname(String data) {
		this.nickname = data;
		return this;
	}

	@Override
	public Chirld readMsg(ByteBuffer buf) {
		this.nickname = readString(buf);
		return this;
	}

	@Override
	public void writeMsg(BubferBuilder builder) {
		writeString(builder, this.nickname);
	}

	public static Chirld newBuilder() {
		return new Chirld();
	}

	@Override
	public String toString() {
		StringBuffer strBuf = new StringBuffer();

		strBuf.append("Chirld{");
		strBuf.append("\n\tnickname:").append(nickname);
		strBuf.append("\n}");

		return strBuf.toString();
	}
}

}