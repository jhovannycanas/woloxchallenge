package co.com.wolox.challengecanas.route;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.component.jackson.ListJacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import co.com.wolox.challengecanas.dto.Album;
import co.com.wolox.challengecanas.dto.AlbumUser;
import co.com.wolox.challengecanas.dto.Comment;
import co.com.wolox.challengecanas.dto.Photo;
import co.com.wolox.challengecanas.dto.Post;
import co.com.wolox.challengecanas.dto.User;
import co.com.wolox.challengecanas.proccessor.AlbumAccesProccessor;
import co.com.wolox.challengecanas.service.AlbumUserService;
import co.com.wolox.challengecanas.service.UserService;
import co.com.wolox.challengecanas.util.AppConstants;

@Component
@ContextName("challengecanas")
public class WoloxRoute extends RouteBuilder {

	@Value("${server.address}")
	private String serviceHost;

	@Autowired
	AlbumUserService albumUserService;

	@Autowired
	UserService userService;

	@Override
	public void configure() throws Exception {

		JacksonDataFormat jacksonDf = new JacksonDataFormat();

		jacksonDf.disableFeature(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
		jacksonDf.disableFeature(SerializationFeature.FAIL_ON_EMPTY_BEANS);

		JacksonDataFormat jacksonListDf = new ListJacksonDataFormat();
		jacksonListDf.setUnmarshalType(Post.class);
		jacksonListDf.disableFeature(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
		jacksonListDf.disableFeature(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		jacksonListDf.setInclude("NON_NULL");
		jacksonListDf.disableFeature(SerializationFeature.FAIL_ON_EMPTY_BEANS);

		ObjectMapper maper = new ObjectMapper();
		maper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

		restConfiguration().bindingMode(RestBindingMode.json).apiContextPath("/api").contextPath("/rest")
				.apiProperty("host", "").host(serviceHost).port(8080).dataFormatProperty("prettyPrint", "true")
				.enableCORS(true)

				.apiProperty("api.title", "API Wolox challenge").apiProperty("api.contact.name", "Jhovanny Canas")
				.apiProperty("api.version", "0.0.1");

		rest().description("servicio wolox").consumes("application/json").produces("application/json")

				.get("/albums/").description("Retorna todos los albumes").outType(Album[].class).responseMessage()
				.code(200).message("OK").endResponseMessage().responseMessage().code(404)
				.message("No se encontraron albumes").endResponseMessage().responseMessage().code(500)
				.message("Ocurrio un error generando la consulta").endResponseMessage().route().streamCaching()
				.toD(AppConstants.URL_ALBUMS).unmarshal(jacksonDf).endRest()

				.get("/users/").description("Retorna todos los usuarios").outType(User[].class).responseMessage()
				.code(200).message("OK").endResponseMessage().responseMessage().code(404)
				.message("No se encontraron usuarios").endResponseMessage().responseMessage().code(500)
				.message("Ocurrio un error generando la consulta").endResponseMessage().route().streamCaching()
				.toD(AppConstants.URL_USERS).unmarshal(jacksonDf).endRest()

				.get("/photos/").description("Retorna todos las fotografias").outType(Photo[].class).responseMessage()
				.code(200).message("OK").endResponseMessage().responseMessage().code(404)
				.message("No se encontraron fotografias").endResponseMessage().responseMessage().code(500)
				.message("Ocurrio un error generando la consulta").endResponseMessage().route().streamCaching()
				.toD(AppConstants.URL_PHOTOS).unmarshal(jacksonDf).endRest()

				.get("/comments/").description("Retorna todos los comentarios").outType(Comment[].class)
				.responseMessage().code(200).message("OK").endResponseMessage().responseMessage().code(404)
				.message("No se encontraron fotografias").endResponseMessage().responseMessage().code(500)
				.message("Ocurrio un error generando la consulta").endResponseMessage().route().streamCaching()
				.toD(AppConstants.URL_COMMENTS).unmarshal(jacksonDf).endRest()

				.get("/posts/").description("Retorna todos los comentarios").outType(Post[].class).responseMessage()
				.code(200).message("OK").endResponseMessage().responseMessage().code(404)
				.message("No se encontraron fotografias").endResponseMessage().responseMessage().code(500)
				.message("Ocurrio un error generando la consulta").endResponseMessage().route().streamCaching()
				.toD(AppConstants.URL_POST).unmarshal(jacksonDf).endRest()

				.get("/users/{id}").description("Retorna un usuario segun ID").outType(User.class).param().name("id")
				.type(RestParamType.path).description("ID").dataType("integer").endParam().responseMessage().code(200)
				.message("OK").endResponseMessage().responseMessage().code(404).message("No se encontraron fotografias")
				.endResponseMessage().responseMessage().code(500).message("Ocurrio un error generando la consulta")
				.endResponseMessage().route().streamCaching()
				.to(AppConstants.URL_SERVER).unmarshal(jacksonDf).endRest()

				.get("/photos/{id}").description("Retorna una fotografia segun ID").outType(Photo.class).param()
				.name("id").type(RestParamType.path).description("ID").dataType("integer").endParam().responseMessage()
				.code(200).message("OK").endResponseMessage().responseMessage().code(404)
				.message("No se encontraron fotografias").endResponseMessage().responseMessage().code(500)
				.message("Ocurrio un error generando la consulta").endResponseMessage().route().streamCaching()
				.to(AppConstants.URL_SERVER).unmarshal(jacksonDf).endRest()

				.get("/albums/{id}").description("Retorna un album segun ID").outType(Album.class).param().name("id")
				.type(RestParamType.path).description("ID").dataType("integer").endParam().responseMessage().code(200)
				.message("OK").endResponseMessage().responseMessage().code(404).message("No se encontraron fotografias")
				.endResponseMessage().responseMessage().code(500).message("Ocurrio un error generando la consulta")
				.endResponseMessage().route().to(AppConstants.URL_SERVER)
				.unmarshal(jacksonDf).endRest()

				.get("/comments/{id}").description("Retorna un comentario segun ID").outType(Comment.class).param()
				.name("id").type(RestParamType.path).description("ID").dataType("integer").endParam().responseMessage()
				.code(200).message("OK").endResponseMessage().responseMessage().code(404)
				.message("No se encontraron fotografias").endResponseMessage().responseMessage().code(500)
				.message("Ocurrio un error generando la consulta").endResponseMessage().route().streamCaching()
				.to(AppConstants.URL_SERVER).unmarshal(jacksonDf).endRest()

				.get("/posts/{id}").description("Retorna un comentario segun ID").outType(Post.class).param().name("id")
				.type(RestParamType.path).description("ID").dataType("integer").endParam().responseMessage().code(200)
				.message("OK").endResponseMessage().responseMessage().code(404).message("No se encontraron fotografias")
				.endResponseMessage().responseMessage().code(500).message("Ocurrio un error generando la consulta")
				.endResponseMessage().route().streamCaching()
				.to(AppConstants.URL_SERVER).unmarshal(jacksonDf).endRest()

				.put("/albumuser/").description("Permite actualizar el permiso de un usuario en un album")
				.type(AlbumUser.class).responseMessage().code(200).message("OK").endResponseMessage().responseMessage()
				.code(500).message("Ocurrio un error generando la consulta").endResponseMessage().route()
				.bean(albumUserService, "updateAccessLevelAlbumUser").endRest()

				.get("/users/accesslevel/{accesid}/album/{albumid}")
				.description("Permite consultar los usuarios con un permiso en un album").outType(User[].class).param()
				.name("accesid").type(RestParamType.path).description("Id acceso").dataType("integer").endParam()
				.param().name("albumid").type(RestParamType.path).description("Id album").dataType("integer").endParam()
				.responseMessage().code(200).message("OK").endResponseMessage().responseMessage().code(500)
				.message("Ocurrio un error generando la consulta").endResponseMessage().route()
				.bean(userService, "findUserByAccessLevelByAlbum").endRest()

				.get("/comments/search").description("Busca un comentario por nombre o usuario").param().name("name")
				.type(RestParamType.query).required(false).description("nombre").dataType("string").endParam().param()
				.name("user").type(RestParamType.query).required(false).description("Id user").dataType("integer")
				.endParam().responseMessage().code(200).message("OK").endResponseMessage().responseMessage().code(500)
				.message("Ocurrio un error generando la consulta").endResponseMessage().route()
				.choice().when().simple("${in.headers.name} != null").to("direct:postbyname").when()
				.simple("${in.headers.user} != null").to("direct:postbyuser").endChoice().end().endRest()

				.post("/albumuser/").description("Permite configurar nivel de acceso a un album segun usuario")
				.type(AlbumUser.class).responseMessage().code(200).message("OK").endResponseMessage().responseMessage()
				.code(500).message("Ocurrio un error generando la consulta").endResponseMessage().route()
				.removeHeaders("*").process(new Processor() {

					@Override
					public void process(Exchange exchange) throws Exception {
						AlbumUser albumUser = (AlbumUser) exchange.getIn().getBody();
						exchange.getIn().setHeader("idalbum", albumUser.getAlbumId());
						exchange.getIn().setHeader("iduser", albumUser.getUserId());
						exchange.setProperty("albumuser", albumUser.getAccesLevel());
					}
				})

				.setBody().simple("${null}").to("direct:starmultiprocesosr").end();

		from("direct:starmultiprocesosr").multicast(new AggregationStrategy() {

			@Override
			public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {

				if (oldExchange == null) {
					return newExchange;
				}

				Album album = maper.convertValue(oldExchange.getIn().getBody(), Album.class);
				oldExchange.setProperty("album", album);
				User user = maper.convertValue(newExchange.getIn().getBody(), User.class);
				oldExchange.setProperty("user", user);
				return oldExchange;
			}
		}).parallelProcessing().to("direct:albumid", "direct:userid").end().bean(AlbumAccesProccessor.class).end();

		from("direct:albumid").to("rest:get:rest/albums/{idalbum}").unmarshal(jacksonDf).end();

		from("direct:userid").to("rest:get:rest/users/{iduser}").unmarshal(jacksonDf).end();

		from("direct:postbyname").setHeader(Exchange.HTTP_QUERY, simple("name=${in.headers.name}"))
				.to(AppConstants.URL_COMMENTS).unmarshal(jacksonDf).end();

		from("direct:postbyuser").setHeader(Exchange.HTTP_QUERY, simple("userId=${in.headers.user}"))
				.to(AppConstants.URL_POST).unmarshal(jacksonListDf)
				.process(

						new Processor() {

							@Override
							public void process(Exchange exchange) throws Exception {

								@SuppressWarnings("unchecked")
								List<Post> posts = (List<Post>) exchange.getIn().getBody();
								String uris = "";
								int contadorUris = 0;
								for (Post post : posts) {

									if (contadorUris++ > 0) {
										uris += ",";
									}
									uris += AppConstants.URL_COMMENTS + post.getId();

								}

								exchange.getIn().setHeader("uris", uris);
							}
						})
				.to("direct:commentsbypost").end();

		from("direct:commentsbypost").setBody().simple("${null}")
				.removeHeader(Exchange.HTTP_PATH).removeHeaders("CamelHttp*").streamCaching()

				.process(new Processor() {

					@Override
					public void process(Exchange exchange) throws Exception {
						String urls = (String) exchange.getIn().getHeader("uris");
						ConsumerTemplate createConsumerTemplate = exchange.getContext().createConsumerTemplate();
						List<Comment> listaCompleta = new ArrayList<>();

						String[] arreglo = urls.split(",");
						for (int i = 0; i < arreglo.length; i++) {
							String respuesta = createConsumerTemplate.receiveBody(arreglo[i], String.class);
							List<Comment> receive = maper.readValue(respuesta, new TypeReference<List<Comment>>() {
							});
							listaCompleta.addAll(receive);
						}
						exchange.getIn().setBody(listaCompleta);
					}
				}).end();

	}

}
