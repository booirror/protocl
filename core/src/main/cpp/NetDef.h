
#pragma  once 

#include "net.h"

class ClientSetup;

extern Net g_Net;
extern Net battle_Net;

extern bool   g_bFastLogin;

typedef signed char  int8;
typedef short int16;
typedef long long int64;

const int BUF_SIZE = 1024 * 10;

#define Writeint64(buf,data) memcpy(buf,&data,8); buf += 8;
#define Writeint(buf,data)	memcpy(buf,&data,4); buf += 4;
#define Writeint16(buf,data) memcpy(buf,&data,2); buf += 2;
#define Writeint8(buf,data)	memcpy(buf,&data,1); buf += 1;
#define Writestring(buf,data) {int len = data.length(); Writeint16(buf,len); memcpy(buf,data.c_str(),len); buf += len;}
#define WriteArray(buf,t, data) { int len=data.size(); Writeint16(buf,len); for(int i=0;i<len;i++){ Write##t(buf,data[i]); }  }

#define Readint(buf,data)	data =*((int*)(buf)); buf += 4;

#define Readint64(buf,data) memcpy(&data,buf,8); buf += 8;
#define Readint16(buf,data) data =*((short*)(buf)); buf += 2;
#define Readint8(buf,data)	data =*((signed char*)(buf)); buf += 1;
#define Readstring(buf,data) {int len; Readint16(buf,len); data.append(buf,len); buf+=len; }

#define ReadArray(buf,t,data) {int len; Readint16(buf,len); data.resize(len); for(int i=0;i<len;i++){Read##t(buf,data[i]);}  }

#define BEING_DEAL()	int cmd; Readint16(data,cmd); switch(cmd) {
#define CMD_DEAL(cmd)	case MSG_##cmd:{	pk::cmd a; Read##cmd(data,a); pk::On##cmd(&a);		}break;
#define END_DEAL()		}


#define BeginSend(cmd) char _buf[BUF_SIZE]; char* buf=_buf+2; Writeint16(buf,MSG_##cmd);
#define EndSend(cmd)   int _len = buf-_buf; memcpy(_buf,&_len,2);if(cmd==1) g_Net.sendData(_buf,_len);else if(cmd==2) battle_Net.sendData(_buf,_len);

