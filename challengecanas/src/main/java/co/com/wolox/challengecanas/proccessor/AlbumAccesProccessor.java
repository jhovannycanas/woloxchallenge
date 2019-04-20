package co.com.wolox.challengecanas.proccessor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;

import co.com.wolox.challengecanas.dto.Album;
import co.com.wolox.challengecanas.dto.User;
import co.com.wolox.challengecanas.entity.AccessLevel;
import co.com.wolox.challengecanas.entity.AlbumUser;
import co.com.wolox.challengecanas.service.AlbumUserService;
import co.com.wolox.challengecanas.util.ModelMapperUtil;

public class AlbumAccesProccessor implements Processor {

	@Autowired
	AlbumUserService albumUserService;
		
	@Override
	public void process(Exchange exchange) throws Exception {
		
		Album album = (Album) exchange.getProperty("album");
		
		if (album == null) {
			throw new Exception("Album no encontrado");
		}
		
		User user = (User) exchange.getProperty("user");
		
		if (user == null) {
			throw new Exception("Usuario no encontrado");
		}
		
	
		co.com.wolox.challengecanas.entity.User userEntity = ModelMapperUtil.map(user, co.com.wolox.challengecanas.entity.User.class);
		co.com.wolox.challengecanas.entity.Album albumEntity = ModelMapperUtil.map(album, co.com.wolox.challengecanas.entity.Album.class);
		AccessLevel accessLevel = AccessLevel.getById((Long)exchange.getProperty("albumuser"));
		
		if (accessLevel == null) {
			throw new Exception("Nivel de acceso no encontrado");
		}
		AlbumUser albumUser = new AlbumUser(albumEntity, userEntity, accessLevel);
		albumUserService.saveAlbumUser(albumUser);
		
	}

}
