package pub.vo;

import java.util.List;

public class CourseAndChepter {

	public String id=null;//唯一标识
	public String name=null;//课程和章节名称
	public String introduction=null;//简介
	public String date=null;//日期
	public String action=null;//操作
	public String state=null;//为colsed，为不展开。要有子项，否则不设置。
	public List children=null;//children
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public List getChildren() {
		return children;
	}
	public void setChildren(List children) {
		this.children = children;
	}
	//测试解决 冲
	public void test2(){
	  System.out.println("使1用这段代码c");
	}
	public void mytest1(String aa){
	  System.out.println("使用这aaaaaa段代码c");
	}
	
}
