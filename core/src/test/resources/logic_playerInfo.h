package mypack;
// -> client to server
// <- server to client
// <-> client & server

///////////////////系统提示////////////////////
struct Alertresponse <-
{
	int16 number;			// 对应language提示号
}

///////////////////发送玩家token////////////////////
struct SendTokenToServen ->
{
	string token;			// 玩家id
}
//========================协议基础信息========================
// 商品
struct Goods
{
	string rid;					// 商品ID
	int coin;					// 购买需要游戏币
	int money;					// 购买需要砖石
}


//我拥有的卡
struct MyCard
{
    string  cardID;         //卡的资源id//
    int8    type;           //1是普通卡，2是金卡//
    int16   count;          //数量//
	int8    isNew;			//卡片新旧    1是新卡，2是旧卡
}

//卡组信息
struct CardGroup
{
    int64   groupID;         //ID//
    string  groupName;      //名字//
    int8    usable;          //是否可用，因为没满的卡组是不可用的，1是可用，2是不可用//
}

//奖励非道具列表
struct PlayerProperty
{
	int8 type;						// 奖励类型[1：游戏币;2:充值币;3:精华; 4:皮肤;5:卡牌;6:经验;7:当前经验;8:升级经验;9:当前等级,10:新手引导步骤数，11：任务数量，12：任务完成数量]
	int value;						// 奖励值
}

//返回玩家信息
struct PlayerInfoResult <-
{
	int playId;				// 玩家Id
	string name;			// 昵称
    vector<PlayerProperty> propertys; //玩家属性值列表 类型[1：游戏币;2:充值币;3:精华;7:当前经验;8:升级经验;9:当前等级,10:新手引导步骤数，11：任务数量，12：任务完成数量 ]
}


// 奖励道具列表
struct RewardItem
{
	string itemRid;					// 道具ID
	int8 type;						// 道具类型  1:card;2:金卡;3:皮肤,
	int8 count;						// 道具数量
}
/////////////////////////////战斗匹配界面///////////////////////////////
struct HeroAndCardGroupRequest  ->
{
    
}

struct HeroAndCardGroupResult <-
{
	string heroId;						// 默认英雄Id
	int64 groupId;						// 默认卡组Id
    vector<string> heroIDList;          // 英雄列表//
    vector<CardGroup> groupList;    	// 卡组列表，这个里面只需要发送，能够使用的卡组//
}

struct StartBattleRequest  ->
{
    int64   groupID;     	   			// 卡组ID
    string  heroID;         			// 英雄id
    string 	historyRid;					// 故事Id
	int8    matchType;      			// 匹配类型[1:休闲; 2:积分; 3:竞技; 4:故事]
}

//返回战斗服务器地址//
struct SendBattleServenToken  <-
{
	int port;			// 端口
	string ip;			// 战斗服务器IP地址
}

// 取消匹配
struct CancelBattleRequest ->
{
}

struct CancelBattleResponse <-
{
	int8 result;		// 取消结果 [1成功;2:失败]
}
////////////////////////卡的信息////////////////////////



//玩家套卡请求//
struct MyCardListRequest ->
{
    
}
struct MyCardListResult <-
{
    vector<MyCard> cardList;  			//玩家拥有的卡即其数量//
    vector<CardGroup> groupList;
}


//请求此卡组的卡列表//
struct CardGroupInfoRequest ->
{
    int64   groupID;
}
struct CardGroupInfoResult <-
{
    int64   groupID;
    string  groupName;                 	// 名字//
    vector<MyCard> cardList;        	// 卡组里面的卡列表//
}


//新建卡组
struct CreateGroupRequest ->
{
	string name;						// 卡组名称
}
struct CreateGroupResponse <-
{
	 int64   groupID;					// 建立结果
	 string name;
}


// 选择卡组进行组卡
struct ChooseGroupRequest ->
{
	int64 groupId;
}

