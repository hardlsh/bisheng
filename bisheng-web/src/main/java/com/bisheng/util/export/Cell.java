package com.bisheng.util.export;

public @interface Cell {
	public abstract String Regex() default "";
	public abstract String Mode();
	public abstract String Message() default "";
	public abstract String ColumnName() default "";
	public abstract int Index();
	public abstract int scale() default 4;
 
}
