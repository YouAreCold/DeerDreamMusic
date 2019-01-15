/**************************************************
 * MKOnlinePlayer v2.32
 * 播放列表配置模块
 * 编写：mengkun(http://mkblog.cn)
 * 时间：2017-9-15
 *************************************************/
// 建议修改前先备份一下
// 获取 歌曲的网易云音乐ID 或 网易云歌单ID 的方法：
// 先在 js/player.js 中开启调试模式，然后按 F12 打开浏览器的控制台。播放歌曲或点开歌单即可看到相应信息

var musicList = [
    // 以下三个系统预留列表请勿更改，否则可能导致程序无法正常运行！
    // 预留列表：搜索结果
    {
        name: "搜索结果",   // 播放列表名字
        cover: "../DeerDreamMusic/DeerDreamMusic-View/images/player_cover.png",          // 播放列表封面
        creatorName: "",        // 列表创建者名字
        creatorAvatar: "",      // 列表创建者头像
        item: []
    },
    // 预留列表：正在播放
    {
        name: "正在播放",   // 播放列表名字
        cover: "../DeerDreamMusic/DeerDreamMusic-View/images/player_cover.png",          // 播放列表封面
        creatorName: "",        // 列表创建者名字
        creatorAvatar: "",      // 列表创建者头像
        item: []
    },
    // 预留列表：播放历史
    {
        name: "播放历史",   // 播放列表名字
        cover: "../DeerDreamMusic/DeerDreamMusic-View/images/history.png",          // 播放列表封面
        creatorName: "",        // 列表创建者名字
        creatorAvatar: "",      // 列表创建者头像
        item: []
    },  
    // 以上三个系统预留列表请勿更改，否则可能导致程序无法正常运行！
    //*********************************************
    // 自定义列表开始，您可以自由添加您的自定义列表
  	 {
  	 	id:2250011882// 抖音排行榜
  	 },
    {
        id:982999271// wzb
    },
  	 {
  	 	id:779407493// 中国有嘻哈
  	 },
  	 {
        id: 3778678     // 云音乐热歌榜
    },
    {
        id: 3779629     // 云音乐新歌榜
    },
    {
        id: 4395559     // 华语金曲榜
    },
    {
        id: 64016     // 中国TOP排行榜（内地榜）
    },
    {
        id: 112504     // 中国TOP排行榜（港台榜）
    },
    {
        id: 19723756     // 云音乐飙升榜
    },
    {
        id: 2884035     // "网易原创歌曲榜"
    },
    // 自定义列表教程开始！
    // 方式一：手动创建列表并添加歌曲信息
    // 温馨提示：各大音乐平台获取到的外链有效期均较短，因此 url 值应该设置为空，以让程序临时抓取
    {
        name: "自定义列表",   // 播放列表名字
        cover: "../DeerDreamMusic/DeerDreamMusic-View/images/other-images.jpg", // 播放列表封面图像
        creatorName: "",        // 列表创建者名字(暂时没用到，可空)
        creatorAvatar: "",      // 列表创建者头像(暂时没用到，可空)
        item: [                 // 这里面放歌曲
            {
                id: "490595927",
                name: "不用去猜",
                artist: "Jony J",
                album: "我是谁 我在哪",
                source: "0002",      
                url_id: "490595927",
                pic_id: "490595927",
                lyric_id: "490595927",
                pic: "http://p1.music.126.net/h_1u3v9vTCRWN_ZJlVdQkQ==/18523472395121303.jpg",
                url: ""
            }
        ]
    }
];