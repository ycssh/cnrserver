package cn.cnr.admin.base.util;

import com.github.pagehelper.Page;

import java.util.List;

/**
 * @author 作者姓名 yc E-mail: ycssh2@163.com
 * @version 创建时间：2014-5-14 下午02:24:35 类说明 分页工具类
 */
public class PageResult<T> {
	private List<T> rows;

	private int total;
	
	private int recordsTotal;
	private int recordsFiltered;

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public PageResult(List<T> rows, int total) {
		super();
		this.rows = rows;
		this.total = total;
	}
	
	public PageResult(List<T> rows, int recordsTotal, int recordsFiltered) {
		super();
		this.rows = rows;
		this.recordsTotal = recordsTotal;
		this.recordsFiltered = recordsFiltered;
	}
	public PageResult(Page<T> rows) {
		super();
		this.rows = rows;
		this.recordsTotal = (int) rows.getTotal();
		this.recordsFiltered = (int) rows.getTotal();
	}

	public static PageResult toPage(Page page){
		return new PageResult(page, (int)page.getTotal());
	}

	public int getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(int recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public int getRecordsFiltered() {
		return recordsFiltered;
	}

	public void setRecordsFiltered(int recordsFiltered) {
		this.recordsFiltered = recordsFiltered;
	}

}
