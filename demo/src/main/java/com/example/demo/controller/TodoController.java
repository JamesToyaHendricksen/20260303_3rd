package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/todo")
public class TodoController {

    @GetMapping
    public String list(Model model) {
        List<Map<String, Object>> todos = List.of(
                Map.of("id", 1, "title", "Spring Boot setup", "status", "DONE"),
                Map.of("id", 2, "title", "Create TodoController", "status", "IN_PROGRESS"),
                Map.of("id", 3, "title", "Build list.html", "status", "TODO"));

        model.addAttribute("todos", todos);
        return "todo/list";
    }
}
