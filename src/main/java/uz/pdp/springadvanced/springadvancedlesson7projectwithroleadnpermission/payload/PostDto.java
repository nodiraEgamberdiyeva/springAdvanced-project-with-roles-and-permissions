package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    private String text;
    @NotNull
    @NotBlank
    private String url;
}
