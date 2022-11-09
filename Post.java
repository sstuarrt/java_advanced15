package app;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "title")
    private String title;

    @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL,
            CascadeType.PERSIST,CascadeType.MERGE })//, mappedBy = "post")
    @Column(nullable = false)
    private Set<Comment> commentSet;

    public Post() {}

    public Post(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Comment> getCommentSet() {
        return commentSet;
    }

    public void setCommentSet(Set<Comment> commentSet) {
        this.commentSet = commentSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Post post)) return false;
        return id.equals(post.id) && title.equals(post.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
