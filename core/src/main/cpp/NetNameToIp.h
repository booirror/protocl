#pragma once
#pragma comment( lib, "ws2_32.lib" )

#include <string>
#include "base.h"

extern int domainname2addr(const char * domain_name, int port, struct sockaddr_in * result);
