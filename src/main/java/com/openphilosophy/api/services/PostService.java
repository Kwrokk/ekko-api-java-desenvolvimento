package com.openphilosophy.api.services;

import com.openphilosophy.api.models.category.Category;
import com.openphilosophy.api.models.post.Post;
import com.openphilosophy.api.models.post.PostMapper;
import com.openphilosophy.api.models.post.dto.PostRequestDTO;
import com.openphilosophy.api.repositories.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

// TODO: refactor methods to include try-catch logic
// TODO: implement delete (D)
// TODO: implement update (put, U)
@Service
public class PostService {

    private final Logger logger;
    private final PostMapper postMapper;
    private final PostRepository postRepository;
    private final String POST_NOT_FOUND_MSG = "Publicação não encontrada: ela pode ter sido deletada ou modificada.";
    private final String UNEXPECTED_ERROR_MSG = "Houve um erro inesperado. Por favor, revise seus dados e tente novamente.";

    public PostService(PostMapper postMapper, PostRepository postRepository) {
        this.postMapper = postMapper;
        this.postRepository = postRepository;
        this.logger = LoggerFactory.getLogger(PostService.class);
    }

    public Post create(PostRequestDTO data) throws ResponseStatusException {
        Post post;
        try {
            post = postMapper.postRequestDtoToPost(data);
        } catch (Exception e) {
            logger.error(UNEXPECTED_ERROR_MSG);
            logger.error("INFO: ", e.getCause());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, UNEXPECTED_ERROR_MSG);
        }
        return postRepository.save(post);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findById(String id) throws ResponseStatusException {
        UUID uuidPost;
        try {
            uuidPost = UUID.fromString(id);
        } catch (Exception e) {
            logger.error("Houve um erro ao tentar converter o ID (String) para UUID (uuid).");
            logger.error("INFO: ", e.getCause());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, UNEXPECTED_ERROR_MSG);
        }
        return postRepository.findById(uuidPost).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, POST_NOT_FOUND_MSG));
    }

    public Post updateById(String id, PostRequestDTO data) {
        try {
            UUID uuidPost = UUID.fromString(id);
            if (postRepository.findById(uuidPost).isPresent()) {
                Post post = postMapper.postRequestDtoToPost(data);
                post.setId(uuidPost);
                postRepository.save(post);
                return post;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, POST_NOT_FOUND_MSG);
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, UNEXPECTED_ERROR_MSG);
        }
    }

    public List<Post> findAllByAuthor(String id) throws ResponseStatusException {
        try {
            UUID authorId = UUID.fromString(id);
            return postRepository.findAllByAuthorId(authorId).orElse(null);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, UNEXPECTED_ERROR_MSG);
        }
    }

    public List<Post> findAllByCategory(Category category) throws ResponseStatusException {
        try {
            return postRepository.findAllByCategory(category).orElse(null);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, UNEXPECTED_ERROR_MSG);
        }
    }

    public List<Post> findAllByAuthorNameLike(String authorName) {
        Optional<List<Post>> posts = postRepository.findAllByAuthorNameLike(authorName);
        return posts.orElse(null);
    }

    public List<Post> findAllByPostTitleLike(String postTitle) {
        Optional<List<Post>> posts = postRepository.findAllByTitleLike(postTitle);
        return posts.orElse(null);
    }

    public List<Post> findAllByPostTagLike(String postTags) {
        Optional<List<Post>> posts = postRepository.findAllByTagsContains(postTags);
        return posts.orElse(null);
    }

    public List<Post> getRandomRecommendations(int qtd) {
        return postRepository.findRandom(qtd).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "BAD REQUEST"));
    }
}
