package org.itas;

import java.nio.ByteBuffer;
import org.itas.buffer.NetService;

public abstract class TCommEvent<T> extends NetService<T> {

	@Override
	public final byte PREFIX() {
		return TComm.PREFIX;
	}

	@Override
	public final void dispatch(T data, byte suffix, ByteBuffer buf) throws Exception {
		switch(suffix) {
		}
	}

}