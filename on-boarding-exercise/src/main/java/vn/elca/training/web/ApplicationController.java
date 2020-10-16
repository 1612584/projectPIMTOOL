package vn.elca.training.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import vn.elca.training.model.dto.ProjectDto;
import vn.elca.training.service.ProjectService;
import vn.elca.training.util.Mapper;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

/**
 * @author vlp
 *
 */
//@Controller
public class ApplicationController {

//    @Autowired
//    private ProjectService projectService;
//
//    @Value("${total.number.of.projects}")
//    private String message;
//
//    @RequestMapping(value="/", method=RequestMethod.GET)
//    public ModelAndView main() {
//        Map<String, Object> model = new HashMap<String, Object>() {
//            private static final long serialVersionUID = -6883088231537577238L;
//            {
//                put("title", "Project Management Tool");
//                put("message", "null");
//            }
//        };
//        return new ModelAndView("search", model);
//    }
//
//    @PostMapping(value = "/detail/update")
//    public String detail(@Valid @ModelAttribute("project") ProjectDto projectDto, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            return "detail";
//        }
//        projectService.save(Mapper.projectDtoToProject(projectDto));
//        return "redirect:/";
//    }
//
//    @GetMapping(value = "/detail/{projectId}")
//    public String detail(@PathVariable("projectId") Long projectId, Model model) {
////        model.addAttribute("project", Mapper.projectToProjectDto(projectService.findById(projectId)));
//        return "detail";
//    }

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        sdf.setLenient(true);
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
//    }
}
