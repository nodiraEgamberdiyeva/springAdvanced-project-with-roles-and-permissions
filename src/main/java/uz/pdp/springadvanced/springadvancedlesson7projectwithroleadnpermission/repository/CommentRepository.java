package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.Comment;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.User;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<User> findByCreatedBy(User createdBy);
}
