package com.company.Io;

import com.company.Entiy.Book;
import com.company.Library;

import java.io.*;
public class Object_io //对象流类
{
	public int DB_Limit=-1;      //测试所用数据量
	public void object_io_out(Library lib, File file) throws Exception   //向文件写入
	{
		FileWriter outOne = new FileWriter(file);
		BufferedWriter outTwo=new BufferedWriter(outOne);
		for(int i=0;i<lib.amount;i++){
			String[] s=lib.books[i].attribute;
			int j=0;
			String str=s[j++];
			for(;j<s.length;j++){
				str=str.concat(",");
				str=str.concat(s[j]);    //将指定字符串连接到此字符串的结尾。
			}
			outTwo.write(str);           //写入一行
			outTwo.newLine();            //写入换行
		}
		outTwo.close();
		outOne.close();
	}
	public void object_io_in(Library lib, File file) throws Exception   //从文件读出
	{
		FileReader inOne = new FileReader(file);
		BufferedReader inTwo=new BufferedReader(inOne);
		String strLine = null;
		int x=0;                                        		//读出的数据条数
		while((strLine = inTwo.readLine())!=null)        		//逐行读取
		{
			String item[]=strLine.split(",",14);         		//分离每行数据,根据给定正则表达式的匹配拆分此字符串。
			if(x==0)   Book.amount=item.length;                  //将表头属性数量存入
			if(item.length==14){
				Book book=new Book(item);
				lib.addBook(book);
				x++;
			}
			if(this.DB_Limit!=-1&&x>this.DB_Limit)  			//判断是否存在数据上限要求
				break;
		}
		System.out.println("读取到的数据为"+x+"条;（包括表头）");
		inOne.close();
		inTwo.close();
	}
}