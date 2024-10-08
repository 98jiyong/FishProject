package main;

import service.FishService;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Fish World");
		FishService fs = new FishService();
		fs.menu();
		
		
		// DAO 단위 테스트 : DAO add 기능 테스트, 결과 : 성공
		/*
		FishDao f = new FishDao();
		FishDto d = new FishDto();
		d.setId("aa");
		d.setPwd("ppp");
		f.add(d);
		*/
	}

}
