package com.example.collaborativewhiteboard.repository;

import com.example.collaborativewhiteboard.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {
    //MongoDB 특정상 ID가 UUID로 받아와짐...그렇기에 String(?) -> 확인이 필요함
}
