#pragma once

#include <string>
#include "base.h"

enum NetEvent
{
	NT_ON_ERROR,
	NT_ON_CONNECTED,
	NT_ON_DISCONNECTED,
	NT_COUNT,
};
typedef void EventFun( CCObject* self, int ret );
typedef void MsgFun( CCObject* self, void* ret );
typedef void SocketRecvError( void );

class NetSocket;
//网络底层库,可用select模型
class Net
{
public:
	Net(void);
	~Net(void);

	bool  connect( const string& ip, int port );

	void registerEvent( NetEvent id, EventFun fun, void* self = NULL );
	void registerMessage( int id, MsgFun fun, void* self = NULL );

	void sendData( const char* data, int size );

	void update();

	bool IsConnected() { return m_bIsConnected; }

	void closeSocket( void );

	void setSocketRecvError( SocketRecvError* p ){ m_callbackRecvError = p; }

	void skipOneFrame() { ++skipCount; }

	void setSkipFrame( int num ) { skipCount = num; }
	
	void clearFrameSkipCounter() { skipCount = 0; }

	int getSkipCount( void ) const { return skipCount; }



	// -1 means: no wait
	// wait util specified message has been recieved
	void waitUntilReceived( int cmd = -1 ) { waitCmd = cmd; }

	int getWaitCmd() const { return waitCmd; }

	bool needWait() const { return waitCmd > 0; }

	bool DnsParse( const char* domain, char* ip );

	bool DealData( const char * buf, int len );
private:
	NetSocket* m_netSocket;
	bool m_bIsConnected;
	char* half_buffer;
	bool isContinue;
	int needLen,overlen;
	int skipCount;
	int waitCmd;

	int	 m_nNextRecvPackCount;
	int  m_nNextSendPackCount;

	SocketRecvError* m_callbackRecvError;
	std::string ReadOverStr;
	void recvData();
    
    bool isInvalid;
	//void recvData1();

};
