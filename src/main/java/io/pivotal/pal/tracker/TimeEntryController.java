package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {

        this.timeEntryRepository = timeEntryRepository;
    }

    //@RequestMapping(path = "/create")
    public ResponseEntity create(TimeEntry timeEntryToCreate) {
        return new ResponseEntity(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);

    }

    //@RequestMapping(path = "/read")
    public ResponseEntity<TimeEntry> read(long timeEntryId) {

        TimeEntry timeEntry= timeEntryRepository.find(timeEntryId);

        if (timeEntry == null) {
            return new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(timeEntry, HttpStatus.OK);
        }
    }

    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity(timeEntryRepository.list(), HttpStatus.OK);
    }

    public ResponseEntity update(long timeEntryId, TimeEntry expected) {

        TimeEntry timeEntry= timeEntryRepository.update(timeEntryId,expected);

        if (timeEntry == null) {
            return new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(timeEntry, HttpStatus.OK);
        }
    }

    public ResponseEntity<TimeEntry> delete(long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);

        return new ResponseEntity(timeEntryRepository.find(timeEntryId), HttpStatus.NO_CONTENT);
    }
}
