package org.itas;

//性别
enum Sex {
	female = 0;//女
	male = 1;//男
}

message Man {
	string name;				// 名字
	bool isMerry;				// 是否已婚
	int8 children;				// 孩子数
	int16 age;					// 年龄
	int32 money;				// 拥有金钱
	int64 mobile;				// 手机号
	string address;				// 地址
	float32 value1;			
	float64 value2;
	Sex sex;					// 性别
	string identity;			// 身份证
}
