package com.company.Entiy;

import java.io.*;
import java.util.*;
public class Book implements Serializable{

	public String[] attribute;
	public static int amount;
	Scanner re=new Scanner(System.in);
	/*
	 *主属性（primary attribute）：征订号
	 *主码（primary key）：征订号
	 *
	 *
	 ***属性****   **编号**
	 *本季征订号      0
	 *分册	          1
	 *出版社          2
	 *ISBN			  3
	 *书名			  4
	 *著作者		  5
	 *适用分级		  6
	 *价格			  7
	 *出版年月		  8
	 *社内分类	      9
	 *中图法分类	  10
	 *学科分类编码	  11
	 *学科分类1		  12
	 *学科分类2		  13
	 *
	 */
	public void setAttribute(int i){
		this.attribute[i]=re.next();
	}

	public String getAttribute(int i){
		return this.attribute[i];
	}
	public Book()   {  //无参构造方法

	}
	public Book(String[] s){
		this.attribute=new String[this.amount];       //初始化该数组；长度为表头属性个数
		for(int i=0;i<s.length;i++){
			this.attribute[i]=s[i];                   //为该图书各个属性赋值
		}
	}
	public void ruleToString(){
		System.out.println("\t       ***属性****    **属性值**\n");
		System.out.println("\t       *本季征订号     "+this.attribute[0]);
		System.out.println("\t       *分册           "+this.attribute[1]);
		System.out.println("\t       *出版社         "+this.attribute[2]);
		System.out.println("\t       *ISBN           "+this.attribute[3]);
		System.out.println("\t       *书名           "+this.attribute[4]);
		System.out.println("\t       *著作者         "+this.attribute[5]);
		System.out.println("\t       *适用分级       "+this.attribute[6]);
		System.out.println("\t       *价格           "+this.attribute[7]);
		System.out.println("\t       *出版年月       "+this.attribute[8]);
		System.out.println("\t       *社内分类       "+this.attribute[9]);
		System.out.println("\t       *中图法分类     "+this.attribute[10]);
		System.out.println("\t       *学科分类编码   "+this.attribute[11]);
		System.out.println("\t       *学科分类1      "+this.attribute[12]);
		System.out.println("\t       *学科分类2      "+this.attribute[13]);
		System.out.println("\t-----------------------------");

	}
	public String toString(){                         //重写toString()方法
		String str=attribute[0];
		for(int j=1;j<attribute.length;j++){
			str=str.concat(",");
			str=str.concat(attribute[j]);        //将指定字符串连接到此字符串的结尾。
		}
		return str;
	}
}