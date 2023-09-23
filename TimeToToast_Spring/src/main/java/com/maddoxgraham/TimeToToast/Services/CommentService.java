package com.maddoxgraham.TimeToToast.Services;

import com.maddoxgraham.TimeToToast.Exception.UserNotFoundException;
import com.maddoxgraham.TimeToToast.Models.Comment;
import com.maddoxgraham.TimeToToast.Repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment addComment(Comment comment){
        return commentRepository.save(comment);
    }

    public List<Comment> findAllComments(){
        return commentRepository.findAll();
    }

       public Comment updateComment(Comment comment){
        return commentRepository.save(comment);
    }

      public Comment findCommentByIdComment(Long idComment){
         return commentRepository.findCommentByIdComment(idComment).orElseThrow(() -> new UserNotFoundException("User n° " + idComment + " was not found"));
    }

        public void deleteComment(Long idComment){
        commentRepository.deleteCommentByIdComment(idComment);
    }
}

