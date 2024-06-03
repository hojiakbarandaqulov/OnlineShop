package org.example.config;

import org.example.dto.history.EmailDTO;
import org.example.entity.ProfileEntity;
import org.example.enums.ProfileRole;
import org.example.enums.ProfileStatus;
import org.example.exp.AppBadException;
import org.example.repository.ProfileRepository;
import org.example.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    private ProfileRepository profileRepository;

    private static final Logger LOG =
            LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

    public static int counter;

    @Override
    public void run(String...args) throws Exception {
        ProfileEntity profileEntity = new ProfileEntity();
        profileEntity.setEmail("hojiakbarandaqulov5@gmail.com");
        profileEntity.setPassword(MD5Util.getMD5("1234"));
        profileEntity.setName("Ali");
        profileEntity.setSurname("Aliyev");
        profileEntity.setRole(ProfileRole.ROLE_ADMIN);
        profileEntity.setStatus(ProfileStatus.ACTIVE);
        profileEntity.setVisible(true);
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndVisibleTrue(profileEntity.getEmail());
        if (optional.isEmpty()) {
            profileRepository.save(profileEntity);
        }
    }
}

