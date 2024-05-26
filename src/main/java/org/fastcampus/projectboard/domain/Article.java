package org.fastcampus.projectboard.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy"),
})
@EntityListeners(AuditingEntityListener.class)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String title;
    @Setter
    @Column(nullable = false, length = 10000)
    private String content;
    @Setter
    private String hashtag;

    @OrderBy("id")
    @OneToMany(mappedBy = "article",cascade = CascadeType.ALL)//이렇게 되면 FK가 설정되어서 article이 삭제되면, 댓글도 삭제됨
    @ToString.Exclude //이렇게 하지 않으면 영원히 순환 참조하는 경우가 발생할 수 있음, 보통 여기서 끊음
    private final Set<ArticleComment> articleComments = new LinkedHashSet<>();

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdAt;
    @CreatedBy
    @Column(nullable = false, length = 100)
    private String createdBy;
    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime modifiedAt;
    @LastModifiedBy
    @Column(nullable = false, length = 100)
    private String modifiedBy;

    protected Article(){}

    private Article(String title, String content, String hashtag) {
        this.title = title;
        this.content = content;
        this.hashtag = hashtag;
    }

    public static Article of(String title, String content, String hashtag) {
        return new Article(title,content,hashtag);
    }

    //리스트로 내보내거나 할때, 동일성, 동등성 검사를 해야함 - 롬복을 쓸 수도 있지만 엔티티에서는 독특한 방법으로 만들어야 함

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return id != null && Objects.equals(getId(), article.getId());
    }
    //아직 id를 갖지 않는 경우 모든 동등성 검사를 탈락한다는 의미
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
