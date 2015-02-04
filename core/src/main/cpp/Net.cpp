#include "Net.h"
#include "base.h"
#include "NetNameToIp.h"
#include "zlib.h"
#pragma comment(lib,"zlib.lib")

#include "AlerterBox.h"

#ifdef WIN32
#include <winsock.h>
//#include <Ws2tcpip.h>
typedef int				socklen_t;
#else
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <sys/types.h>
#include <arpa/inet.h>
typedef int				SOCKET;

//#pragma region define win32 const variable in linux
#define INVALID_SOCKET	-1
#define SOCKET_ERROR	-1
//#pragma endregion
#endif

//change by xp
//extern bool		g_bFirstConnectLSToSendedMachineInfo;
extern string	g_SkipDoMessageData;

class NetSocket {

public:
	NetSocket(SOCKET sock = INVALID_SOCKET);
	~NetSocket();

	// Create socket object for snd/recv data
	bool Create(int af, int type, int protocol = 0);

	// Connect socket
	bool Connect(const char* ip, unsigned short port);

	// Bind socket
	bool Bind(unsigned short port);

	// Listen socket
	bool Listen(int backlog = 5); 

	// Accept socket
	bool Accept(NetSocket& s, char* fromip = NULL);


	// Send socket
	int Send(const char* buf, int len, int flags = 0);

	// Recv socket
	int Recv(char* buf, int len, int flags = 0);

	// Close socket
	int Close();

	// Get errno
	int GetError();

	inline bool IsSocketInvalid()
	{
		return (m_sock == INVALID_SOCKET);
	}

	//#pragma region just for win32
	// Init winsock DLL 
	static int Init();	
	// Clean winsock DLL
	static int Clean();
	//#pragma endregion

	// Domain parse
	static bool DnsParse(const char* domain, char* ip);

	NetSocket& operator = (SOCKET s);

	operator SOCKET ();

protected:
	SOCKET m_sock;

};


#ifdef WIN32
#pragma comment(lib, "wsock32")
#endif


NetSocket::NetSocket(SOCKET sock)
{
	m_sock = sock;
}

NetSocket::~NetSocket()
{
}

int NetSocket::Init()
{
#ifdef WIN32
	WSADATA wsaData;

	WORD version = MAKEWORD(2, 0);
	int ret = WSAStartup(version, &wsaData);

	if ( ret ) {		
		return -1;
	}
#endif

	return 0;
}

//this is just for windows
int NetSocket::Clean()
{
#ifdef WIN32
	return (WSACleanup());
#endif
	return 0;
}

NetSocket& NetSocket::operator = (SOCKET s)
{
	m_sock = s;
	return (*this);
}

NetSocket::operator SOCKET ()
{
	return m_sock;
}
//create a socket object win/lin is the same
// af:
bool NetSocket::Create(int af, int type, int protocol)
{
	m_sock = socket(af, type, protocol);
	if ( m_sock == INVALID_SOCKET ) {
//		ERROR_LOG( "socket FALSE" );
		return false;
	}

	//INFO_LOG( "socket m_sock:%d", m_sock );

	return true;
}


bool NetSocket::Connect(const char* ip, unsigned short port)
{
	if( m_sock == INVALID_SOCKET )
	{
		Create(AF_INET, SOCK_STREAM, IPPROTO_TCP);
	}
	struct sockaddr_in svraddr;
	//svraddr.sin_family = AF_INET;
	//svraddr.sin_addr.s_addr = inet_addr(ip);
	//svraddr.sin_port = htons(port);

	int addrRet =domainname2addr(ip, port, &svraddr);
	if(addrRet != 0)
		return false;

//	INFO_LOG( "connect ip:%s port:%d begin", ip, port );

	int ret = connect(m_sock, (struct sockaddr*)&svraddr, sizeof(svraddr));
	if ( ret == SOCKET_ERROR ) {
//		INFO_LOG( "connect ip:%s port:%d error", ip, port );
		return false;
	}

//	INFO_LOG( "connect ip:%s port:%d succ", ip, port );

	return true;
}

