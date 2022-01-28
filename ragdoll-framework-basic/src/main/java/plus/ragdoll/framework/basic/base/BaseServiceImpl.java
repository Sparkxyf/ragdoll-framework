package plus.ragdoll.framework.basic.base;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


/**
 * @author : spark
 * @date : 2021-07-18 16:14
 */
public class BaseServiceImpl<M extends MyBaseMapper<T>, T> extends ServiceImpl<M, T> implements BaseService<T> {

    @Autowired(required = false)
    private M myBaseMapper;

    @Override
    public boolean insertBatchSomeColumn(List<T> entityList) {
        return myBaseMapper.insertBatchSomeColumn(entityList) > 0;
    }

    @Override
    public boolean upsertBatchSomeColumn(List<T> entityList) {
        return myBaseMapper.upsertBatchSomeColumn(entityList) > 0;
    }
}
