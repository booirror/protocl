package com.uxuan.buffer.tools;

import static com.uxuan.buffer.tools.util.StringUtils.firstCharLowerCase;
import static com.uxuan.buffer.tools.util.StringUtils.firstCharUpCase;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.uxuan.buffer.tools.util.MsgStatus;

public class MsgBody {
	
	/**
	 * 消息名称
	 */
	private String msgName;
	
	
	/**
	 * 消息所在消息file中的序号, short的后2位字节
	 */
	private short msgOrder;
	
	/**
	 * 消息体列表
	 */
	private List<MsgField> fields;
	
	/**
	 * 消息类型列表
	 */
	private List<MsgStatus> msgTypes;
	
	public MsgBody(short msgOrder, String msgName) {
		this.msgOrder = msgOrder;
		this.msgName = msgName;
		this.fields = new LinkedList<>();
		this.msgTypes = new ArrayList<>(2);
	}
	
	public String getMsgName(boolean isFirstUpCase) {
		return isFirstUpCase ? firstCharUpCase(msgName) : firstCharLowerCase(msgName);
	}

	public List<MsgField> getMsgFields() {
		return fields;
	}
	
	public void addField(MsgField field) {
		fields.add(field);
	}
	
	public List<MsgStatus> getMsgTypes() {
		return msgTypes;
	}
	
	public boolean isTypeExist(MsgStatus msgType) {
		return msgTypes.contains(msgType);
	}

	public void addMsgType(MsgStatus msgType) {
		this.msgTypes.add(msgType);
	}

	public void addAllMsgType(List<MsgStatus> msgTypes) {
		this.msgTypes.addAll(msgTypes);
	}
	
	/**
	 * 获取消息在消息文件中的16进制序号
	 * @return
	 */
	public String getHexOrder() {
		String hex;
		if (msgOrder <= 0xF) {
			hex = "0x0" + Integer.toHexString(msgOrder);
		} else {
			hex = "0x" + Integer.toHexString(msgOrder);
		}
		
		System.out.println(hex);
		return hex;
	}
	
	public void clear() {
		msgName = "";
		fields.clear();
		msgTypes.clear();
	}
	
	public boolean hasArray() {
		for (MsgField info : fields) {
			if (info.getDefClassType() == MsgFiledType.FieldType.VECTOR) {
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("message ").append(msgName);
		for (MsgStatus sta : msgTypes) {
			builder.append(sta.getName());
		}
		builder.append("\n{");
		
		for (MsgField filed : fields) {
			builder.append("\n\t").append(filed);
		}
		
		builder.append("\n}");
		return builder.toString();
	}
}
