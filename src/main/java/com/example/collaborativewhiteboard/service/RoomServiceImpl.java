package com.example.collaborativewhiteboard.service;

import com.example.collaborativewhiteboard.model.Room;
import com.example.collaborativewhiteboard.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public Room save(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public Room update(Room room) {
        return roomRepository.save(room);
    }

    @Override
    public void deleteRoom(String roomId) {
        roomRepository.deleteById(roomId);
    }

    @Override
    public Optional<Room> getRoom(String roomId) {
        return roomRepository.findById(roomId);
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }
}
