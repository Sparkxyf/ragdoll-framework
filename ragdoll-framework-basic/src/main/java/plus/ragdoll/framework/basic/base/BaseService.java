package plus.ragdoll.framework.basic.base;

import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author : spark
 * @date : 2021-07-18 16:19
 */
public interface BaseService<T> extends IService<T> {

    /**
     * 插入多条记录
     *
     * @param entityList 实体
     * @return 成功行数
     */
    boolean insertBatchSomeColumn(List<T> entityList);
    /**
     * 插入或更新多条记录
     * @param entityList 实体
     * @return 成功行数
     */
    boolean upsertBatchSomeColumn(List<T> entityList);

}
