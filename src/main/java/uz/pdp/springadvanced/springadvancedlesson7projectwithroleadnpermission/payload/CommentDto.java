package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data@AllArgsConstructor@NoArgsConstructor
public class CommentDto {
    @NotNull
    @NotBlank
    private String text;

    @NotNull
    private Long postId;
}
