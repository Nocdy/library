package com.nocdy.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nocdy.web.dao.RecordMapper;
import com.nocdy.web.entity.Record;
import com.nocdy.web.service.RecordService;
import org.springframework.stereotype.Service;

/**
 * @author Nocdy
 * @date 2021年 09月06日 11:30:16
 */
@Service
public class RecordServiceImpl extends ServiceImpl<RecordMapper, Record> implements RecordService {
}
