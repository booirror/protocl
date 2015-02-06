package org.itas;

import java.nio.ByteBuffer;
import org.itas.buffer.NetService;
import org.itas.LoginInfo.*;

public abstract class LoginInfoEvent<T> extends NetService<T> {

	@Override
	public final byte PREFIX() {
		return LoginInfo.PREFIX;
	}

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

	public abstract void playerRequest(T value, PlayerRequest request);

	public abstract void playerInfo(T value, PlayerInfo request);

}