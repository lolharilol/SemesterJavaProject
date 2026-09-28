package JavaProject.Semester.Controller;

import JavaProject.Semester.Models.Entry;
import JavaProject.Semester.Services.EntryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entries")
@CrossOrigin(origins = "*")
public class EntryController {

    public final EntryService entryService;

    @Autowired
    public EntryController(EntryService entryService) {
        this.entryService = entryService;
    }

    @PostMapping
    public ResponseEntity<Entry> createEntry(@Valid @RequestBody Entry entry) {
        Entry created = entryService.createEntry(entry);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Entry>> getAllEntries() {
        List<Entry> entries = entryService.getAllEntries();
        return ResponseEntity.ok(entries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entry> getEntryById(@PathVariable Long id) {
        Entry entry = entryService.getEntryById(id);
        return ResponseEntity.ok(entry);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entry> updateEntry(@PathVariable Long id, @Valid @RequestBody Entry entry) {
        Entry updated = entryService.updateEntry(id, entry);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntry(@PathVariable Long id) {
        entryService.deleteEntry(id);
        return ResponseEntity.noContent().build();
    }
}
