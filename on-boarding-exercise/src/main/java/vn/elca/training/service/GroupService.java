package vn.elca.training.service;

import vn.elca.training.model.dto.GroupDto;
import vn.elca.training.model.entity.Group;

import java.util.List;

public interface GroupService {
    List<GroupDto> findAll();
    Group getGroupById(Long id);
}
