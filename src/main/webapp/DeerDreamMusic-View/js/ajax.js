/**************************************************
 * MKOnlinePlayer v2.4
 * Ajax 后台数据交互请求模块
 * 编写：mengkun(https://mkblog.cn)
 * 时间：2018-3-11
 *************************************************/

// ajax加载搜索结果(搜索歌曲)
function ajaxSearch() {
    if(rem.wd === ""){
        layer.msg('老铁，搜索内容嘞？', {anim:6});
        return false;
    }
    
    if(rem.loadPage == 1) { // 弹出搜索提示
        var tmpLoading = layer.msg('服务器正在吃鸡...', {icon: 16,shade: 0.01});
    }
    
    $.ajax({
        type: mkPlayer.method,
        url: 'http://192.168.1.180:8089/DeerDreamMusic/musicapi/queryMusicAndName',
        data: {
		"keywork": rem.wd,					// 关键字
		"page": rem.loadPage,				// 页码
		"pagesize" : mkPlayer.loadcount, 	// 记录数
		"source" : rem.source  				// 平台标识
		},
		dataType : "json",
        complete: function(XMLHttpRequest, textStatus) {
            if(tmpLoading) layer.close(tmpLoading);    // 关闭加载中动画
        },  // complete
        success: function(jsonData){
          	var count = 0;
            var num = 0;
            $.each(jsonData, function(index, obj) {
				if(num == 0){
					count = obj['count'];
					num++;
				}
			});
			 // 调试信息输出
            if(mkPlayer.debug) {
                console.debug();
            }
            console.info("搜索结果数：" + count);
             
            if(rem.loadPage == 1)   // 加载第一页，清空列表
            {
                if(jsonData.length === 0)   // 返回结果为零
                {
                    layer.msg('没有找到相关歌曲', {anim:6});
                    return false;
                }
                musicList[0].item = [];
                rem.mainList.html('');   // 清空列表中原有的元素
                addListhead();      // 加载列表头
            } else {
                $("#list-foot").remove();     //已经是加载后面的页码了，删除之前的“加载更多”提示
            }
            
            if(jsonData.length === 0)
            {
                addListbar("nomore");  // 加载完了
                return false;
            }
            
            var tempItem = [], no = musicList[0].item.length;
            num = -1;
            $.each(jsonData, function(index, obj) {
				if(num == 0){
					count = obj['count'];
					num ++;
				}else{
					no ++;
					//mplayer_song.push({'name': ,'singer':,'img':obj['img'],'src':obj['src'],'lrc':obj['lrc']});
					tempItem =  {
	                    id: obj['id'],  		// 音乐ID
	                    name: obj['name'],  	// 音乐名字
	                    artist: obj['singer'], 	// 艺术家名字
	                    album: obj['album'],    // 专辑名字
	                    source: obj['source'],  // 音乐来源
	                    url_id:  obj['src'] ,	// 链接ID
	                    pic_id: obj['id'],  	// 封面ID
	                    lyric_id: obj['id'],  	// 歌词ID
	                    pic: obj['img'],    	// 专辑图片
	                    url: obj['src']   		// mp3链接
	                };
	                musicList[0].item.push(tempItem);   // 保存到搜索结果临时列表中
	                addItem(no, tempItem.name, tempItem.artist, tempItem.album);  // 在前端显示
				}
			});
            
            rem.dislist = 0;    // 当前显示的是搜索列表
            rem.loadPage ++;    // 已加载的列数+1
            
            dataBox("list");    // 在主界面显示出播放列表
            refreshList();  // 刷新列表，添加正在播放样式
            
            if(no < mkPlayer.loadcount) {
                addListbar("nomore");  // 没加载满，说明已经加载完了
            } else {
                addListbar("more");     // 还可以点击加载更多
            }
            
            if(rem.loadPage == 2) listToTop();    // 播放列表滚动到顶部
        },   //success
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer.msg('搜索结果获取失败 - ' + XMLHttpRequest.status);
            console.error(XMLHttpRequest + textStatus + errorThrown);
        }   // error
    });//ajax
}

