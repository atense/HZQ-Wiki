package com.hzqing.admin.mapper.config;

import com.hzqing.admin.domain.config.Config;

import java.util.List;

/**
 * @author hzqing
 * @date 2019-05-17 09:55
 */
public interface ConfigMapper {

    List<Config> selectList();

    int updateBatch(List<Config> toList);
}
