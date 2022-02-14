package plus.ragdoll.framework.basic.base;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import plus.ragdoll.framework.basic.api.ApiResponse;

import javax.validation.Valid;
import java.util.List;

/**
 * @author spark
 */
public class BaseController<M extends BaseService<T>, T extends BaseEntity>{
  
    @Autowired
    private M baseService;

    @GetMapping("page")
    @ApiOperation(value = "获取分页列表")
    public ApiResponse<IPage<T>> page(Page<T> page, T entity) {
        LambdaQueryWrapper<T> lambdaQueryWrapper = new LambdaQueryWrapper<>(entity);
        IPage<T> entityPage = baseService.page(page, lambdaQueryWrapper);
        return ApiResponse.ok(entityPage);
    }


    @GetMapping
    @ApiOperation(value = "获取全部列表")
    public ApiResponse<List<T>> list(T entity) {
        LambdaQueryWrapper<T> entityLambdaQueryWrapper = new LambdaQueryWrapper<>(entity);
        return ApiResponse.ok(baseService.getBaseMapper().selectList(entityLambdaQueryWrapper));
    }

    @GetMapping("{id}")
    @ApiOperation(value = "获取详情")
    public ApiResponse<T> get(@PathVariable String id) {
        return ApiResponse.ok(baseService.getById(id));
    }

    @PostMapping
    @ApiOperation(value = "添加一个")
    public ApiResponse<String> add(@RequestBody @Valid T entity) {
        baseService.save(entity);
        return ApiResponse.ok(entity.getId());
    }

    @PutMapping
    @ApiOperation(value = "修改")
    public ApiResponse<Void> edit(@RequestBody @Valid T entity) {
        baseService.updateById(entity);
        return ApiResponse.ok();
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "删除一个")
    public ApiResponse<Void> delete(@PathVariable String id) {
        baseService.removeById(id);
        return ApiResponse.ok();
    }

    @DeleteMapping
    @ApiOperation(value = "批量删除")
    public ApiResponse<Void> delete(@RequestParam("idList") List<String> idList) {
        baseService.removeByIds(idList);
        return ApiResponse.ok();
    }

}
