package org.example.service;

import org.example.dto.*;
import org.example.dto.filter.ProductFilterDTO;
import org.example.dto.filter.ProfileFilterDTO;
import org.example.dto.response.FilterResponseDTO;
import org.example.dto.update.ProfileUpdateDTO;
import org.example.entity.ProductEntity;
import org.example.entity.ProfileEntity;
import org.example.exp.AppBadException;
import org.example.repository.ProfileRepository;
import org.example.repository.customRepository.ProfileCustomRepository;
import org.example.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileCustomRepository profileCustomRepository;

    public ProfileDTO create(ProfileCreateDTO profileDTO) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(profileDTO.getName());
        entity.setSurname(profileDTO.getSurname());
        entity.setEmail(profileDTO.getEmail());
        entity.setPassword(MD5Util.getMD5(profileDTO.getPassword()));
        entity.setStatus(profileDTO.getStatus());
        entity.setRole(profileDTO.getRole());
        profileRepository.save(entity);
        return profileToDTO(entity);
    }

    public Boolean update(Integer id,ProfileDTO profile) {
        ProfileEntity profileEntity = get(id);
        profileEntity.setName(profile.getName());
        profileEntity.setSurname(profile.getSurname());
        profileEntity.setEmail(profile.getEmail());
        profileEntity.setPassword(profile.getPassword());
        profileEntity.setStatus(profile.getStatus());
        profileEntity.setRole(profile.getRole());
        profileRepository.save(profileEntity);
        return true;
    }


    public Boolean updateUser(Integer id, ProfileUpdateDTO profileUser) {
        ProfileEntity profileEntity = get(id);
        profileEntity.setName(profileUser.getName());
        profileEntity.setSurname(profileUser.getSurname());
        profileRepository.save(profileEntity);
        return true;
    }

    public ProfileDTO profileToDTO(ProfileEntity entity) {
        ProfileDTO profileDTO = new ProfileDTO();
        profileDTO.setId(entity.getId());
        profileDTO.setName(entity.getName());
        profileDTO.setSurname(entity.getSurname());
        profileDTO.setEmail(entity.getEmail());
        profileDTO.setPassword(entity.getPassword());
        profileDTO.setStatus(entity.getStatus());
        profileDTO.setRole(entity.getRole());
        return profileDTO;
    }

    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id).orElseThrow(() -> {
            throw new AppBadException("Profile not found");
        });
    }


    public PageImpl<ProfileDTO> getAllPagination(int page, int size) {
        Sort sort = Sort.by(Sort.Order.desc("createdDate"));
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<ProfileEntity> pageObj = profileRepository.findAll(pageable);

        List<ProfileDTO> dtoList = new LinkedList<>();
        for (ProfileEntity entity : pageObj.getContent()) {
            dtoList.add(profileToDTO(entity));
        }

        Long totalCount = pageObj.getTotalElements();
        return new PageImpl<ProfileDTO>(dtoList, pageable, totalCount);
    }

    public Boolean deleteId(Integer id) {
        profileRepository.deleteById(id);
        return true;
    }
    public PageImpl<ProfileDTO> filter(ProfileFilterDTO filter, int page, int size) {
        FilterResponseDTO<ProfileEntity> filterResponse = profileCustomRepository.filter(filter, page, size);

        List<ProfileDTO> dtoList = new LinkedList<>();
        for (ProfileEntity entity : filterResponse.getContent()) {
            ProfileDTO dto = new ProfileDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setEmail(entity.getEmail());
            dto.setRole(entity.getRole());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        }
        return new PageImpl<ProfileDTO>( dtoList, PageRequest.of(page,size), filterResponse.getTotalCount());
    }
}

