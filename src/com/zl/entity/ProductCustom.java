package com.zl.entity;

public class ProductCustom extends Product{
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

    private String remark;

    private String xremark;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getXremark() {
        return xremark;
    }

    public void setXremark(String xremark) {
        this.xremark = xremark == null ? null : xremark.trim();
    }

	@Override
	public String toString() {
		return "ProductCustom [category=" + category + ", remark=" + remark + ", xremark=" + xremark + "]";
	}
    
    
	
	
}
