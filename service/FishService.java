package service;

import java.util.ArrayList;
import java.util.Scanner;

import dao.FishDao;
import dto.FishDto;

public class FishService {
	
	Scanner sc = new Scanner(System.in);
	// Fishdata 테이블에 데이터를 입력하기 위해서는 FishDao 객체에 의존한다.
	FishDao fishdao = FishDao.getInstance();
	public void menu() {
		while(true) {
			System.out.println("1. 등록 / 2. 수정 / 3. 삭제 / 4. 전체보기 / 5. 자세히보기 / 6. 종료");
			int selNum = sc.nextInt();	sc.nextLine();
			if(selNum == 1) {
				add();
			}else if(selNum == 2) {
				update();
			}else if(selNum == 3) {
				del();
			}else if(selNum == 4) {
				list();
			}else if(selNum == 5) {
				search();
			}else if(selNum == 6) {
				break;
			}else {
				System.out.println("다시 입력하세요.");
			}
		}
		
	}
	private void add() {
		System.out.println("신규 Fish 등록");
		System.out.println("아이디를 입력하세요");
		String id = sc.nextLine();
		System.out.println("비밀번호를 입력하세요");
		String pwd = sc.nextLine();
		// DTO 에 저장
		FishDto fishdto = new FishDto();
		fishdto.setId(id);
		fishdto.setPwd(pwd);
		// DAO의 add메서드 호출하여 데이터베이스에 insert
		fishdao.add(fishdto);
	}
	
	private void update() {
		// 시나리오 ..  아이디를 입력받아서 해당 정보를 가져온다.
		System.out.println("검색할 아이디를 입력하세요");
		String searchId = sc.nextLine();
		FishDto f = fishdao.selectOne(searchId);
		if(f != null) {
			System.out.println("수정할 정보는 다음과 같습니다.");
			System.out.println(f.toString());
		}
		System.out.println("변경할 비밀번호를 입력하세요");
		String udtPass = sc.nextLine();
		f.setPwd(udtPass);
		fishdao.update(f);
	}
	private void del() {
		System.out.println("삭제할 물고기의 아이디를 입력하세요");
		String delId = sc.nextLine();
		fishdao.del(delId);
	}
	private void list(){
		ArrayList<FishDto> f = fishdao.selectAll();
		// DB에 저장된 정보를 모두 출력..
		System.out.println(f.size() + " 마리의 물고기가 있습니다.");
		for(FishDto tempf : f) {
			System.out.println(tempf.toString());
		}
	}
	private void search() {
		System.out.println("검색할 아이디를 입력해주세요");
		String searchId = sc.nextLine();
		FishDto f = fishdao.selectOne(searchId);
		System.out.println(f.toString());
	}
}
