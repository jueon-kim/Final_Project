package com.hwagae.market.post;


import com.hwagae.market.file.FileEntity;
import com.hwagae.market.file.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final FileRepository fileRepository;

    public void save(PostDTO postDTO) throws IOException {
        if (postDTO.getPost_upLoadFile().isEmpty()) {
            PostEntity postEntity = PostEntity.toSaveEntity(postDTO);
            postRepository.save(postEntity);
        } else {
            PostEntity postEntity = PostEntity.toSaveFileEntity(postDTO);
            Integer postNum = postRepository.save(postEntity).getPost_num();
            PostEntity post = postRepository.findById(postNum).get();

            for (MultipartFile postFile : postDTO.getPost_upLoadFile()) {
                String originalFilename = postFile.getOriginalFilename();

                if (originalFilename != null && !originalFilename.isEmpty()) {
                    String file_url = System.currentTimeMillis() + "_" + originalFilename;
                    String savePath = "C:/image/" + file_url;
                    postFile.transferTo(new File(savePath));
                    FileEntity fileEntity = FileEntity.toFileEntity(post, file_url);
                    fileRepository.save(fileEntity);
                } else {
                    System.out.println("파일이 입력되지않았습니다~" + originalFilename);
                }
            }
        }
    }


    public List<PostDTO> findAll() {
        List<PostEntity> postEntityList = postRepository.findAll();
        List<PostDTO> postDTOList = new ArrayList<>();
        for(PostEntity postEntity: postEntityList){
            postDTOList.add(PostDTO.toPostDTO(postEntity));
        }
        System.out.println("postDTOList = " + postDTOList);
        return postDTOList;
    }


}










