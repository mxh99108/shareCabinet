package com.mxh.share.Service;

import com.mxh.share.common.Result;
import com.mxh.share.entity.Orderings;
import com.mxh.share.entity.Price;
import com.mxh.share.entity.User;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * author:erhui
 * version:1.0
 **/
public interface OrderingsService {

    Result save(@RequestBody Orderings orderings);

    Result delete(Long id);

    Price getPrice();

    Result setPrice(Price price);
}
