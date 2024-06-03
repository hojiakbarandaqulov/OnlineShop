package org.example.controller;

import org.example.dto.*;
import org.example.dto.filter.ProfileFilterDTO;
import org.example.dto.update.ProfileUpdateDTO;
import org.example.service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api")
@RestController
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    @PostMapping("/adm/create")
    public ResponseEntity<ProfileDTO> create(@Valid @RequestBody ProfileCreateDTO profileDTO) {
        ProfileDTO response = profileService.create(profileDTO);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/adm/current/{id}")
    public ResponseEntity<Boolean> updateUser(@PathVariable("id") Integer id, @RequestBody ProfileUpdateDTO profile) {
        profileService.updateUser(id, profile);
        return ResponseEntity.ok().body(true);
    }

    @PutMapping("/adm/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id,
                                          @Valid @RequestBody ProfileDTO profile) {
        profileService.update(id, profile);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/adm/profilePagination")
    public ResponseEntity<PageImpl<ProfileDTO>> getAll(@RequestParam(value = "page", defaultValue = "1") int page,
                                                       @RequestParam(value = "size", defaultValue = "10") int size) {
        PageImpl<ProfileDTO> typeList = profileService.getAllPagination(page - 1, size);
        return ResponseEntity.ok().body(typeList);
    }

    @DeleteMapping("/adm/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id,
                                          @RequestHeader("Authorization") String token) {
        profileService.deleteId(id);
        return ResponseEntity.ok().body(true);
    }
    @PostMapping("/filter")
    public ResponseEntity<PageImpl<ProfileDTO>> pageableFilter(@RequestParam(value = "page", defaultValue = "1") int page,
                                                               @RequestParam(value = "size", defaultValue = "10") int size,
                                                               @RequestBody ProfileFilterDTO filter) {
        PageImpl<ProfileDTO> studentDTOList = profileService.filter(filter, page - 1, size);
        return ResponseEntity.ok().body(studentDTOList);
    }
}
