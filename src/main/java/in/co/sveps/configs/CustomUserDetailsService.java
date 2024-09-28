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

        Set<GrantedAuthority> authorities = Optional.ofNullable(employee.getGroups()) // Wrap the groups in an Optional
                .orElseGet(Collections::emptyList) // If groups are null, use an empty list
                .stream() // Create a stream from the list
                .map(groupName -> Optional.ofNullable(groupRepository.findByName(groupName))) // Map to Optional<Group>
                .filter(Optional::isPresent) // Filter out empty Optionals
                .flatMap(groupOpt -> groupOpt.get().getPermissions().stream()) // Get permissions from each present group
                .map(SimpleGrantedAuthority::new) // Convert permissions to GrantedAuthority
                .collect(Collectors.toSet()); // Collect the result into a Set
        // Collect the result into a List

        List<GrantedAuthority> authorities2 = Optional.ofNullable(employee.getPermissions())
                .orElse(Collections.emptyList()) // If null, use an empty list
                .stream()
                .map(SimpleGrantedAuthority::new) // Convert each permission into a SimpleGrantedAuthority
                .collect(Collectors.toList());


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