bool NetSocket::Bind(unsigned short port)
{
	struct sockaddr_in svraddr;
	svraddr.sin_family = AF_INET;
	svraddr.sin_addr.s_addr = INADDR_ANY;
	svraddr.sin_port = htons(port);

	int opt =  1;
	if ( setsockopt(m_sock, SOL_SOCKET, SO_REUSEADDR, (char*)&opt, sizeof(opt)) < 0 ) 
		return false;

	int ret = (int)::bind(m_sock, (struct sockaddr*)&svraddr, sizeof(svraddr));
	if ( ret == SOCKET_ERROR ) {
		return false;
	}
	return true;
}
//for server
bool NetSocket::Listen(int backlog)
{
	int ret = listen(m_sock, backlog);
	if ( ret == SOCKET_ERROR ) {
		return false;
	}
	return true;
}

bool NetSocket::Accept(NetSocket& s, char* fromip)
{
	struct sockaddr_in cliaddr;
	socklen_t addrlen = sizeof(cliaddr);
	SOCKET sock = accept(m_sock, (struct sockaddr*)&cliaddr, &addrlen);
	if ( sock == SOCKET_ERROR ) {
		return false;
	}

	s = sock;
	if ( fromip != NULL )
		sprintf(fromip, "%s", inet_ntoa(cliaddr.sin_addr));

	return true;
}

int NetSocket::Send(const char* buf, int len, int flags)
{
	int bytes;
	int count = 0;

	while ( count < len ) {

		bytes = send(m_sock, buf + count, len - count, flags);
		if ( bytes == -1 || bytes == 0 )
		{
//			ERROR_LOG( "send m_sock:%d len:%d bytes:%d", m_sock, len, bytes );
			return -1;
		}
		count += bytes;
	} 

	return count;
}

int NetSocket::Recv(char* buf, int len, int flags)
{	
	fd_set  set;
	FD_ZERO( &set );
	FD_SET(m_sock,&set);

	timeval val = { 0, 0 };

	// ‘⁄£…£œ£”œ¬±ÿ–Ë «+£±µƒƒ£ Ωº‡Ã˝ ˝æ›
	int ret = select(m_sock+1,&set,NULL,NULL,&val);

	if ( ret == -1 )
		return -1;
	if ( ret == 0 ) return  0;

#ifdef _MSC_VER
	ret = recv(m_sock, buf, len, 0);
#else
	ret = recv(m_sock, buf, len, 0 ); // TODO MSG_NOSIGNAL);      
#endif

	return ret;
}

int NetSocket::Close()
{
#ifdef WIN32
	if( m_sock != INVALID_SOCKET )
	{
		int ret = (closesocket(m_sock));
		m_sock = INVALID_SOCKET;
		return ret;
	}
	return 0;
#else
	// if INVALID_SOCKET undefine , set INVALID_SOCKET = -1
	if( m_sock != INVALID_SOCKET )
	{
		int ret = (close(m_sock));
		m_sock = INVALID_SOCKET;
		return ret;
	}
#endif
}

#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
#include <errno.h>
#endif

int NetSocket::GetError()
{
#ifdef WIN32
	return (WSAGetLastError());
#else
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
	return errno;
#endif
	return 0 ; // TODO (errno);
#endif
}

bool NetSocket::DnsParse(const char* domain, char* ip)
{
	struct hostent* p;
	if ( (p = gethostbyname(domain)) == NULL )
	{
//		ERROR_LOG( "DnsParse domain:%s =NULL", domain );
		return false;
	}

	sprintf(ip, 
		"%u.%u.%u.%u",
		(unsigned char)p->h_addr_list[0][0], 
		(unsigned char)p->h_addr_list[0][1], 
		(unsigned char)p->h_addr_list[0][2], 
		(unsigned char)p->h_addr_list[0][3]);
	return true;
}
const int maxLen = 1024*10;
static char buf[maxLen];

