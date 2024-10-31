package com.java.socialmedia.controller;

import com.java.socialmedia.models.Message;
import com.java.socialmedia.models.User;
import com.java.socialmedia.service.MessageService;
import com.java.socialmedia.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CreateMessage {
    private MessageService messageService;
    private UserService userService;

    @PostMapping("/api/messages/chat/{chatId}")
    public Message createMessage(@RequestBody Message req,
                                 @RequestHeader("Authorization") String jwt,
                                 @PathVariable Integer chatId) throws Exception {
        User user = userService.findUserByJwt(jwt);
        Message message = messageService.createMessage(user, chatId, req);

        return message;
    }

    @GetMapping("/api/messages/chat/{chatId}")
    public List<Message> findChatsMessage( @RequestHeader("Authorization") String jwt,
                                     @PathVariable Integer chatId) {
        User user = userService.findUserByJwt(jwt);
        List<Message> messages = messageService.findChatsMessages(chatId);

        return messages;
    }
}
