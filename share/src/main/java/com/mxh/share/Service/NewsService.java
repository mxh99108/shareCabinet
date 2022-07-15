package com.mxh.share.Service;

import com.mxh.share.common.Result;
import com.mxh.share.entity.News;
import com.mxh.share.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * author:erhui
 * version:1.0
 **/
public interface NewsService {

    Result save(@RequestBody News news);

    Result update(News news);

    Result delete(Long id);


}
