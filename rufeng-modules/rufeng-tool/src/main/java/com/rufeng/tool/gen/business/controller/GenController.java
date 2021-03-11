package com.rufeng.tool.gen.business.controller;

import com.rufeng.business.tool.gen.domain.GenTable;
import com.rufeng.business.tool.gen.domain.GenTableColumn;
import com.rufeng.business.tool.gen.service.IGenTableColumnService;
import com.rufeng.business.tool.gen.service.IGenTableService;
import com.rufeng.core.framework.common.core.text.Convert;
import com.rufeng.core.framework.web.ResultData;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成 操作处理
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/tool/gen")
public class GenController {
    @Autowired
    private IGenTableService genTableService;

    @Autowired
    private IGenTableColumnService genTableColumnService;

    /**
     * 查询代码生成列表
     */
    @GetMapping("/list")
    public ResultData genList(GenTable genTable) {
        ResultData resultData = ResultData.init();
        List<GenTable> list = genTableService.selectGenTableList(genTable);
        resultData.setData("rows", list);
        return resultData;
    }

    /**
     * 修改代码生成业务
     */
    @GetMapping(value = "/{talbleId}")
    public ResultData getInfo(@PathVariable Long talbleId) {
        ResultData resultData = ResultData.init();
        GenTable table = genTableService.selectGenTableById(talbleId);
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(talbleId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", table);
        map.put("rows", list);
        resultData.setData(map);
        return resultData;
    }

    /**
     * 查询数据库列表
     */
    @GetMapping("/db/list")
    public ResultData dataList(GenTable genTable) {
        ResultData resultData = ResultData.init();
        List<GenTable> list = genTableService.selectDbTableList(genTable);
        resultData.setData("rows", list);
        return resultData;
    }

    /**
     * 查询数据表字段列表
     */
    @GetMapping(value = "/column/{talbleId}")
    public ResultData columnList(Long tableId) {
        ResultData resultData = ResultData.init();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
        resultData.setData("rows", list);
        resultData.setData("total", list.size());
        return resultData;
    }

    /**
     * 导入表结构（保存）
     */
    @PostMapping("/importTable")
    public ResultData importTableSave(String tables) {
        ResultData resultData = ResultData.init();
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        genTableService.importGenTable(tableList);
        return resultData;
    }

    /**
     * 修改保存代码生成业务
     */
    @PutMapping
    public ResultData editSave(@Validated @RequestBody GenTable genTable) {
        ResultData resultData = ResultData.init();
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return resultData;
    }

    /**
     * 删除代码生成
     */
    @DeleteMapping("/{tableIds}")
    public ResultData remove(@PathVariable Long[] tableIds) {
        ResultData resultData = ResultData.init();
        genTableService.deleteGenTableByIds(tableIds);
        return resultData;
    }

    /**
     * 预览代码
     */
    @GetMapping("/preview/{tableId}")
    public ResultData preview(@PathVariable("tableId") Long tableId) throws IOException {
        ResultData resultData = ResultData.init();
        Map<String, String> dataMap = genTableService.previewCode(tableId);
        resultData.setData("data", dataMap);
        return resultData;
    }

    /**
     * 生成代码
     */
    @GetMapping("/genCode/{tableName}")
    public void genCode(HttpServletResponse response, @PathVariable("tableName") String tableName) throws IOException {
        byte[] data = genTableService.generatorCode(tableName);
        genCode(response, data);
    }

    /**
     * 批量生成代码
     */
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String tables) throws IOException {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableService.generatorCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) throws IOException {
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}