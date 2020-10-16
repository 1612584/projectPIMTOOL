//package vn.elca.training.service;
//
//import com.sun.xml.internal.ws.policy.spi.AssertionCreationException;
//import org.assertj.core.api.Assertions;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.util.AssertionErrors;
//import vn.elca.training.ApplicationWebConfig;
//import vn.elca.training.model.dto.ProjectDto;
//
//import javax.validation.ConstraintViolationException;
//
//@ContextConfiguration(classes = {ApplicationWebConfig.class})
//@RunWith(value= SpringRunner.class)
//public class ProgrammaticallyValidatingServiceTest {
//    @Autowired
//    private ProgrammaticallyValidatingService service;
//
//    @Test
//    public void whenInputIsInvalid_thenThrowsException(){
//        ProjectDto projectDto = new ProjectDto();
//        projectDto.setId(null);
//        projectDto.setStatus(null);
//        projectDto.setStartDate(null);
//        projectDto.setCustomer(null);
//        projectDto.setName("abcc");
//        projectDto.setProjectNumber(null);
//
//        try {
//            this.service.validateInputWithInjectedValidator(projectDto);
//        } catch (ConstraintViolationException ex) {
//            System.err.println(ex.getConstraintViolations());
//            Assert.assertEquals(5,ex.getConstraintViolations().size());
//        }
//    }
//
////    @Test
////    void givenInjectedValidator_whenInputIsInvalid_thenThrowsException(){
////        Input input = invalidInput();
////
////        assertThrows(ConstraintViolationException.class, () -> {
////            service.validateInputWithInjectedValidator(input);
////        });
////    }
//}
