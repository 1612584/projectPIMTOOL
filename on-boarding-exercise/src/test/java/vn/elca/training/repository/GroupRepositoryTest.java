//package vn.elca.training.repository;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//import vn.elca.training.ApplicationWebConfig;
//import vn.elca.training.model.entity.Group;
//
//import java.util.Optional;
//
//@ContextConfiguration(classes = {ApplicationWebConfig.class})
//@RunWith(value= SpringRunner.class)
//public class GroupRepositoryTest {
////    @PersistenceContext
////    private EntityManager em;
////    @Autowired
////    private GroupRepository groupRepository;
////
////    @Test
////    public void testFindByIdAndSave(){
////        // prepare data
////        Group group = new Group();
////        group.setName("ABC");
////        Group saveGroup = groupRepository.saveAndFlush(group);
////        Optional<Group> groupById = groupRepository.findById(saveGroup.getId());
////        Assert.assertEquals("ABC",groupById.orElse(new Group()).getName());
////    }
////
////    @Test
////    public void testUpdateUsingSave(){
////        Group group = new Group();
////        group.setName("CDE");
////        Group saveGroup = groupRepository.saveAndFlush(group);
////        Optional<Group> groupById = groupRepository.findById(saveGroup.getId());
////        groupById.ifPresent(existGroup -> {
////            Assert.assertEquals("CDE",existGroup.getName());
////            existGroup.setName("DEF");
////            existGroup = groupRepository.saveAndFlush(existGroup);
////            Assert.assertEquals("DEF",existGroup.getName());
////        });
////        Assert.assertTrue(groupById.isPresent());
////    }
////
////    @Test
////    public void testFindFirstByName() {
////        Group group = updateOrCreateGroup("ABC");
////        Optional<Group> findByNameGroup = groupRepository.findFirstByName("ABC");
////        Assert.assertTrue(findByNameGroup.isPresent() && findByNameGroup.get().getName().equals("ABC"));
////    }
////
////    @Test
////    public void testRollbackParentTransactionAffectInnerTransaction() {
////        try {
////            testParentTransaction();
////        } finally {
////            Optional<Group> innerGroup = groupRepository.findFirstByName("DEF");
////            Assert.assertTrue(innerGroup.isPresent());
////            System.out.println(innerGroup.get().getName());
////        }
////    }
////
////    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
////    public void testParentTransaction() {
////        updateOrCreateGroup("ABC");
////        Group group = testNestedTransaction();
////        Assert.assertEquals("DEF",group.getName());
////        try
////        {
////        throw new Exception();
////        } catch (Exception ex){
////
////        }
////    }
////
////    @Transactional(propagation = Propagation.NESTED)
////    public Group testNestedTransaction() {
////        return updateOrCreateGroup("DEF");
////    }
////    @Transactional(propagation = Propagation.MANDATORY)
////    public Group updateOrCreateGroup(String name) {
////        Group group = new Group();
////        group.setName(name);
////        return groupRepository.save(group);
////    }
//}
