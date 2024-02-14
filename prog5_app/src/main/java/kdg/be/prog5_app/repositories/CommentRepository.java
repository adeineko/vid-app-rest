package kdg.be.prog5_app.repositories;

import kdg.be.prog5_app.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query("SELECT comments FROM Comment comments")
    List<Comment> showAllComments();

    List<Comment> findByText(String words);

    Comment findById(long id);

    Comment save(Comment comment);

    Comment deleteById(long id);
}
