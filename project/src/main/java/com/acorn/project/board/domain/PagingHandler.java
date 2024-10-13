package com.acorn.project.board.domain;

import lombok.Data;

@Data
public class PagingHandler {

     int  currentPage;          //현재페이지
	 int  totRecords ;          // 총 레코드 수 	 
	 int  pageSize ;              //페이지 사이즈  (데이터 건수 , 한 페이지에 보이는 레코드 수 )	 	 
	 int  totalPage;             // 총 페이지수: 전체 페이수   
	  
	 int  grpSize=5 ;             // 한 그룹에 5개씩 보겟다
	 int  currentGrp ;  	      // 현재그룹
	 int  grpStartPage;           // 현재그룹의 시작번호
	 int  grpEndPage;             // 현재그룹의 마지막번호
	

	public PagingHandler(int currentPage, int totRecords, int pageSize) {
		super();
		this.currentPage = currentPage;
		this.totRecords = totRecords;
		this.pageSize = pageSize;
		
		calcPage(); 
	}
	
	private void calcPage(){
		 int reamin = totRecords  %  pageSize ;		 
		 if( reamin ==0 )
			 totalPage = totRecords / pageSize;			 
		 else 
			 totalPage = totRecords / pageSize +1;		 
		 // 현재그룹 구하기
		 // 현재그룹의 시작번호
		 // 현재그룹의 끝번호
		 int remain2 = currentPage % grpSize;    
		 
		 if( remain2 ==0 )
			 currentGrp  = currentPage  / grpSize ;    
		 else 
			 currentGrp = currentPage  / grpSize  +1;  
		 
			 
		 grpStartPage = ( currentGrp -1 ) * grpSize +1 ;  
		 grpEndPage = currentGrp * grpSize;               
		 
		 // 그룹의 마지막번호가 마지막페이지보다 크다면 
		 //그룹의 마지막번호가 마지막페이지로 변경해야됨
		 if( grpEndPage > totalPage){
			 grpEndPage = totalPage;           
		 }
	}
	 
}



