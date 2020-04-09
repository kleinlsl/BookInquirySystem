package com.company;

import com.company.Entiy.Book;
import com.company.Io.Object_io;
import com.company.Utils.NormalComparator;

import java.io.*;
import java.util.*;
public class Library{
	public Book[]books;   			//创建图书数组对象
	public int amount;     			//存入图书的种类数
	String f_Path;              //当前图书库源文件路径
	String f_Name;				//当前图书库源文件名称
	NormalComparator NC=new NormalComparator();
	Scanner re=new Scanner(System.in);
	Library() {
		books = new Book[1000];  //定义图书数组长度为1000
		this.amount=0;
	}
	//求长度
	public int length(){
		return this.amount;
	}
	//堆排序  ：表头不参与排序
	//将以low为根的子树调整成大顶堆，low、high是序列下界和上界
	public void sift(int low, int high,int w) {
		int i = low;                            //子树的根
		int j = 2 * i ;                         //j为i结点的左孩子
		Book temp = books[i];
		while (j<high) {  				    	//沿较小值孩子结点向下筛选
			if (j<high-1 && (NC.compare(books[j].attribute[w],books[j + 1].attribute[w]))<=0) { //有右孩子
				j++; 							//数组元素比较,j为左右孩子的较大者
			}
			if (NC.compare(temp.attribute[w],books[j].attribute[w]) <= 0) { //若父母结点值较小
				books[i] = books[j];            //孩子结点中的较大值上移
				i = j;
				j = 2 * i ;
			} else {
				j = high + 1;         			//退出循环
			}
		}
		books[i] = temp;                   		//当前子树的原根值调整后的位置
	}

	public void heapSort(int w) {               // w 为关键字属性对应编号
		int n = this.amount;                    //元素数
		Book temp;
		for (int i = n/2;i>0;i--) {             //创建堆
			sift(i, n, w);
		}
		for (int i=n-1; i>1;i--) {              //每趟将最大值交换到后面，再调整成堆
			temp = books[1];
			books[1] = books[i];
			books[i] = temp;
			sift(1, i, w);
		}
	}


	//添加图书
	public void addBook(Book a_book) {  //添加图书
		if(amount==books.length){
			Book[] B = books;
			books = new Book[amount+500];	//扩大可存储书目数量，每次扩容500
			for(int j=0;j<B.length;j++){
				books[j]=B[j];
			}
		}
		books[amount]=a_book;
		amount++;
	}



	//删除图书
	public void del_Book(int i){
		if(i>0&&i<this.amount){
			this.books[i].toString();
			this.books[i]=this.books[--this.amount];
		}
		else
			System.out.println("使用了非法索引！");
	}


	//修改图书
	public void alter_Book(int i,int w){       //修改books[i]图书的第w个属性值
		if(i>0&&i<this.amount){
			this.books[i].attribute[w]=re.next();
		}
		else
			System.out.println("使用了非法索引！");
	}
	//查找图书（完整关键字查找） ：表头不参与查找
	/*
	 *先排序  查找所在位置
	 *有序记录下的，折半查找：精确查找需要输入完整的属性值
	 */
	public int binarySearch(String key,int w){
		if(length()>0){
			int low=1,high=length()-1;
			while(low<=high){
				int n=(low+high)/2;
				if((NC.compare(books[n].attribute[w],key))==0)
					return n;
				else if((NC.compare(books[n].attribute[w],key))>0)   //关键字值小于中间值
					high=n-1;
				else
					low=n+1;
			}
		}
		return -1;         //查找不成功
	}



//查找图书（部分关键字查找）  ：表头不参与

	//顺序查找   ：返回位置
	public int seqSearch(String key,int w){
		int i=0,n=length();
		while((books[i].attribute[w].indexOf(key))==-1){
			i++;
		}
		if(i<n)  return i;
		else    return -1;
	}


	//顺序查找所有     ：返回一个Lirary类型
	public Library seqAllSearch(String key,int w){
		Library lib=new Library();
		lib.addBook(this.books[0]);      //将表头插入
		int i=0,n=length();
		while(i<n){
			if((books[i].attribute[w].indexOf(key))!=-1){
				lib.addBook(this.books[i]);
			}
			i++;
		}
		return lib;
	}


	//保存到源文件
	public void save_File(){
		try{
			Object_io io=new Object_io();
			io.object_io_out(this,new File(this.f_Path.concat(this.f_Name)));
		}
		catch(Exception e){
			System.out.print(e);
		}
	}
	//另存为新文件
	public void save_As_File(Library lib,String str){
		try{
			Object_io io=new Object_io();
			str=str.concat(".csv");
			str=lib.f_Path.concat(str);
			io.object_io_out(lib,new File(str));
		}
		catch(Exception e){
			System.out.print(e);
		}
	}

}
 
