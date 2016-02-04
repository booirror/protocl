package com.uxuan.protocl.parse;

import java.util.List;

import com.uxuan.protocl.AttrType;
import com.uxuan.protocl.LanguageManager;
import com.uxuan.protocl.MessageType;
import com.uxuan.protocl.module.Line;
import com.uxuan.protocl.module.ProtoclMsg;

final class MsgParser implements Parser {
	
	private final Parser parent;
	
	private final ProtoclMsg msg;
	
	public MsgParser(Parser parent, ProtoclMsg msg) {
		this.parent = parent;
		this.msg = msg;
	}
	
	@Override
	public Parser parse(Line line) {
		if (line.isEmpty()) {
			return this;
		}
		
		List<String> groups = line.toGroup();
		
		String group0 = groups.get(0);
		if (MessageType.PACKAGE.isType(group0)) {
			defindException(line);
		}
		
		if (MessageType.IMPORT.isType(group0)) {
			defindException(line);
		}
		
		if (MessageType.ENUM.isType(group0)) {
			defindException(line);
		}
		
		if (MessageType.MESSAGE.isType(group0)) {
			defindException(line);
		}
		
		if ("{".equals(group0)) {
			defindException(line);
		}
		
		if (groups.size() == 1 && "}".equals(group0)) {
			return parent;
		}
		
		if (groups.size() != 3 || !";".equals(groups.get(2))) {
			defindException(line);
		}
		
		
		int index = group0.indexOf('<');
		int lastIndex = group0.indexOf('>');
		//只有一个< 或者 >号
		if ((index == -1 && lastIndex != -1) || 
			(index != -1 && lastIndex == -1) || 
			(index != -1 && lastIndex != group0.length() - 1)) {
			defindException(line);
		} 
		
		
		String defind = group0;
		if (index != -1) {
			defind = defind.substring(0, index);
		}
		
		LanguageManager instance = LanguageManager.getInstance();
		AttrType attrType = instance.getType(group0);

		instance.getAttribute(attrType);
		if (attrType == null) {
			
		} else if (AttrType.ARRAY == attrType) {
			
		} else if (AttrType.SET == attrType) {
			
		} else if (AttrType.MAP == attrType) {
			
		} else {
			
		}
		
		
		
		String type = group0.substring(0, index);
		AttrType attrType = new AttrType(type);
		
		String content = group0.substring(index + 1, lastIndex);
		int indexMap = content.indexOf(',');
		if (indexMap == -1) {
			attrType.addGeneric(content);
		}
		
		String[] strs = content.split(",");
		if (strs.length != 2 || strs[0].length() == 0 || strs[1].length() == 0) {
			throwDefindException(line);
		}
		
		for (String str : strs) {
			attrType.addGeneric(str);
		}
//		if (msg.isType(MessageType.ENUM)) {
//			msg.addAttribute(ProtoclAttr.newBuilder()
//					 .setName(groups.get(0))
//					 .setMessage(msg)
//					 .setIndex(Integer.parseInt(groups.get(1)))
//					 .setType(instance.getAttribute(AttrType.ENUM))
//					 .build());
//		}  else if (msg.isType(MessageType.MESSAGE)) {
//			AttrType attrType = doAttrDefType(group0, line);
//			msg.addAttribute(new ProtoclAttr(msg, attrType, groups.get(1)));
//		} 
//		
		return this;
	}
	
}
