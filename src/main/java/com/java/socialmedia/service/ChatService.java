package com.java.socialmedia.service;

import com.java.socialmedia.models.Chat;
import com.java.socialmedia.models.User;

import java.util.List;

public interface ChatService {
    Chat createChat(User reqUser, User targetUser);

    Chat findChatById(Integer chatId) throws Exception;

    List<Chat> findUsersChats(Integer userId);
}
