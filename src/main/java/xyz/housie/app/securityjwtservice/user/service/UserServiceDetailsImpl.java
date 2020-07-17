package xyz.housie.app.securityjwtservice.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import xyz.housie.app.securityjpaservice.role.model.Role;
import xyz.housie.app.securityjpaservice.user.model.User;
import xyz.housie.app.securityjpaservice.user.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsService")
public class UserServiceDetailsImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);

        if(user == null)
            throw new UsernameNotFoundException("User not found with the username" + username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(),true,true,true,true,getAuthority(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> getAuthority(List<Role> role) {
        String roles = role.stream().map(Role::getRole).collect(Collectors.joining(","));
        return AuthorityUtils.createAuthorityList(roles);
    }
}
