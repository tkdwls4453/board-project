package boardproject.service;

import boardproject.domain.Article;
import boardproject.domain.ArticleComment;
import boardproject.dto.ArticleCommentDto;
import boardproject.repsitory.ArticleCommentRepository;
import boardproject.repsitory.ArticleRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks private ArticleCommentService sut;

    @Mock private ArticleRepository articleRepository;
    @Mock private ArticleCommentRepository articleCommentRepository;

    @DisplayName("게시글 아이디로 조회하면, 해당하는 댓글 리스트를 반환한다.")
    @Test
    void givenArticleId_whenSearchingComments_thenReturnsComments(){
        // Given
        Long articleId = 1L;
        given(articleRepository.findById(articleId)).willReturn(Optional.of(Article.of("title", "content", "#java")));

        // When
        List<ArticleCommentDto> articleComment = sut.searchArticleComment(articleId);

        // Then
        assertThat(articleComment).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("댓글 정보를 입력하면, 댓글을 저장한다")
    @Test
    void givenArticleCommentInfo_whenSavingArticleComment_thenSavesArticleComments(){
        // Given
        Long articleId = 1L;
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);
        // When
        List<ArticleCommentDto> articleComment = sut.saveArticleComment(ArticleCommentDto.of(LocalDateTime.now(), "Uno", LocalDateTime.now(), "Uno", "comment"));

        // Then
        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }
}