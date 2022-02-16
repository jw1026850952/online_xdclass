package net.xdclass.online_xdclass.service;

import net.xdclass.online_xdclass.model.entity.Video;
import net.xdclass.online_xdclass.model.entity.VideoBanner;

import java.util.List;

public interface VideoService {
    /**
     * 查询视频列表
     * @return
     */
    List<Video> listVideo();

    /**
     * 视频轮播图列表
     * @return
     */
    List<VideoBanner> ListBanner();

    /**
     * 根据ID查询视频详情
     * @param videoId
     * @return
     */
    Video findDetailById(int videoId);
}
