package in.co.sveps.service;

import in.co.sveps.entity.Employee;
import in.co.sveps.entity.Group;
import in.co.sveps.repo.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;


    public List<Group> findAll(){
        return groupRepository.findAll();
    }

//    public List<String> findAllGroupNames() {
//        return groupRepository.findAll()
//                .stream()
//                .map(Group::getName) // Extracting only the names
//                .collect(Collectors.toList());
//    }

    public List<Group> findAllByIds(List<String> groupIds) {
        return (List<Group>) groupRepository.findAllById(groupIds);
    }
}
