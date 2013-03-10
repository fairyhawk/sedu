package com.shangde.edu.ass.domain;

import java.io.Serializable;

/**
 * 澶勭悊鐭ヨ瘑鐐规槦绾т俊鎭�
 * @author zhangjuqiang
 *
 */
public class AssKpointStarInfo implements Serializable{

	private int kcAll;//所有已评价知识点的数量
	private int kc1;//评1分的数量
	private int kc2;//评2分的数量
	private int kc3;//评3分的数量
	private int kc4;//评4分的数量
	private int kc5;//评5分的数量
	public int getKcAll() {
		return kcAll;
	}
	public void setKcAll(int kcAll) {
		this.kcAll = kcAll;
	}
	public int getKc1() {
		return kc1;
	}
	public void setKc1(int kc1) {
		this.kc1 = kc1;
	}
	public int getKc2() {
		return kc2;
	}
	public void setKc2(int kc2) {
		this.kc2 = kc2;
	}
	public int getKc3() {
		return kc3;
	}
	public void setKc3(int kc3) {
		this.kc3 = kc3;
	}
	public int getKc4() {
		return kc4;
	}
	public void setKc4(int kc4) {
		this.kc4 = kc4;
	}
	public int getKc5() {
		return kc5;
	}
	public void setKc5(int kc5) {
		this.kc5 = kc5;
	}
}
