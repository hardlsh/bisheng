package com.bisheng.util;

import java.lang.reflect.Field;

import com.bisheng.util.export.Cell;

public class CellEntity {
	
	public Field field;
	public Class<?> fieldType;
	public Cell cell ;

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}

	public Class<?> getFieldType() {
		return field.getType();
	}

	public void setFieldType(Class<?> fieldType) {
		this.fieldType = fieldType;
	}
	
	
	
}
