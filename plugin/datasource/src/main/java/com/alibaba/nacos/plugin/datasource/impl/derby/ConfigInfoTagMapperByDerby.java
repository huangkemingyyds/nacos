/*
 * Copyright 1999-2022 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.plugin.datasource.impl.derby;

import com.alibaba.nacos.plugin.datasource.constants.DataSourceConstant;
import com.alibaba.nacos.plugin.datasource.dialects.DialectFactory;
import com.alibaba.nacos.plugin.datasource.dialects.IDialect;
import com.alibaba.nacos.plugin.datasource.enums.DbTypeEnum;
import com.alibaba.nacos.plugin.datasource.mapper.AbstractMapper;
import com.alibaba.nacos.plugin.datasource.mapper.ConfigInfoTagMapper;
import com.alibaba.nacos.plugin.datasource.model.MapperContext;
import com.alibaba.nacos.plugin.datasource.model.MapperResult;

import java.util.Collections;

/**
 * The derby implementation of ConfigInfoTagMapper.
 *
 * @author hyx
 **/

public class ConfigInfoTagMapperByDerby extends AbstractMapper implements ConfigInfoTagMapper {
    
    @Override
    public MapperResult findAllConfigInfoTagForDumpAllFetchRows(MapperContext context) {
        String sql = "SELECT t.id,data_id,group_id,tenant_id,tag_id,app_name,content,md5,gmt_modified "
                + " FROM ( SELECT id FROM config_info_tag  ORDER BY id  OFFSET " + context.getStartRow()
                + " ROWS FETCH NEXT " + context.getPageSize() + " ROWS ONLY ) "
                + " g, config_info_tag t  WHERE g.id = t.id";
        return new MapperResult(sql, Collections.emptyList());
    }
    
    @Override
    public String getDataSource() {
        return DataSourceConstant.DERBY;
    }
    
    @Override
    public DbTypeEnum getDbTypeEnum() {
        return DbTypeEnum.DERBY;
    }
    
    @Override
    public IDialect getIDialect() {
        return DialectFactory.getDialect(getDbTypeEnum());
    }
}
