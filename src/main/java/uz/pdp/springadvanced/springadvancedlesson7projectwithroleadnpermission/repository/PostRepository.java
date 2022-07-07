package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.repository;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    boolean existsByUrl(String url);
}
