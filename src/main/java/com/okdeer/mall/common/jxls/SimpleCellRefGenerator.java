package com.okdeer.mall.common.jxls;

import org.jxls.command.CellRefGenerator;
import org.jxls.common.CellRef;
import org.jxls.common.Context;

/**
 * 简单的单元实现
 */
public class SimpleCellRefGenerator implements CellRefGenerator {

    private String sheetName = "sheet";

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public SimpleCellRefGenerator() {
    }

    public SimpleCellRefGenerator(String sheetName) {

        this.sheetName = sheetName;
    }

    @Override
    public CellRef generateCellRef(int index, Context context) {
        return new CellRef(this.sheetName + index + "!A1");
    }
}