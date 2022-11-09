package app;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class Application {
    public static void main(String[] args) {
        Configuration config = new Configuration();
        config.configure("hibernate.cfg.xml");

        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(config.getProperties())
                .buildServiceRegistry();
        Session session = config.buildSessionFactory(serviceRegistry).openSession();

        Post post = new Post();
        post.setTitle("Nature");

        Comment comment1 = new Comment();
        comment1.setAuthorName("Nick");
        comment1.setPost(post);

        Comment comment2 = new Comment();
        comment2.setAuthorName("Laura");
        comment2.setPost(post);

        Set<Comment> comments = new HashSet<>();
        comments.add(comment1);
        comments.add(comment2);

        post.setCommentSet(comments);

        Transaction transaction = session.beginTransaction();
        session.save(post);
        transaction.commit();

        Post postDB = (Post) session.get(Post.class, 1);
        System.out.println(postDB + "---->" + postDB.getCommentSet());
    }
}
