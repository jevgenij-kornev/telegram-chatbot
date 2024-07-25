package com.example.chatbot;

import com.example.chatbot.service.OpenAIService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TelegramBotTest {

    @Mock
    private OpenAIService openAIService;

    @Spy
    @InjectMocks
    private TelegramBot telegramBot;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testOnUpdateReceived() throws IOException, TelegramApiException {
        // Mock Update
        Update update = mock(Update.class);
        Message message = mock(Message.class);
        when(update.hasMessage()).thenReturn(true);
        when(update.getMessage()).thenReturn(message);
        when(message.hasText()).thenReturn(true);
        when(message.getText()).thenReturn("Hello");
        when(message.getChatId()).thenReturn(123456L);

        // Mock OpenAIService response
        when(openAIService.getResponse("Hello")).thenReturn("Mock response");

        // Mock execute method in telegramBot
        doAnswer(invocation -> null).when(telegramBot).execute(any(SendMessage.class));

        // Capture the SendMessage object passed to execute
        ArgumentCaptor<SendMessage> captor = ArgumentCaptor.forClass(SendMessage.class);

        // Call the method
        telegramBot.onUpdateReceived(update);

        // Verify the interactions and capture the SendMessage object
        verify(telegramBot).execute(captor.capture());

        // Assert the captured SendMessage object
        SendMessage sentMessage = captor.getValue();
        assertEquals("123456", sentMessage.getChatId());
        assertEquals("Mock response", sentMessage.getText());
    }

    @Test
    public void testOnUpdateReceivedWithError() throws IOException, TelegramApiException {
        // Mock Update
        Update update = mock(Update.class);
        Message message = mock(Message.class);
        when(update.hasMessage()).thenReturn(true);
        when(update.getMessage()).thenReturn(message);
        when(message.hasText()).thenReturn(true);
        when(message.getText()).thenReturn("Hello");
        when(message.getChatId()).thenReturn(123456L);

        // Mock OpenAIService to throw IOException
        when(openAIService.getResponse("Hello")).thenThrow(new IOException("Test exception"));

        // Mock execute method in telegramBot
        doAnswer(invocation -> null).when(telegramBot).execute(any(SendMessage.class));

        // Capture the SendMessage object passed to execute
        ArgumentCaptor<SendMessage> captor = ArgumentCaptor.forClass(SendMessage.class);

        // Call the method
        telegramBot.onUpdateReceived(update);

        // Verify the interactions and capture the SendMessage object
        verify(telegramBot).execute(captor.capture());

        // Assert the captured SendMessage object
        SendMessage sentMessage = captor.getValue();
        assertEquals("123456", sentMessage.getChatId());
        assertEquals("Error while accessing OpenAI API.", sentMessage.getText());
    }
}