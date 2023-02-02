package com.example.collaborativewhiteboard.controller;

import com.example.collaborativewhiteboard.model.ActionResponseDto;
import com.example.collaborativewhiteboard.model.Coordinates;
import com.example.collaborativewhiteboard.model.Room;
import com.example.collaborativewhiteboard.model.RoomActionsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class WebSocketTestController {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    SimpMessagingTemplate template;

    @MessageMapping("/send/{roomId}")
    @SendTo("/topic/{roomId}")
    public Coordinates sendMessage(@Payload Coordinates coordinates) {
        return coordinates;
    }

    @MessageMapping("/send/{roomId}/user")
    @SendTo("/topic/{roomId}/user")
    public ActionResponseDto joinUser(@Payload RoomActionsDTO roomActionsDTO) {
        String username = roomActionsDTO.getUsername();
        String action = roomActionsDTO.getPayload();
        String roomId = roomActionsDTO.getRoomId();

        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(roomId));
        Update updateQuery = new Update();

        if (action.equals("CONNECT_USER")){
            updateQuery.push("participants", username);
            mongoTemplate.updateFirst(query, updateQuery, Room.class);


            /*String message = String.format("Username : %s 이 성공적으로 접속을 하였습니다.", username);*/
            String message = "atomic";
            Room room = mongoTemplate.findById(roomId, Room.class);

            return new ActionResponseDto(message, room.getParticipants());
        } else if (action.equals("DISCONNECT_USER")) {
            updateQuery.push("participants", username);
            mongoTemplate.updateFirst(query, updateQuery, Room.class);

            if(mongoTemplate.findById(roomId, Room.class).getParticipants().size() == 0)
                mongoTemplate.remove(query, Room.class);

            //String message = String.format("Username : %d 이 연결을 종료하였습니다.", username);
            String message = "close_atomic";
            return new ActionResponseDto(message, mongoTemplate.findById(roomId, Room.class).getParticipants());
        } else {
            return new ActionResponseDto("지원하지 않는 요청입니다.", new ArrayList<String>());
        }
    }
}