// 完善获取音乐信息
// 音乐所在列表ID、音乐对应ID、回调函数(获取歌曲播放路径)
function ajaxUrl(music, callback)
{
    // 已经有数据，直接回调
    if(music.url !== null && music.url !== "err" && music.url !== "") {
        callback(music);
        return true;
    }
    // id为空，赋值链接错误。直接回调
    if(music.id === null) {
        music.url = "err";
        updateMinfo(music); // 更新音乐信息
        callback(music);
        return true;
    }
    
    $.ajax({ 
        type: mkPlayer.method,
        url: 'http://192.168.1.180:8089/DeerDreamMusic/musicapi/queryInfo',
        data: {
		"musicId": music.id,  			// 音乐ID
		"source" : music.source  			// 平台标识
		},
		dataType : "json",
        success: function(jsonData){
        	var songString = '';
            
            $.each(jsonData, function(index, obj) {
			//alert(obj["lrc"])
			//mplayer_song = '[{' + 'name:' + obj['name'] + ',singer:' + obj['singer'] + ',img:' + obj['img'] + ',src:' + obj['src'] + ',lrc:' + obj['lrc'] + '}]';
				songString = obj['src'];
			});
			// 调试信息输出
            if(mkPlayer.debug) {
                console.debug("歌曲链接：" + songString);
            }
            if(songString === "") {
                music.url = "err";
            } else {
                music.url = songString;    // 记录结果
            }
            
            updateMinfo(music); // 更新音乐信息
            
            callback(music);    // 回调函数
            return true;
        },   //success
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer.msg('歌曲链接获取失败 - ' + XMLHttpRequest.status);
            console.error(XMLHttpRequest + textStatus + errorThrown);
        }   // error 
    }); //ajax
    
}

// 完善获取音乐封面图
// 包含音乐信息的数组、回调函数（获取音乐封面图）
function ajaxPic(music, callback)
{
    // 已经有数据，直接回调
    if(music.pic !== null && music.pic !== "err" && music.pic !== "") {
        callback(music);
        return true;
    }
    // pic_id 为空，赋值链接错误。直接回调
    if(music.pic_id === null) {
        music.pic = "err";
        updateMinfo(music); // 更新音乐信息
        callback(music);
        return true;
    }
    
    $.ajax({ 
        type: mkPlayer.method,
        url: 'http://192.168.1.180:8089/DeerDreamMusic/musicapi/queryInfo',
        data: {
		"musicId": music.pic_id,  			// 音乐ID
		"source" : music.source  				// 平台标识
		},
		dataType : "json",
        success: function(jsonData){
            // 调试信息输出
            if(mkPlayer.debug) {
                console.log("歌曲封面：" + jsonData.url);
            }
            var picString = '';
            $.each(jsonData, function(index, obj) {
			//alert(obj["lrc"])
			//mplayer_song = '[{' + 'name:' + obj['name'] + ',singer:' + obj['singer'] + ',img:' + obj['img'] + ',src:' + obj['src'] + ',lrc:' + obj['lrc'] + '}]';
				picString = obj['img'];
				
			});
            if(picString !== "") {
                music.pic = picString;    // 记录结果
            } else {
                music.pic = "err";
            }
            
            updateMinfo(music); // 更新音乐信息
            
            callback(music);    // 回调函数
            return true;
        },   //success
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer.msg('歌曲封面获取失败 - ' + XMLHttpRequest.status);
            console.error(XMLHttpRequest + textStatus + errorThrown);
        }   // error 
    }); //ajax
    
}

