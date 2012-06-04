// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.StandardizedPatientDataOnDemand;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

privileged aspect StandardizedPatientIntegrationTest_Roo_IntegrationTest {
    
    declare @type: StandardizedPatientIntegrationTest: @RunWith(SpringJUnit4ClassRunner.class);
    
    declare @type: StandardizedPatientIntegrationTest: @ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext.xml");
    
    declare @type: StandardizedPatientIntegrationTest: @Transactional;
    
    @Autowired
    private StandardizedPatientDataOnDemand StandardizedPatientIntegrationTest.dod;
    
    @Test
    public void StandardizedPatientIntegrationTest.testCountStandardizedPatients() {
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedPatient' failed to initialize correctly", dod.getRandomStandardizedPatient());
        long count = ch.unibas.medizin.osce.domain.StandardizedPatient.countStandardizedPatients();
        org.junit.Assert.assertTrue("Counter for 'StandardizedPatient' incorrectly reported there were no entries", count > 0);
    }
    
    @Test
    public void StandardizedPatientIntegrationTest.testFindStandardizedPatient() {
        ch.unibas.medizin.osce.domain.StandardizedPatient obj = dod.getRandomStandardizedPatient();
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedPatient' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedPatient' failed to provide an identifier", id);
        obj = ch.unibas.medizin.osce.domain.StandardizedPatient.findStandardizedPatient(id);
        org.junit.Assert.assertNotNull("Find method for 'StandardizedPatient' illegally returned null for id '" + id + "'", obj);
        org.junit.Assert.assertEquals("Find method for 'StandardizedPatient' returned the incorrect identifier", id, obj.getId());
    }
    
    @Test
    public void StandardizedPatientIntegrationTest.testFindAllStandardizedPatients() {
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedPatient' failed to initialize correctly", dod.getRandomStandardizedPatient());
        long count = ch.unibas.medizin.osce.domain.StandardizedPatient.countStandardizedPatients();
        org.junit.Assert.assertTrue("Too expensive to perform a find all test for 'StandardizedPatient', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        java.util.List<ch.unibas.medizin.osce.domain.StandardizedPatient> result = ch.unibas.medizin.osce.domain.StandardizedPatient.findAllStandardizedPatients();
        org.junit.Assert.assertNotNull("Find all method for 'StandardizedPatient' illegally returned null", result);
        org.junit.Assert.assertTrue("Find all method for 'StandardizedPatient' failed to return any data", result.size() > 0);
    }
    
    @Test
    public void StandardizedPatientIntegrationTest.testFindStandardizedPatientEntries() {
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedPatient' failed to initialize correctly", dod.getRandomStandardizedPatient());
        long count = ch.unibas.medizin.osce.domain.StandardizedPatient.countStandardizedPatients();
        if (count > 20) count = 20;
        java.util.List<ch.unibas.medizin.osce.domain.StandardizedPatient> result = ch.unibas.medizin.osce.domain.StandardizedPatient.findStandardizedPatientEntries(0, (int) count);
        org.junit.Assert.assertNotNull("Find entries method for 'StandardizedPatient' illegally returned null", result);
        org.junit.Assert.assertEquals("Find entries method for 'StandardizedPatient' returned an incorrect number of entries", count, result.size());
    }
    
    @Test
    public void StandardizedPatientIntegrationTest.testFlush() {
        ch.unibas.medizin.osce.domain.StandardizedPatient obj = dod.getRandomStandardizedPatient();
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedPatient' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedPatient' failed to provide an identifier", id);
        obj = ch.unibas.medizin.osce.domain.StandardizedPatient.findStandardizedPatient(id);
        org.junit.Assert.assertNotNull("Find method for 'StandardizedPatient' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyStandardizedPatient(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        obj.flush();
        org.junit.Assert.assertTrue("Version for 'StandardizedPatient' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void StandardizedPatientIntegrationTest.testMerge() {
        ch.unibas.medizin.osce.domain.StandardizedPatient obj = dod.getRandomStandardizedPatient();
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedPatient' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedPatient' failed to provide an identifier", id);
        obj = ch.unibas.medizin.osce.domain.StandardizedPatient.findStandardizedPatient(id);
        boolean modified =  dod.modifyStandardizedPatient(obj);
        java.lang.Integer currentVersion = obj.getVersion();
        ch.unibas.medizin.osce.domain.StandardizedPatient merged = (ch.unibas.medizin.osce.domain.StandardizedPatient) obj.merge();
        obj.flush();
        org.junit.Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        org.junit.Assert.assertTrue("Version for 'StandardizedPatient' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }
    
    @Test
    public void StandardizedPatientIntegrationTest.testPersist() {
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedPatient' failed to initialize correctly", dod.getRandomStandardizedPatient());
        ch.unibas.medizin.osce.domain.StandardizedPatient obj = dod.getNewTransientStandardizedPatient(Integer.MAX_VALUE);
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedPatient' failed to provide a new transient entity", obj);
        org.junit.Assert.assertNull("Expected 'StandardizedPatient' identifier to be null", obj.getId());
        obj.persist();
        obj.flush();
        org.junit.Assert.assertNotNull("Expected 'StandardizedPatient' identifier to no longer be null", obj.getId());
    }
    
    @Test
    public void StandardizedPatientIntegrationTest.testRemove() {
        ch.unibas.medizin.osce.domain.StandardizedPatient obj = dod.getRandomStandardizedPatient();
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedPatient' failed to initialize correctly", obj);
        java.lang.Long id = obj.getId();
        org.junit.Assert.assertNotNull("Data on demand for 'StandardizedPatient' failed to provide an identifier", id);
        obj = ch.unibas.medizin.osce.domain.StandardizedPatient.findStandardizedPatient(id);
        obj.remove();
        obj.flush();
        org.junit.Assert.assertNull("Failed to remove 'StandardizedPatient' with identifier '" + id + "'", ch.unibas.medizin.osce.domain.StandardizedPatient.findStandardizedPatient(id));
    }
    
}