Net::Net(void)
{
	m_callbackRecvError = NULL;
    m_netSocket = new NetSocket();
	m_netSocket->Init();
	half_buffer = new char[maxLen];
	overlen = 0;
	needLen =0;
	skipCount = 0;
	waitCmd = -1;
	memset(half_buffer,0,maxLen);
	isContinue = false;
	m_nNextRecvPackCount = 0;
	m_nNextSendPackCount = 0;
	m_netSocket->Create(AF_INET, SOCK_STREAM, IPPROTO_TCP);
}

Net::~Net(void)
{
	if( half_buffer) delete(half_buffer);
	delete m_netSocket;
}


bool Net::connect( const string& ip, int port )
{
	if ( m_netSocket->Connect( ip.c_str(), port ) )
	{
		m_nNextRecvPackCount = 0;
		m_nNextSendPackCount = 0;

		m_bIsConnected = true;

		skipCount = 0;
		waitCmd = -1;
        isInvalid = true;
		//change by xp
/*
		if( !g_bFirstConnectLSToSendedMachineInfo )
		{
			g_bFirstConnectLSToSendedMachineInfo = true;
			extern void OnFirstConnectLSToSendedMachineInfo();
			OnFirstConnectLSToSendedMachineInfo();
		}
		*/
		return true;
	}
	else
	{
		m_bIsConnected = false;
		return false;
	}
}


void Net::registerEvent( NetEvent id, EventFun fun, void* self )
{

}

void Net::registerMessage( int id, MsgFun fun, void* self )
{

}

extern void DoMessage(char* data, int data_len);

