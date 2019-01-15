package com.xky.roll.music_service.pojo;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;





/**
 * <p>
 * 音乐管理表
 * </p>
 *
 * @author wjx
 * @since 2018-06-05
 */
@Table(name="sys_music_song")
public class SysMusicSong {

    /**
     * 音乐ID
     */
    @Id
    @Column(name = "music_id")
	private String musicId;
    /**
     * 歌曲名称
     */
    @Column(name="music_songName")
	private String musicSongname;
    /**
     * 歌手名称
     */
    @Column(name="music_singleName")
	private String musicSinglename;
    /**
     * 平台ID
     */
    @Column(name="music_platformId")
	private String musicPlatformId;
    /**
     * 歌曲发布时间
     */
    @Column(name="music_song_time")
	private String musicSongtime;
    /**
     * 专辑名称
     */
    @Column(name="music_albumName")
	private String musicAlbumname;
    /**
     * 专辑图片路径
     */
    @Column(name="music_albumImage_url")
	private String musicAlbumimageUrl;
    /**
     * 音乐管理表
     */
    @Column(name="music_source")
	private String musicSource;
    /**
     * 歌词
     */
    @Column(name= "music_lyric")
	private String musicLyric;
    /**
     * 歌曲路径
     */
    @Column(name= "music_url")
	private String musicUrl;


	public String getMusicUrl() {
		return musicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}

	public String getMusicId() {
		return musicId;
	}

	public void setMusicId(String musicId) {
		this.musicId = musicId;
	}

	public String getMusicSongname() {
		return musicSongname;
	}

	public void setMusicSongname(String musicSongname) {
		this.musicSongname = musicSongname;
	}

	public String getMusicSinglename() {
		return musicSinglename;
	}

	public void setMusicSinglename(String musicSinglename) {
		this.musicSinglename = musicSinglename;
	}

	public String getMusicPlatformId() {
		return musicPlatformId;
	}

	public void setMusicPlatformId(String musicPlatformId) {
		this.musicPlatformId = musicPlatformId;
	}

	public String getMusicSongtime() {
		return musicSongtime;
	}

	public void setMusicSongtime(String musicSongtime) {
		this.musicSongtime = musicSongtime;
	}

	public String getMusicAlbumname() {
		return musicAlbumname;
	}

	public void setMusicAlbumname(String musicAlbumname) {
		this.musicAlbumname = musicAlbumname;
	}

	public String getMusicAlbumimageUrl() {
		return musicAlbumimageUrl;
	}

	public void setMusicAlbumimageUrl(String musicAlbumimageUrl) {
		this.musicAlbumimageUrl = musicAlbumimageUrl;
	}

	public String getMusicSource() {
		return musicSource;
	}

	public void setMusicSource(String musicSource) {
		this.musicSource = musicSource;
	}

	public String getMusicLyric() {
		return musicLyric;
	}

	public void setMusicLyric(String musicLyric) {
		this.musicLyric = musicLyric;
	}

}