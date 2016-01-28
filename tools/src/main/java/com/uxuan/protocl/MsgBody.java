package com.uxuan.protocl;

import java.util.LinkedList;
import java.util.List;

import com.uxuan.protocl.util.Next;
import com.uxuan.protocl.util.StringUtils;

/**
 * message body
 *
 * @author liuzhen
 * Email liuxing521a@163.com<br>
 * CreateTime 2016年1月10日 下午7:44:43
 */
public class MsgBody implements Next {
	
	/** message name */
	private String name;
	/** message attributes*/
	private List<MsgField> fields;
	
	public MsgBody(String name) {
		this.name = name;
		this.fields = new LinkedList<>();
	}
	
	public List<MsgField> getMsgFields() {
		return fields;
	}
	
	public void addField(MsgField field) {
		fields.add(field);
	}

	public void clear() {
		fields.clear();
	}
	
	public boolean isArrayExist() {
		return fields.stream().filter(f->f.isArrayExist()).findFirst().isPresent();
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("message ").append(name);
		builder.append(next(1, 1)).append('{');
		
		for (MsgField filed : fields) {
			builder.append(StringUtils.nextLine(1, 1)).append(filed);
		}
		
		builder.append('}');
		return builder.toString();
	}
}
