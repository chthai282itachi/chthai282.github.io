// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package ch.unibas.medizin.osce.domain;

import ch.unibas.medizin.osce.domain.LogEntry;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Component;

privileged aspect LogEntryDataOnDemand_Roo_DataOnDemand {
    
    declare @type: LogEntryDataOnDemand: @Component;
    
    private Random LogEntryDataOnDemand.rnd = new java.security.SecureRandom();
    
    private List<LogEntry> LogEntryDataOnDemand.data;
    
    public LogEntry LogEntryDataOnDemand.getNewTransientLogEntry(int index) {
        ch.unibas.medizin.osce.domain.LogEntry obj = new ch.unibas.medizin.osce.domain.LogEntry();
        setShibId(obj, index);
        setLogtime(obj, index);
        setOldValue(obj, index);
        setNewValue(obj, index);
        return obj;
    }
    
    private void LogEntryDataOnDemand.setShibId(LogEntry obj, int index) {
        java.lang.Integer shibId = new Integer(index);
        obj.setShibId(shibId);
    }
    
    private void LogEntryDataOnDemand.setLogtime(LogEntry obj, int index) {
        java.util.Date logtime = new java.util.GregorianCalendar(java.util.Calendar.getInstance().get(java.util.Calendar.YEAR), java.util.Calendar.getInstance().get(java.util.Calendar.MONTH), java.util.Calendar.getInstance().get(java.util.Calendar.DAY_OF_MONTH), java.util.Calendar.getInstance().get(java.util.Calendar.HOUR_OF_DAY), java.util.Calendar.getInstance().get(java.util.Calendar.MINUTE), java.util.Calendar.getInstance().get(java.util.Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setLogtime(logtime);
    }
    
    private void LogEntryDataOnDemand.setOldValue(LogEntry obj, int index) {
        java.lang.String oldValue = "oldValue_" + index;
        if (oldValue.length() > 255) {
            oldValue = oldValue.substring(0, 255);
        }
        obj.setOldValue(oldValue);
    }
    
    private void LogEntryDataOnDemand.setNewValue(LogEntry obj, int index) {
        java.lang.String newValue = "newValue_" + index;
        if (newValue.length() > 255) {
            newValue = newValue.substring(0, 255);
        }
        obj.setNewValue(newValue);
    }
    
    public LogEntry LogEntryDataOnDemand.getSpecificLogEntry(int index) {
        init();
        if (index < 0) index = 0;
        if (index > (data.size() - 1)) index = data.size() - 1;
        LogEntry obj = data.get(index);
        return LogEntry.findLogEntry(obj.getId());
    }
    
    public LogEntry LogEntryDataOnDemand.getRandomLogEntry() {
        init();
        LogEntry obj = data.get(rnd.nextInt(data.size()));
        return LogEntry.findLogEntry(obj.getId());
    }
    
    public boolean LogEntryDataOnDemand.modifyLogEntry(LogEntry obj) {
        return false;
    }
    
    public void LogEntryDataOnDemand.init() {
        data = ch.unibas.medizin.osce.domain.LogEntry.findLogEntryEntries(0, 10);
        if (data == null) throw new IllegalStateException("Find entries implementation for 'LogEntry' illegally returned null");
        if (!data.isEmpty()) {
            return;
        }
        
        data = new java.util.ArrayList<ch.unibas.medizin.osce.domain.LogEntry>();
        for (int i = 0; i < 10; i++) {
            ch.unibas.medizin.osce.domain.LogEntry obj = getNewTransientLogEntry(i);
            obj.persist();
            obj.flush();
            data.add(obj);
        }
    }
    
}