// ajax加载用户歌单
// 参数：歌单网易云 id, 歌单存储 id，回调函数（加载用户歌单）
function ajaxPlayList(lid, id, callback) {
    if(!lid) return false;
    
    // 已经在加载了，跳过
    if(musicList[id].isloading === true) {
        return true;
    }
    
    musicList[id].isloading = true; // 更新状态：列表加载中
    
    $.ajax({
        type: mkPlayer.method, 
        url: 'http://192.168.1.180:8089/DeerDreamMusic/musicapi/queryUserInfoPlayList',
        data:{
			"pId": lid,  			// 音乐ID
			"source" : '0002'  			// 平台标识
        },
        dataType : "json",
        complete: function(XMLHttpRequest, textStatus) {
            musicList[id].isloading = false;    // 列表已经加载完了
        },  // complete
        success: function(jsonData){
        	 // 存储歌单信息
            var tempList ;
        	$.each(jsonData, function(index, obj) {
				tempList = {
                id: lid,    // 列表的网易云 id
                name: obj['coverName'],   // 列表名字
                cover: obj['coverImgUrl'],   // 列表封面
                creatorName: ['nickname'],   // 列表创建者名字
                creatorAvatar: ['avatarUrl'],   // 列表创建者头像
                item: []
            	};
            	if(obj['coverImgUrl'] !== '') {
                	tempList.cover = obj['coverImgUrl']
	            } else {
	                tempList.cover = musicList[id].cover;
	            }
            	return false;
			});
           
            
            
            // 存储歌单中的音乐信息
            var no = 0;    
                 $.each(jsonData, function(index, obj) {
				tempList.item[no] =  {
                        id: obj['id'],  // 音乐ID
                        name: obj['name'],  // 音乐名字
                        artist: obj['singerName'], // 艺术家名字
                        album: obj['alName'],    // 专辑名字
                        source: "0002",     // 音乐来源
                        url_id: obj['id'],  // 链接ID
                        pic_id: null,  // 封面ID
                        lyric_id: obj['id'],  // 歌词ID
                        pic: obj['picUrl'],    // 专辑图片
                        url: null   // mp3链接
                    };
                    no++;
			});
            
            
            // 歌单用户 id 不能丢
            if(musicList[id].creatorID) {
                tempList.creatorID = musicList[id].creatorID;
                if(musicList[id].creatorID === rem.uid) {   // 是当前登录用户的歌单，要保存到缓存中
                    var tmpUlist = playerReaddata('ulist');    // 读取本地记录的用户歌单
                    if(tmpUlist) {  // 读取到了
                        for(i=0; i<tmpUlist.length; i++) {  // 匹配歌单
                            if(tmpUlist[i].id == lid) {
                                tmpUlist[i] = tempList; // 保存歌单中的歌曲
                                playerSavedata('ulist', tmpUlist);  // 保存
                                break;
                            }
                        }
                    }
                }
            }
            
            // 存储列表信息
            musicList[id] = tempList;
            
            // 首页显示默认列表
            if(id == mkPlayer.defaultlist) loadList(id);
            if(callback) callback(id);    // 调用回调函数
            
            // 改变前端列表
            $(".sheet-item[data-no='" + id + "'] .sheet-cover").attr('src', tempList.cover);    // 专辑封面
            $(".sheet-item[data-no='" + id + "'] .sheet-name").html(tempList.name);     // 专辑名字
            
            // 调试信息输出
            if(mkPlayer.debug) {
                console.debug("歌单 [" +tempList.name+ "] 中的音乐获取成功");
            }
        },   //success
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer.msg('歌单读取失败 - ' + XMLHttpRequest.status);
            console.error(XMLHttpRequest + textStatus + errorThrown);
            $(".sheet-item[data-no='" + id + "'] .sheet-name").html('<span style="color: #EA8383">读取失败</span>');     // 专辑名字
        }   // error  
    });//ajax
}

