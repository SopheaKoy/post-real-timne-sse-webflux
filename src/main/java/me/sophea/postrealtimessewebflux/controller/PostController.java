package me.sophea.postrealtimessewebflux.controller;

import me.sophea.postrealtimessewebflux.model.Comment;
import me.sophea.postrealtimessewebflux.model.Post;
import me.sophea.postrealtimessewebflux.request.CommentRequest;
import me.sophea.postrealtimessewebflux.request.LikeRequest;
import me.sophea.postrealtimessewebflux.service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;


    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/stream")
    public Flux<ServerSentEvent<List<Post>>> streamPosts() {
        return postService.streamPosts();
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(postService.getAll());
    }

    @GetMapping("/{postID}")
    public ResponseEntity<Post> getPostById(@PathVariable String postID) {
        return ResponseEntity.ok(postService.getPostByID(postID));
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(postService.create(post));
    }

    @GetMapping("/comment/{postID}")
    public ResponseEntity<List<Comment>> getCommentsByPostID(@PathVariable String postID) {
        return ResponseEntity.ok(postService.getCommentsByPostID(postID));
    }

    @PostMapping("/comment")
    public ResponseEntity<Post> addComment(@RequestBody CommentRequest commentRequest) {
        return ResponseEntity.ok(postService.addComment(commentRequest));
    }

    @DeleteMapping("/comment")
    public ResponseEntity<Post> removeComment(@RequestBody CommentRequest commentRequest) {
        return ResponseEntity.ok(postService.removeComment(commentRequest));
    }

    @PostMapping("/like")
    public ResponseEntity<Post> addLike(@RequestBody LikeRequest likeRequest) {
        return ResponseEntity.ok(postService.addLike(likeRequest));
    }

    @DeleteMapping("/like")
    public ResponseEntity<Post> removeLike(@RequestBody LikeRequest likeRequest) {
        return ResponseEntity.ok(postService.removeLike(likeRequest));
    }

    @DeleteMapping("/{postID}")
    public ResponseEntity<?> deletePostByID(@PathVariable String postID) {
        postService.deletePostByID(postID);
        return ResponseEntity.ok().build();
    }

}
