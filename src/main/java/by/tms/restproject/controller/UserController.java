package by.tms.restproject.controller;

import by.tms.restproject.dao.InMemoryUserDao;
import by.tms.restproject.entity.User;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jdk.jfr.Name;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.UsesSunHttpServer;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
@Tag(name = "User")
//@OpenAPIDefinition()
public class UserController {
    @Autowired
    private InMemoryUserDao inMemoryUserDao;

    @PostMapping
    @Operation(summary = "Save User")
    public ResponseEntity<User> save(@Valid @RequestBody User user) {
        User save = inMemoryUserDao.save(user);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Find All Users")
    public ResponseEntity<List<User>> findAll() {
        List<User> all = inMemoryUserDao.findAll();
        if (all.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(all);
    }

    @GetMapping("/id")
    @Operation(summary = "Find User By ID")
    public ResponseEntity<User> findById(long id) {
        Optional<User> byId = inMemoryUserDao.findById(id);
        if (byId.isPresent()) {
            return ResponseEntity.ok(byId.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/username")
    @Operation(summary = "Find User Bu Username")
    public ResponseEntity<User> findByUsername(String username) {
        Optional<User> byUsername = inMemoryUserDao.getByUsername(username);
        if (byUsername.isPresent()) {
            return ResponseEntity.ok(byUsername.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping
    @Operation(summary = "Update User")
    public ResponseEntity<User> update(@Valid @RequestBody User user) {
        Optional<User> update = inMemoryUserDao.update(user);
        if (update.isPresent()) {
            return ResponseEntity.ok(update.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping
    @Operation(summary = "Delete User")
    public ResponseEntity<?> delete(@Valid @RequestBody User user) {
        if (inMemoryUserDao.delete(user)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


    @DeleteMapping("/id")
    @Operation(summary="Delete User By ID")
    public ResponseEntity<User> deleteById(long id) {
        Optional<User> user = inMemoryUserDao.deleteById(id);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        }
        return ResponseEntity.badRequest().build();
    }
}
