package com.java.socialmedia.controller;

import com.java.socialmedia.models.Chat;
import com.java.socialmedia.models.User;
import com.java.socialmedia.request.CreateChatRequest;
import com.java.socialmedia.service.ChatService;
import com.java.socialmedia.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class ChatController {
    private ChatService chatService;

    private UserService userService;

    @PostMapping("/api/chats")
    public Chat createChat(@RequestHeader("Authorization") String jwt,
                           @RequestBody CreateChatRequest req) throws Exception {
        User reqUser = userService.findUserByJwt(jwt);
        User targetUser = userService.findUserById(req.getTargetUserId());
        Chat chat = chatService.createChat(reqUser, targetUser);

        return chat;
    }

    @GetMapping("/api/chats")
    public List<Chat> findUsersChat(@RequestHeader("Authorization") String jwt) {
        User user = userService.findUserByJwt(jwt);

        List<Chat> chats = chatService.findUsersChats(user.getId());

        return chats;
    }
}
