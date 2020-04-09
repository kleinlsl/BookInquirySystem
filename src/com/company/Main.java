package com.company;
/*
 *主菜单
 */

import com.company.Io.Object_io;
import com.company.Utils.Date_s;

import java.io.*;
import java.util.*;
public class Main{
	public static void main(String []args) throws Exception 	{
		Scanner re=new Scanner(System.in);
		System.out.println("\n\n-------------------------欢迎来到图书查询系统------------------------\n");
		System.out.println("                       "+ Date_s.h_Date()+"                          \n");
		System.out.println("   注意：在使用过程中，请保证系统正常退出；否则可能会出现数据丢失！  \n");
		System.out.println("\n   注意：本次为测试过程；请输入合适的测试数据量（1--6万）            \n");
		System.out.println("   \t\t谢谢配合！                                                    \n");
		System.out.println("                                            您可以进入系统了         \n\n");
		System.out.print("请输入测试所用数据量：");
		int max_data=re.nextInt();
		Library lib=new Library();
		Object_io io=new Object_io();
		io.DB_Limit=max_data;
		lib.f_Path= "Resources/Access/";              //当前图书库源文件路径
		lib.f_Name="2019春教材书目数据_(源).csv";   //当前图书库源文件名称
		File  file=new File(lib.f_Path.concat(lib.f_Name));
		io.object_io_in(lib,file);
		Menu m=new Menu(lib);
	}
}
