package sky.pro.course3.homework.service;

import sky.pro.course3.homework.model.Faculty;
import sky.pro.course3.homework.repository.FacultyRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Stream;

@Service
public class FacultyService {
    private final FacultyRepository facultyRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(FacultyService.class);

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty add(Faculty faculty) {
        LOGGER.info("Was invoked method for create faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty get(long id) {
        LOGGER.info("Was invoked method for get faculty with id = {}", id);
        return facultyRepository.findById(id).get();
    }

    public Faculty set(Faculty faculty) {
        LOGGER.info("Was invoked method for set faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty del(long id) {
        LOGGER.info("Was invoked method for delete faculty with id {}", id);
        Faculty faculty = facultyRepository.findById(id).get();
        facultyRepository.deleteById(id);
        return faculty;
    }

    public Collection<Faculty> colorCollect(String color) {
        LOGGER.info("Was invoked method for colorCollect faculty");
        return facultyRepository.findAllByColor(color);
    }

    public Collection<Faculty> colorOrNameCollect(String color, String name) {
        LOGGER.info("Was invoked method for colorOrNameCollect faculty");
        return facultyRepository.findAllByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

    public String longestFaculty() {
        return facultyRepository.findAll().stream()
                .map(Faculty::getName)
                .max(Comparator.comparing(String::length))
                .get();
    }

    public Integer intSum() {
        return Stream.iterate(1, a -> a + 1)
                .parallel()
                .limit(1000000)
                .reduce(0, (a, b) -> a + b);
    }
}