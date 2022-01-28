package plus.ragdoll.framework.basic.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * The interface My base mapper.
 *
 * @param <T> the type parameter
 * @author : spark
 * @date : 2021-07-18 10:19
 */
public interface MyBaseMapper<T> extends BaseMapper<T> {
    /**
     * 插入多条记录
     *
     * @param entityList 实体
     * @return 成功行数 int
     */
    int insertBatchSomeColumn(@Param("list")List<T> entityList);

    /**
     * Insert ignore batch all column int.
     *
     * @param entityList the entity list
     * @return the int
     */
    int insertIgnoreBatchAllColumn(@Param("list")List<T> entityList);

    /**
     * 插入或更新多条记录
     *
     * @param entityList 实体
     * @return 成功行数 int
     */
    int upsertBatchSomeColumn(@Param("list")List<T> entityList);
}
