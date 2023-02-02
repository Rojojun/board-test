package com.example.collaborativewhiteboard.controller;

import com.example.collaborativewhiteboard.model.Drawing;
import com.example.collaborativewhiteboard.model.Room;
import com.example.collaborativewhiteboard.service.RoomService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/rooms")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;
    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping
    public ResponseEntity<Room> createRoom(@RequestBody Room room) {
        roomService.save(room);
        return ResponseEntity.ok(room);
    }

    @PostMapping("/{roomId}/save-drawing")
    public ResponseEntity<String> saveDrawing(@PathVariable String roomId, @RequestBody Drawing drawing) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(roomId));
        Update updateQuery = new Update();
        updateQuery.push("drawings", drawing);
        mongoTemplate.updateFirst(query, updateQuery, Room.class);

        return ResponseEntity.ok("Drawing 저장 완료");
    }

    @GetMapping("/{roomId}/get-drawings")
    public ResponseEntity<List<Drawing>> getDrawings(@PathVariable String roomId) {
        List<Drawing> drawings = mongoTemplate.findById(roomId, Room.class).getDrawings();
        return ResponseEntity.ok(drawings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Room> getRoom(@PathVariable String id) {
        Optional<Room> room = roomService.getRoom(id);
        if (room.isPresent()) {
            return new ResponseEntity<>(room.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public List<Room> getAllRooms() {
        return roomService.getAllRooms();
    }
}
