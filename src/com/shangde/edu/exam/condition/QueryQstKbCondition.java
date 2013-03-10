package com.shangde.edu.exam.condition;

public class QueryQstKbCondition {
    private int id;
    private int kId;
    private int qstId;
        
    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

	public int getKId() {
		return kId;
	}

	public void setKId(int id) {
		kId = id;
	}

	public int getQstId() {
		return qstId;
	}

	public void setQstId(int qstId) {
		this.qstId = qstId;
	}
}