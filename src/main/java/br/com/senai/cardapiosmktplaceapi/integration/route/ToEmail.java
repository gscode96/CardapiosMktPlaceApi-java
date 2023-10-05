package br.com.senai.cardapiosmktplaceapi.integration.route;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.http.HttpMethods;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.senai.cardapiosmktplaceapi.dto.Notificacao;
import br.com.senai.cardapiosmktplaceapi.integration.processor.ErroProcessor;

@Component
public class ToEmail extends RouteBuilder {

	@Value("${email.url}")
	private String urlDeEnvio;

	@Autowired
	private ErroProcessor erroProcessor;

	@Override
	public void configure() throws Exception {
		from("direct:eviarEmail").doTry().setHeader(Exchange.HTTP_METHOD, HttpMethods.POST)
				.setHeader(Exchange.CONTENT_TYPE, simple("application/json;charset=UTF-8")).process(new Processor() {

					@Override
					public void process(Exchange exchange) throws Exception {
						Notificacao notificacao = exchange.getMessage().getBody(Notificacao.class);
						JSONObject notificacaoJson = new JSONObject();
						notificacaoJson.put("destinatario", notificacao.getDestinatario());
						notificacaoJson.put("titulo", notificacao.getTitulo());
						notificacaoJson.put("mensagem", notificacao.getMensagem());
						exchange.getMessage().setBody(notificacaoJson.toString());
					}
				}).toD(urlDeEnvio).doCatch(Exception.class).setProperty("error", simple("${exception}"))
				.process(erroProcessor).end();

	}

}
