package com.java.socialmedia.service;

import com.java.socialmedia.models.Chat;
import com.java.socialmedia.models.Message;
import com.java.socialmedia.models.User;

import java.util.List;

public interface MessageService {
    Message createMessage(User user, Integer chatId, Message req) throws Exception;
    List<Message> findChatsMessages(Integer chatId);
}
