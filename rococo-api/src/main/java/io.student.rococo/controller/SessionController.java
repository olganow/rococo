package io.student.rococo.controller;

import io.student.rococo.model.SessionJson;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api/session")
public class SessionController {
    @GetMapping
    public SessionJson getSession(){
        return new SessionJson(
                "MockUser",
                new Date(),
                new Date()
        );
    }
}