package mapper;

import static mapper.DemoDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import model.Demo;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSL;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.UpdateModel;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.CommonCountMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonDeleteMapper;
import org.mybatis.dynamic.sql.util.mybatis3.CommonUpdateMapper;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;
import org.springframework.stereotype.Repository;

@Repository
public interface DemoMapper extends CommonCountMapper, CommonDeleteMapper, CommonUpdateMapper {
    /**
     * @mbg.generated generated automatically, do not modify!
     */
    BasicColumn[] selectList = BasicColumn.columnList(id, message);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    @Options(useGeneratedKeys=true,keyProperty="row.id")
    int insert(InsertStatementProvider<Demo> insertStatement);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultipleWithGeneratedKeys")
    @Options(useGeneratedKeys=true,keyProperty="records.id")
    int insertMultiple(@Param("insertStatement") String insertStatement, @Param("records") List<Demo> records);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="DemoResult", value = {
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="message", property="message", jdbcType=JdbcType.VARCHAR)
    })
    List<Demo> selectMany(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("DemoResult")
    Optional<Demo> selectOne(SelectStatementProvider selectStatement);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, demo, completer);
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, demo, completer);
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    default int deleteByPrimaryKey(Integer id_) {
        return delete(c -> 
            c.where(id, isEqualTo(id_))
        );
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    default int insert(Demo row) {
        return MyBatis3Utils.insert(this::insert, row, demo, c ->
            c.map(message).toProperty("message")
        );
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    default int insertMultiple(Collection<Demo> records) {
        return MyBatis3Utils.insertMultipleWithGeneratedKeys(this::insertMultiple, records, demo, c ->
            c.map(message).toProperty("message")
        );
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    default int insertSelective(Demo row) {
        return MyBatis3Utils.insert(this::insert, row, demo, c ->
            c.map(message).toPropertyWhenPresent("message", row::getMessage)
        );
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    default Optional<Demo> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, demo, completer);
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    default List<Demo> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, demo, completer);
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    default List<Demo> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, demo, completer);
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    default Optional<Demo> selectByPrimaryKey(Integer id_) {
        return selectOne(c ->
            c.where(id, isEqualTo(id_))
        );
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, demo, completer);
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    static UpdateDSL<UpdateModel> updateAllColumns(Demo row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(message).equalTo(row::getMessage);
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    static UpdateDSL<UpdateModel> updateSelectiveColumns(Demo row, UpdateDSL<UpdateModel> dsl) {
        return dsl.set(message).equalToWhenPresent(row::getMessage);
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    default int updateByPrimaryKey(Demo row) {
        return update(c ->
            c.set(message).equalTo(row::getMessage)
            .where(id, isEqualTo(row::getId))
        );
    }

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    default int updateByPrimaryKeySelective(Demo row) {
        return update(c ->
            c.set(message).equalToWhenPresent(row::getMessage)
            .where(id, isEqualTo(row::getId))
        );
    }
}