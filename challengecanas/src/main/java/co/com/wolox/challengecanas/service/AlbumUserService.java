package co.com.wolox.challengecanas.service;

import co.com.wolox.challengecanas.entity.AlbumUser;

public interface AlbumUserService {

	public AlbumUser saveAlbumUser(AlbumUser albumUser);
	
	public void updateAccessLevelAlbumUser(co.com.wolox.challengecanas.dto.AlbumUser albumUser) throws Exception;
}
