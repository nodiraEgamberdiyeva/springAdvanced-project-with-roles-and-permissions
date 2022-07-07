package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.template.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(callSuper = true)
public class Post extends AbstractEntity {
    @Column(nullable = false, columnDefinition = "text")    //columnDefinition dbda column text type qiladi.
    private String title;
    @Column(nullable = false, columnDefinition = "text")
    private String text;
    @Column(nullable = false, columnDefinition = "text", unique = true)
    private String url;
}
