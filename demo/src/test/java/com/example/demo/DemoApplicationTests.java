package com.example.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import reactor.core.publisher.Flux;

import java.awt.*;
import java.sql.SQLException;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DemoApplicationTests {
	@Autowired
	private WebTestClient webTestClient;


	@Test
	public void testKeyWithValue() {
		webTestClient
				.get().uri("/?word=HTTP")
				.accept(MediaType.TEXT_PLAIN)
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class).isEqualTo(
				"REST является альтернативой RPC[1]. В сети Интернет вызов удалённой процедуры может представлять собой обычный HTTP-запрос (обычно GET или POST;\n" +
						"Несмотря на то, что REST не является стандартом сам по себе, большинство RESTful-реализаций используют такие стандарты, как HTTP, URL, JSON и XML\n"
		);
	}

	@Test
	public void testKeyWithoutValue() {
		webTestClient
				.get().uri("/?word=")
				.accept(MediaType.TEXT_PLAIN)
				.exchange()
				.expectStatus().isOk()
				.expectBody(String.class).isEqualTo(
				"REST (от англ. Representational State Transfer — «передача состояния представления»)\n" +
						"архитектурный стиль взаимодействия компонентов распределённого приложения в сети. REST представляет собой согласованный набор\n" +
						"ограничений, учитываемых при проектировании распределённой гипермедиа-системы. В определённых случаях (интернет-магазины,\n" +
						"поисковые системы, прочие системы, основанные на данных) это приводит к повышению производительности и упрощению архитектуры.\n" +
						"В широком смысле[уточнить] компоненты в REST взаимодействуют наподобие взаимодействия клиентов и серверов во Всемирной паутине.\n" +
						"REST является альтернативой RPC[1]. В сети Интернет вызов удалённой процедуры может представлять собой обычный HTTP-запрос (обычно GET или POST;\n" +
						"такой запрос называют «REST-запрос»), а необходимые данные передаются в качестве параметров запроса[2][3]. Для веб-служб, построенных с учётом REST\n" +
						"(то есть не нарушающих накладываемых им ограничений), применяют термин «RESTful». В отличие от веб-сервисов (веб-служб) на основе SOAP, не существует\n" +
						"«официального» стандарта для RESTful веб-API. Дело в том, что REST является архитектурным стилем, в то время как SOAP является протоколом.\n" +
						"Несмотря на то, что REST не является стандартом сам по себе, большинство RESTful-реализаций используют такие стандарты, как HTTP, URL, JSON и XML\n" +
						"(но XML зачастую не принято использовать).\n"
		);
	}
}

