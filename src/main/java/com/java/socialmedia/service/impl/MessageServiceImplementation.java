package com.java.socialmedia.service.impl;

import com.java.socialmedia.models.Chat;
import com.java.socialmedia.models.Message;
import com.java.socialmedia.models.User;
import com.java.socialmedia.repository.ChatRepository;
import com.java.socialmedia.repository.MessageRepository;
import com.java.socialmedia.service.ChatService;
import com.java.socialmedia.service.MessageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class MessageServiceImplementation implements MessageService {
    private MessageRepository messageRepository;
    private ChatService chatService;
    private ChatRepository chatRepository;

    @Override
    public Message createMessage(User user, Integer chatId, Message req) throws Exception {
        Chat chat = chatService.findChatById(chatId);

        Message message = new Message();
        message.setChat(chat);
        message.setContent(req.getContent());
        message.setImage(req.getImage());
        message.setUser(user);
        message.setTimestamp(LocalDateTime.now());

        Message savedMessage = messageRepository.save(message);
        chat.getMessages().add(savedMessage);
        chatRepository.save(chat);

        return savedMessage;
    }

    @Override
    public List<Message> findChatsMessages(Integer chatId) {
        return messageRepository.findByChatId(chatId);
    }
}