//接受数据//
void Net::recvData()
{
	static const int MAX_LEN = 4096;
	static int		s_nNextDealData = 0;

	char buf[MAX_LEN] = {0,};
	bool bdealResult = true;
    
    
	do{
		if( m_netSocket->IsSocketInvalid() )
		{
            if (isInvalid == false)
            {
                break;
            }
            isInvalid = false;
			break;
		}
		if( skipCount > 0 ) {
//			INFO_LOG( "net skip count:%d", skipCount );
			--skipCount;

			s_nNextDealData = 1;

			break;
		} 
		int len = m_netSocket->Recv( buf, MAX_LEN, 0 );
		if( len == 0 && s_nNextDealData )
		{
			DealData( buf, 0 );
		}
		
		s_nNextDealData = 0;

		if( len > 0)
		{
			bdealResult = DealData( buf, len );//处理数据//
			if( !bdealResult ) {
				break; 
			}
		}
		else
		{
			if( len < 0)
			{
//				ERROR_LOG( "recvData len < 0" );

				if( m_callbackRecvError )
				{
					m_callbackRecvError();
				}
				closeSocket();//断开网络//
                
                if (isInvalid == false)
                {
                    break;
                }
                isInvalid = false;
			}
			break;
		}
	}while(1);
}
//处理数据库，及上次接收的时间值处理//
bool Net::DealData( const char * buf, int len )
{

	const char * readPtr;
	std::string str = string(buf, len);
	ReadOverStr += str;
    
   
    
	static int LEN_SIZE = 2;
	//int size_len = sizeof(short); 
	bool ret = true;
	readPtr = ReadOverStr.c_str();
	
	int remainLen = (int)(ReadOverStr.length());
	int dealLen = remainLen;
    
	while(remainLen >= LEN_SIZE * 2) //数据符合要求//
	{
		short datalen = *(short*)readPtr;
		if( (datalen & 0x8000) != 0)
		{
			datalen = (datalen & 0x7FFF);
			if (datalen > 4096)
			{
//				ERROR_LOG( "DealData datalen:%d < 0 remainLen:%d, DealData is Compressed!", datalen, remainLen );
				remainLen = 0;
				closeSocket();//断开网络// why need check m_callbackRecvError ,should close socket  and return error code
				if( m_callbackRecvError )
				{
					m_callbackRecvError();			
				}
				return false;
			}

			if(remainLen >= datalen)
			{
				Bytef compreBuf[4096];
				memset((char *)compreBuf, 0, sizeof(compreBuf));
				unsigned long desLen = sizeof(compreBuf);
				int skipLen = 2*LEN_SIZE;
				int realCompresDataLen = datalen-skipLen;
				
				int err = uncompress(compreBuf, &desLen, (Bytef *)readPtr+skipLen, realCompresDataLen);
				//int err =0;
				if (err >= 0) //uncompress succ
				{
					// compreDataStartPos: //压缩数据开始位置（相对于ReadOverStr)//
					int compreDataStartPos = dealLen-remainLen+skipLen;
					// otherDataLen£∫¥À—πÀı∞¸∫Û√Ê ˝æ›≥§∂»
					int otherDataLen = remainLen-datalen;
					std::string strData1 = "", strData2 = "", strData3 = "";
					const char *pBuff = ReadOverStr.c_str();
					strData1 = string((char *)pBuff, compreDataStartPos); //在压缩数据前面的数据//
					strData2 = string((char *)compreBuf, desLen); //解压后的数据//
					if (otherDataLen > 0) //说明后面还有数据//
					{
						strData3 = string(pBuff+compreDataStartPos+realCompresDataLen, otherDataLen); //保存数据块末尾的数据//
					}

					ReadOverStr.clear();
					ReadOverStr = strData1+strData2+strData3;

					readPtr = (char *)(ReadOverStr.c_str())+compreDataStartPos;
					int remainDataLen = desLen-realCompresDataLen;
					remainLen += remainDataLen; //整理数据信息//
					dealLen += remainDataLen;
					remainLen -= skipLen;
					datalen = *(short*)readPtr;
				}
				else
				{
//					ERROR_LOG( "uncompress error:%d tempDatalen:%d compressing data failed!", err, datalen );
					closeSocket();//断开网络//  why need check m_callbackRecvError ,should close socket  and return error code
					if( m_callbackRecvError )
					{
						m_callbackRecvError();			
					}
					return false;
				}
			}		
		}

		if( remainLen>= datalen) //数据足够长//
		{
			datalen -= LEN_SIZE;
			remainLen -= LEN_SIZE; //长度减少//
			readPtr += LEN_SIZE; //移位//
			str.clear();
			str.assign(readPtr, datalen);
			remainLen -= datalen;
			readPtr += datalen;

			int nCmd = *(const short*)(str.c_str());
			int nRecvPackCount = (nCmd & 0xF800) >> 11;
			if( nRecvPackCount != m_nNextRecvPackCount )
			{
//				ERROR_LOG( "nCmd:%d nRecvPackCount[%d] != m_nNextRecvPackCount[%d]", ( nCmd & 0x7FF ), nRecvPackCount, m_nNextRecvPackCount );
			}
			if( ++m_nNextRecvPackCount > 31 )
				m_nNextRecvPackCount = 0;

			*(short*)(str.c_str()) = short( nCmd & 0x7FF );

			//ERROR_LOG( "nCmd:%d   len[%d]", ( nCmd & 0x7FF ), datalen );
			DoMessage((char*)(str.c_str()), int( str.size() ) );
			if ( skipCount > 0 ) {
				ret = false;
				break;
			}
		}
		else
		{
			break;//数据长度不够退出循环//
		}
	}

	if( dealLen != remainLen)
	{
		ReadOverStr = ReadOverStr.substr(dealLen - remainLen);
	}
	return ret;

}

