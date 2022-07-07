package uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.aop.CheckDeletedComment;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.ApiResponse;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.payload.CommentDto;
import uz.pdp.springadvanced.springadvancedlesson7projectwithroleadnpermission.service.CommentService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    //CUSTOM ANNOTATION (similar with @PreAuthorize)
    @PreAuthorize(value = "hasAnyAuthority('ADD_COMMENT')")
    @PostMapping
    public HttpEntity<?> addComment(@Valid @RequestBody CommentDto commentDto){
        ApiResponse apiResponse = commentService.addComment(commentDto);
        return ResponseEntity.status(apiResponse.isStatus()?201:409).body(apiResponse.getMessage());
    }

    @PreAuthorize(value = "hasAnyAuthority('DELETE_COMMENT', 'DELETE_MY_COMMENT')")
    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteComment(@PathVariable Long id){
        ApiResponse response = commentService.deleteComment(id);
        return ResponseEntity.status(response.isStatus()?200:409).body(response.getMessage());
    }
}
