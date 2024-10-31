package com.java.socialmedia.service.impl;

import com.java.socialmedia.models.Chat;
import com.java.socialmedia.models.User;
import com.java.socialmedia.repository.ChatRepository;
import com.java.socialmedia.service.ChatService;
import com.java.socialmedia.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ChatServiceImplementation implements ChatService {
    private ChatRepository chatRepository;

    @Override
    public Chat createChat(User reqUser, User targetUser) {
        Chat existingChat = chatRepository.findChatByUsersId(targetUser, reqUser);

        if (existingChat != null) {
            return existingChat;
        }

        Chat newChat = new Chat();
        newChat.getUsers().add(targetUser);
        newChat.getUsers().add(reqUser);
        newChat.setTimestamp(LocalDateTime.now());


        return chatRepository.save(newChat);
    }

    @Override
    public Chat findChatById(Integer chatId) throws Exception {
        Optional<Chat> chat = chatRepository.findById(chatId);

        if (chat.isEmpty()) {
            throw new Exception("Chat not found with id: " + chatId);
        }

        return chat.get();
    }

    @Override
    public List<Chat> findUsersChats(Integer userId) {
        return chatRepository.findByUsersId(userId);
    }
}
