package com.learningplatform.web.service;

import com.learningplatform.web.DTO.LectureDTO;
import com.learningplatform.web.DTO.RegistrationDTO;
import com.learningplatform.web.models.UserEntity;

public interface UserService {
    void saveUser(RegistrationDTO registrationDTO);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);

}
