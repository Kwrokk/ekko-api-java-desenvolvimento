package com.openphilosophy.api.repositories;

import com.openphilosophy.api.models.category.Category;
import com.openphilosophy.api.models.post.Post;
import com.openphilosophy.api.models.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {

    Optional<List<Post>> findAllByCategory(Category category);
    Optional<List<Post>> findAllByAuthor(User author);
    Optional<List<Post>> findAllByAuthorId(UUID id);
    Optional<List<Post>> findAllByAuthorNameLike(String name);
    Optional<List<Post>> findAllByTitleLike(String title);
    Optional<List<Post>> findAllByTagsContains(String tag);

    @Query(
        value = "SELECT * FROM APP_POSTS ORDER BY RAND() LIMIT :qtd",
        nativeQuery = true
    )
    Optional<List<Post>> findRandom(int qtd);
}