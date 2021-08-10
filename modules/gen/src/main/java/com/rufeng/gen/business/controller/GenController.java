package com.rufeng.gen.business.controller;

import com.rufeng.common.core.text.Convert;
import com.rufeng.common.core.web.domain.R;
import com.rufeng.gen.business.domain.GenTable;
import com.rufeng.gen.business.domain.GenTableColumn;
import com.rufeng.gen.business.service.IGenTableColumnService;
import com.rufeng.gen.business.service.IGenTableService;
import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.formula.functions.T;
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
 * @author chengen
 */
@RestController
@RequestMapping("/gen")
public class GenController {
    @Autowired
    private IGenTableService genTableService;

    @Autowired
    private IGenTableColumnService genTableColumnService;

    /**
     * 查询代码生成列表
     */
    @GetMapping("/list")
    public R<List<GenTable>> genList(GenTable genTable) {
        List<GenTable> list = genTableService.selectGenTableList(genTable);
        return R.ok(list);
    }

    /**
     * 修改代码生成业务
     */
    @GetMapping(value = "/{talbleId}")
    public R<Map<String, Object>> getInfo(@PathVariable Long talbleId) {
        GenTable table = genTableService.selectGenTableById(talbleId);
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(talbleId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", table);
        map.put("rows", list);
        return R.ok(map);
    }

    /**
     * 查询数据库列表
     */
    @GetMapping("/db/list")
    public R<List<GenTable>> dataList(GenTable genTable) {
        List<GenTable> list = genTableService.selectDbTableList(genTable);
        return R.ok(list);
    }

    /**
     * 查询数据表字段列表
     */
    @GetMapping(value = "/column/{talbleId}")
    public R<Map<String, Object>> columnList(Long tableId) {
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
        Map<String, Object> map = new HashMap<>();
        map.put("rows", list);
        map.put("total", list.size());
        return R.ok(map);
    }

    /**
     * 导入表结构（保存）
     */
    @PostMapping("/importTable")
    public R<T> importTableSave(String tables) {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        genTableService.importGenTable(tableList);
        return R.ok();
    }

    /**
     * 修改保存代码生成业务
     */
    @PutMapping
    public R<T> editSave(@Validated @RequestBody GenTable genTable) {
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return R.ok();
    }

    /**
     * 删除代码生成
     */
    @DeleteMapping("/{tableIds}")
    public R<T> remove(@PathVariable Long[] tableIds) {
        genTableService.deleteGenTableByIds(tableIds);
        return R.ok();
    }

    /**
     * 预览代码
     */
    @GetMapping("/preview/{tableId}")
    public R<Map<String, String>> preview(@PathVariable("tableId") Long tableId) throws IOException {
        Map<String, String> dataMap = genTableService.previewCode(tableId);
        return R.ok(dataMap);
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
        response.setHeader("Content-Disposition", "attachment; filename=\"chengen.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        IOUtils.write(data, response.getOutputStream());
    }
}