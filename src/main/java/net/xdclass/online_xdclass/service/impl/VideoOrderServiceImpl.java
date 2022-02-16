package net.xdclass.online_xdclass.service.impl;

import net.xdclass.online_xdclass.exception.XDException;
import net.xdclass.online_xdclass.mapper.*;
import net.xdclass.online_xdclass.model.entity.Episode;
import net.xdclass.online_xdclass.model.entity.PlayRecord;
import net.xdclass.online_xdclass.model.entity.Video;
import net.xdclass.online_xdclass.model.entity.VideoOrder;
import net.xdclass.online_xdclass.service.VideoOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class VideoOrderServiceImpl implements VideoOrderService {

    @Autowired
    private VideoOrderMapper videoOrderMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private VideoMapper videoMapper;

    @Autowired
    private EpisodeMapper episodeMapper;
    @Autowired
    private PlayRecordMapper playRecordMapper;
    /**
     * 下单操作
     * 未来版本：优惠券，抵扣什么的
     * @param userId
     * @param videoId
     * @return
     */
    @Transactional
    @Override
    public int save(int userId, int videoId) {

        //判断是否已经购买
        VideoOrder videoOrder = videoOrderMapper.findByUserIdAndVideoIdAndState(userId, videoId, 1);
        if (videoOrder!=null){return 0;}
        Video video = videoMapper.findById(videoId);
        VideoOrder newvideoOrder = new VideoOrder();
        newvideoOrder.setCreateTime(new Date());
        newvideoOrder.setOutTradeNo(UUID.randomUUID().toString());
        newvideoOrder.setState(1);
        newvideoOrder.setTotalFee(video.getPrice());
        newvideoOrder.setUserId(userId);

        newvideoOrder.setVideoId(videoId);
        newvideoOrder.setVideoImg(video.getCoverImg());
        newvideoOrder.setVideoTitle(video.getTitle());

        int rows = videoOrderMapper.saveOrder(newvideoOrder);

        //生成播放记录
        if (rows==1){
            Episode episode = episodeMapper.findFistEpisodeById(videoId);
            if (episode==null){
                throw new XDException(-1, "视频没有集信息,请运营人员检查");
            }
            PlayRecord playRecord = new PlayRecord();
            playRecord.setCreateTime(new Date());
            playRecord.setEpisodeId(episode.getId());
            playRecord.setCurrentNum(episode.getNum());
            playRecord.setUserId(userId);
            playRecord.setVideoId(videoId);
            playRecordMapper.saveRecord(playRecord);
        }

        return rows;
    }

    @Override
    public List<VideoOrder> listOrderByUserId(Integer userId) {
        return videoOrderMapper.listOrderByUserId(userId);
    }
}
