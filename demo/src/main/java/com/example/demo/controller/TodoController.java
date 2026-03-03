package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.service.TodoService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("todos", todoService.findAll());
        return "todo/list";
    }

    @GetMapping("/new")
    public String newForm() {
        return "todo/form";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("todo", todoService.findById(id));
        return "todo/edit";
    }

    @PostMapping("/confirm")
    public String confirm(@RequestParam("title") String title, Model model) {
        model.addAttribute("title", title);
        return "todo/confirm";
    }

    @PostMapping("/complete")
    public String complete(@RequestParam("title") String title) {
        todoService.create(title);
        return "redirect:/todo";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        boolean deleted = todoService.deleteById(id);
        if (deleted) {
            redirectAttributes.addFlashAttribute("successMessage", "ToDo\u3092\u524A\u9664\u3057\u307E\u3057\u305F");
        } else {
            redirectAttributes.addFlashAttribute("errorMessage", "\u524A\u9664\u306B\u5931\u6557\u3057\u307E\u3057\u305F");
        }
        return "redirect:/todo";
    }
}
