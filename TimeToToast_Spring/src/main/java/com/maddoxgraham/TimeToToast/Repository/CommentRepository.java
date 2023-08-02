package com.maddoxgraham.TimeToToast.Repository;

import com.maddoxgraham.TimeToToast.Models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    void deleteCommentByIdComment(Long idComment);
    Optional<Comment> findCommentByIdComment(Long idComment);
}
