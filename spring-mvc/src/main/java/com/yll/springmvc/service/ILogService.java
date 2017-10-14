package com.yll.springmvc.service;

import com.yll.springmvc.entity.Log;
import com.yll.springmvc.util.PageVo;

import java.util.List;

/**
 * Author：linlin.yang
 * Date：2017/5/15 18:49
 */
public interface ILogService {
    /**
     * 查询所有日志
     * @return
     */
    List<Log> getLogByUserId(Integer userId);

    /**
     * 分页查询日志
     * @param currentPage
     * @param pageSize
     * @return
     */
    List<Log> getLogByPage(Integer currentPage, Integer pageSize, Integer userId);

    /**
     * 根据userId分页查询日志
     * @param pageVo
     * @param userId
     * @return
     */
    PageVo<Log> getLogPageVoByUserId(PageVo<Log> pageVo, Integer userId);

//    /**
//     * 根据name分页查询日志
//     * @param pageVo
//     * @param name
//     * @return
//     */
//    PageVo<Log> getLogPageVoByName(PageVo<Log> pageVo, String name);

    /**
     * 根据id查询日志
     * @param id
     * @return
     */
    Log getLogById(Integer id);

    /**
     * 添加日志
     * @param log
     * @return
     */
    Integer insertLog(Log log);

    /**
     * 更新日志
     * @param log
     * @return
     */
    Integer updateLog(Log log);

    /**
     * 删除日志
     * @param id
     * @return
     */
    Integer deleteLogById(Integer id);

    /**
     * 删除指定id的日志
     * @param ids
     * @return
     */
    Integer deleteLogByIds(List<Integer> ids);

}