// ajax加载歌词
// 参数：音乐ID，回调函数（加载歌词）
function ajaxLyric(music, callback) {
    lyricTip('歌词加载中...');
    
    if(!music.lyric_id) callback('');  // 没有歌词ID，直接返回
    
    $.ajax({
        type: mkPlayer.method,
        url: 'http://192.168.1.180:8089/DeerDreamMusic/musicapi/queryInfo',
        data: {
		"musicId": music.lyric_id,				// 音乐ID
		"source":music.source  					// 平台标识
		},
		dataType : "json",
        success: function(jsonData){
            // 调试信息输出
            if (mkPlayer.debug) {
                console.debug("歌词获取成功");
            }
            var lyricString = '';
            $.each(jsonData, function(index, obj) {
			//alert(obj["lrc"])
			//mplayer_song = '[{' + 'name:' + obj['name'] + ',singer:' + obj['singer'] + ',img:' + obj['img'] + ',src:' + obj['src'] + ',lrc:' + obj['lrc'] + '}]';
				lyricString = obj['lrc'];
				lyricString = lyricString.replace(/\<.*?\>/g,'');
			});
            
            if (lyricString) {
                callback(lyricString, music.lyric_id);    // 回调函数
            } else {
                callback('', music.lyric_id);    // 回调函数
            }
        },   //success
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer.msg('歌词读取失败 - ' + XMLHttpRequest.status);
            console.error(XMLHttpRequest + textStatus + errorThrown);
            callback('', music.lyric_id);    // 回调函数
        }   // error   
    });//ajax
}


// ajax加载用户的播放列表
// 参数 用户的网易云 id（获取歌单列表）
function ajaxUserList(uid)
{
    var tmpLoading = layer.msg('服务器正在吃鸡...', {icon: 16,shade: 0.01});
    $.ajax({
        type: mkPlayer.method,
        url: 'http://192.168.1.180:8089/DeerDreamMusic/musicapi/queryUserInfo',
        data: {
			"userId": uid,  			// 音乐ID
			"source" : '0002'  			// 平台标识
        },
        dataType : "json",
        complete: function(XMLHttpRequest, textStatus) {
            if(tmpLoading) layer.close(tmpLoading);    // 关闭加载中动画
        },  // complete
        success: function(jsonData){
            /*if(jsonData.code == "-1" || jsonData.code == 400){
                layer.msg('用户 uid 输入有误');
                return false;
            }*/
           
           $.each(jsonData, function(index, obj) {
           		rem.uname = obj['nickname'];// 第一个列表(喜欢列表)的创建者即用户昵称  
           		rem.uid = obj['userId'];// 记录已同步用户 uid
           		return false;
               	
           });
           if(rem.uname == null || rem.uname === undefined || rem.name == '')
           {
               layer.msg('没找到用户 ' + uid + ' 的歌单');
               return false;
           }else{
                var tempList,userList = [];
                $("#sheet-bar").remove();   // 移除登陆条
                layer.msg('欢迎您 '+rem.uname);
                // 记录登录用户
                playerSavedata('uid', rem.uid);
                playerSavedata('uname', rem.uname);
               $.each(jsonData, function(index, obj) {
	                // 获取歌单信息
                    tempList = {
                        id: obj['id'],    // 列表的网易云 id
                        name: obj['name'],   // 列表名字
                        cover: obj['coverImgUrl'],   // 列表封面
                        creatorID: obj['userId'],   // 列表创建者id
                        creatorName: obj['nickname'],   // 列表创建者名字
                        creatorAvatar: obj['avatarUrl'],   // 列表创建者头像
                        item: []
                    };
                    // 存储并显示播放列表
                    addSheet(musicList.push(tempList) - 1, tempList.name, tempList.cover);
                    userList.push(tempList);
				});
                playerSavedata('ulist', userList);
                // 显示退出登录的提示条
                sheetBar();
            }
            // 调试信息输出
            if(mkPlayer.debug) {
                console.debug("用户歌单获取成功 [用户网易云ID：" + uid + "]");
            }
        },   //success
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            layer.msg('歌单同步失败 - ' + XMLHttpRequest.status);
            console.error(XMLHttpRequest + textStatus + errorThrown);
        }   // error
    });//ajax
    return true;
}