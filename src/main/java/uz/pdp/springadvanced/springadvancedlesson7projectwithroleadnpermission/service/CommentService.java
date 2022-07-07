package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.config.SecurityConfiguration;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.Comment;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.Post;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.entity.User;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.exception.ForbiddenException;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.ApiResponse;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.CommentDto;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.repository.CommentRepository;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.repository.PostRepository;

import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;


    public ApiResponse addComment(CommentDto commentDto) {
        Optional<Post> byId = postRepository.findById(commentDto.getPostId());
        if (!byId.isPresent()){
            return new ApiResponse("post not found", false);
        }
        commentRepository.save(new Comment(commentDto.getText(), byId.get()));
        return new ApiResponse("commnet is added", true);
    }

    public ApiResponse deleteComment(Long id) {
        Optional<Comment> byId = commentRepository.findById(id);
        User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.getRole().getName().equals("user")&&
                byId.get().getCreatedBy().getId()!=user.getId()){
            return new ApiResponse("users can delete only their comments", false);
        }

        if (byId.isPresent()){
            commentRepository.delete(byId.get());
            return new ApiResponse("comment is deleted", true);
        }
        return new ApiResponse("comment is not exist", false);
    }

}
