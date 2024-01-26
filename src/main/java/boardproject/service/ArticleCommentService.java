package boardproject.service;

import boardproject.dto.ArticleCommentDto;
import boardproject.repsitory.ArticleCommentRepository;
import boardproject.repsitory.ArticleRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;


    @Transactional(readOnly = true)
    public List<ArticleCommentDto> searchArticleComment(Long articleId) {
        return List.of();
    }


    public List<ArticleCommentDto> saveArticleComment(ArticleCommentDto dto) {
        return null;
    }
}
