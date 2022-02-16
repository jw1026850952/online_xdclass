package net.xdclass.online_xdclass.service;

import net.xdclass.online_xdclass.model.entity.VideoOrder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface VideoOrderService {

    int save(int userId,int videoId);

    List<VideoOrder> listOrderByUserId(Integer userId);
}
