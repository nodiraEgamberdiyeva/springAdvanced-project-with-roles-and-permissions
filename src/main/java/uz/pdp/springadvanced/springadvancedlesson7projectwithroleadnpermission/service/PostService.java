package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.Post;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.ApiResponse;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.PostDto;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.repository.PostRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    public ApiResponse addPost(PostDto postDto) {
        if (postRepository.existsByUrl(postDto.getUrl())){
            return new ApiResponse("post is exist", false);
        }

        postRepository.save(new Post(postDto.getTitle(), postDto.getText(), postDto.getUrl()));
        return new ApiResponse("post is saved", true);
    }
}
