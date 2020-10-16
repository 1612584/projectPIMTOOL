package vn.elca.training.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.elca.training.model.dto.GroupDto;
import vn.elca.training.model.entity.Group;
import vn.elca.training.model.exception.ForeignKeyNotFoundException;
import vn.elca.training.repository.GroupRepository;
import vn.elca.training.service.GroupService;
import vn.elca.training.util.Mapper;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<GroupDto> findAll() {
        return Mapper.groupListToGroupDtoList(this.groupRepository.findAll());
    }

    public Group getGroupById(Long id) {
        final Optional<Group> group = groupRepository.findById(id);
        if (!group.isPresent()) {
            throw new ForeignKeyNotFoundException("ForeignKey Group_ID not found");
        }
        return group.get();
    }
}
