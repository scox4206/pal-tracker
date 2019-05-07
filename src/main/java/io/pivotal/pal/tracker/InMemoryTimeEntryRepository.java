package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository{

    private Map<Long,TimeEntry> timeEntryMap = new HashMap<>();
    private long counter = 1;

    public TimeEntry create(TimeEntry timeEntry) {
        long timeEntryId = generateId();

        TimeEntry timeEntryToSave = new TimeEntry(timeEntryId, timeEntry.getProjectId(), timeEntry.getUserId(),
                timeEntry.getDate(), timeEntry.getHours());
        timeEntryMap.put(timeEntryId,timeEntryToSave);
        return timeEntryToSave;
    }

    private long generateId() {
        return counter++;
    }

    public TimeEntry find(long id) {
        
        return timeEntryMap.get(id);
    }

    public List<TimeEntry> list() {

       return new ArrayList<TimeEntry>(timeEntryMap.values());
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {



        if(timeEntryMap.get(id) == null) {
            return null;
        } else {

            TimeEntry timeEntryToSave = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(),
                    timeEntry.getDate(), timeEntry.getHours());
            timeEntryMap.put(id, timeEntryToSave);
            return timeEntryToSave;
        }


    }

    public void delete(long id) {
        this.timeEntryMap.remove(id);
    }
}
