package org/itas;
import org/itas/TComm;

// -> client to server
// <- server to client
// <-> client & server

///////////////////系统提示////////////////////
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
	Wife wife;
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
