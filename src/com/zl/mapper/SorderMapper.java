package com.zl.mapper;

import com.zl.entity.Sorder;
import com.zl.entity.SorderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SorderMapper {
    int countByExample(SorderExample example);

    int deleteByExample(SorderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Sorder record);

    int insertSelective(Sorder record);

    List<Sorder> selectByExample(SorderExample example);

    Sorder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Sorder record, @Param("example") SorderExample example);

    int updateByExample(@Param("record") Sorder record, @Param("example") SorderExample example);

    int updateByPrimaryKeySelective(Sorder record);

    int updateByPrimaryKey(Sorder record);
}