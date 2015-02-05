package org.itas;

import java.nio.ByteBuffer;
import org.itas.buffer.Dispatch;
import org.itas.Test.*;

public abstract class TestEvent<T> extends Dispatch<T> {

	public abstract void playerRequest(T value, PlayerRequest request);

	public abstract void playerInfo(T value, PlayerInfo request);

	@Override
	public final void dispatch(T data, byte suffix, ByteBuffer buf) throws Exception {
		switch(suffix) {
		case PlayerRequest.SUFFIX:
			playerRequest(data, PlayerRequest.fromBuffer(buf));
			break;
		case PlayerInfo.SUFFIX:
			playerInfo(data, PlayerInfo.fromBuffer(buf));
			break;
		}
	}

}