//删除卡组
struct DelGroupRequest ->
{
	int64 groupId;						// 卡组id
}
struct DelGroupResponse <-
{
	int8 result;						// 选择结果[1:成功;其他以语言包方式返回]
}

//修改卡组名字
struct UpGroupNameRequest ->
{
	string groupName;						// 卡组ming
}
struct UpGroupNameResponse <-
{
	int8 result;						// 选择结果[1:成功;其他以语言包方式返回]
}


// 玩家选卡请求
struct  CardChooseRequest ->
{
	MyCard choose;						// 选择的卡片
}
struct CardChooseResponse <-
{
	int8 result;						// 选择结果[1:成功;其他以语言包方式返回]
}


// 玩家删除卡请求
struct DelCardRequest ->
{
	MyCard choose;						// 选择的卡片
}
struct DelCardResponse <-
{
	int8 result;						// 选择结果[1:成功;其他以语言包方式返回]
}

//制作卡牌界面精华请求
struct MakeCardViewRequest ->
{

}
struct MakeCardViewResponse <-
{
	int essence;						//精华数量
}

//制作卡片
struct MakeCardRequest ->
{
	MyCard choose;						// 选择的卡片
}
struct MakeCardResponse <-
{
	int8 result;						// 选择结果[1:成功;其他以语言包方式返回]
}


//分解卡片
struct ResolveCardRequest ->
{
	MyCard choose;						// 选择的卡片
}
struct ResolveCardResponse <-
{
	int8 result;						// 选择结果[1:成功;其他以语言包方式返回]
}


// 选择卡牌
struct ChooseArenaCardRequest ->
{
	string rid;							// 卡牌资源Id
}
struct ChooseArenaCardResponse <-
{
	int8 result;						// 结果[1:成功;2:卡组已满;3:卡组已满，英雄已选择，等待竞技;4:竞技次数不足]
	vector<MyCard> cards;				// 随机卡牌
}


// 请求随机英雄
struct ChooseHeroRequest ->
{
	string rid;							// 英雄资源Id
}
struct ChooseHeroResponse <-
{
	int8 result;						// 结果[1:成功;2:选择成功]
	vector<string> rid;					// 随机英雄资源Id
}


// 请求竞技场卡组英雄信息
struct ArenaInfoRequest ->
{
}
struct ArenaInfoResponse <-
{
	string heroRid;						// 英雄资源Id
	vector<MyCard>	cards;				// 卡牌列表
}


// 奖励结果
struct RewardResult <-
{
	int8 type;                            // 获取类型	[101:积分战斗; 103:天梯战斗; 105:竞技战斗; 107:完成竞技; 108:任务奖励; 109:充值;
	vector<PlayerProperty> propertys;     // 非物品奖励
	vector<RewardItem> items;             // 物品奖励
}

// 卡包信息
struct CardBagInfo 
{
	string rid;							// 卡包资源Id
	int coin;							// 游戏币价格
	int16 money;						// 充值币价格
}

//请求卡包数
struct OpenCardsBoxCountRequest ->
{
    
}

struct OpenCardsBoxCountResponse <-
{
    int count;              			//拥有的卡包数量
    vector<CardBagInfo> boxsPrice;  	//商店卡包价格
}

//开卡包
struct OpenCardsBoxRequest ->
{
    int8 type;                  //点击按钮类型 1为开卡包，2为开全部卡包
}
struct OpenCardsBoxResponse <-
{
	int8 type;
    vector<MyCard> cardIDs;
}





//========================任务协议========================
// 任务目标
struct MyTaskObjective
{
	int8 type;								// 目标类型
	string rid;								// 关联的其他资源id
	int8 nowCount;							// 现在已完成数量
	int8 needCount;							// 任务需要的数量
}

// 任务信息
struct MyTask
{
	string taskId;							// 任务ID，用于提交任务
	int8 status;							// 任务状态[1:接受；2:完成未提交;3:完成已提交]	
	string taskRid;							// 任务资源Id
	vector<MyTaskObjective> objective;		// 任务目标列表
	vector<PlayerProperty> properties;		// 非物品奖励
	vector<RewardItem> items;            	// 物品奖励;
}

