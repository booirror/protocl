#include "Netdef.h"
#include "message.h" 
#include "package.h" 

extern void DoMessage_logic_playerInfo(char* data);
extern void DoMessage_battle(char* data);
extern void DoMessage_friends(char* data);


static string	g_SkipDoMessageData;

void DoMessage(char* data, int data_len)
{
	int nCmd = *(const short*)(data);
    
	if( g_Net.needWait() )
	{
		char szLen[256];
		memset( szLen, 0, sizeof(szLen) );
		*(short*)szLen = (short)data_len + 2;
		
		g_SkipDoMessageData.append(szLen, 2);

		g_SkipDoMessageData.append(data, data_len);

		if( g_Net.getWaitCmd() == nCmd )
		{
			string str = g_SkipDoMessageData;
			g_Net.waitUntilReceived( -1 );
			g_SkipDoMessageData.clear();

			int nSize = (int)str.size();
			int nPos = 0;
			while( nPos < nSize )
			{
				const char* pszData = str.c_str() + nPos;
				short nMsgLen = *(short*)pszData;
				
				DoMessage( (char*)pszData + 2, nMsgLen );

				nPos += nMsgLen;
			}
		}

		return;
	}

	DoMessage_logic_playerInfo(data);
	DoMessage_battle(data);
	DoMessage_friends(data);
}
