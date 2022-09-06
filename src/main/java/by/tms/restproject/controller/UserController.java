package by.tms.restproject.controller;

import by.tms.restproject.dao.InMemoryUserDao;
import by.tms.restproject.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private InMemoryUserDao inMemoryUserDao;

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody User user) {
        User save = inMemoryUserDao.save(user);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> all = inMemoryUserDao.findAll();
        if (all.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(all);
    }

    @GetMapping("/id")
    public ResponseEntity<User> findById(long id) {
        Optional<User> byId = inMemoryUserDao.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/username")
    public ResponseEntity<User> findByUsername(String username) {
        Optional<User> byUsername = inMemoryUserDao.getByUsername(username);
        if (byUsername.isPresent()) {
            return ResponseEntity.ok(byUsername.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        Optional<User> update = inMemoryUserDao.update(user);
        if (update.isPresent()) {
            return ResponseEntity.ok(update.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestBody User user) {
        if (inMemoryUserDao.delete(user)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


    @DeleteMapping("/id")
    public ResponseEntity<User> deleteById(long id) {
        Optional<User> user = inMemoryUserDao.deleteById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.badRequest().build();
    }
}
