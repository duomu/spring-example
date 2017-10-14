package com.yll.springmvc.service.impl;

import com.github.pagehelper.PageHelper;
import com.yll.springmvc.entity.Log;
import com.yll.springmvc.entity.LogExample;
import com.yll.springmvc.mapper.LogMapper;
import com.yll.springmvc.service.ILogService;
import com.yll.springmvc.util.PageVo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author：linlin.yang
 * Date：2017/5/15 19:38
 */
@Service
public class LogServiceImpl implements ILogService {
    private Logger logger= Logger.getLogger(LogServiceImpl.class);
    @Autowired
    private LogMapper logMapper;

//    @Autowired
//    private RedisTemplate redisTemplate;

    @Override
    public List<Log> getLogByUserId(Integer userId) {
        LogExample example = new LogExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return logMapper.selectByExample(example);
    }

    @Override
    public List<Log> getLogByPage(Integer currentPage, Integer pageSize, Integer userId) {
        PageHelper.startPage(currentPage,pageSize);
        LogExample example = new LogExample();
        example.createCriteria().andUserIdEqualTo(userId);
        return logMapper.selectByExample(example);
    }

    @Override
    public PageVo<Log> getLogPageVoByUserId(PageVo<Log> pageVo, Integer userId) {
//        List<Log> logs=null;
//        if(pageVo.getCurrentPage()==1){//只第一页走缓存
//            //读缓存
//            String key="logs_"+userId;
//            ValueOperations<String,Object> operations=redisTemplate.opsForValue();
//            boolean hasKey=redisTemplate.hasKey(key);
//
//            if(hasKey){
//                logs= (List<Log>) operations.get(key);
//                logger.info("从缓存中获取了日志列表");
//            }else{
//                PageHelper.startPage(pageVo.getCurrentPage(),pageVo.getPageSize());
//                logs=logMapper.getLogList(userId);
//                //插入缓存
//                operations.set(key,logs,5, TimeUnit.MINUTES);
//                logger.info("将日志列表插入缓存");
//            }
//        }else{
//            PageHelper.startPage(pageVo.getCurrentPage(),pageVo.getPageSize());
//            logs=logMapper.getLogList(userId);
//        }

        List<Log> logs = null;
        PageHelper.startPage(pageVo.getCurrentPage(), pageVo.getPageSize());
        LogExample example = new LogExample();
        example.createCriteria().andUserIdEqualTo(userId);
        logs = logMapper.selectByExample(example);

        int logCount = logMapper.countByExample(example);
        int pageCount= Double.valueOf(Math.ceil(1.0*logCount/pageVo.getPageSize())).intValue();
        pageVo.setPageCount(pageCount);
        pageVo.setVoList(logs);
        return pageVo;
    }

    @Override
    public Log getLogById(Integer id) {
        return logMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer insertLog(Log log) {
//        deleteCache("logs_"+userId);
        return logMapper.insert(log);
    }

    @Override
    public Integer updateLog(Log log) {
//        deleteCache("logs_"+userId);
        return logMapper.updateByPrimaryKey(log);
    }

    @Override
    public Integer deleteLogById(Integer id) {
        return logMapper.deleteByPrimaryKey(id);
    }

    @Override
    public Integer deleteLogByIds(List<Integer> ids) {
//        deleteCache("logs_"+userId);
        LogExample example = new LogExample();
        example.createCriteria().andIdIn(ids);
        return logMapper.deleteByExample(example);
    }

//    /**
//     * 从缓存中删除指定的key
//     * @param key
//     */
//    private void deleteCache(String key){
//        logger.info("从缓存中删除了日志列表");
//        ValueOperations<String,Object> operations=redisTemplate.opsForValue();
//        if(redisTemplate.hasKey(key)){
//            redisTemplate.delete(key);
//        }
//    }
}
