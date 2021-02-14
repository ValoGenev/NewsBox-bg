package scam.security.userdetails;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import scam.entity.UserEntity;
import scam.repository.IUserRepository;

import static scam.utils.Constants.USER_NOT_FOUND_MESSAGE;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final IUserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UserEntity userInDb = userRepository.findByUsername(userName)
                .orElseThrow(()->new UsernameNotFoundException(USER_NOT_FOUND_MESSAGE));

        return new UserDetailsImpl(userInDb.getUsername(),userInDb.getPassword(),userInDb.getRole().getGrantedAuthorities());
    }
}
