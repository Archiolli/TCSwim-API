package com.example.TCSwim.controller;

import com.example.TCSwim.dto.AthleteDto;
import com.example.TCSwim.dto.ClubDto;
import com.example.TCSwim.model.Athlete;
import com.example.TCSwim.model.Club;
import com.example.TCSwim.repository.ClubRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController()
public class ClubController {

    @Autowired
    ClubRepository clubRepository;
    @PostMapping("/club")
    public ResponseEntity<Club> saveClub(@RequestBody @Valid ClubDto clubDto){
        var clubModel = new Club();
        BeanUtils.copyProperties(clubDto, clubModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(clubRepository.save(clubModel));
    }


    @GetMapping("/club")
    public ResponseEntity<List<Club>> getAllClubs() {
        return ResponseEntity.status(HttpStatus.OK).body((clubRepository.findAll()));
    }

    @GetMapping("/club/{id}")
    public ResponseEntity<Object> getClubsById(@PathVariable(value = "id") Long id) {
        Optional<Club> club = clubRepository.findById(id);
        return club.<ResponseEntity<Object>>map(clubs -> ResponseEntity.status(HttpStatus.OK)
                .body(clubs)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Clube não encontrado!"));
    }

    @PutMapping("/club/{id}")
    public ResponseEntity<Object> updateAthlete(@PathVariable(value = "id") Long id,
                                                @RequestBody @Valid ClubDto clubDto){
        var club = clubRepository.findById(id);
        if(club.isEmpty()){
            var map = new HashMap<String, String>();
            map.put("message", "Clube não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
        }
        var clubModel = club.get();
        BeanUtils.copyProperties(clubDto, clubModel);
        return ResponseEntity.status(HttpStatus.OK).body(clubRepository.save(clubModel));
    }

}
