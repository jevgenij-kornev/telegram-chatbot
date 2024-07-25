package com.example.telegram_chatbot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@SpringBootApplication
public class TelegramChatbotApplication {

	public static void main(String[] args) {
		SpringApplication.run(TelegramChatbotApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
			try {
				TelegramBot bot = ctx.getBean(TelegramBot.class);
				botsApi.registerBot(bot);
			} catch (TelegramApiException e) {
				e.printStackTrace();
			}
		};
	}
}