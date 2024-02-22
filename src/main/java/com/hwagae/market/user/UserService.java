package com.hwagae.market.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void save(UserDTO userDTO) {
        // 1. dto -> entity 변환
        // 2. repository의 save 메소드 호출

        UserEntity userEntity = UserEntity.toUserEntity(userDTO);
        userRepository.save(userEntity);
        // repository의 save 메소드 호출 (조건 = entity객체를 넘겨줘야 함)
    }

    public UserDTO login(UserDTO userDTO) {
        Optional<UserEntity> id = userRepository.findByUserId(userDTO.getUser_id());
        if (id.isPresent()){
            UserEntity userEntity = id.get();
            if(userEntity.getUserPw().equals(userDTO.getUser_pw())){
                UserDTO dto = UserDTO.toUserDTO(userEntity);
                return dto;
            }else {
                return null;
            }
        }else {
                return null;
        }
    }


    public UserDTO findID(UserDTO userDTO) {
        Optional<UserEntity> findID = userRepository.findByUserName(userDTO.getUser_name());
        if(findID.isPresent()){
            UserEntity userEntity = findID.get();
            if (userEntity.getUserName().equals(userDTO.getUser_name())){
                UserDTO dto = UserDTO.toUserDTO(userEntity);
                return dto;
            }else{
                return null;
            }
        }else {
            return null;
        }
    }


    public UserDTO findPW(UserDTO userDTO) {
        Optional<UserEntity> findPW = userRepository.findByUserName(userDTO.getUser_name());
        if(findPW.isPresent()){
            UserEntity userEntity = findPW.get();
            if (userEntity.getUserName().equals(userDTO.getUser_name())){
                UserDTO dto = UserDTO.toUserDTO(userEntity);
                return dto;
            }else{
                return null;
            }
        }else {
            return null;
        }
    }


    public UserDTO updateForm(String myInfo) {
        Optional<UserEntity> optionalUserEntity = userRepository.findByUserId(myInfo);
        if(optionalUserEntity.isPresent()){
            return UserDTO.toUserDTO(optionalUserEntity.get());
        }else {
            return null;
        }
    }


    public void update(UserDTO userDTO) {
        /*UserEntity userEntity = UserEntity.toUserEntity(userDTO);
        userRepository.save(userEntity);*/
        if(userDTO.getUser_photo2().isEmpty()){
            //첨부파일 없음
            userRepository.save(UserEntity.toUserUpdateEntity(userDTO));

        }else {
            //첨부파일 없음
            // 1. DTO에 담긴 파일 꺼내고
            // 2. 파일 이름 가져오고
            // 3. 서버저장용 이름으로 수정
            // 4. 저장경로 설정
            // 5. 해당 경로에 파일 저장
            // 6. user테이블에 해당 데이터 update
            // 8. user_file 테이블에 해당 데이터 update
        }

    }


    @Transactional
    public void deleteUser(String userId) {
        userRepository.deleteByUserId(userId);
    }


    public String idCheck(String userId) {
        Optional<UserEntity> byUserId = userRepository.findByUserId(userId);
        if(byUserId.isPresent()){
            //조회결과가 있으면 사용 불가
            return null;
        }else {
            //조회결과가 없으면 사용 가능
            return "ok";
        }
    }

    public String nickCheck(String userNick) {
        Optional<UserEntity> byUserNick = userRepository.findByUserNick(userNick);
        if(byUserNick.isPresent()){
            //조회결과가 있으면 사용 불가
            return null;
        }else {
            //조회결과가 없으면 사용 가능
            return "ok";
        }
    }
}