// 请求任务
struct TaskRequest ->
{
}
struct TaskResponse <-
{
	int8 isReflushAble;						// 是否可以刷新任务
	vector<MyTask> tasks;					// 任务列表
}

// 刷新任务任务[TaskStatusChanged返回]
struct TaskReflushRequest ->
{
	string taskId;							// 任务ID
}

// 提交任务[TaskStatusChanged返回]
struct TaskSubmitRequest ->
{
	string taskId;							// 任务ID
}
// 任务目标改变
struct TaskStatusChanged <-
{
	vector<MyTask> tasks;					// 改变的任务目标
}





// 请求商品
struct GoodsListRequest ->
{
	int8 shopType;				// 商店类型[1:英雄; 2:装备皮肤; 3:场景皮肤; 4:礼包]
	int16 pageNum;				// 请求页数
}
struct GoodsListResponse <-
{
	int8 shopType;				// 商店类型
	int16 maxPage;				// 最大页数
	vector<Goods> goods;		// 商品列表
}


// 请求购买商品
struct GoodsBuyRequest ->
{
	string rid;					// 商品ID
	int8 type;                  //交易类型，1：金币2：钻石
}
struct GoodsBuyResponse <-
{
	string rid;					//商品ID
	int8 result;                //结果[1:成功;]
}




////////////////////////////////////////
///////英雄皮肤装扮///////////////
struct HeroViewRequest ->
{
	string heroId;	                //默认英雄
}
struct HeroViewResponse <-
{
	string heroId;	                //默认英雄
	vector<string> ownHero;         //拥有英雄
	vector<string> initskins;        	//默认皮肤
}

//请求皮肤
struct HeroSkinsRequest ->
{
	string skinId;                //皮肤服务器ID
}
struct HeroSkinsResponse <-
{
	vector<string> skins;            //拥有皮肤
}


///////场景皮肤装扮///////////////
//请求场景界面
struct SceneViewRequest ->
{
	int8 type;                     		//类型 [1为地板，2为墙，3为装饰品]
}
struct SceneViewResponse <-
{
	int8 type;                     		//类型
	vector<string> Skins;        		//拥有的地板
	vector<string> initSkins;    		//默认的地板
}


//请求换皮肤
struct SkinChangeRequest ->
{
	string skinId;                	//皮肤服务器ID
	int8 type;                      //1为英雄，2为地图皮肤
}
struct SkinChangeResponse <-
{
	int8 type;
}

struct EssenceChanged <-
{
	int value;							// 精华改变
}


//请求故事模式
struct StoryViewRequest ->
{
}

struct StoryViewResponse <-
{
	string storyId;                       //故事Id,已经解锁，没有通过，前面都是通过，后面都是没解锁的
}

struct SetNewPlayerGuideStepToServer ->
{
	int step;				//步骤数
}

struct getNewPlayerGuideStepToServer <-
{
	int step;				//步骤数
}


//请求我拥有的皮肤列表//
struct GetMyAllSkinRequest ->
{
	int8 type;                      //[1:英雄，2：装备皮肤3：地图皮肤]
}

struct GetMyAllSkinResponse <-
{
	int8 type;                      //[1:英雄，2：装备皮肤3：地图皮肤]
    vector<string> skinIdList;
}

//请求竞技场信息
struct ArenaTimesRequest ->
{
}
struct ArenaTimesResponse <-
{
	int8 isOpen;						// 是否开启状态
	int8 winTimes;						// 胜利次数
	int8 maxWinTimes;					// 结束胜利次数
	int8 loseTimes;						// 胜利次数
	int8 maxLoseTimes;					// 结束失败次数
}

// 竞技场奖励信息
struct ArenaRewardRequest ->
{
	//奖励结果见 RewardResult
}

//排名称号请求
struct TitleDataRequest ->
{
	
}
struct TitleDataResponse <-
{
	int8 rank;      //排名
	int8 score;     //分数
}

