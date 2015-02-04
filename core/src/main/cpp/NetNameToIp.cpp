#include <stdio.h>

#ifdef WIN32
#include <Ws2tcpip.h>

#else
#include <sys/types.h>
#include <sys/socket.h>
#include <netdb.h>
#include <string.h>
#endif

#include "cocos2d.h"
#if (CC_TARGET_PLATFORM == CC_PLATFORM_ANDROID)
#include <netinet/in.h>
#endif
//change by xp

int domainname2addr(const char * domain_name, int port, struct sockaddr_in * result) {
	struct addrinfo hints;
	char serviceName[64];
	struct addrinfo * aiList = NULL;
	int r = 0;
	//
	sprintf(serviceName, "%d", port);
	memset(&hints, 0, sizeof(hints));
	hints.ai_family = AF_INET;
	hints.ai_socktype = SOCK_STREAM;
	hints.ai_protocol = IPPROTO_TCP;

	r = getaddrinfo(domain_name, serviceName, &hints, &aiList);
	if (r == 0) {
		memcpy(result, aiList->ai_addr, sizeof(*result));
		freeaddrinfo(aiList);
	}
	return r;
}
