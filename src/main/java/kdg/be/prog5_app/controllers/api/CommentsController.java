package kdg.be.prog5_app.controllers.api;

import jakarta.servlet.http.HttpServletRequest;
import kdg.be.prog5_app.security.CustomUserDetails;
import kdg.be.prog5_app.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static kdg.be.prog5_app.domain.UserRole.ADMIN;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {
    private final CommentService commentService;
    private final ModelMapper modelMapper;

    @Autowired
    public CommentsController(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }


    @DeleteMapping("{id}")
    ResponseEntity<Void> deleteComment(@PathVariable("id") long commentId,
                                       @AuthenticationPrincipal CustomUserDetails user,
                                       HttpServletRequest request) {
        if (user == null || !request.isUserInRole(ADMIN.getCode())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        if (commentService.removeComment(commentId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
