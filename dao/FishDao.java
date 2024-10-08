package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.FishDto;

// fishdata table CRUD
public class FishDao {
	
	private String driverName = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:orcl";
	private String userName = "system";
	private String password = "11111111";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public FishDao(){
		init();
	}
	
	private void init() {	// 드라이버 로드
		try {
			Class.forName(driverName);
			System.out.println("오라클 드라이버 로드 성공");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private boolean conn() {	// 커넥션 가져오는 공통 코드를 메서드로 정의
		try {
			conn = DriverManager.getConnection(url, userName, password);
			System.out.println("커넥션 자원 획득 성공");
			return true;	// 커넥션 자원을 정상적으로 획득 할 시
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return false;	// 커넥션 자원을 획득하지 못한 경우
	}
	
	public void add(FishDto fdto){
		if(conn()) {
			try {
				String sql = "insert into fishdata values (?,?,default)";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, fdto.getId());
				pstmt.setString(2, fdto.getPwd());
				// 쿼리 실행
				int result = pstmt.executeUpdate();
				if(result == 0) {
					conn.rollback();
					System.out.println("결과에 의해 롤백 완료");
				}else {
					conn.commit();
					System.out.println("결과에 의해 커밋 완료");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(conn != null) {
					try {
						conn.close();	// 자원 반납
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		}else {
			System.out.println("데이터베이스 커넥션 실패");
		}
	}
	
	public void del(String delId) {
		if(conn()) {
			try {
				String sql = "delete from fishdata where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, delId);
				// 쿼리 실행
				int result = pstmt.executeUpdate();
				if(result == 0) {
					conn.rollback();
					System.out.println("결과에 의해 롤백 완료");
				}else {
					conn.commit();
					System.out.println("결과에 의해 커밋 완료");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(conn != null) {
					try {
						conn.close();	// 자원 반납
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		}else {
			System.out.println("데이터베이스 커넥션 실패");
		}
	}
	
	public void update(FishDto fdto) {
		if(conn()) {
			try {
				String sql = "update fishdata set pwd = ? where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, fdto.getPwd());
				pstmt.setString(2, fdto.getId());
				// 쿼리 실행
				int result = pstmt.executeUpdate();
				if(result == 0) {
					conn.rollback();
					System.out.println("결과에 의해 롤백 완료");
				}else {
					conn.commit();
					System.out.println("결과에 의해 커밋 완료");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(conn != null) {
					try {
						conn.close();	// 자원 반납
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		}else {
			System.out.println("데이터베이스 커넥션 실패");
		}
	}
	
	public ArrayList<FishDto> selectAll() {
		ArrayList<FishDto> flist = new ArrayList<FishDto>();
		if(conn()) {			
			try {
				String sql = "select * from fishdata order by id";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				// ResultSet은 테이블 형식으로 가져온다고 이해합니다.
				while(rs.next()) {	// next()메서드는 rs에서 참조하는 테이블에서
									// 튜플을 순차적으로 하나씩 접근하는 메서드
					FishDto fishTemp = new FishDto();
					fishTemp.setId(rs.getString("id"));
					fishTemp.setPwd(rs.getString("pwd"));
					fishTemp.setIndate(rs.getString("indate"));
					flist.add(fishTemp);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(conn != null) {
					try {
						conn.close();	// 자원 반납
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		}
		return flist;
	}
	
	public FishDto selectOne(String searchId){
		if(conn()) {
			try {
				String sql = "select * from fishdata where id = ?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, searchId);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					FishDto fishTemp = new FishDto();
					fishTemp.setId(rs.getString("id"));
					fishTemp.setPwd(rs.getString("pwd"));
					fishTemp.setIndate(rs.getString("indate"));
					return fishTemp;
				}
				// ResultSet은 테이블 형식으로 가져온다고 이해합니다.
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if(conn != null) {
					try {
						conn.close();	// 자원 반납
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			}
		}
		return null;
	}
}
