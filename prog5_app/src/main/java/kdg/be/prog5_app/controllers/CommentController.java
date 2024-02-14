package kdg.be.prog5_app.controllers;

import jakarta.validation.Valid;
import kdg.be.prog5_app.domain.Comment;
import kdg.be.prog5_app.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public String getCommentsPage(Model model) {
        model.addAttribute("comments", commentService.getComments());
        return "comment/Comments";
    }

    @PostMapping("delete/{id}")
    public String deleteComment(@PathVariable(value = "id") Long id) {
        commentService.deleteComment(id);
        return "redirect:/comments";
    }

    @GetMapping("/{id}")
    public String getComment(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("comment", commentService.getComment(id));
        return "comment/details";
    }

    @GetMapping("/add")
    public String getCommentPage(Model model) {
        model.addAttribute("getPage", new Comment());
        return "comment/AddComment";
    }

    @PostMapping("create")
    public String addComment(@Valid @ModelAttribute Comment comment, BindingResult errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("getPage", new Comment());
            model.addAttribute("message2", errors.getAllErrors().stream().findFirst().orElse(null).getDefaultMessage());
            return "comment/AddComment";
        }
        commentService.addComment(comment);
        model.addAttribute("getPage", new Comment());
        model.addAttribute("message", "Comment added!");
        return "comment/AddComment";
    }
}
