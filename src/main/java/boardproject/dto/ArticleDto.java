package boardproject.dto;

import boardproject.domain.Article;

import java.io.Serializable;
import java.time.LocalDateTime;

public record ArticleDto(
        Long id,
        UserAccountDto userAccountDto,
        String title,
        String content,
        String hashtag,
        LocalDateTime createdAt,
        String createdBy,
        LocalDateTime modifiedAt,
        String modifiedBy
        ) {
    public static ArticleDto of(
            Long id,
            UserAccountDto userAccountDto,
            String title,
            String content,
            String hashtag,
            LocalDateTime createdAt,
            String createdBy,
            LocalDateTime modifiedAt,
            String modifiedBy) {
        return new ArticleDto(id, userAccountDto, title,content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public Article toEntity(){
        return Article.of(
                userAccountDto.toEntity(),
                title,
                content,
                hashtag
        );
    }
}