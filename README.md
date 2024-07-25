Telegram Chatbot

This project is a Telegram bot integrated with OpenAI API for processing and generating responses to user messages. 
The bot is written in Java using Spring Boot and includes Docker support for easy deployment.

Technologies

The project uses the following technologies:

	•	Java 17: Main programming language.
	•	Spring Boot: Framework for building and running applications.
	•	Telegram Bots API: Library for interacting with Telegram API.
	•	OpenAI API: For generating responses to messages.
	•	Maven: Tool for dependency management and project build.
	•	Docker: For containerizing the application.
	•	JUnit and Mockito: For writing and running unit tests.
	•	SLF4J: For logging.

Features

	•	Receive messages from users via Telegram.
	•	Send requests to OpenAI API to generate responses.
	•	Return generated responses to users in Telegram.
	•	Log events and errors.
	•	Docker support for easy deployment and running.

Requirements

To run this project on your computer, you will need the following tools:

	•	JDK 17: To compile and run Java code.
	•	Maven: To build the project and manage dependencies.
	•	Docker: To containerize and deploy the application.
	•	Telegram Bot API Token: To interact with Telegram API.
	•	OpenAI API Key: To interact with OpenAI API.

Installation and Running

Follow these steps to install and run the project on your computer:

Configure the Application

Create a file src/main/resources/application.properties and add your tokens:
```
telegram.bot.username=YOUR_BOT_USERNAME
telegram.bot.token=YOUR_BOT_TOKEN
openai.api.key=YOUR_OPENAI_API_KEY
```
Build the Project

Build the project using Maven:
```
mvn clean install
```
Run Without Docker
```
mvn spring-boot:run
```
Run With Docker
```
docker build -t telegram-chatbot .
docker run -d -p 8080:8080 --name telegram-chatbot telegram-chatbot
```
If you have any questions or issues with the project, please create a new issue on GitHub or contact me directly.
