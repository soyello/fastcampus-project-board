package org.fastcampus.projectboard.repository;

import org.fastcampus.projectboard.config.JpaConfig;
import org.fastcampus.projectboard.domain.Article;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Import(JpaConfig.class)//이렇게 하지 않으면 이 JpaConfig가 동작하지 않는다.
@DataJpaTest //이 덕분에 test 데이터를 업데이트 쿼리 발생시키지 않음
@DisplayName("JPA 연결 테스트")
class JpsRepositoryTest {

    private final ArticleRepository articleRepository;
    private final ArticleCommentRepository articleCommentRepository;

    public JpsRepositoryTest(
            @Autowired ArticleRepository articleRepository,
            @Autowired ArticleCommentRepository articleCommentRepository) { //이렇게 autowired 거는 것도 먹힌다!
        this.articleRepository = articleRepository;
        this.articleCommentRepository = articleCommentRepository;
    }
    @DisplayName("select 테스트")
    @Test
    void givenTestDate_whenSelecting_thenWorksFine(){
        //given

        //when
        List<Article> articles = articleRepository.findAll();
        //then
        assertThat(articles)
                .isNotNull()
                .hasSize(4);

    }
    @DisplayName("insert 테스트")
    @Test
    void givenTestDate_whenInserting_thenWorksFine(){
        //given
        long previousCount = articleRepository.count();

        //when
        Article savedArticles = articleRepository.save(Article.of("new article","행복하니","화이팅"));
        //then
        assertThat(articleRepository.count())
                .isEqualTo(previousCount+1);

    }
    @DisplayName("update 테스트")
    @Test
    void givenTestDate_whenUpdating_thenWorksFine(){
        //given
        Article article = articleRepository.findById(1L).orElseThrow();
        String updatedHashtag = "#springboot";
        article.setHashtag(updatedHashtag);

        //when
        Article savedArticle = articleRepository.saveAndFlush(article);

        //then
        assertThat(savedArticle).hasFieldOrPropertyWithValue("hashtag",updatedHashtag);

    }
    @DisplayName("delete 테스트")
    @Test
    void givenTestDate_whenDeleting_thenWorksFine(){
        //given
        Article article = articleRepository.findById(1L).orElseThrow();
        long previousArticleCount = articleRepository.count();
        long previousArticleComment = articleCommentRepository.count(); //게시글 삭제하면 댓글 삭제되는지
        int deletedCommentsSize = article.getArticleComments().size();

        //when
        articleRepository.delete(article);

        //then
        assertThat(articleRepository.count()).isEqualTo(previousArticleCount-1);
        assertThat(articleCommentRepository.count()).isEqualTo(previousArticleComment-deletedCommentsSize);


    }
}