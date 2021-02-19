package scam.config;


import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import scam.repository.*;
import scam.service.*;
import scam.service.common.RandomAuthorNameGenerator;
import scam.service.common.RandomAvatarColorGenerator;

@Configuration
public class AppConfiguration {

    @Bean
    ICommentService commentService(ICommentRepository commentRepository, ModelMapper modelMapper, IUserService userService, IPostService postService){
        return new CommentService(commentRepository,modelMapper,userService,postService);
    }

    @Bean
    IPictureService pictureService(IPictureRepository pictureRepository, ModelMapper modelMapper){
        return new PictureService(pictureRepository,modelMapper);
    }

    @Bean
    IThumbNailService thumbNailService(IThumbNailRepository thumbNailRepository, ModelMapper modelMapper){
        return new ThumbNailService(thumbNailRepository,modelMapper);
    }

    @Bean
    IUserService userService(ModelMapper modelMapper, IUserRepository userRepository, PasswordEncoder passwordEncoder,RandomAvatarColorGenerator randomAvatarColorGenerator) {
        return new UserService(userRepository,modelMapper,passwordEncoder,randomAvatarColorGenerator);
    }

    @Bean
    IPostService postService(IPostRepository postRepository,ModelMapper modelMapper,IUserService userService){
        return new PostService(postRepository,modelMapper,userService);
    }

    @Bean
    ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    RandomAuthorNameGenerator randomAuthorNameGenerator(){
        return new RandomAuthorNameGenerator();
    }

    @Bean
    RandomAvatarColorGenerator randomAvatarColorGenerator(){
        return new RandomAvatarColorGenerator();
    }


}
