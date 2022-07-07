package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.template;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.User;

import javax.persistence.*;
import java.sql.Timestamp;

@MappedSuperclass  //Designates a class whose mapping information is applied to the entities that inherit from it.
@Data@NoArgsConstructor@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;

    @CreatedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false)  //agar field custom obyekt bolsa Cloumn orniga JoinColumn ishlatiladi
    private User createdBy;

    @LastModifiedBy
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(updatable = false)
    private User updatedBy;
}
