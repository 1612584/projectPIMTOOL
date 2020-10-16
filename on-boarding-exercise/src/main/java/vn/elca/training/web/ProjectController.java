package vn.elca.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import vn.elca.training.model.dto.ListIdDto;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.model.dto.ResponseDto;
import vn.elca.training.model.exception.ConcurrentUpdateException;
import vn.elca.training.service.ProjectService;

import javax.validation.Valid;
import java.util.List;

/**
 * @author gtn
 *
 */
@RestController
@RequestMapping("/api/projects")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping
    @ResponseBody
    public Page<ProjectDto> query(@RequestParam(required = false)  String name,
                                  @RequestParam(required = false) String status,
                                  Pageable pageable) {
        return projectService.findAllByNameAndStatus(name, status, pageable);
    }

    @GetMapping("/list")
    @ResponseBody
    public List<ProjectDto> queryList(@RequestParam(required = false)  String name,
                                  @RequestParam(required = false) String status) {
        return projectService.findAllByNameAndStatus(name, status);
    }

    @PostMapping
    public ResponseDto createProject(@Valid @RequestBody ProjectDto project) {
        try {
            return projectService.createProject(project);
        } catch (ConcurrentUpdateException ex) {
            return new ResponseDto(true, false, ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseDto removeProject(@PathVariable Long id) {
        projectService.removeProjectById(id);
        ResponseDto dto = new ResponseDto();
        dto.setSuccess(true);
        return dto;
    }

    @GetMapping("/exists")
    public boolean checkProjectNumberExist(@RequestParam Integer projectNumber) {
        return projectService.checkProjectNumberExist(projectNumber);
    }

    @GetMapping("/{id}")
    public ProjectDto getProjectById(@PathVariable Long id) {
        return projectService.findById(id);
    }

    @PutMapping
    public long deleteProjectsByListId(@RequestBody ListIdDto listIdDto) {
        final long deleteFailureNumber = -1l;
        try {
           return projectService.deleteByListId(listIdDto.getListId());
        } catch (Exception ex) {
            return deleteFailureNumber;
        }
    }

    @PatchMapping("/{id}")
    public ResponseDto  updateProject(@PathVariable Long id, @Valid @RequestBody ProjectDto projectDto) {
        try {
            return projectService.updateProject(id, projectDto);
        } catch (ConcurrentUpdateException ex) {
            return new ResponseDto(true, false, ex.getMessage());
        }
    }
}