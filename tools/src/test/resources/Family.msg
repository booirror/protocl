package org.itas;

import org.itas.Comm;

//家庭
message Famils {
	Comm.Man wife;
	Comm.Man husBand;
	array<Comm.Man> child;
}

//国家
message Country{
	map<string,Comm.Man> persons;
	ArrayTest arrays;
	SetTest sets;
	MapTest maps;
}

message ArrayTest { 
	array<string> name;				// 名字
	array<bool> isMerry;				// 是否已婚
	array<int8> children;				// 孩子数
	array<int16> age;					// 年龄
	array<int32> money;				// 拥有金钱
	array<int64> mobile;				// 手机号
	array<string> address;				// 地址
	array<float32> value1;			
	array<float64> value2;
	array<Comm.Sex> sex;					// 性别
	array<Comm.Man> sex1;			// 性别
}

message SetTest {
	set<string> name;				// 名字
	set<bool> isMerry;				// 是否已婚
	set<int8> children;				// 孩子数
	set<int16> age;					// 年龄
	set<int32> money;				// 拥有金钱
	set<int64> mobile;				// 手机号
	set<string> address;				// 地址
	set<float32> value1;			
	set<float64> value2;
	set<Comm.Sex> sex;					// 性别
	set<Comm.Man> sex1;					// 性别
}

message MapTest {
	map<string,Comm.Man> name;				// 名字
	map<bool,Comm.Man> isMerry;				// 是否已婚
	map<int8,Comm.Man> children;				// 孩子数
	map<int16,Comm.Man> age;					// 年龄
	map<int32,Comm.Man> money;				// 拥有金钱
	map<int64,Comm.Man> mobile;				// 手机号
	map<string,Comm.Man> address;				// 地址
	map<float32,Comm.Man> value1;			
	map<float64,Comm.Man> value2;
	map<Comm.Sex,Comm.Man> sex;					// 性别
	map<Comm.Man,Comm.Sex> sex1;					// 性别
}



