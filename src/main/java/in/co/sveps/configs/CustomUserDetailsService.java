package in.co.sveps.configs;

import java.util.*;
import java.util.stream.Collectors;

import in.co.sveps.entity.Group;
import in.co.sveps.repo.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import in.co.sveps.entity.Employee;
import in.co.sveps.entity.LoggedInUser;
import in.co.sveps.repo.EmployeeRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private EmployeeRepository employeeRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
         Employee employee = employeeRepository.findByEmail(email).orElseThrow(() ->  new UsernameNotFoundException("User not found with username: " + email));

        Set<GrantedAuthority> authorities = employee.getGroups().stream()
                .map(groupRepository::findByName) // Map each group name to the corresponding Group object
                .filter(Objects::nonNull) // Filter out null groups, just in case
                .flatMap(group -> group.getPermissions().stream()) // Flatten the permissions from each group
                .map(SimpleGrantedAuthority::new) // Convert each permission to a GrantedAuthority
                .collect(Collectors.toSet()); // Collect the result into a List

        List<GrantedAuthority> authorities2 = employee.getPermissions().stream()
                .map(SimpleGrantedAuthority::new) // Convert each permission into a SimpleGrantedAuthority
                .collect(Collectors.toList()); // Collect into a List

        authorities.addAll(authorities2);

        LoggedInUser loggedInUser = new LoggedInUser();
        loggedInUser.setAuthorities(authorities);
        loggedInUser.setId(employee.getId());
        loggedInUser.setUsername(employee.getEmail());
        loggedInUser.setPassword(employee.getPassword());
        loggedInUser.setFullName(employee.getFirstName()+" "+employee.getLastName());
        loggedInUser.setEmail(employee.getEmail());
        loggedInUser.setEnabled(employee.isEnabled());
        return loggedInUser;
    }
}