// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.StandardizedRoleDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect StandardizedRoleIntegrationTest_Roo_IntegrationTest {
    
    declare @type: StandardizedRoleIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: StandardizedRoleIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    declare @type: StandardizedRoleIntegrationTest: @Transactional;
    
    @Autowired
    private StandardizedRoleDataOnDemand StandardizedRoleIntegrationTest.dod;
    
    @Test
    public void StandardizedRoleIntegrationTest.testCountStandardizedRoles() {
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedRole' failed to initialize correctly", dod.getRandomStandardizedRole());
        long count = ch.unibas.medizin.osce.domain.StandardizedRole.countStandardizedRoles();
        org.junit.Assert.assertTrue("Counter for 'StandardizedRole' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void StandardizedRoleIntegrationTest.testFindStandardizedRole() {
        ch.unibas.medizin.osce.domain.StandardizedRole obj = dod.getRandomStandardizedRole();
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedRole' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedRole' failed to provide an identifier", id);
        obj = ch.unibas.medizin.osce.domain.StandardizedRole.findStandardizedRole(id);
        org.junit.Assert.assertNotNull("Find method for 'StandardizedRole' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'StandardizedRole' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void StandardizedRoleIntegrationTest.testFindAllStandardizedRoles() {
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedRole' failed to initialize correctly", dod.getRandomStandardizedRole());
        long count = ch.unibas.medizin.osce.domain.StandardizedRole.countStandardizedRoles();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'StandardizedRole', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<ch.unibas.medizin.osce.domain.StandardizedRole> result = ch.unibas.medizin.osce.domain.StandardizedRole.findAllStandardizedRoles();
        org.junit.Assert.assertNotNull("Find all method for 'StandardizedRole' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'StandardizedRole' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void StandardizedRoleIntegrationTest.testFindStandardizedRoleEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedRole' failed to initialize correctly", dod.getRandomStandardizedRole());
        long count = ch.unibas.medizin.osce.domain.StandardizedRole.countStandardizedRoles();
        if (count > 20) count = 20;
        java.util.List<ch.unibas.medizin.osce.domain.StandardizedRole> result = ch.unibas.medizin.osce.domain.StandardizedRole.findStandardizedRoleEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'StandardizedRole' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'StandardizedRole' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void StandardizedRoleIntegrationTest.testFlush() {
        ch.unibas.medizin.osce.domain.StandardizedRole obj = dod.getRandomStandardizedRole();
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedRole' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedRole' failed to provide an identifier", id);
        obj = ch.unibas.medizin.osce.domain.StandardizedRole.findStandardizedRole(id);
        org.junit.Assert.assertNotNull("Find method for 'StandardizedRole' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyStandardizedRole(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'StandardizedRole' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void StandardizedRoleIntegrationTest.testMerge() {
        ch.unibas.medizin.osce.domain.StandardizedRole obj = dod.getRandomStandardizedRole();
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedRole' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedRole' failed to provide an identifier", id);
        obj = ch.unibas.medizin.osce.domain.StandardizedRole.findStandardizedRole(id);
        boolean modified =  dod.modifyStandardizedRole(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        ch.unibas.medizin.osce.domain.StandardizedRole merged = (ch.unibas.medizin.osce.domain.StandardizedRole) obj.merge();
        obj.flush();
        org.junit.Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        org.junit.Assert.assertTrue("Version for 'StandardizedRole' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void StandardizedRoleIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedRole' failed to initialize correctly", dod.getRandomStandardizedRole());
        ch.unibas.medizin.osce.domain.StandardizedRole obj = dod.getNewTransientStandardizedRole(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedRole' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'StandardizedRole' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'StandardizedRole' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void StandardizedRoleIntegrationTest.testRemove() {
        ch.unibas.medizin.osce.domain.StandardizedRole obj = dod.getRandomStandardizedRole();
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedRole' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedRole' failed to provide an identifier", id);
        obj = ch.unibas.medizin.osce.domain.StandardizedRole.findStandardizedRole(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'StandardizedRole' with identifier '" + id + "'", ch.unibas.medizin.osce.domain.StandardizedRole.findStandardizedRole(id));
    }
    
}
