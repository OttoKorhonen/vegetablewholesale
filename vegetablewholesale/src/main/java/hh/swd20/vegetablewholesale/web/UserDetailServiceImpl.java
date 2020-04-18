//package hh.swd20.vegetablewholesale.web;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.AuthorityUtils;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import hh.swd20.vegetablewholesale.domain.User;
//import hh.swd20.vegetablewholesale.domain.UserRepository;
//
//@Service
//public class UserDetailServiceImpl implements UserDetailsService {
//	
//	private final UserRepository uRepo;
//	
//	@Autowired
//	public UserDetailServiceImpl(UserRepository userRepository) {
//		this.uRepo = userRepository;
//	}
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
//		User curruser = uRepo.findByUsername(username);
//		UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHash(), 
//        		AuthorityUtils.createAuthorityList(curruser.getRole()));
//        return user;
//	}
//}
