package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/time-entries")
@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {

        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody TimeEntry timeEntryToCreate) {
        return new ResponseEntity(timeEntryRepository.create(timeEntryToCreate), HttpStatus.CREATED);

    }

    @GetMapping ("/{id}")
    public ResponseEntity<TimeEntry> read(@PathVariable("id") Long timeEntryId) {

        TimeEntry timeEntry= timeEntryRepository.find(timeEntryId);

        if (timeEntry == null) {
            return new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(timeEntry, HttpStatus.OK);
        }
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return new ResponseEntity(timeEntryRepository.list(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Long timeEntryId, @RequestBody TimeEntry expected) {

        TimeEntry timeEntry= timeEntryRepository.update(timeEntryId,expected);

        if (timeEntry == null) {
            return new ResponseEntity(timeEntry, HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity(timeEntry, HttpStatus.OK);
        }
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<TimeEntry> delete(@PathVariable("id") Long timeEntryId) {
        timeEntryRepository.delete(timeEntryId);

        return new ResponseEntity(timeEntryRepository.find(timeEntryId), HttpStatus.NO_CONTENT);
    }
}
