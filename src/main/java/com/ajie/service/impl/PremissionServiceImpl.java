package com.ajie.service.impl;

import com.ajie.constant.MessageConstant;
import com.ajie.entity.Premission;
import com.ajie.mapper.PremissionMapper;
import com.ajie.result.PageResult;
import com.ajie.result.QueryInfo;
import com.ajie.result.Result;
import com.ajie.service.PremissionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ajie
 * @createTime 2022
 */
@Service
@Slf4j
public class PremissionServiceImpl implements PremissionService {

    @Autowired
    private PremissionMapper premissionMapper;

    @Override
    public Result findPage(QueryInfo queryInfo) {
        try {
            PageHelper.startPage(queryInfo.getPageNumber(), queryInfo.getPageSize());
            Page<Premission> page = premissionMapper.findPage(queryInfo.getQueryString());
            return Result.success(MessageConstant.PREMISSION_GET_SUCCESS, new PageResult(page.getResult(), page.getTotal()));
        } catch (Exception e) {
            log.info("程序出错 ---> {}", e.getMessage());
            return Result.fail(MessageConstant.PREMISSION_GET_FAIL + e.getMessage());
        }
    }

    @Override
    public Result insert(Premission premission) {
        try {
            premissionMapper.insert(premission);
            premissionMapper.insertPremission(premission.getId());
            return Result.success(MessageConstant.PREMISSION_INSERT_SUCCESS);
        } catch (Exception e) {
            log.info("程序出错 ---> {}", e.getMessage());
            return Result.fail(MessageConstant.PREMISSION_GET_FAIL + e.getMessage());
        }
    }

    @Override
    public Result delete(Integer id) {
        try {
            premissionMapper.delete(id);
            return Result.success(MessageConstant.PREMISSION_DELETE_SUCCESS);
        } catch (Exception e) {
            log.info("程序出错 ---> {}", e.getMessage());
            return Result.fail(MessageConstant.PREMISSION_DELETE_FAIL + e.getMessage());
        }
    }

    @Override
    public Result update(Premission premission) {
        try {
            premissionMapper.update(premission);
            return Result.success(MessageConstant.PREMISSION_UPDATE_SUCCESS);
        } catch (Exception e) {
            log.info("程序出错 ---> {}", e.getMessage());
            return Result.fail(MessageConstant.PREMISSION_UDPATE_FAIL + e.getMessage());
        }
    }
}
