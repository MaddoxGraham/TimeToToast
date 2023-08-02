package com.maddoxgraham.TimeToToast.Controllers;

import com.maddoxgraham.TimeToToast.Models.Comment;
import com.maddoxgraham.TimeToToast.Services.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Comment>> getAllComment() {
        List<Comment> comments = commentService.findAllComments();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/find/{idComment}")
    public ResponseEntity<Comment> getCommentById(@PathVariable("idComment") Long idComment) {
        Comment comment = commentService.findCommentByIdComment(idComment);
        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment){
        Comment newComment = commentService.addComment(comment);
        return new ResponseEntity<>(newComment, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment comment){
        Comment updateComment = commentService.updateComment(comment);
        return new ResponseEntity<>(updateComment, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{idComment}")
    public ResponseEntity<?> deleteComment(@PathVariable("idComment") Long idComment){
        commentService.deleteComment(idComment);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}