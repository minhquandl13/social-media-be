package com.java.social_media.service;

import com.java.social_media.models.Chat;
import com.java.social_media.models.User;

import java.util.List;

public interface ChatService {
    Chat createChat(User reqUser, User targetUser);

    Chat findChatById(Integer chatId) throws Exception;

    List<Chat> findUsersChats(Integer userId);
}
