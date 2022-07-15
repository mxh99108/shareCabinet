package com.mxh.share.Service;

import com.mxh.share.common.Result;
import com.mxh.share.entity.Box;
import com.mxh.share.entity.Orderings;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * author:erhui
 * version:1.0
 **/
public interface BoxService {

    Result save(@RequestBody Box box);

    Result update(Box box);

    Result delete(Long id);

    Result put(@RequestBody Box box);

    Result take(@RequestBody Orderings orderings);

}
