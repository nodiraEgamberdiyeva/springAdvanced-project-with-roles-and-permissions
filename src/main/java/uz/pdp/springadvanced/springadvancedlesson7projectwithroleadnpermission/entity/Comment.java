package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.template.AbstractEntity;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
@EntityListeners(AuditingEntityListener.class)
public class Comment extends AbstractEntity {

    @Column(nullable = false, columnDefinition = "text")
    private String text;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Post post;

}
