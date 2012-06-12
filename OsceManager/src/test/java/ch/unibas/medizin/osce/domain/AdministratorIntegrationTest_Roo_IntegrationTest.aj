// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.AdministratorDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect AdministratorIntegrationTest_Roo_IntegrationTest {
    
    declare @type: AdministratorIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: AdministratorIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    declare @type: AdministratorIntegrationTest: @Transactional;
    
    @Autowired
    private AdministratorDataOnDemand AdministratorIntegrationTest.dod;
    
    @Test
    public void AdministratorIntegrationTest.testCountAdministrators() {
        org.junit.Assert.assertNotNull("Data on demand for 'Administrator' failed to initialize correctly", dod.getRandomAdministrator());
        long count = ch.unibas.medizin.osce.domain.Administrator.countAdministrators();
        org.junit.Assert.assertTrue("Counter for 'Administrator' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void AdministratorIntegrationTest.testFindAdministrator() {
        ch.unibas.medizin.osce.domain.Administrator obj = dod.getRandomAdministrator();
        org.junit.Assert.assertNotNull("Data on demand for 'Administrator' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Administrator' failed to provide an identifier", id);
        obj = ch.unibas.medizin.osce.domain.Administrator.findAdministrator(id);
        org.junit.Assert.assertNotNull("Find method for 'Administrator' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'Administrator' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void AdministratorIntegrationTest.testFindAllAdministrators() {
        org.junit.Assert.assertNotNull("Data on demand for 'Administrator' failed to initialize correctly", dod.getRandomAdministrator());
        long count = ch.unibas.medizin.osce.domain.Administrator.countAdministrators();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'Administrator', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<ch.unibas.medizin.osce.domain.Administrator> result = ch.unibas.medizin.osce.domain.Administrator.findAllAdministrators();
        org.junit.Assert.assertNotNull("Find all method for 'Administrator' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'Administrator' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void AdministratorIntegrationTest.testFindAdministratorEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'Administrator' failed to initialize correctly", dod.getRandomAdministrator());
        long count = ch.unibas.medizin.osce.domain.Administrator.countAdministrators();
        if (count > 20) count = 20;
        java.util.List<ch.unibas.medizin.osce.domain.Administrator> result = ch.unibas.medizin.osce.domain.Administrator.findAdministratorEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'Administrator' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'Administrator' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void AdministratorIntegrationTest.testFlush() {
        ch.unibas.medizin.osce.domain.Administrator obj = dod.getRandomAdministrator();
        org.junit.Assert.assertNotNull("Data on demand for 'Administrator' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Administrator' failed to provide an identifier", id);
        obj = ch.unibas.medizin.osce.domain.Administrator.findAdministrator(id);
        org.junit.Assert.assertNotNull("Find method for 'Administrator' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyAdministrator(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'Administrator' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void AdministratorIntegrationTest.testMerge() {
        ch.unibas.medizin.osce.domain.Administrator obj = dod.getRandomAdministrator();
        org.junit.Assert.assertNotNull("Data on demand for 'Administrator' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Administrator' failed to provide an identifier", id);
        obj = ch.unibas.medizin.osce.domain.Administrator.findAdministrator(id);
        boolean modified =  dod.modifyAdministrator(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        ch.unibas.medizin.osce.domain.Administrator merged = (ch.unibas.medizin.osce.domain.Administrator) obj.merge();
        obj.flush();
        org.junit.Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        org.junit.Assert.assertTrue("Version for 'Administrator' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void AdministratorIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'Administrator' failed to initialize correctly", dod.getRandomAdministrator());
        ch.unibas.medizin.osce.domain.Administrator obj = dod.getNewTransientAdministrator(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'Administrator' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'Administrator' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'Administrator' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void AdministratorIntegrationTest.testRemove() {
        ch.unibas.medizin.osce.domain.Administrator obj = dod.getRandomAdministrator();
        org.junit.Assert.assertNotNull("Data on demand for 'Administrator' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'Administrator' failed to provide an identifier", id);
        obj = ch.unibas.medizin.osce.domain.Administrator.findAdministrator(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'Administrator' with identifier '" + id + "'", ch.unibas.medizin.osce.domain.Administrator.findAdministrator(id));
    }
    
}