package com.joshlong.google.pubsubhubbub;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URL;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@AutoConfiguration
class PubsubHubbubClientAutoConfiguration {

	@Bean
	@ConditionalOnMissingBean
	DefaultPubsubHubbubClient pubsubHubbubClient(WebClient http) {
		return new DefaultPubsubHubbubClient(http, url("https://pubsubhubbub.appspot.com"));
	}

	@NonNull
	private static URL url(String url) {
		try {
			return new URL(url);
		}
		catch (Throwable throwable) {
			log.error("ooops!", throwable);
		}
		throw new IllegalArgumentException("the URL could not be created!");
	}

}
