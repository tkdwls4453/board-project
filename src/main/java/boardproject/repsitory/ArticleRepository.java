package boardproject.repsitory;

import boardproject.domain.Article;
import boardproject.domain.QArticle;
import boardproject.repsitory.querydsl.ArticleRepositoryCustom;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        ArticleRepositoryCustom,
        QuerydslPredicateExecutor<Article>,
        QuerydslBinderCustomizer<QArticle> {

    Page<Article> findByTitleContaining(String title, Pageable pageable);
    Page<Article> findByContentContaining(String content, Pageable pageable);
    Page<Article> findByUserAccount_UserIdContaining(String userId, Pageable pageable);
    Page<Article> findByUserAccount_NicknameContaining(String nickname, Pageable pageable);
    Page<Article> findByHashtag(String hashtag, Pageable pageable);
    @Override
    default void customize(QuerydslBindings bindings, QArticle root) {
        // 현재는 'QuerydslPredicateExecutor<Article>' 문구로 인해서 모든 필드가 검색 필드로 들어가는데 이것은 우리가 원한 것이 아니다.
        // 기본값이 false 인데 true 로 변경해줘서 모든 필드가 검색 필드로 가는 것을 막음
        bindings.excludeUnlistedProperties(true);

        // 검색 필드에 추가함
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);

        // 기존에 정확히 검색해야 하는 부분을 변경
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase);
        bindings.bind(root.createdAt).first(DateTimeExpression::eq);
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase);
    }
}
