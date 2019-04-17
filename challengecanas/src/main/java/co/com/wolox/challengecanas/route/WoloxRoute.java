package co.com.wolox.challengecanas.route;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;

import co.com.wolox.challengecanas.dto.Album;
import co.com.wolox.challengecanas.dto.Comment;
import co.com.wolox.challengecanas.dto.Photo;
import co.com.wolox.challengecanas.dto.Post;
import co.com.wolox.challengecanas.dto.User;

@Component
@ContextName("challengecanas")
public class WoloxRoute  extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		JacksonDataFormat jacksonDf = new JacksonDataFormat();

		jacksonDf.disableFeature(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES);
		jacksonDf.disableFeature(SerializationFeature.FAIL_ON_EMPTY_BEANS);
		
		
		restConfiguration()
        .bindingMode(RestBindingMode.json)
        .apiContextPath("/api")
        .contextPath("/rest")
        .apiProperty("host", "")
        .dataFormatProperty("prettyPrint", "true")
        .enableCORS(true)		        

        .apiProperty("api.title", "API Wolox challenge")
        .apiProperty("api.contact.name", "Jhovanny Canas")
        .apiProperty("api.version", "0.0.1");
		
		
		rest().description("servicio wolox")
		.consumes("application/json")
		.produces("application/json")
		
		.get("/albums/").description("Retorna todos los albumes")
		.outType(Album[].class)
		.responseMessage().code(200).message("OK").endResponseMessage()
		.responseMessage().code(404).message("No se encontraron albumes").endResponseMessage()
		.responseMessage().code(500).message("Ocurrio un error generando la consulta").endResponseMessage()
		.route().streamCaching().toD("http4://jsonplaceholder.typicode.com/albums?bridgeEndpoint=true").unmarshal(jacksonDf)
		.endRest()
		
		.get("/users/").description("Retorna todos los usuarios")
		.outType(User[].class)
		.responseMessage().code(200).message("OK").endResponseMessage()
		.responseMessage().code(404).message("No se encontraron usuarios").endResponseMessage()
		.responseMessage().code(500).message("Ocurrio un error generando la consulta").endResponseMessage()
		.route().streamCaching().toD("http4://jsonplaceholder.typicode.com/users?bridgeEndpoint=true").unmarshal(jacksonDf)
		.endRest()
		
		.get("/photos/").description("Retorna todos las fotografias")
		.outType(Photo[].class)
		.responseMessage().code(200).message("OK").endResponseMessage()
		.responseMessage().code(404).message("No se encontraron fotografias").endResponseMessage()
		.responseMessage().code(500).message("Ocurrio un error generando la consulta").endResponseMessage()
		.route().streamCaching().toD("http4://jsonplaceholder.typicode.com/photos?bridgeEndpoint=true").unmarshal(jacksonDf)
		.endRest()
		
		.get("/comments/").description("Retorna todos los comentarios")
		.outType(Comment[].class)
		.responseMessage().code(200).message("OK").endResponseMessage()
		.responseMessage().code(404).message("No se encontraron fotografias").endResponseMessage()
		.responseMessage().code(500).message("Ocurrio un error generando la consulta").endResponseMessage()
		.route().streamCaching().toD("http4://jsonplaceholder.typicode.com/comments?bridgeEndpoint=true").unmarshal(jacksonDf)
		.endRest()
		
		.get("/posts/").description("Retorna todos los comentarios")
		.outType(Post[].class)
		.responseMessage().code(200).message("OK").endResponseMessage()
		.responseMessage().code(404).message("No se encontraron fotografias").endResponseMessage()
		.responseMessage().code(500).message("Ocurrio un error generando la consulta").endResponseMessage()
		.route().streamCaching().toD("http4://jsonplaceholder.typicode.com/posts?bridgeEndpoint=true").unmarshal(jacksonDf)
		.endRest()
		
		.get("/users/{id}").description("Retorna un usuario segun ID")
		.outType(User.class)
		.param().name("id").type(RestParamType.path).description("ID")
		.dataType("integer").endParam()
		.responseMessage().code(200).message("OK").endResponseMessage()
		.responseMessage().code(404).message("No se encontraron fotografias").endResponseMessage()
		.responseMessage().code(500).message("Ocurrio un error generando la consulta").endResponseMessage()
		.route().streamCaching()
		.to("http4://jsonplaceholder.typicode.com/?bridgeEndpoint=true").unmarshal(jacksonDf)
		.endRest()

		
		.get("/photos/{id}").description("Retorna una fotografia segun ID")
		.outType(Photo.class)
		.param().name("id").type(RestParamType.path).description("ID")
		.dataType("integer").endParam()
		.responseMessage().code(200).message("OK").endResponseMessage()
		.responseMessage().code(404).message("No se encontraron fotografias").endResponseMessage()
		.responseMessage().code(500).message("Ocurrio un error generando la consulta").endResponseMessage()
		.route().streamCaching()
		.to("http4://jsonplaceholder.typicode.com/?bridgeEndpoint=true").unmarshal(jacksonDf)
		.endRest()

		.get("/albums/{id}").description("Retorna un album segun ID")
		.outType(Album.class)
		.param().name("id").type(RestParamType.path).description("ID")
		.dataType("integer").endParam()
		.responseMessage().code(200).message("OK").endResponseMessage()
		.responseMessage().code(404).message("No se encontraron fotografias").endResponseMessage()
		.responseMessage().code(500).message("Ocurrio un error generando la consulta").endResponseMessage()
		.route().streamCaching()
		.to("http4://jsonplaceholder.typicode.com/?bridgeEndpoint=true").unmarshal(jacksonDf)
		.endRest()
		
		
		.get("/comments/{id}").description("Retorna un comentario segun ID")
		.outType(Comment.class)
		.param().name("id").type(RestParamType.path).description("ID")
		.dataType("integer").endParam()
		.responseMessage().code(200).message("OK").endResponseMessage()
		.responseMessage().code(404).message("No se encontraron fotografias").endResponseMessage()
		.responseMessage().code(500).message("Ocurrio un error generando la consulta").endResponseMessage()
		.route().streamCaching()
		.to("http4://jsonplaceholder.typicode.com/?bridgeEndpoint=true").unmarshal(jacksonDf)
		.endRest()
		
		.get("/posts/{id}").description("Retorna un comentario segun ID")
		.outType(Post.class)
		.param().name("id").type(RestParamType.path).description("ID")
		.dataType("integer").endParam()
		.responseMessage().code(200).message("OK").endResponseMessage()
		.responseMessage().code(404).message("No se encontraron fotografias").endResponseMessage()
		.responseMessage().code(500).message("Ocurrio un error generando la consulta").endResponseMessage()
		.route().streamCaching()
		.to("http4://jsonplaceholder.typicode.com/?bridgeEndpoint=true").unmarshal(jacksonDf)
		.endRest();
		
		
//		.get("/albums").description("Retorna un album segun ID")
//		.outType(Album[].class)
//		.param().name("userId").type(RestParamType.query).description("ID").required(true)
//		.dataType("integer").endParam()
//		.responseMessage().code(200).message("OK").endResponseMessage()
//		.responseMessage().code(404).message("No se encontraron fotografias").endResponseMessage()
//		.responseMessage().code(500).message("Ocurrio un error generando la consulta").endResponseMessage()
//		.route().streamCaching()
//		.toD("http4://jsonplaceholder.typicode.com/?bridgeEndpoint=true").unmarshal(jacksonDf)
//		.endRest();
		
		
		
		
	}

}
