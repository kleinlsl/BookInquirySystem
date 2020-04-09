package com.company;

import com.company.Entiy.Book;
import com.company.Utils.NormalComparator;

import java.util.*;
public class Menu{
	Scanner re=new Scanner(System.in);
	Library lib;
	boolean label;

	public Menu(Library lib){
		this.lib=lib;
		this.label=true;
		//	this.lib.books[this.lib.seqSearch("高等",2)].ruleToString();
		while(this.label){
			this.first_Meun();
		}
	}

	//主菜单
	public void first_Meun(){
		System.out.println("\t==============================");
		System.out.println("\t---------[ 主菜单 ]-----------");
		System.out.println("\t[1]  插入图书：");
		System.out.println("\t[2]  删除图书：");
		System.out.println("\t[3]  修改图书：");
		System.out.println("\t[4]  查询图书：");
		System.out.println("\t[5]  排序图书：");
		System.out.println("\t[6]  保存至源文件：");
		System.out.println("\t[7]  另存为新文件：");
		System.out.println("\t[0]  退出系统：");
		System.out.println("\t-----------------------------");
		System.out.print("\t请输入选项编号：");
		int ser=re.nextInt();
		switch(ser){
			case 1:this.insert_Book();break;
			case 2:this.del_Book();break;
			case 3:this.amend_Book();break;
			case 4:this.search_Menu();break;
			case 5:this.sort();break;
			case 6:this.lib.save_File();break;
			case 7:System.out.print("请输入新的文件名：");
				String str=re.next();
				this.lib.save_As_File(this.lib,str);
				break;
			case 0:	System.out.println("请选择：[1]保存并退出；[2]直接退出；[3]取消退出");
				int xu=re.nextInt();
				if(xu==1) {  this.lib.save_File();this.label=false;break;	}
				if(xu==2) {  this.label=false;break;	}
				if(xu==3) {  break;	}
				else  break;
			default:System.out.println("\t----无此选项!------");break;
		}
	}
	//查找子菜单
	public void search_Menu(){
		System.out.println("\t==============================");
		System.out.println("\t----------[ 查找 ]------------");
		System.out.println("\t[1]  精确查找：");
		System.out.println("\t[2]  模糊查找：");
		System.out.println("\t[3]  查找位置：");
		System.out.println("\t-----------------------------");
		System.out.print("\t请输入选项编号：");
		int ser=re.nextInt();
		this.attribute_Meun();
		switch(ser){
			case 1:this.search_True();break;
			case 2:this.search_Flase();break;
			case 3:this.search_W();break;
			default:System.out.println("\t----无此选项!------");break;
		}
	}
	//属性菜单
	public void attribute_Meun(){
		System.out.println("\t==============================");
		System.out.println("\t-----[ attribute_Meun ]-------");
		System.out.println("\t       ***属性****   **编号**\n");
		System.out.println("\t       *本季征订号      0");
		System.out.println("\t       *分册            1");
		System.out.println("\t       *出版社          2");
		System.out.println("\t       *ISBN            3");
		System.out.println("\t       *书名            4");
		System.out.println("\t       *著作者          5");
		System.out.println("\t       *适用分级        6");
		System.out.println("\t       *价格            7");
		System.out.println("\t       *出版年月        8");
		System.out.println("\t       *社内分类        9");
		System.out.println("\t       *中图法分类      10");
		System.out.println("\t       *学科分类编码    11");
		System.out.println("\t       *学科分类1       12");
		System.out.println("\t       *学科分类2       13");
		System.out.println("\t-----------------------------");
	}
	//插入图书
	public void insert_Book(){
		String []str=new String[this.lib.books[0].attribute.length];
		this.attribute_Meun();
		System.out.println("请依次输入对应的属性值：");
		for(int i=0;i<str.length;i++){
			str[i]=re.next();
		}
		Book book=new Book(str);
		this.lib.addBook(book);
	}
	//删除
	public void del_Book(){
		System.out.println("请输入要删除图书所在的位置：");
		int i=re.nextInt();
		this.lib.books[i].ruleToString();
		this.lib.del_Book(i);
		System.out.println("已删除！");
	}
	//修改
	public void amend_Book(){
		System.out.println("请输入要修改图书所在的位置：");
		int i=re.nextInt();
		int p=1;;
		System.out.println(this.lib.books[i].toString());
		this.attribute_Meun();
		while(p>=0&&p<this.lib.books[i].amount){
			System.out.println("请输入要修改的属性，对应的编号;输入-1 退出修改:");
			p=re.nextInt();
			if(p!=-1){
				System.out.println("请输入修改后的值：");
				this.lib.books[i].attribute[p]=re.next();
			}
		}
		System.out.println("已修改为：");
		this.lib.books[i].ruleToString();
	}
	//查询
	//精确
	public void search_True(){
		System.out.println("请输入要查询的属性编号：");
		int w=re.nextInt();
		System.out.println("请输入要查询的属性值：");
		String str=re.next();
		this.lib.heapSort(w);
		Library we=new Library();                  //查询结果保存到临时图书库
		we.f_Path=this.lib.f_Path;                 //设置默认路径
		we.addBook(this.lib.books[0]);             //将表头添加进去
		NormalComparator NC=new NormalComparator();
		int mark=this.lib.binarySearch(str,w);
		int high=mark++,low=mark--;
		if(mark==-1){
			System.out.println("未找到");
		}
		else{
			we.addBook(this.lib.books[mark]);
			while(low>0&&(NC.compare(this.lib.books[low].attribute[w],str))==0)
				we.addBook(this.lib.books[low--]);
			while(high<this.lib.amount&&(NC.compare(this.lib.books[high].attribute[w],str))==0)
				we.addBook(this.lib.books[high++]);
		}
		str=str.concat("_".concat(Integer.toString(we.amount)));
		str="查找结果_".concat(str);
		lib.save_As_File(we,str);
		System.out.println("查询结果，已保存到文件；文件名为："+str);
	}
	//模糊
	public void search_Flase(){
		System.out.println("请输入要查询的属性编号：");
		int w=re.nextInt();
		System.out.println("请输入要查询的属性值：");
		String str=re.next();
		Library we=this.lib.seqAllSearch(str,w);
		we.f_Path=this.lib.f_Path;
		str=str.concat("_".concat(Integer.toString(we.amount)));
		str="查找结果_".concat(str);
		lib.save_As_File(we,str);
		System.out.println("查询结果，已保存到文件；文件名为："+str);
	}
	//查询位置
	public void search_W(){
		System.out.println("请输入要查询的属性编号：");
		int w=re.nextInt();
		System.out.println("请输入要查询的属性值：");
		String str=re.next();
		int we=this.lib.seqSearch(str,w);

		this.lib.books[we].ruleToString();
		System.out.println("查询到该图书的位置："+we);
	}
	//排序
	public void sort(){
		this.attribute_Meun();
		System.out.println("请输入作为排序条件的属性编号：");
		int w=re.nextInt();
		this.lib.heapSort(w);
		String str=this.lib.books[0].attribute[w];
		str=str.concat("_".concat(Integer.toString(this.lib.amount)));
		str="排序结果_".concat(str);
		this.lib.save_As_File(this.lib,str);
		System.out.println("查询结果，已保存到文件；文件名为："+str);
	}

}