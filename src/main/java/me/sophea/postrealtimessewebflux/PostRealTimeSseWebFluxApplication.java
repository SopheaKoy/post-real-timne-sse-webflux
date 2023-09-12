package me.sophea.postrealtimessewebflux;

import me.sophea.postrealtimessewebflux.model.Comment;
import me.sophea.postrealtimessewebflux.model.Post;
import me.sophea.postrealtimessewebflux.model.User;
import me.sophea.postrealtimessewebflux.repository.PostRepository;
import me.sophea.postrealtimessewebflux.repository.UserRepository;
import me.sophea.postrealtimessewebflux.request.CommentRequest;
import me.sophea.postrealtimessewebflux.request.LikeRequest;
import me.sophea.postrealtimessewebflux.service.NotificationStorageService;
import me.sophea.postrealtimessewebflux.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PostRealTimeSseWebFluxApplication implements CommandLineRunner {

    public static void main(String[] args) {

        SpringApplication.run(PostRealTimeSseWebFluxApplication.class, args);
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostService postService;

    @Autowired
    NotificationStorageService notificationStorageService;

    @Override
    public void run(String... args) throws Exception {

        userRepository.deleteAll();
        postRepository.deleteAll();

        User user = userRepository.save(User.builder().username("sophea").name("Gurkan").profileImageUrl("https://avatars.githubusercontent.com/u/25080366?v=4").build());
        User user2 = userRepository.save(User.builder().username("saran").name("sezai").profileImageUrl("https://pbs.twimg.com/profile_images/1232374207729799170/IqtUTP4s_400x400.jpg").build());
        User user3 = userRepository.save(User.builder().username("piseth").name("serif").profileImageUrl("https://i.ytimg.com/vi/0Z996ZDAdPg/maxresdefault.jpg").build());
        User user4 = userRepository.save(User.builder().username("samroth").name("serif").profileImageUrl("https://i.ytimg.com/vi/0Z996ZDAdPg/maxresdefault.jpg").build());
        User user5 = userRepository.save(User.builder().username("odinn").name("serif").profileImageUrl("https://i.ytimg.com/vi/0Z996ZDAdPg/maxresdefault.jpg").build());
        User user6 = userRepository.save(User.builder().username("noch").name("serif").profileImageUrl("https://i.ytimg.com/vi/0Z996ZDAdPg/maxresdefault.jpg").build());


        Post post1 = postService.create(Post.builder().content("first post, this is test!").user(user).build());
        Post post2 = postService.create(Post.builder().content("Post2 Lorem ipsum dolor sit amet, consectetur adipiscing elit.").user(user2).build());
//        Post post3 = postService.create(Post.builder().content("Post3 Lorem ipsum dolor sit amet, consectetur adipiscing elit.").user(user3).build());
//        Post post4 = postService.create(Post.builder().content("Post4 Lorem ipsum dolor sit amet, consectetur adipiscing elit.").user(user).build());
//        Post post5 = postService.create(Post.builder().content("Post5 Lorem ipsum dolor sit amet, consectetur adipiscing elit.").user(user2).build());
//        Post post6 = postService.create(Post.builder().content("Post6 Lorem ipsum dolor sit amet, consectetur adipiscing elit.").user(user2).build());


        for (int i = 0; i < 5; i++) {

            postService.addComment(new CommentRequest(post2, Comment.builder().user(user).content("comment " + i).build()));
            postService.addComment(new CommentRequest(post2, Comment.builder().user(user3).content("comment  " + i).build()));
            postService.addComment(new CommentRequest(post1, Comment.builder().user(user).content("comment " + i).build()));
        }


        postService.addLike(new LikeRequest(post1, user2));
        postService.addLike(new LikeRequest(post2, user));
        postService.addLike(new LikeRequest(post2, user3));
        postService.addLike(new LikeRequest(post2, user4));
        postService.addLike(new LikeRequest(post2, user5));
        postService.addLike(new LikeRequest(post2, user6));


        notificationStorageService.clear();


    }
}
