package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.ApiResponse;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.CommentDto;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.PostDto;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.service.CommentService;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.service.PostService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PreAuthorize(value = "hasAuthority('ADD_POST')")
    @PostMapping
    public HttpEntity<?> addComment(@Valid @RequestBody PostDto postDto){
        ApiResponse apiResponse = postService.addPost(postDto);
        return ResponseEntity.status(apiResponse.isStatus()?201:409).body(apiResponse.getMessage());
    }
}
