package com.openphilosophy.api.controllers;

import com.openphilosophy.api.models.category.Category;
import com.openphilosophy.api.models.post.Post;
import com.openphilosophy.api.models.post.dto.PostRequestDTO;
import com.openphilosophy.api.services.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/")
    public ResponseEntity<Post> create(@RequestBody @Valid PostRequestDTO data) {
        return ResponseEntity.ok(postService.create(data));
    }

    @GetMapping("/")
    public ResponseEntity<List<Post>> findAll() {
        return ResponseEntity.ok(postService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        return ResponseEntity.ok(postService.findById(id));
    }

    // TODO: implement update (put, U)
    @PutMapping("/{id}")
    public ResponseEntity<Post> updateById(@PathVariable String id, @RequestBody PostRequestDTO data) {
        return ResponseEntity.ok(postService.updateById(id, data));
    }

    // TODO: implement delete (D)
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable String id) {
        return ResponseEntity.ok(id);
    }

    // TODO: implement get by author's id
    @GetMapping("/author/{id}")
    public ResponseEntity<List<Post>> findAllByAuthor(@PathVariable String id) {
        return ResponseEntity.ok(postService.findAllByAuthor(id));
    }

    @GetMapping("/search/author/")
    public ResponseEntity<List<Post>> findAllByAuthorName(@RequestParam String authorName) {
        return ResponseEntity.ok(postService.findAllByAuthorNameLike(authorName));
    }

    // TODO: implement get by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Post>> findAllByCategory(@PathVariable Category category) {
        return ResponseEntity.ok(postService.findAllByCategory(category));
    }

    // TODO: implement get by tag (like)
    @GetMapping("/tag/{tag}")
    public ResponseEntity<List<Post>> getByTags(@PathVariable String tag) {
        return ResponseEntity.ok(postService.findAllByPostTagLike(tag));
    }

    // TODO: implement get by post's title (like)
    @GetMapping("/search/title/{title}")
    public ResponseEntity<List<Post>> getByPostTitle(@PathVariable String title) {
        return ResponseEntity.ok(postService.findAllByPostTitleLike(title));
    }

    @GetMapping("/recommendations/{qtd}")
    public List<Post> getRecommendations(@PathVariable int qtd) {
        return ResponseEntity.ok(postService.getRandomRecommendations(qtd)).getBody();
    }
}
