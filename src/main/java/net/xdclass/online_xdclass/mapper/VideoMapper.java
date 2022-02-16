package net.xdclass.online_xdclass.mapper;

import net.xdclass.online_xdclass.model.entity.Video;
import net.xdclass.online_xdclass.model.entity.VideoBanner;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VideoMapper {

    /**
     * 查询视频列表
     * @return
     */
    List<Video> listVideo();

    /**
     * 视频轮播图列表
     * @return
     */
    List<VideoBanner> listVideoBanner();

    /**
     * 查询视频详情
     * @param videoId
     * @return
     */
    Video findDetailById(@Param("video_id") int videoId);

    Video findById(@Param("video_id") int videoId);
}
