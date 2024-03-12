package com.hwagae.market.post;


import com.hwagae.market.file.FileEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

@Getter@Setter
@Table(name= "file")
@Entity
public class PostFileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fileNum;

    @Column
    private String fileUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_num")
    private PostEntity postEntity;

    public static PostFileEntity  toFileEntity(PostEntity PostEntity, String fileUrl){
        PostFileEntity fileEntity = new PostFileEntity();
        fileEntity.setFileUrl(fileUrl);
        fileEntity.setPostEntity(PostEntity);
        return fileEntity;
    }

    // Update 로직 추가
    public void updateFileUrl(String newFileUrl){
        this.fileUrl = newFileUrl;
    }


}
