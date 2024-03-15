package com.hwagae.market.restrictedUser;

import com.hwagae.market.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
@RequiredArgsConstructor
public class ResUserService {
    private final ResUserRepository resUserRepository;

    public void save(ResUserDTO resUserDTO, UserEntity userEntity) throws IOException {
        ResUserEntity resUserEntity = ResUserEntity.toSaveEntity(resUserDTO, userEntity);
        resUserRepository.save(resUserEntity);
    }

}
