//package vn.elca.training.repository;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//import com.querydsl.jpa.impl.JPAQuery;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import vn.elca.training.ApplicationWebConfig;
//import vn.elca.training.model.entity.Group;
//import vn.elca.training.model.entity.Project;
//import vn.elca.training.model.entity.QGroup;
//import vn.elca.training.model.entity.QProject;
//
//@ContextConfiguration(classes = {ApplicationWebConfig.class})
//@RunWith(value=SpringRunner.class)
//public class ProjectRepositoryTest {
//    @PersistenceContext
//    private EntityManager em;
//    @Autowired
//    private ProjectRepository projectRepository;
//    @Autowired
//    private GroupRepository groupRepository;
//
//    @Test
//    public void testCountAll() {
//        projectRepository.save(new Project("KSTA", LocalDate.now()));
//        projectRepository.save(new Project("LAGAPEO", LocalDate.now()));
//        projectRepository.save(new Project("ZHQUEST", LocalDate.now()));
//        projectRepository.save(new Project("SECUTIX", LocalDate.now()));
//        Assert.assertEquals(4, projectRepository.count());
//    }
//
//    @Test
//    public void testFindOneWithQueryDSL() {
//        final String PROJECT_NAME = "KSTA";
//        projectRepository.save(new Project(PROJECT_NAME, LocalDate.now()));
//        Project project = new JPAQuery<Project>(em)
//                .from(QProject.project)
//                .where(QProject.project.name.eq(PROJECT_NAME))
//                .fetchFirst();
//        Assert.assertEquals(PROJECT_NAME, project.getName());
//    }
//
//    @Test
//    public void testSaveOne() {
//        Project project = this.save("ABCDEF");
//        LocalDate now = project.getEndDate();
//
//        Project foundProject = new JPAQuery<Project>(em)
//                                    .from(QProject.project)
//                                    .where(QProject.project.name.eq("ABCDEF").and(
//                                            QProject.project.endDate.eq(now))
//                                    ).fetchFirst();
//        Assert.assertTrue(foundProject != null);
//    }
//
//    @Test
//    public void testDeleteProject() {
//        Project project = this.save("ABC");
//        Assert.assertTrue(projectRepository.exists(project.getId()));
//        projectRepository.delete(project);
//        Assert.assertFalse(projectRepository.exists(project.getId()));
//    }
//
//    @Test
//    public void testQueryDSL() {
//        Project project = new Project();
//        project.setName("ABCDEF");
//        LocalDate now = LocalDate.now();
//        project.setEndDate(now);
//        projectRepository.saveAndFlush(project);
//        Project foundProject = projectRepository.findFistProjectByNameAndFinishingDate("ABCDEF", now);
//        Assert.assertNotNull(foundProject);
//        Assert.assertTrue(foundProject.getName().equals("ABCDEF") && foundProject.getEndDate().equals(now));
//    }
//
//    @Test
//    public void testComplexQuery() {
////        LocalDate now = LocalDate.now();
////        Project project = new Project("ABCDEF", now);
////        Group group = new Group();
////        group.setName("Group ABC");
////        Project project2 = new Project("ABCDEF", now);
////        Group saveGroup = groupRepository.saveAndFlush(group);
////        Assert.assertNotNull(saveGroup);
////        project2.setGroup(saveGroup);
////        project.setGroup(saveGroup);
////        Project p1 = projectRepository.save(project);
////        Project p2 = projectRepository.save(project2);
////
////        List<Project> projects = new JPAQuery<Project>(em)
////                                        .from(QProject.project)
////                                        .where(QProject.project.name.eq("ABCDEF")
////                                                .and(QProject.project.finishingDate.eq(now)))
////                                        .innerJoin(QProject.project.group)
////                                        .where(QProject.project.group.name.eq("Group ABC"))
////                                        .fetch();
////
////        Assert.assertTrue(projects.size() >= 2);
//    }
//
//    private Project save(String name) {
//        Project project = new Project();
//        project.setName("ABCDEF");
//        LocalDate now = LocalDate.now();
//        project.setEndDate(now);
//        return projectRepository.saveAndFlush(project);
//    }
//}