//void Net::recvData1()
//{
//	int bufLen = 0;
//	do {
//		if( isContinue) // «∑Ò”–∞Î∞¸
//		{
//			int len = m_netSocket->Recv(buf, needLen,0); //Ω” ’∞Î∞¸
//			if( len < 0) //Ω” ’≥ˆ¥Ì£¨ Ã¯≥ˆ
//			{
//				if( m_callbackRecvError )
//					m_callbackRecvError();
//
//				break;
//			}
//			if( len != needLen)
//			{
//				if( len == 0) continue;
//				if( len < needLen)
//				{
//					needLen -= len;
//					memcpy(half_buffer+overlen, buf, len);//—π»Î ˝æ›
//					overlen += len;
//					half_buffer[overlen]='\0';
//				}
//				else if( m_callbackRecvError )
//				{
//					m_callbackRecvError();
//				}
//				else
//				{
//					memcpy(half_buffer+overlen, buf, len);
//					half_buffer[overlen+len]='\0';
//					isContinue = false;
//				}
//			}
//			else
//			{
//				memcpy(half_buffer+overlen, buf, len);// …˙≥…—π»Î ˝æ›
//				half_buffer[overlen+len]='\0';
//				isContinue = false;
//			}
//		}
//		else
//		{
//			const int LEN_SIZE = 2;
//			int size = 0;
//			int len = m_netSocket->Recv( (char*)(&size), LEN_SIZE, 0 );
//			memset(half_buffer,0, 1024*10);
//			if( len < 0) //Ω” ’≥ˆ¥Ì£¨ Ã¯≥ˆ
//			{
//				if( m_callbackRecvError )
//					m_callbackRecvError();
//
//				break;
//			}
//			if ( len != LEN_SIZE )
//			{
//				if( len != 0 )
//				{
//					if( m_callbackRecvError )
//						m_callbackRecvError();
//					//error ;
//				}
//				break;
//			}
//			if( size < 4 || size > 4096) // ≈–∂œ «∑ÒŒ™”—…∆µƒ ˝æ›∞¸
//			{
//				if( m_callbackRecvError )
//					m_callbackRecvError();
//				break;
//			}
//			size -= LEN_SIZE;
//			len = m_netSocket->Recv( buf, size, 0 );
//			if( len < 0) //Ω” ’≥ˆ¥Ì£¨ Ã¯≥ˆ
//			{
//				if( m_callbackRecvError )
//					m_callbackRecvError();
//
//				break;
//			}
//			if ( len != size )
//			{
//				if( len != 0 )
//				{
//					if( len < size)// ˝æ›≤ªπª£¨…˙≥…∞Î∞¸
//					{
//						isContinue = true;
//						memcpy(half_buffer,buf, len);
//						half_buffer[len]='\0';
//						needLen = size - len;
//						overlen = len;
//					}
//					else if( m_callbackRecvError )
//						m_callbackRecvError();
//					//error ;
//				}
//				else //Ω” ’µƒ ˝æ›≤ªπª£¨ …˙≥…∞Î∞¸
//				{
//					isContinue = true; //…˙≥…KEY
//					//memcpy(half_buffer,buf, len);
//					half_buffer[len]='\0';
//					needLen = size - len;
//					overlen = len;
//					continue;
//				}
//			}
//			else
//			{
//				memcpy(half_buffer, buf, len);//◊È≥…Õ¯¬Á ˝æ›
//			}
//		}
//		if( !isContinue)
//		{
//			DoMessage(half_buffer);
//			isContinue = false;
//			overlen = 0;
//			needLen =0;
//			memset(half_buffer,0, 1024*10);
//		}
//
//	}while(1);
//}

void Net::update()
{
	recvData();	
}

void Net::sendData( const char* data, int size )
{
	if( m_netSocket->IsSocketInvalid())
	{
		return;
	}
	
	m_netSocket->Send(data,size);
}

void Net::closeSocket()
{
//	INFO_LOG( "closeSocket" );
	if( m_netSocket )
	{
		m_netSocket->Close();
		m_bIsConnected = false;
	}
}

bool Net::DnsParse(const char* domain, char* ip)
{
	if( !m_netSocket )
		return false;

	return m_netSocket->DnsParse( domain, ip );
}