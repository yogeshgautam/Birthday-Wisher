package com.yogeshbirthdaywisher.birthdaywisher.date_conversion;

import java.text.SimpleDateFormat;
//import java.time.*;
import java.util.Date;


/**
 * Created by yogesh on 5/17/2017.
 */


public class Conversion
{   int ease;
	int arr_totals;
	int y1,m1,d1,y2,m2,d2;
	int j1,j2;
	int days;
	private int monthDays[] = {31, 28, 31, 30, 31, 30,31, 31, 30, 31, 30, 31};
	int date[]= {2072 , 1 , 1};
	int[] arr_total=new int[19];

	int arr[][]={
			{31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30},  //2072
			{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},   //2073
			{31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30},
			{  31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
			{ 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30},
			{ 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31},
			{ 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30},
			{ 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
			{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30},
			{31, 31, 32, 32, 31, 30, 30, 30, 29, 30, 30, 30},
			{ 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30},
			{31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30},
			{ 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30},
			{ 31, 32, 31, 32, 30, 31, 30, 30, 29, 30, 30, 30},
			{ 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30},
			{ 31, 31, 32, 31, 31, 31, 30, 30, 29, 30, 30, 30},
			{ 30, 31, 32, 32, 30, 31, 30, 30, 29, 30, 30, 30},
			{ 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30},
			{ 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30},

	};

	public int getdifference()
	{


		return (arr_totals-ease);
	}


	public void futuredate(int y,int m,int d)
	{
		arr_totals=0;


		if ((y-2072)>=0)
		{
			int year=y-2072;

			for (int i=0;i<year;i++)
			{

				for (int j=0;j<=11;j++)
				{
					arr_totals+=arr[i][j];
				}
			}
		}


		if (m>=1)
		{
			int month=m-1;
			for (int j=0;j<month;j++)
			{
				arr_totals+=arr[y-2072][j];
			}
		}

		if (d>=1)
		{
			arr_totals+=(d-1);
		}


	}

	public void setCurrentDate()
	{


		Date dNow = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy");
		int y = new Integer(ft.format(dNow));
		ft = new SimpleDateFormat("MM");
		int m = new Integer(ft.format(dNow));
		ft = new SimpleDateFormat("dd");
		int d = new Integer(ft.format(dNow));
		setDate(y,m,d);

	}
	public void  setDate(int y2,int m2,int d2)
	{
		this.y2=y2;
		this.m2=m2;
		this.d2=d2;
		int a=countday(2015,4,14);
		int b=countday(y2,m2,d2);


		for (int i=0;i<=18;i++)
		{
			arr_total[i]=0;
			for (int j=0;j<=11;j++)
			{
				arr_total[i]+= arr[i][j];
			}
		}

		int k=0;
		int z=0;

		int  diffInDays =b-a;
		ease=diffInDays;   // for getting difference in date between curernt date and 2072/1/1


		while (diffInDays>=0)
		{
			if (diffInDays>=arr_total[k])
			{	diffInDays=diffInDays-arr_total[k];
				k++;
				date[0]+=1;
			}
			else if ((diffInDays<arr_total[k])  && (diffInDays>=arr[k][z]))
			{
				date[1]+=1;
				diffInDays-=arr[k][z];
				z++;


			}

			else
			{
				date[2] += (int)diffInDays;
				break;
			}
		}


	}

	public int countday(int y,int m,int d)
	{  int i;
		int k=y*365+d;



		for (i=0;i<m-1;i++)
			k=k+monthDays[i];

		k=k+countleap(y,m);
		return k;
	}

	public int countleap(int y,int m)
	{
		if (m<=2)
			y--;

		return (y/4-y/100+y/400);
	}


	public int  getDay()
	{
		return (date[2]);
	}

	public int getMonth()
	{
		return (date[1]);
	}

	public int getYear()
	{
		return (date[0]);
	}

	public String toString()
	{
		return toString("-");
	}

	public String toString(String mid)
	{
		return new String(""+date[0] + mid + date[1] + mid + date[2]);
	}



}

