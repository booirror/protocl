package org/itas;
import org/itas/TComm;

message Wife {
	string nickname;
	Chirld chirld;
	vector<Chirld> chirlds;
}

message Player
{
	bool merry;
	int8 chirld;
	int16 age;
	int whifes;
	int64 phoneNum;
	float credit;
	double money
	string nickname;
	vector<bool> chirldMerry;
	vector<int8> chirldAges;
	vector<string> chirldNames;
	vector<Wife> wifs;
	object<Wife> wife;
}


message PlayerRequest ->
{
	Player player;			
}

message PlayerResponse <-
{
	Player player;
}

message PlayerInfo <->
{
	Player player;
}
